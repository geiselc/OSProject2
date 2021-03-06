/**
 * @author Conrad Geisel
 */
package pack0;

/**
 * The main data structure used for maintaining a process's page table. Each page
 * read in has its own entry in the page table. Each entry consists of the page number,
 * frame number, and boolean flags indicating valid and resident bits. 
 */
public class PageTableEntry {
	
	protected int pageNum;
	public int frameNum;
	protected boolean isValid;
	protected boolean isResident;

	public PageTableEntry() {}
	
	public PageTableEntry(int pageNumber, int frameNumber, boolean validBit, boolean residentBit){
		this.pageNum = pageNumber;
		this.frameNum = frameNumber;
		this.isValid = validBit;
		this.isResident = residentBit;
	}
	
	public String toString(){
		return this.pageNum + " " + this.frameNum + " " + this.isValid + " " + this.isResident;
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