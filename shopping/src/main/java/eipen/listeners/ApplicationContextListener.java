package eipen.listeners;

import eipen.Utils.ExecutorsUtils;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    //我们考虑到程序启动时就应该吧线程池进行创建启动，那么久需要这样一个监听来处理

    /**
     * 程序开始时执行
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //程序启动时创建一个线程数量为10的线程池
        ExecutorsUtils.setExecutorService(Executors.newScheduledThreadPool(10));
    }

    /**
     * 程序结束时执行
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //结束时进行线程池关闭
        ExecutorsUtils.shutdown();
    }

}
