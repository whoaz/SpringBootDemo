package com.yida.demo.controller;

import com.yida.demo.entity.User;
import com.yida.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * Created by helloz on 2017/2/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public User get(Integer id){
        if (id == null) {
            return new User();
        }
        return userRepository.getOne(id);
    }

    @RequestMapping(value = {"", "/list"})
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        List<User> users = userRepository.findAll();
        mv.addObject("users", users);
        mv.setViewName("user/list");
        return mv;
    }

    @RequestMapping("/form")
    public ModelAndView form(User user){
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("user/form");
        return mv;
    }


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(User user, RedirectAttributes redirectAttributes){
        if(user.getId()==null){
            user.setCreateAt(new Date());
        }
        userRepository.save(user);
        redirectAttributes.addAttribute("id", user.getId());
        redirectAttributes.addFlashAttribute("msg", "保存成功");
        return "redirect:/user/form";
    }


    @RequestMapping("/delete")
    public String delete(User user,RedirectAttributes redirectAttributes){
        userRepository.delete(user);
        redirectAttributes.addFlashAttribute("msg","删除成功");
        return "redirect:/user/list";
    }
}
