package pack0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {
	
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
		new Main(args[0]);
	}
	
	public Main(String file){
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
			
			for(int i = 0; i < pages.size(); i++){
				System.out.println(pages.get(i));
			}
		} catch(IOException e){
			System.out.println("Error parsing input file");
			e.printStackTrace();
			System.exit(1);
		}
	}
}