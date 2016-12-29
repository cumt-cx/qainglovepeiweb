package com.demo.web;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by cumt_ on 2016/12/17.
 */
@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        return "login";
    }

    @RequestMapping(value = "/login/auth",method = RequestMethod.POST)
    public  String doLogin(HttpServletRequest request,@RequestParam("userName") String userName,@RequestParam("passWord") String passWord){
/*        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");*/
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        Subject currentUser = SecurityUtils.getSubject();
        String resultPageURL = "/login";
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            System.out.println("对用户[" + userName + "]进行登录验证..验证开始");
            currentUser.login(token);
            System.out.println("对用户[" + userName + "]进行登录验证..验证通过");
            resultPageURL = "redirect:/sucessLogin.jsp";
        }catch(UnknownAccountException uae){
            System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
            request.setAttribute("message_login", "未知账户");
        }catch(IncorrectCredentialsException ice){
            System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
            request.setAttribute("message_login", "密码不正确");
        }catch(LockedAccountException lae){
            System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
            request.setAttribute("message_login", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
            request.setAttribute("message_login", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            request.setAttribute("message_login", "用户名或密码不正确");
        }
        return resultPageURL;
    }


    @RequestMapping("admin/index")
    public String index(HttpServletRequest request){
        return "sucessLogin";
    }

    @RequestMapping("admin/channel")
    public String channel(HttpServletRequest request){
        return "wellcome channel";
    }
    @ResponseBody
    @RequestMapping("admin/content")
    public String content(HttpServletRequest request){
        return "wellcome content";
    }
    @ResponseBody
    @RequestMapping("admin/sys")
    public String sys(HttpServletRequest request){
        return "wellcome sys";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }


    @RequestMapping(value="/curDate",method = RequestMethod.GET)
    public long getCurDate(){
        Date curDate = new Date();
        return curDate.getTime();
    }

    @RequestMapping(value="/getUser",method = RequestMethod.GET)
    public @ResponseBody User getUser(@RequestParam(value = "userNameId") String userNameId){
        return userService.getUser(userNameId);
    }
}
