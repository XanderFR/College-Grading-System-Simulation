/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getLoginForm() {
        ModelAndView model = new ModelAndView("login");
        return model;
    }
    
    @RequestMapping(value = "/LoginController", method = RequestMethod.POST)
    public ModelAndView submitLoginForm(@RequestParam("username") String username, @RequestParam("password") String password) {

        String un = username;
        String pw = password;
        
        if (un.equals("admin") && pw.equals("admin")) {
            ModelAndView model = new ModelAndView("index");
            return model;
        }
        else {
            ModelAndView model = new ModelAndView("login");
            model.addObject("message", "Credentials Incorrect, Please Try Again");
            return model;
        }
    }
}
