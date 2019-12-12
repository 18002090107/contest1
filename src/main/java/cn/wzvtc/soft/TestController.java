package cn.wzvtc.soft;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController("/")
public class TestController {

    @RequestMapping(value="userinfo")
    public Map<String,String> userinfo(HttpSession httpSession){
        Map<String,String> resultMap=new HashMap<>();
        //1.判断是否已经登录，判断SESSION里面是否有我的用户名信息
        if(httpSession.getAttribute("loginnumber")!=null){
            resultMap.put("myname","薛苇鹏");
            resultMap.put("mynumber","18002090139");
        }
        return resultMap;
    }

    @RequestMapping(value="/login")
    public Map<String,String> login(@RequestBody Map<String,String> map, HttpSession httpSession){
        String number=map.get("number");
        String password=map.get("password");
        Map<String,String> resultMap=new HashMap<>();
        if("18002090139".equals(number)&&"123456".equals(password)){
            //将用户名存入session中
            httpSession.setAttribute("loginnumber",number);
            resultMap.put("result","success");
        }
        return resultMap;
    }
    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("loginnumber");
    }
}