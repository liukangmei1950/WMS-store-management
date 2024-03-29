package com.example.wms.common;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;

@Data
public class Result {
    private int code; //编码 200/400
    private String msg; //成功/失败
    private  Long total; //总计数
    private Object data; //数据

    private static Result result(int code,String msg,Long total,Object data){
        Result result = new Result();
        result.setData(data);
        result.setMsg(msg);
        result.setCode(code);
        result.setTotal(total);
        return result;
    }

    public static Result fail(){
        return result(400,"失败",0L,null);
    }
    public static Result suc(){
        return result(200,"成功",0L,null);
    }
    public static Result suc(Object data){
        return result(200,"成功",0L,data);
    }
    public static Result suc(Object data,Long total){
        return result(200,"成功",total,data);
    }
}
