package pack0;

public class Page {

	protected String pid;
	protected String pageNumber;
	
	public Page() {}
	
	public Page(String p, String l){
		this.pid = p;
	}
	
	public String toString(){
		return getPid()+":\t"+getLocation();
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getLocation() {
		return pageNumber;
	}

	public void setLocation(String location) {
		this.pageNumber = location;
	}
}
