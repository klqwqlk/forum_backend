package io.kelin.forum.interceptor;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.kelin.forum.util.CommonResult;
import io.kelin.forum.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    //在preHandle方法中进行登录判断
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = request.getHeader("AuthToken");
        Claims claims = JwtUtil.verifyJwt(authToken);

        if(claims==null){

                //设置response状态
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");

                //返回的数据
                CommonResult commonResult = CommonResult.fail().setState(-1).setMessage("token is invalid");
                PrintWriter out = null ;
                out = response.getWriter();
                out.write(new Gson().toJson(commonResult));
                out.flush();
                out.close();


            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("执行了TestInterceptor的postHandle方法");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}


