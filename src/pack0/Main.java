package pack0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.*;


public class Main {
	
	/** Constants **/
	private final int LOGICAL = 64;		// KB = 64 Pages in Logical Mem Space
	private final int PHYSICAL = 16; 	// KB = 16 Frames in Physical Mem Space
	private final int PAGE_FRAME = 1;	// KB. 
	
	/** Structures **/
	private ArrayList<Reference> inFile;
	private ArrayList<String> processList;
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
	private boolean isFreeFrame = true;
	
	public static void main(String[] args) {
		new Main(args[0]);
	}
	
	public Main(String file){
		this.inFile = new ArrayList<Reference>();
		this.processList = new ArrayList<String>();
		this.frames = new String[PHYSICAL];
		
		try{
			String line;
			String[] parts;
			Reference ref;
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null){
				parts = line.split(":\t");
				ref = new Reference(parts[0], parts[1]);
				if(Integer.parseInt(parts[1], 2) >= LOGICAL){
					System.out.println("ERROR: Not enough memory!");
					System.exit(1);
				}
				inFile.add(ref);
			}
			
			br.close();
			for(int i = 0; i < inFile.size(); i++){
				processReference(inFile.get(i));
				refCount++;
			}
		} catch(IOException e){
			System.out.println("Error parsing input file");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void processReference(Reference r){
		boolean fault = true;
		pt = new PageTableEntry();
		System.out.println(r.toString());
		
		switch(r.getPid()){
			case "P1":
				if(processList.contains(r.getPid())){
					P1.setCurrRef(r);
					pt = processEntry(P1);
					if(TableOne.containsKey(r.binToInt(r.getPageNumber())))
						System.out.println("Duplicate");
					else
						TableOne.put(r.binToInt(r.getPageNumber()), processEntry(P1));
						//System.out.println(P1.getPid() + "\t" +TableOne.toString());
				} else {
					processList.add(r.getPid());
					P1 = new Process(r.getPidFromString(r.getPid()));
					TableOne = new HashMap<Integer, PageTableEntry>();
					P1.setPageTable(TableOne);
					P1.setCurrRef(r);
					TableOne.put(r.binToInt(r.getPageNumber()), processEntry(P1));
					//System.out.println(P1.getPid() + "\t" +TableOne.toString());
				}
				break;
			case "P2":
				if(processList.contains(r.getPid())){
					P2.setCurrRef(r);
					pt = processEntry(P2);
					if(TableTwo.containsKey(r.binToInt(r.getPageNumber())))
						System.out.println("Duplicate");
					else
						TableTwo.put(r.binToInt(r.getPageNumber()), processEntry(P2));
				} else {
					processList.add(r.getPid());
					P2 = new Process(r.getPidFromString(r.getPid()));
					TableTwo = new HashMap<Integer, PageTableEntry>();
					P2.setPageTable(TableTwo);
					P2.setCurrRef(r);
					TableTwo.put(r.binToInt(r.getPageNumber()), processEntry(P2));
					//System.out.println(P1.getPid() + "\t" +TableOne.toString());
				}
				break;
			case "P3":
				if(processList.contains(r.getPid())){
					P3.setCurrRef(r);
					pt = processEntry(P3);
					if(TableThree.containsKey(r.binToInt(r.getPageNumber())))
						System.out.println("Duplicate");
					else
						TableThree.put(r.binToInt(r.getPageNumber()), processEntry(P3));
				} else {
					processList.add(r.getPid());
					P3 = new Process(r.getPidFromString(r.getPid()));
					TableThree = new HashMap<Integer, PageTableEntry>();
					P3.setPageTable(TableThree);
					P3.setCurrRef(r);
					TableThree.put(r.binToInt(r.getPageNumber()), processEntry(P3));
					//System.out.println(P1.getPid() + "\t" +TableOne.toString());
				}
				break;
			case "P4":
				if(processList.contains(r.getPid())){
					P4.setCurrRef(r);
					pt = processEntry(P4);
					if(TableFour.containsKey(r.binToInt(r.getPageNumber())))
						System.out.println("Duplicate");
					else
						TableFour.put(r.binToInt(r.getPageNumber()), processEntry(P4));
				} else {
					processList.add(r.getPid());
					P4 = new Process(r.getPidFromString(r.getPid()));
					TableFour = new HashMap<Integer, PageTableEntry>();
					P4.setPageTable(TableFour);
					P4.setCurrRef(r);
					TableFour.put(r.binToInt(r.getPageNumber()), processEntry(P4));
					//System.out.println(P1.getPid() + "\t" +TableOne.toString());
				}
				break;
			case "P5":
				if(processList.contains(r.getPid())){
					P5.setCurrRef(r);
					pt = processEntry(P5);
					if(TableFive.containsKey(r.binToInt(r.getPageNumber())))
						System.out.println("Duplicate");
					else
						TableFive.put(r.binToInt(r.getPageNumber()), processEntry(P5));
				} else {
					processList.add(r.getPid());
					P5 = new Process(r.getPidFromString(r.getPid()));
					TableFive = new HashMap<Integer, PageTableEntry>();
					P5.setPageTable(TableFive);
					P5.setCurrRef(r);
					TableFive.put(r.binToInt(r.getPageNumber()), processEntry(P5));
					//System.out.println(P1.getPid() + "\t" +TableOne.toString());
				}
				break;
			default:
				System.out.println("Error processing reference: " +r.toString());
				System.exit(1);
		}
	}
	
	private PageTableEntry processEntry(Process p){
		PageTableEntry pte = new PageTableEntry();
		String temp = p.getCurrRef().getPageNumber();
		//System.out.println(temp);
		pte.setPageNum(Integer.parseInt(temp, 2));
		// TODO SET FRAME NUM
		pte.setValid(true);
		pte.setResident(true);
		
		return pte;
	}
	
	private void pageFault(Process p){
		faultCount++;

		checkFrameTable();
		
		if(!isFreeFrame)
			pageReplacement();
		//else
			//addEntry(p);
	}
	
	private boolean checkFrameTable(){
		
		return false;
	}
	
	private void pageReplacement(){
		
	}
}