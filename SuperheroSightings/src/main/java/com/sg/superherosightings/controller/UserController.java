package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.DaoUser;
import com.sg.superherosightings.model.User;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/user"})
public class UserController {

    private DaoUser dao;
    private PasswordEncoder encoder;

    @Inject
    public UserController(DaoUser dao, PasswordEncoder encoder) {
        this.dao = dao;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List users = dao.getAllUsers();
        model.put("users", users);
        return "displayUserList";
    }

    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Model model) {
        
        User newUser = new User();
        model.addAttribute("user", newUser);
        
        return "addUserForm";
    }

    @RequestMapping(value ="/displayEditUser", method = RequestMethod.GET)
    public String displayEditUser(HttpServletRequest request, Model model) {
        
        String userIdParameter = request.getParameter("user_id");
        int user_id = Integer.parseInt(userIdParameter);
        User user = dao.getUser(user_id);
        user.setPassword("");
        model.addAttribute("user", user);
        
        return "editUser";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest req) {
        User newUser = new User();
        if (result.hasErrors()) {
            return "addUserForm";
        }
        
        newUser.setUsername(req.getParameter("username"));
        String clearPw = req.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);
        newUser.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }

        dao.addUser(newUser);

        return "redirect:displayUserList";
    }
    
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest req) {
        
        if (result.hasErrors()) {
            return "editUser";
        }
        
        String clearPw = user.getPassword();
        String hashPw = encoder.encode(clearPw);
        user.setPassword(hashPw);
        
        user.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            user.addAuthority("ROLE_ADMIN");
        }
        
        dao.editUser(user);
        
        return "redirect:displayUserList";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("username") String username, Map<String, Object> model) {
        dao.deleteUser(username);
        return "redirect:displayUserList";
    }
}
