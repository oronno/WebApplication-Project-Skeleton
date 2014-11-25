package com.oneous.webapp.controller;

import com.oneous.webapp.persistance.entity.User;
import com.oneous.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Abdullah Al Mamun Oronno (www.oneous.com)
 */

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/admin/users")
    public String getUserList(Model model) {
        log.debug("getUserList");
        model.addAttribute(userService.findAllUsers());

        return "users";
    }

    @RequestMapping(value = "/admin/users/create", method = RequestMethod.GET)
    public String createNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users-create";
    }

    @RequestMapping(value = "/admin/users/create", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult) {
        log.debug("createNewUser, username={}, email={}, errorCount={}", user.getUsername(), user.getEmail(), bindingResult.getErrorCount());

        if (userService.findUserByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.username.exist");
        }

        if (userService.findUserByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email.exist");
        }

        if (bindingResult.hasErrors()) {
            return "users-create";
        }

        user.setEnabled(true);
        userService.saveUser(user);

        return "redirect:/admin/users";
    }

}
