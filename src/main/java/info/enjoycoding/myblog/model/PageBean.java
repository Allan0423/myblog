package info.enjoycoding.myblog.model;

/**
 * 分页数据模型
 */
public class PageBean {

    /**
     * 页码
     */
    private int pageNo;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 起始条目
     */
    private int start;

    public PageBean(int pageNo, int pageSize){
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }
}
