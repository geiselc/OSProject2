package pack0;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	/**
	 * TODO
	 * - Setup structures for Logical Mem, Page Table, Physical Mem, and Disk
	 * - Setup Process Object
	 * - 
	 */
	private ArrayList<String> contents;
	
	public static void main(String[] args) {
		new Main(args[0]);
	}
	
	public Main(String file){
		this.contents = new ArrayList<String>();
		
		try{
			String line;
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null){
				contents.add(line);
			}
			
			for(int i = 0; i < contents.size(); i++){
				System.out.println(contents.get(i));
			}
		} catch(IOException e){
			System.out.println("Error parsing input file");
			e.printStackTrace();
			System.exit(1);
		}
	}

}
