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
	private HashMap<Integer, PageTable> pageTable;
	
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
		this.pageTable = new HashMap<Integer, PageTable>();
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
				processLine(inFile.get(i));
				
				refCount++;
			}
		} catch(IOException e){
			System.out.println("Error parsing input file");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void processLine(Reference r){
		boolean fault = true;
		System.out.println(r.toString());
		
		if(processList.contains(r.getPid())){
			fault = false;
			
		} else {
			processList.add(r.getPid());
			
			//get page, add to log m, then add to page table, then add to frame. 
		}
	
		if(fault)
			pageFault();
	}
	
	private void pageFault(){
		faultCount++;

		if(!isFreeFrame)
			pageReplacement();
	}
	
	private void pageReplacement(){
		
	}
}