package pack0;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	/** Constants **/
	private final int LOGICAL = 64;		// KB = 64 Pages in Logical Mem Space
	private final int PHYSICAL = 16; 	// KB = 16 Frames in Physical Mem Space
	private final int PAGE_FRAME = 1;	// KB. 
	
	/** Structures **/
	private ArrayList<Reference> inFile;
	private ArrayList<String> processList;
	private ArrayList<String> history;
	private ArrayList<Integer> freeFrames;
	private String[] frames;
	
	/** Page Tables **/
	private HashMap<Integer, PageTableEntry> TableOne;
	private HashMap<Integer, PageTableEntry> TableTwo;
	private HashMap<Integer, PageTableEntry> TableThree;
	private HashMap<Integer, PageTableEntry> TableFour;
	private HashMap<Integer, PageTableEntry> TableFive;
	private PageTableEntry pt;
	
	/** Processes **/
	private Process P1;
	private Process P2;
	private Process P3;
	private Process P4;
	private Process P5;
	
	/** Variables **/
	private int faultCount = 0;
	private int refCount = 0;
	private boolean isFreeFrame;
	private boolean isFault;
	private String victim;
	
	/** File reading **/
	private BufferedReader br;
	private int remainingLines;
	
	/** GUI **/
	GUI gui;
	DecimalFormat df = new DecimalFormat("#.##");
	
	public static void main(String[] args) {
		new Main(args[0]);
	}
	
	public Main(String file){
		
		/** Start the GUI **/
		this.gui = new GUI();
		this.gui.run(gui);

		this.inFile = new ArrayList<Reference>();
		this.processList = new ArrayList<String>();
		this.frames = new String[PHYSICAL];
		this.history = new ArrayList<String>();
		this.freeFrames = new ArrayList<Integer>();
		
		try {
			br = new BufferedReader(new FileReader(file));
			BufferedReader br2 = new BufferedReader(new FileReader(file));
			remainingLines = getNumLines(br2);
		} catch (FileNotFoundException e1) {
			System.out.println("Error reading file");
			e1.printStackTrace();
			System.exit(1);
		}
		
		for(int i = 0; i < (PHYSICAL); i++)
			freeFrames.add(i);
		
		this.gui.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readNextLine(br);
				remainingLines--;
			}
		});
		
		this.gui.button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while(remainingLines >= 1){
					readNextLine(br);
					remainingLines--;
				}
			}
		});		
	}
	
	public void readNextLine(BufferedReader br){
		String line;
		String[] parts;
		Reference ref;
		this.gui.Empty4.setText("");
		try {
			line = br.readLine();
			parts = line.split(":\t");
			ref = new Reference(parts[0], parts[1]);
			
			if(Integer.parseInt(parts[1], 2) >= LOGICAL){
				System.out.println("ERROR: Not enough memory!");
				System.exit(1);
			}

			//inFile.add(ref);
			if(history.contains(ref.toString())){
				history.remove(ref.toString());
				history.add(ref.toString());
			} else {
				history.add(ref.toString());
			}
			this.gui.CurrentProc.setText(ref.getPid());
			this.gui.CurrentPage.setText(ref.getPageNumber());
			processReference(ref);
			trimHistory(history);
		} catch (IOException e) {
			System.out.println("Error parsing input file");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private int getNumLines(BufferedReader br2){
		String line;
		int result=0;
		try {
			while((line = br2.readLine()) != null)
				result++;
			br2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private void processReference(Reference r){
		System.out.println("New Line");
		isFault = false;
		pt = new PageTableEntry();
		System.out.println(r.toString());
		
		// Reset Victim Labels if need be
		this.gui.VictimPage.setText("");
		this.gui.VictimProcess.setText("");
		
		// Get and handle the current Process ID 
		switch(r.getPid()){
			case "P1":
				if(processList.contains(r.getPid())){
					P1.setCurrRef(r);
					P1.refCount++;
					pt = processEntry(P1);
					if(TableOne.containsKey(r.binToInt(r.getPageNumber()))){
						System.out.println("Already in Mem");
						this.gui.Empty4.setText("Already in memory");
						TableOne.put(r.binToInt(r.getPageNumber()), pt);
					} else
						TableOne.put(r.binToInt(r.getPageNumber()), pt);
				} else {
					isFault = true;
					processList.add(r.getPid());
					P1 = new Process(r.getPidFromString(r.getPid()));
					P1.refCount++;
					P1.faultCount++;
					TableOne = new HashMap<Integer, PageTableEntry>();
					P1.setPageTable(TableOne);
					P1.setCurrRef(r);
					TableOne.put(r.binToInt(r.getPageNumber()), processEntry(P1));
				}
				System.out.println(P1.getPid() + "\t" +TableOne.toString());
				if(TableOne.get(r.binToInt(r.getPageNumber())).getFrameNum() == -1)
					this.gui.PT1.setValueAt(null, TableOne.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				else {
					this.gui.PT1.setValueAt(TableOne.get(r.binToInt(r.getPageNumber())).getFrameNum(), TableOne.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				}
				this.gui.PT1.setValueAt(TableOne.get(r.binToInt(r.getPageNumber())).isValid(), TableOne.get(r.binToInt(r.getPageNumber())).getPageNum(),  2);
				this.gui.PT1.setValueAt(TableOne.get(r.binToInt(r.getPageNumber())).isResident(), TableOne.get(r.binToInt(r.getPageNumber())).getPageNum(),  3);
				this.gui.P1FR.setText(""+(df.format((double) P1.faultCount / P1.refCount * 100) + "%")); 
				break;
			case "P2":
				if(processList.contains(r.getPid())){
					P2.setCurrRef(r);
					P2.refCount++;
					pt = processEntry(P2);
					if(TableTwo.containsKey(r.binToInt(r.getPageNumber()))) {
						System.out.println("Already in Mem");
						this.gui.Empty4.setText("Already in memory");
						TableTwo.put(r.binToInt(r.getPageNumber()), pt);
					} else
						TableTwo.put(r.binToInt(r.getPageNumber()), pt);
				} else {
					isFault = true;
					processList.add(r.getPid());
					P2 = new Process(r.getPidFromString(r.getPid()));
					P2.refCount++;
					P2.faultCount++;
					TableTwo = new HashMap<Integer, PageTableEntry>();
					P2.setPageTable(TableTwo);
					P2.setCurrRef(r);
					TableTwo.put(r.binToInt(r.getPageNumber()), processEntry(P2));
				}
				if(TableTwo.get(r.binToInt(r.getPageNumber())).getFrameNum() == -1)
					this.gui.PT2.setValueAt(null, TableTwo.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				else 
					this.gui.PT2.setValueAt(TableTwo.get(r.binToInt(r.getPageNumber())).getFrameNum(), TableTwo.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				this.gui.PT2.setValueAt(TableTwo.get(r.binToInt(r.getPageNumber())).isValid(), TableTwo.get(r.binToInt(r.getPageNumber())).getPageNum(),  2);
				this.gui.PT2.setValueAt(TableTwo.get(r.binToInt(r.getPageNumber())).isResident(), TableTwo.get(r.binToInt(r.getPageNumber())).getPageNum(),  3);
				this.gui.P2FR.setText(""+(df.format((double) P2.faultCount / P2.refCount * 100) + "%")); 
				break;
			case "P3":
				if(processList.contains(r.getPid())){
					P3.setCurrRef(r);
					P3.refCount++;
					pt = processEntry(P3);
					if(TableThree.containsKey(r.binToInt(r.getPageNumber()))) {
						System.out.println("Already in Mem");
						this.gui.Empty4.setText("Already in memory");
						TableThree.put(r.binToInt(r.getPageNumber()), pt);
					} else
						TableThree.put(r.binToInt(r.getPageNumber()), pt);
				} else {
					isFault = true;
					processList.add(r.getPid());
					P3 = new Process(r.getPidFromString(r.getPid()));
					P3.refCount++;
					P3.faultCount++;
					TableThree = new HashMap<Integer, PageTableEntry>();
					P3.setPageTable(TableThree);
					P3.setCurrRef(r);
					TableThree.put(r.binToInt(r.getPageNumber()), processEntry(P3));
				}
				if(TableThree.get(r.binToInt(r.getPageNumber())).getFrameNum() == -1)
					this.gui.PT3.setValueAt(null, TableThree.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				else
					this.gui.PT3.setValueAt(TableThree.get(r.binToInt(r.getPageNumber())).getFrameNum(), TableThree.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				this.gui.PT3.setValueAt(TableThree.get(r.binToInt(r.getPageNumber())).isValid(), TableThree.get(r.binToInt(r.getPageNumber())).getPageNum(),  2);
				this.gui.PT3.setValueAt(TableThree.get(r.binToInt(r.getPageNumber())).isResident(), TableThree.get(r.binToInt(r.getPageNumber())).getPageNum(),  3);
				this.gui.P3FR.setText(""+(df.format((double) P3.faultCount / P3.refCount * 100) + "%")); 
				break;
			case "P4":
				if(processList.contains(r.getPid())){
					P4.setCurrRef(r);
					P4.refCount++;
					pt = processEntry(P4);
					if(TableFour.containsKey(r.binToInt(r.getPageNumber()))) {
						System.out.println("Already in Mem");
						this.gui.Empty4.setText("Already in memory");
						TableFour.put(r.binToInt(r.getPageNumber()), pt);
					} else
						TableFour.put(r.binToInt(r.getPageNumber()), pt);
				} else {
					isFault = true;
					processList.add(r.getPid());
					P4 = new Process(r.getPidFromString(r.getPid()));
					P4.refCount++;
					P4.faultCount++;
					TableFour = new HashMap<Integer, PageTableEntry>();
					P4.setPageTable(TableFour);
					P4.setCurrRef(r);
					TableFour.put(r.binToInt(r.getPageNumber()), processEntry(P4));
				}
				if(TableFour.get(r.binToInt(r.getPageNumber())).getFrameNum() == -1)
					this.gui.PT4.setValueAt(null, TableFour.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				else
					this.gui.PT4.setValueAt(TableFour.get(r.binToInt(r.getPageNumber())).getFrameNum(), TableFour.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				this.gui.PT4.setValueAt(TableFour.get(r.binToInt(r.getPageNumber())).isValid(), TableFour.get(r.binToInt(r.getPageNumber())).getPageNum(),  2);
				this.gui.PT4.setValueAt(TableFour.get(r.binToInt(r.getPageNumber())).isResident(), TableFour.get(r.binToInt(r.getPageNumber())).getPageNum(),  3);
				this.gui.P4FR.setText(""+(df.format((double) P4.faultCount / P4.refCount * 100) + "%")); 
				break;
			case "P5":
				if(processList.contains(r.getPid())){
					P5.setCurrRef(r);
					P5.refCount++;
					pt = processEntry(P5);
					if(TableFive.containsKey(r.binToInt(r.getPageNumber()))) {
						System.out.println("Already in Mem");
						this.gui.Empty4.setText("Already in memory");
						TableFive.put(r.binToInt(r.getPageNumber()), pt);
					} else {
						TableFive.put(r.binToInt(r.getPageNumber()), pt);
					}
				} else {
					isFault = true;
					processList.add(r.getPid());
					P5 = new Process(r.getPidFromString(r.getPid()));
					P5.refCount++;
					P5.faultCount++;
					TableFive = new HashMap<Integer, PageTableEntry>();
					P5.setPageTable(TableFive);
					P5.setCurrRef(r);
					TableFive.put(r.binToInt(r.getPageNumber()), processEntry(P5));
				}
				if(TableFive.get(r.binToInt(r.getPageNumber())).getFrameNum() == -1)
					this.gui.PT5.setValueAt(null, TableFive.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				else
					this.gui.PT5.setValueAt(TableFive.get(r.binToInt(r.getPageNumber())).getFrameNum(), TableFive.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				this.gui.PT5.setValueAt(TableFive.get(r.binToInt(r.getPageNumber())).isValid(), TableFive.get(r.binToInt(r.getPageNumber())).getPageNum(),  2);
				this.gui.PT5.setValueAt(TableFive.get(r.binToInt(r.getPageNumber())).isResident(), TableFive.get(r.binToInt(r.getPageNumber())).getPageNum(),  3);
				this.gui.P5FR.setText(""+(df.format((double) P5.faultCount / P5.refCount * 100) + "%")); 
				
				break;
			default:
				System.out.println("Error processing reference: " +r.toString());
				System.exit(1);
		}
		
		isFault = false;
		
		// Draw the current state of the frame table on the GUI
		for(int i = 0; i < frames.length; i++){
			this.gui.frameTable.setValueAt(frames[i], i, 1);
		}
	}
	
	private PageTableEntry processEntry(Process p){
		PageTableEntry pte = new PageTableEntry();
		String temp = p.getCurrRef().getPageNumber();
		pte.setPageNum(Integer.parseInt(temp, 2));
		int f;
		if(!isFrameSet(p)){
			if(!freeFrames.isEmpty()){
				//if frame available add to frame
				f = freeFrames.get(0);
				frames[f] = p.toString();
				freeFrames.remove(0);
			} else {
				//no frame available, need to replace 
				isFault = true;
				p.faultCount++;
				System.out.println("No free frame found");
				pageReplacement();
				f = freeFrames.get(0);
				frames[f] = p.toString();
				freeFrames.remove(0);
			}
			
			pte.setFrameNum(f);
		} else
			pte.setFrameNum(getTheFrame(p));
		
		pte.setValid(true);
		pte.setResident(true);
		
		return pte;
	}
	
	private boolean isFrameSet(Process p){
		boolean result = false;
		for(int i = 0; i < frames.length; i++){
			if(frames[i] != null && frames[i].equals(p.getCurrRef().toString())){
				result = true;
				break;
			}
		}
		return result;
	}
	
	private int getTheFrame(Process p){
		int result=0;
		for(int i = 0; i < frames.length; i++){
			if(frames[i] != null && frames[i].equals(p.getCurrRef().toString())){
				result = i;
				break;
			}
		}
		return result;
	}
	
	private void pageReplacement(){
		victim = history.get(0);
		this.gui.VictimProcess.setText(victim.substring(0, 2));
		this.gui.VictimPage.setText(victim.substring(3));
		for(int i = 0; i < frames.length; i++){
			if(frames[i] != null && frames[i].equals(victim)){
				frames[i] = null;
				freeFrames.add(i);
				break;
			} 
		}
		
		setVictimStats(victim);
		
		history.remove(0);
	}
	
	private void setVictimStats(String v){
		String p = v.substring(0, 2);
		String pNum = v.substring(4);
		
		switch(p){
			case "P1":
				TableOne.get(Integer.parseInt(pNum, 2)).setFrameNum(-1);
				TableOne.get(Integer.parseInt(pNum, 2)).setResident(false);
				this.gui.PT1.setValueAt(null, TableOne.get(Integer.parseInt(pNum, 2)).getPageNum(),  1);
				this.gui.PT1.setValueAt(false, TableOne.get(Integer.parseInt(pNum, 2)).getPageNum(), 3);
				break;
			case "P2":
				TableTwo.get(Integer.parseInt(pNum, 2)).setFrameNum(-1);
				TableTwo.get(Integer.parseInt(pNum, 2)).setResident(false);
				this.gui.PT2.setValueAt(null, TableTwo.get(Integer.parseInt(pNum, 2)).getPageNum(),  1);
				this.gui.PT2.setValueAt(false, TableTwo.get(Integer.parseInt(pNum, 2)).getPageNum(), 3);
				break;
			case "P3":
				TableThree.get(Integer.parseInt(pNum, 2)).setFrameNum(-1);
				TableThree.get(Integer.parseInt(pNum, 2)).setResident(false);
				this.gui.PT3.setValueAt(null, TableThree.get(Integer.parseInt(pNum, 2)).getPageNum(),  1);
				this.gui.PT3.setValueAt(false, TableThree.get(Integer.parseInt(pNum, 2)).getPageNum(), 3);
				break;
			case "P4":
				TableFour.get(Integer.parseInt(pNum, 2)).setFrameNum(-1);
				TableFour.get(Integer.parseInt(pNum, 2)).setResident(false);
				this.gui.PT4.setValueAt(null, TableFour.get(Integer.parseInt(pNum, 2)).getPageNum(),  1);
				this.gui.PT4.setValueAt(false, TableFour.get(Integer.parseInt(pNum, 2)).getPageNum(), 3);
				break;
			case "P5":
				TableFive.get(Integer.parseInt(pNum, 2)).setFrameNum(-1);
				TableFive.get(Integer.parseInt(pNum, 2)).setResident(false);
				this.gui.PT5.setValueAt(null, TableFive.get(Integer.parseInt(pNum, 2)).getPageNum(),  1);
				this.gui.PT5.setValueAt(false, TableFive.get(Integer.parseInt(pNum, 2)).getPageNum(), 3);
				break;
		}
	}
	
	private void trimHistory(ArrayList<String> h) {
		boolean found = false;
		for(int i = 0; i < h.size(); i++){
			for(int j = 0; j < frames.length; j++){
				if(frames[j] == null)
					break;
				if(h.get(i).equals(frames[j])){
					found = true;
					break;
				}
			}

			if(!found)
				h.remove(i);
		}
	}
}