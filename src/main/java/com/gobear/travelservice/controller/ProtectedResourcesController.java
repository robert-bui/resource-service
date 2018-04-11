package com.gobear.travelservice.controller;

import com.gobear.travelservice.utils.CookieUtils;
import com.gobear.travelservice.utils.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ProtectedResourcesController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/protected-resource";
    }

    @RequestMapping("/welcome")
    @ResponseBody
    public String hello() {
        return "Welcome, you're authenticated!!!";
    }

    @RequestMapping("/protected-resource")
    @ResponseBody
    public String protectedResource() {
        return "Well done! You're passed the security check.";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse httpServletResponse) {
        CookieUtils.clear(httpServletResponse, TokenUtils.LOGIN_TOKEN);
        return "redirect:/";
    }
}
