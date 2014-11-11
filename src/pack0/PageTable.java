package pack0;

public class PageTable {
	
	protected int pageNum;
	protected int frameNum;
	protected boolean isValid;
	protected boolean isResident;

	public PageTable() {}
	
	public PageTable(int pageNumber, int frameNumber, boolean validBit, boolean residentBit){
		this.pageNum = pageNumber;
		this.frameNum = frameNumber;
		this.isValid = validBit;
		this.isResident = residentBit;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(int frameNum) {
		this.frameNum = frameNum;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isResident() {
		return isResident;
	}

	public void setResident(boolean isResident) {
		this.isResident = isResident;
	}
}