package io.kelin.forum.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回类
 */
@Data
public class CommonResult<T> {
    private int state;
    private String message;
    private Map<String,Object> data;

    public CommonResult(int state,String message, Map<String,Object> data){
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public static CommonResult ok(){
        return new CommonResult(200,"success",null);
    }
    public static CommonResult fail(){
        return new CommonResult(-1,"failure",null);
    }

    public CommonResult setState(int state){
        this.state = state;
        return this;
    }
    public CommonResult setMessage(String  message){
        this.message = message;
        return this;
    }
    public CommonResult setData(Map<String,Object> data){
        this.data = data;
        return this;
    }
    public CommonResult put(String s,Object o){
        if(this.data == null){
            this.data = new HashMap<>();
        }
        this.data.put(s, o);
        return this;
    }
}
