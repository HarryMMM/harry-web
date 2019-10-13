package cn.harryai.harryweb.service.impl;

import cn.harryai.harryweb.model.SaveFileErrorMessage;
import cn.harryai.harryweb.model.dto.ResponseDTO;
import cn.harryai.harryweb.service.TaskLimitService;
import cn.harryai.harryweb.thread.SaveFileTask;
import cn.harryai.harryweb.thread.ThreadPoolFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Harry
 * @since 2019/10/13 22:14
 **/
@Service
public class TaskLimitServiceImpl implements TaskLimitService {
    public static final int MAX_TASK_LIMIT = 5;
    private static final Map<String, AtomicInteger> USER_TASKS = new ConcurrentHashMap<>();
//    private static final ExecutorService taskPool = ThreadPoolFactory.createThreadPool(5, 5, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(8), r -> new Thread("SaveFile-"), new ThreadPoolExecutor.AbortPolicy());
    private static final ExecutorService taskPool =Executors.newFixedThreadPool(5);


    @Override
    public ResponseDTO exec(HttpServletResponse response, String session) {
        if (checkExceedUserTaskLimit(session)) {
            return ResponseDTO.buildFailure(SaveFileErrorMessage.USER_TASK_EXCEED.getErrorMessage());
        }
        AtomicInteger atomicInteger = USER_TASKS.get(session);
        atomicInteger.incrementAndGet();
        try {
            Future<Boolean> submit = taskPool.submit(new SaveFileTask(session, atomicInteger, response));
            Boolean success = submit.get();
            if (!success){
                return ResponseDTO.buildSuccess("failure");
            }
        } catch (RejectedExecutionException e) {
            return ResponseDTO.buildFailure(SaveFileErrorMessage.ALL_TASK_EXCEED.getErrorMessage());
        }
        catch (InterruptedException | ExecutionException e) {
            return ResponseDTO.buildSuccess("failure");
        }

        return ResponseDTO.buildSuccess("success");
    }

    private boolean checkExceedUserTaskLimit(String session) {
        AtomicInteger atomicInteger = USER_TASKS.get(session);
        // 如果查不到当前用户的任务计数，表示该用户第一次创建任务
        // 为该用户新创建任务计数
        if (Objects.isNull(atomicInteger)) {
            AtomicInteger newAtomicInteger = new AtomicInteger();
            USER_TASKS.put(session, newAtomicInteger);
            return false;
        }
        return atomicInteger.get() >= MAX_TASK_LIMIT;
    }
}
