package cn.harryai.harryweb.thread;

import java.util.concurrent.*;

/**
 * @author Harry
 * @since 2019/10/13 22:40
 **/
public class ThreadPoolFactory {
    private ThreadPoolFactory() {
    }

    /**
     * @param corePoolSize    线程池核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   额外线程数存活时间
     * @param unit            存活时间单位
     * @param workQueue       工作队列
     * @param threadFactory   线程工厂
     * @param handler         拒绝策略
     * @return
     */
    public static final ExecutorService createThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                                         TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
                                                         RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory,
                handler);
    }
}
