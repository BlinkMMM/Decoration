package util;
/**
 * 1.总记录数totalSize
 * 2.每页记录条数 final pageSize
 * 3.共几页 totalPages
 * 4.当前页码 currentPageCode
 * 5.从第几条记录开始 startCode
 * 5.从第几条记录结束 endCode
 *
 */
public class Page {
	private int totalSize;//总数据条数
	private final int pageSize = 8;//每页数据条数
	private int totalPages;//总页数
	private int currentPageCode;//当前页数
	private int startCode;//当前页起始数据的行号
	private int endCode;//当前页终末数据的行号
	public Page(){
		
	}
	public Page(int totalSize,int currentPageCode){
		this.totalSize = totalSize;
		this.currentPageCode = currentPageCode;
		//根据总数据条数和每页数据条数计算出总页数
		this.totalPages = (this.totalSize%this.pageSize ==0)?(this.totalSize/this.pageSize) : (this.totalSize/this.pageSize+1);
		//计算当前页起始数据的行号
		this.startCode = (this.currentPageCode-1)*this.pageSize;
		//当前页终末数据的行号
		this.endCode = (this.currentPageCode==this.totalPages)?(this.totalSize):(this.currentPageCode*this.pageSize);
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPageCode() {
		return currentPageCode;
	}
	public void setCurrentPageCode(int currentPageCode) {
		this.currentPageCode = currentPageCode;
	}
	public int getStartCode() {
		return startCode;
	}
	public void setStartCode(int startCode) {
		this.startCode = startCode;
	}
	public int getEndCode() {
		return endCode;
	}
	public void setEndCode(int endCode) {
		this.endCode = endCode;
	}
	public int getPageSize() {
		return pageSize;
	}
}
