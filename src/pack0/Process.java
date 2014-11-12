package pack0;

import java.util.ArrayList;

public class Process {

	protected int pid;
	protected Reference currRef;
	protected ArrayList<PageTableEntry> pt;

	public Process() {}
	
	public Process(int pid){
		this.pid = pid;
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
	
	public ArrayList<PageTableEntry> getPageTable(){
		return pt;
	}
	
	public void setPageTable(ArrayList<PageTableEntry> pt){
		this.pt = pt;
	}
}
