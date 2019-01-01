package dao;


import Web.BookItem;
import Web.Page;
import domian.Book;

import java.util.List;

public interface BookDAO {
    /**
     * 根据 id 获取指定 Book 对象
     * @param id
     * @return
     */
    public abstract Book getBook(int id);

    /**
     * 根据传入的 BookItem 对象返回对应的 Page 对象
     * @param bt
     * @return
     */
    public abstract Page<Book> getPage(BookItem bt);

    /**
     * 根据传入的 BookItem 对象返回其对应的记录数
     * @param cb
     * @return
     */
    public abstract long getTotalBookNumber(BookItem cb);

    /**
     *根据传入的 BookItem 和 pageSize 返回当前页对应的 List
     * @param cb
     * @param pageSize
     * @return
     */
    public abstract List<Book> getPageList(BookItem cb, int pageSize);

    /**
     * 返回指定 id 的 book 的 storeNumber 字段的值
     * @param id
     * @return
     */
    public abstract int getStoreNumber(Integer id);



}
