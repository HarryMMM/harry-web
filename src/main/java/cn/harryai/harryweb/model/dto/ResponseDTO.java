package cn.harryai.harryweb.model.dto;

/**
 * @author Harry
 * @since 2019/10/13 22:07
 **/
public class ResponseDTO<T> {
    private boolean success;
    private String message;
    private String errCode;
    private T data;

    public static <T> ResponseDTO<T> buildSuccess(T data) {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.data = data;
        responseDTO.success=Boolean.TRUE;
        return responseDTO;
    }

    public static ResponseDTO buildFailure(String errCode, String message) {
        ResponseDTO responseDTO = new ResponseDTO<>();
        responseDTO.errCode = errCode;
        responseDTO.message = message;
        responseDTO.success=Boolean.FALSE;
        return responseDTO;
    }

    public static ResponseDTO buildFailure(String message) {
        return buildFailure(null, message);
    }

    public String getMessage() {
        return message;
    }

    public String getErrCode() {
        return errCode;
    }

    public T getData() {
        return data;
    }
}
