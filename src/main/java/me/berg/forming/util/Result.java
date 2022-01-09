package me.berg.forming.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 响应信息
 * @param <T> 任意类型数据
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "响应信息主体")
public class Result<T> {

    @ApiModelProperty(value = "返回标记：成功标记=200，失败标记=999, 详见 ResultCode 类")
    private int statusCode;

    @ApiModelProperty(value = "返回信息")
    private String statusMsg;


    @ApiModelProperty(value = "返回的数据")
    private T data;


    public Result(ResultCode rs) {
        statusCode = rs.getCode();
        statusMsg = rs.getMessage();
    }

    public Result(int statusCode, T data) {
        this.statusCode = statusCode;
        statusMsg = ResultCode.getMessageByCode(statusCode);
        this.data = data;
    }

    public Result(int statusCode) {
        this.statusCode = statusCode;
        statusMsg = ResultCode.getMessageByCode(statusCode);
    }


    public static <T> Result<T> success() {
        return restResult(null, ResultCode.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return restResult(data, ResultCode.SUCCESS, null);
    }

    public static <T> Result<T> success(T data, String msg) {
        return restResult(data, ResultCode.SUCCESS, msg);
    }

    public static <T> Result<T> isSuccess(boolean flag) {
        return flag ? success() : failed();
    }

    public static <T> Result<T> failed() {
        return restResult(null, ResultCode.COMMON_FAIL, null);
    }

    public static <T> Result<T> failed(ResultCode code) {
        return restResult(null, code, null);
    }

    public static <T> Result<T> failed(ResultCode code, String msg) {
        return restResult(null, code, msg);
    }


    public static <T> Result<T> failed(int code, String msg) {
        return restResult(null, ResultCode.COMMON_FAIL, msg);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, ResultCode.COMMON_FAIL, msg);
    }

    public static <T> Result<T> failed(String msg, T data) {
        return restResult(data, ResultCode.COMMON_FAIL, msg);
    }

    public static <T> Result<T> restResult(T data, ResultCode code, String msg) {
        Result<T> rj = new Result<>();
        rj.setStatus(code);
        rj.setData(data);
        rj.setStatusMsg(msg);
        return rj;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        statusMsg = ResultCode.getMessageByCode(statusCode);
    }

    public void setStatus(ResultCode rs) {
        this.statusCode = rs.getCode();
        statusMsg = rs.getMessage();
    }

}
