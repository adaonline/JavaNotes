本文涉及到的知识点：

1. thread.join(),
2. object.wait(),
3. object.notify(),
4. CountdownLatch,
5. CyclicBarrier,
6. FutureTask,
7. Callable 。



## 1.两个线程依次执行
希望B在A运行完之后再执行，可以用thread.join

**示例：ThreadCommunication\src\ThreadDemo\OneByOneDemo.java**

## 2.那如何让 两个线程按照指定方式有序交叉运行呢？
想让A先运行，然后B再去运行，B运行完之后再运行A后续步骤。
可以利用 object.wait() 和 object.notify() 两个方法来实现

**示例：ThreadCommunication\src\ThreadDemo\OrderCrossover.java**

## 3.一个线程等待其他线程运行完再去运行，其他线程而且是同步运行的
D等待ABC运行完再去运行，这时候就需要CountdownLatch

它的基本用法是：

1. 创建一个计数器，设置初始值，CountdownLatch countDownLatch = new CountDownLatch(2);
2. 在 等待线程 里调用 countDownLatch.await() 方法，进入等待状态，直到计数值变成 0；
3. 在 其他线程 里，调用 countDownLatch.countDown() 方法，该方法会将计数值减小 1；
4. 当 其他线程 的 countDown() 方法把计数值变成 0 时，等待线程 里的 countDownLatch.await() 立即退出，继续执行下面的代码。

**示例：ThreadCommunication\src\ThreadDemo\WaitOtherThread.java**
## 4.多个线程同时准备好，然后同时运行
上述的countDownLatch最终只能触发一个await。这次是需要多个线程同时等待准备好一起运行。

为了实现线程间互相等待这种需求，我们可以利用 CyclicBarrier 数据结构，它的基本用法是：

1.先创建一个公共CyclicBarrier 对象，设置同时等待的线程数，CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
2. 这些线程同时开始自己做准备，自身准备完毕后，需要等待别人准备完毕，这时调用 cyclicBarrier.await(); 即可开始等待别人；
3. 当指定的同时等待的线程数都调用了cyclicBarrier.await();时，意味着这些线程都准备完毕好，然后这些线程才 同时继续执行。


**示例：ThreadCommunication\src\ThreadDemo\AllWaitAllStart.java**
## 5.子线程完成某件任务后，把得到的结果回传给主线程
线程创建，一般是把Runnable传给Thread去执行，但是没有任何返回的内容。所以子线程需要给主线程传递信息的时候，就需要另外一种接口类，callable():
```
@FunctionalInterface
public interface Callable<V> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    V call() throws Exception;
}
```
callable主要功能就是返回泛型结果。
在Java里，FutureTask实现了callable的主要功能，只不过他的get会阻塞主线程。

**示例：ThreadCommunication\src\ThreadDemo\FutureTaskTest.java**