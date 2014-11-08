package pack0;

public class Page {

	protected String pid;
	protected String location;
	
	public Page() {
		
	}
	
	public Page(String p, String l){
		this.pid = p;
		this.location = l;
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
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
