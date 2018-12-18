package stack.arraystack;

public interface Stack<T> {
    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 入栈
     * @param t
     */
    void push(T t);

    /**
     * 出栈
     * @return
     */
    T pop();

    /**
     * 栈顶元素，不出栈
     * @return
     */
    T peek();

    /**
     * 是否已经满了(需要扩容)
     * @return
     */
    boolean isFull();
}
