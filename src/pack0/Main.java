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
	private String[] logical;
	
	/** Page Tables **/
	private ArrayList<PageTableEntry> TableOne;
	private ArrayList<PageTableEntry> TableTwo;
	private ArrayList<PageTableEntry> TableThree;
	private ArrayList<PageTableEntry> TableFour;
	private ArrayList<PageTableEntry> TableFive;
	private PageTableEntry pt;
	
	/** Processes **/
	Process P1;
	Process P2;
	Process P3;
	Process P4;
	Process P5;
	
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
		
		for(int i = 0; i < TableOne.size(); i++){
			System.out.println(P1.getPid() + "\t" +TableOne.get(i).toString());
			System.out.println(P2.getPid() + "\t" +TableTwo.get(i).toString());
			System.out.println(P3.getPid() + "\t" +TableThree.get(i).toString());
			System.out.println(P4.getPid() + "\t" +TableFour.get(i).toString());
			System.out.println(P5.getPid() + "\t" +TableFive.get(i).toString());
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
					System.out.println(pt.getPageNum());
					if(TableOne.contains(pt.getPageNum()))
						System.out.println("Duplicate");
					else
						TableOne.add(processEntry(P1));
				} else {
					processList.add(r.getPid());
					P1 = new Process(r.getPidFromString(r.getPid()));
					TableOne = new ArrayList<PageTableEntry>();
					P1.setPageTable(TableOne);
					P1.setCurrRef(r);
					TableOne.add(processEntry(P1));
				}
				break;
			case "P2":
				if(processList.contains(r.getPid())){
					P2.setCurrRef(r);
					pt = processEntry(P2);
					if(TableTwo.contains(pt))
						System.out.println("Duplicate");
					else
						TableTwo.add(processEntry(P2));
				} else {
					processList.add(r.getPid());
					P2 = new Process(r.getPidFromString(r.getPid()));
					TableTwo = new ArrayList<PageTableEntry>();
					P2.setPageTable(TableTwo);
					P2.setCurrRef(r);
					TableTwo.add(processEntry(P2));
				}
				break;
			case "P3":
				if(processList.contains(r.getPid())){
					P3.setCurrRef(r);
					pt = processEntry(P3);
					if(TableThree.contains(pt))
						System.out.println("Duplicate");
					else
						TableThree.add(processEntry(P3));
				} else {
					processList.add(r.getPid());
					P3 = new Process(r.getPidFromString(r.getPid()));
					TableThree = new ArrayList<PageTableEntry>();
					P3.setPageTable(TableThree);
					P3.setCurrRef(r);
					TableThree.add(processEntry(P3));
				}
				break;
			case "P4":
				if(processList.contains(r.getPid())){
					P4.setCurrRef(r);
					pt = processEntry(P4);
					if(TableFour.contains(pt))
						System.out.println("Duplicate");
					else
						TableFour.add(processEntry(P4));
				} else {
					processList.add(r.getPid());
					P4 = new Process(r.getPidFromString(r.getPid()));
					TableFour = new ArrayList<PageTableEntry>();
					P4.setPageTable(TableFour);
					P4.setCurrRef(r);
					TableFour.add(processEntry(P4));
				}
				break;
			case "P5":
				if(processList.contains(r.getPid())){
					P5.setCurrRef(r);
					pt = processEntry(P5);
					if(TableFive.contains(pt))
						System.out.println("Duplicate");
					else
						TableFive.add(processEntry(P5));
				} else {
					processList.add(r.getPid());
					P5 = new Process(r.getPidFromString(r.getPid()));
					TableFive = new ArrayList<PageTableEntry>();
					P5.setPageTable(TableFive);
					P5.setCurrRef(r);
					TableFive.add(processEntry(P5));
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