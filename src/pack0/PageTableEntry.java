package pack0;

public class PageTableEntry {
	
	protected int pageNum;
	protected int frameNum;
	protected boolean isValid;
	protected boolean isResident;
	protected int secondaryAddress;

	public PageTableEntry() {}
	
	public PageTableEntry(int pageNumber, int frameNumber, boolean validBit, boolean residentBit, int diskAddress){
		this.pageNum = pageNumber;
		this.frameNum = frameNumber;
		this.isValid = validBit;
		this.isResident = residentBit;
		this.secondaryAddress = diskAddress;
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

	public int getSecondaryAddress() {
		return secondaryAddress;
	}

	public void setSecondaryAddress(int secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}

}
