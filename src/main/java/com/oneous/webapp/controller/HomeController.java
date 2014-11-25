package com.oneous.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Abdullah Al Mamun Oronno (www.oneous.com)
 */

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String firstPage(ModelMap model) {
        log.debug("at first page");

        return "home";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String getLoginPage(ModelMap model) {
        log.debug("at login page");

        return "login";
    }

}
