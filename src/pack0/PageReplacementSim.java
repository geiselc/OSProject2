package pack0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class PageReplacementSim {
	
	/**
	 * TODO
	 * - Setup structures for Logical Mem, Page Table, Physical Mem, and Disk
	 * - Setup Process Object
	 * - 
	 */
	
	/** Constants **/
	private final int LOGICAL = 64; 	// KB - 6 pages
	private final int PHYSICAL = 16; 	// KB - 4 frames
	private final int PAGE_FRAME = 1;	// KB 
	
	private ArrayList<Page> pages;
	private HashMap<Integer, PageTableEntry> pageTable;
	
	public static void main(String[] args) {
		new PageReplacementSim(args[0]);
	}
	
	public PageReplacementSim(String file){
		this.pages = new ArrayList<Page>();
		
		try{
			String line;
			String[] parts;
			Page page;
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null){
				parts = line.split(":\t");
				page = new Page(parts[0], parts[1]);
				pages.add(page);
			}
			
			br.close();
			for(int i = 0; i < pages.size(); i++){
				process(pages.get(i));
			}
		} catch(IOException e){
			System.out.println("Error parsing input file");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void process(Page p){
		boolean fault = false;
		System.out.println(p.toString());
		
		if(fault)
			pageFault();
	}
	
	private void pageFault(){
		
	}
}