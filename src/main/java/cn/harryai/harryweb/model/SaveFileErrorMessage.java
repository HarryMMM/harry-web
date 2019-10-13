package cn.harryai.harryweb.model;

/**
 * @author Harry
 * @since 2019/10/13 23:06
 **/
public enum SaveFileErrorMessage {
    // 用户任务超限
    USER_TASK_EXCEED("The task you created has exceeded the limit"),
    ALL_TASK_EXCEED("The system is busy, please try again later.")
    ;

    private String errorMessage;

    SaveFileErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }
}
