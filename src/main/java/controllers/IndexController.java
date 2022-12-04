/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
@Controller
public class IndexController {
    
    @RequestMapping(value = "/IndexController", method = RequestMethod.GET)
    public ModelAndView gotoIndexPage() {

        ModelAndView model = new ModelAndView("index");
        
        return model;

    }
}
