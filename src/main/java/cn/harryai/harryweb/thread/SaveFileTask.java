package cn.harryai.harryweb.thread;

import cn.harryai.toolkit.io.FileContentOperateUtils;
import cn.harryai.toolkit.io.FileOperateUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Harry
 * @since 2019/10/13 22:47
 **/
public class SaveFileTask implements Callable<Boolean> {
    private AtomicInteger taskCount;
    private String session;
    private HttpServletResponse response;

    public SaveFileTask(String session, AtomicInteger taskCount, HttpServletResponse response) {
        this.taskCount = taskCount;
        this.session = session;
        this.response = response;

    }


    @Override
    public Boolean call() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(2);
            String fileName = session + "_" + UUID.randomUUID() + ".txt";
            String filePath = "F:" + File.separator + "test" + File.separator;
//            String downloadfilePath = filePath + File.separator + "download";
            FileContentOperateUtils.writeToText("h发大水了success", new File(FileOperateUtils.concatPath(filePath, fileName)));
            FileOperateUtils.download(response, filePath, fileName);
        } finally {
            taskCount.decrementAndGet();
        }
        return true;
    }
}
