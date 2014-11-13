package pack0;

import java.util.ArrayList;
import java.util.HashMap;

public class Process {

	protected int pid;
	protected Reference currRef;
	protected HashMap<Integer, PageTableEntry> pt;
	protected int faultCount = 0;
	protected int refCount = 0;

	public Process() {}
	
	public Process(int pid){
		this.pid = pid;
	}
	
	public String toString(){
		return getCurrRef().toString();
	}
	
	public int getPid() {
		return pid;
	}

	public void setPID(int pID) {
		this.pid = pID;
	}
	
	public Reference getCurrRef(){
		return currRef;
	}
	
	public void setCurrRef(Reference r){
		this.currRef = r;
	}
	
	public HashMap<Integer, PageTableEntry> getPageTable(){
		return pt;
	}
	
	public void setPageTable(HashMap<Integer, PageTableEntry> pt){
		this.pt = pt;
	}
}
