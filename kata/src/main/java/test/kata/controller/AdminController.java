package test.kata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.kata.model.Role;
import test.kata.model.User;
import test.kata.service.UserDetailsServiceImpl;
import test.kata.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(name = "/admin")
public class AdminController {

    private final UserService userService;


    @Autowired
    public AdminController(UserService userService) { this.userService = userService;}

    @GetMapping(value = "/admin/adminpanel")
    public String findAll(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", user);
        model.addAttribute("users", userService.all());
        model.addAttribute("roles", userService.listRoles());
        return "adminpanel";
    }



    @PostMapping("/admin/adminpanel/new")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("listRoles") Set<Role> roles) {
        userService.save(user, roles);
        return "redirect:/admin/adminpanel";
    }



    @PostMapping(value = "/admin/adminpanel/edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("listRoles") Set<Role> roles) {
        userService.save(user, roles);
        return "redirect:/admin/adminpanel";
    }

    @DeleteMapping(value = "/admin/adminpanel/delete/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/adminpanel";
    }

    @GetMapping("/admin/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserBiId(id));
        return "userpage";
    }
}
