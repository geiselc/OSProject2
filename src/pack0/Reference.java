package pack0;

/**
 * Data structure for storing a reference (a line read in from the input file)
 * in a more convenient way. 
 */
public class Reference {

	protected String pid;
	protected String pageNumber;
	
	public Reference() {}
	
	public Reference(String p, String l){
		this.pid = p;
		this.pageNumber = l;
	}
	
	public String toString(){
		return getPid()+":\t"+getPageNumber();
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int getPidFromString(String s){
		return Integer.parseInt(s.substring(1,2));
	}
	
	public int binToInt(String s){
		return Integer.parseInt(s, 2);
	}
}
