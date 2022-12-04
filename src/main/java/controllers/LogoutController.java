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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

    @RequestMapping(value = "/LogoutController", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();
        
        ModelAndView model = new ModelAndView("login");
        
        return model;

    }
}
