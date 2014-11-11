package pack0;

public class Process {

	protected int pid;
	protected Reference currRef;

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
}
