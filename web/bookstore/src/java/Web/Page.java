package Web;

import java.util.List;

public class Page<T> {
    //当前第几页
    private int pageNo;
    //当前页的list
    private List<T> list;
    //每页显示的记录数目
    private int pageSize=3;
    //总共有多少记录
    private long totalItemNumber;

    public Page(int pageNo) {
        this.pageNo = pageNo;
    }
    //需要验证一下
    public int getPageNo() {
        if(pageNo<0){
            pageNo=1;
        }
        if(pageNo>getTotalPageNumber()){
            pageNo=getTotalPageNumber();
        }
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalItemNumber() {
        return totalItemNumber;
    }

    public void setTotalItemNumber(long totalItemNumber) {
        this.totalItemNumber = totalItemNumber;
    }
    //总页数
    public int getTotalPageNumber(){
        int totalpageNumber=(int)totalItemNumber/pageSize;
        if(totalpageNumber%pageSize!=0){
            return totalpageNumber++;
        }
        return totalpageNumber;
    }
    //是否有下一页
    public boolean isHasNext(){
        if(getPageNo()<getTotalPageNumber()){
            return true;
        }
        return false;
    }
    //是否有前一页
    public boolean isHasPrevious(){
        if(getPageNo()>1){
            return true;
        }
        return false;
    }
    //返回上一页页码
    public int getPrevPage(){
        if(isHasPrevious()){
            return getPageNo()-1;
        }
        return getPageNo();
    }
    //返回下页页码
    public int getNextPage(){
        if(isHasNext()){
            return getPageNo()+1;
        }
        return getPageNo();
    }
}
