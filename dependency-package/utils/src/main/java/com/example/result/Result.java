package com.example.result;



import com.sgcc.exception.TopErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = -3948389268625625059L;

    private String msg;

    private int resultCode;

    private Object data;

    public static Result success( ) {
        Result result = new Result();
        result.resultCode = TopErrorCode.SUCCESS.getErrcode();
        result.msg = TopErrorCode.SUCCESS.getDesc();
        return result;
    }

    public static Result success( String msg , Object data ) {
        Result result = new Result();
        result.resultCode = TopErrorCode.SUCCESS.getErrcode();
        result.msg = msg;
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.resultCode = TopErrorCode.SUCCESS.getErrcode();
        result.msg = TopErrorCode.SUCCESS.getDesc();
        result.setData(data);
        return result;
    }

    public static Result failure( TopErrorCode topErrorCode  ) {
        Result result = new Result();
        result.resultCode = topErrorCode.getErrcode();
        result.msg = topErrorCode.getDesc();
        return result;
    }

    public static Result failure(TopErrorCode topErrorCode , Object data) {
        Result result = new Result();
        result.resultCode = topErrorCode.getErrcode();
        result.msg = topErrorCode.getDesc();
        result.setData(data);
        return result;
    }

    public void setData(Object data){
        this.data = data;
    }

}