package pack0;

public class Page {

	protected String pid;
	protected String pageNumber;
	
	public Page() {}
	
	public Page(String p, String l){
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
}
