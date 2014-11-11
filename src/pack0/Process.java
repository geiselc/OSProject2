package pack0;

public class Process {

	protected PageTable pTable;
	protected int pID;
	protected int pageNum;
	protected String pageString;
	protected String processString;

	public Process() {}
	
	public Process(String processString, String pageString){
		this.processString = processString;
		this.pageString = pageString;
		this.pID = getPIDfromString(processString);
		this.pageNum = binToInt(pageString);
		
		//TODO Construct page table for this process
	}
	
	public PageTable getpTable() {
		return pTable;
	}

	public void setpTable(PageTable pTable) {
		this.pTable = pTable;
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	
	public String getProcessString() {
		return processString;
	}

	public void setProcessString(String processString) {
		this.processString = processString;
	}
	
	public String toString(){
		return this.getProcessString() +":\t" +this.getPageString();
	}

	private int getPIDfromString(String s){
		return Integer.parseInt(s.substring(1,2));
	}
	
	private int binToInt(String s){
		return Integer.parseInt(s, 2);
	}
}
