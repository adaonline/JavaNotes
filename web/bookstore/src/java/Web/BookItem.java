package Web;

public class BookItem {
    private float minprice=0;
    private float maxprice=Integer.MAX_VALUE;

    private int pageNo;

    public BookItem(float minprice, float maxprice, int pageNo) {
        this.minprice = minprice;
        this.maxprice = maxprice;
        this.pageNo = pageNo;
    }

    public float getMinprice() {
        return minprice;
    }

    public void setMinprice(float minprice) {
        this.minprice = minprice;
    }

    public float getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(float maxprice) {
        this.maxprice = maxprice;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
