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
	private ArrayList<Process> inFile;
	private String[] processList;
	private String[] frames;
	private HashMap<Integer, PageTable> pageTable;
	
	/** Variables **/
	private int faultCount = 0;
	private boolean isFreeFrame = true;
	
	public static void main(String[] args) {
		new Main(args[0]);
	}
	
	public Main(String file){
		this.inFile = new ArrayList<Process>();
		this.pageTable = new HashMap<Integer, PageTable>();
		this.frames = new String[PHYSICAL];
		try{
			String line;
			String[] parts;
			Process process;
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null){
				parts = line.split(":\t");
				process = new Process(parts[0], parts[1]);
				inFile.add(process);
			}
			
			br.close();
			for(int i = 0; i < inFile.size(); i++){
				processLine(inFile.get(i));
			}
		} catch(IOException e){
			System.out.println("Error parsing input file");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void processLine(Process p){
		boolean fault = true;
		System.out.println(p.toString());
		
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