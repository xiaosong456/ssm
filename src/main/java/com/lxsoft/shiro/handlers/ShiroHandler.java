package com.lxsoft.shiro.handlers;

import com.lxsoft.shiro.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


/**
 * ShiroHandler
 *
 * @author lxs
 * @version v1.0.0
 */
@Controller
@RequestMapping("/shiro")
public class ShiroHandler {

    private Logger log = LoggerFactory.getLogger(ShiroHandler.class);

    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/testShiroAnnotation")
    public  String testShiroAnnotation(HttpSession session){
        session.setAttribute("key","value1123");
        shiroService.testMethod();
        return "redirect:/user.jsp";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
        Subject currentUser= SecurityUtils.getSubject();

        if(!currentUser.isAuthenticated()){
            //把用户名和密码封装为 UsernamePaswordToken 对象
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            // rememberme
            token.setRememberMe(true);
            try {
                // 执行登录
                currentUser.login(token);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                log.error("登录失败："+e.getMessage());
            }

        }

        return "redirect:/list.jsp";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "redirect:/admin.jsp";
    }




}
