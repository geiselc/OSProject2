/**
 * @author Conrad Geisel
 */
package pack0;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

public class Main extends JFrame{
	
	/** Constants **/
	private final int LOGICAL = 64;		// KB = 64 Pages in Logical Mem Space
	private final int PHYSICAL = 16; 	// KB = 16 Frames in Physical Mem Space
	private final int PAGE_FRAME = 1;	// KB. 
	
	/** Structures **/
	private ArrayList<Reference> inFile;	// Main input data file 
	private ArrayList<String> processList;	// List of processes that will have page tables
	private ArrayList<String> history;		// For tracking LRU 
	private ArrayList<Integer> freeFrames;	// For tracking frames that are available for allocation
	private String[] frames;				// Main Physical memory data structure	
	
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

	private boolean isFreeFrame;			// indicates frame is free
	private boolean isFault;				// indicates a page fault
	private String victim;					// The page to be replaced by LRU
	
	/** File reading **/
	private BufferedReader br;			
	private int remainingLines;				// number of lines in the input file
	
	/** GUI **/
	GUI gui;								// Main gui
	DecimalFormat df = new DecimalFormat("#.##");	// for printing fault rates
	
	public static void main(String[] args) {
		new Main(args[0]);
	}
	
	public Main(String file){
		
		/** Start the GUI **/
		this.gui = new GUI();
		this.gui.run(gui);

		/** Instantiate DataStructures **/
		this.inFile = new ArrayList<Reference>();
		this.processList = new ArrayList<String>();
		this.frames = new String[PHYSICAL];
		this.history = new ArrayList<String>();
		this.freeFrames = new ArrayList<Integer>();
		
		/** Get Data from input file **/
		try {
			br = new BufferedReader(new FileReader(file));
			BufferedReader br2 = new BufferedReader(new FileReader(file));
			remainingLines = getNumLines(br2);
		} catch (FileNotFoundException e1) {
			System.out.println("Error reading file");
			e1.printStackTrace();
			System.exit(1);
		}
		
		// Add 16 free frames to freeFrame list 
		for(int i = 0; i < (PHYSICAL); i++)
			freeFrames.add(i);
		
		// if step button clicked, process one line of input file 
		this.gui.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readNextLine(br);
				remainingLines--;
			}
		});
		
		// if run to completion clicked, keep reading until at end of file
		this.gui.button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while(remainingLines >= 1){
					readNextLine(br);
					remainingLines--;
				}
			}
		});		
	}
	
	/**
	 * Reads one line of the text input file. 
	 * Processes a page of a given process  
	 */
	public void readNextLine(BufferedReader br){
		String line;
		String[] parts;
		Reference ref;
		this.gui.Empty4.setText(""); // clear 'Already in Mem' Label if need be
		try {
			// Get next line, split process id and page number
			line = br.readLine();
			parts = line.split(":\t");
			
			// Create new referenced process id and page number 
			ref = new Reference(parts[0], parts[1]);
			
			// If a page is outside the allocated VM
			if(Integer.parseInt(parts[1], 2) >= LOGICAL){
				// A Seg fault has happened, exit with error 
				System.out.println("ERROR: SEG FAULT!");
				System.exit(1);
			}

			/** If referenced page is already in history, 
			 *  Remove its entry and append to end of list as most
			 *  recently referenced page. Otherwise just add it. 
			 * **/
			if(history.contains(ref.toString())){
				history.remove(ref.toString());
				history.add(ref.toString());
			} else {
				history.add(ref.toString());
			}
			
			// Label currently referenced process and page
			this.gui.CurrentProc.setText(ref.getPid());
			this.gui.CurrentPage.setText(ref.getPageNumber());
			
			// Enter the main process function. 
			processReference(ref);
			
			// Clean up history list
			trimHistory(history);
		} catch (IOException e) {
			System.out.println("Error parsing input file");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/** Basic helper function for executing a 'Run-to-Completion' call
	 *  Loops through the file, and returns the total number of lines
	 *  to process. 
	 */
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

	/**
	 * The main processing loop. It is passed a reference taken from
	 * the input file. It's data is entered into a PageTableEntry,
	 * and that entry is added to the PageTable of the Process ID. 
	 */
	private void processReference(Reference r){
		System.out.println("New Line"); // indicates a new reference on the console
		isFault = false; // assume no fault will occur unless specified 
		pt = new PageTableEntry(); // page table entry to be populated for this reference
		System.out.println(r.toString()); // show current reference being worked on in console
		
		// Reset Victim Labels if need be
		this.gui.VictimPage.setText("");
		this.gui.VictimProcess.setText("");
		
		// Get and handle the current Process ID 
		switch(r.getPid()){
			case "P1":
				// If this process id has been processed before
				if(processList.contains(r.getPid())){
					P1.setCurrRef(r); // update the current reference 
					P1.refCount++; 
					pt = processEntry(P1); // update page table entry to this processes data
					// If the process already exists on the frame table (in physical mem)
					if(TableOne.containsKey(r.binToInt(r.getPageNumber()))){
						// indicate to console and GUI
						System.out.println("Already in Mem");
						this.gui.Empty4.setText("Already in memory");
						TableOne.put(r.binToInt(r.getPageNumber()), pt); // update page table entry
					} else
						TableOne.put(r.binToInt(r.getPageNumber()), pt); // add page table entry to table 
				} else { // Process does not exist yet 
					isFault = true; // Thus, a fault 
					processList.add(r.getPid()); // add it to list of processes so we don't fault next time
					P1 = new Process(r.getPidFromString(r.getPid())); // create new process with process ID
					P1.refCount++;
					P1.faultCount++;
					TableOne = new HashMap<Integer, PageTableEntry>(); // Create PCB for this process
					P1.setPageTable(TableOne);  // Set PCB to this process
					P1.setCurrRef(r); // update to current ref (current line of input file) 
					TableOne.put(r.binToInt(r.getPageNumber()), processEntry(P1)); // add page table entry to page table 
				}
				System.out.println(P1.getPid() + "\t" +TableOne.toString()); // Display current page table to console 
				if(TableOne.get(r.binToInt(r.getPageNumber())).getFrameNum() == -1) // If page was replaced on frame table
					this.gui.PT1.setValueAt(null, TableOne.get(r.binToInt(r.getPageNumber())).getPageNum(),  1); // indicate on page table 
				else { // Otherwise, update page table with current frame
					this.gui.PT1.setValueAt(TableOne.get(r.binToInt(r.getPageNumber())).getFrameNum(), TableOne.get(r.binToInt(r.getPageNumber())).getPageNum(),  1);
				}
				
				// update remaining data to GUI
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
			default: // Should never be hit, just here as a catch-all in case of erroneous input 
				System.out.println("Error processing reference: " +r.toString());
				System.exit(1); 
		}
		
		isFault = false; // reset fault flag once everything has been processed 
		
		// Draw the current state of the frame table on the GUI
		for(int i = 0; i < frames.length; i++){
			this.gui.frameTable.setValueAt(frames[i], i, 1);
		}
	}
	
	/**
	 * Populates an entry of the page table for a given process
	 */
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
	
	/**
	 * Returns true if the page table has an entry on the frame table 
	 */
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
	
	/**
	 * Returns the frame number the page table has an entry on
	 */
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
	
	/**
	 * The LRU algorithm. It sets the victim to be replaced as the first
	 * entry in the history list (the least recently referenced page). It
	 * sets that page's frame id to null, and indicates that the frame is now
	 * available. 
	 */
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
	
	/** 
	 * Updates page table data for a process that is being replaced by LRU
	 */
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
	
	/**
	 * Removes entries on history list that do not exist on the frame table.
	 * Prevents pages from indicating they are candidates to be replaced if they
	 * do not have an entry on the frame table to begin with. 
	 */
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