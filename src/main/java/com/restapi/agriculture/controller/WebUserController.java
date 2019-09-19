package com.restapi.agriculture.controller;


import com.restapi.agriculture.config.AES;
import com.restapi.agriculture.dto.UserReqDto;
import com.restapi.agriculture.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/webUser")
public class WebUserController {

    @Autowired
    UserService userService;

    @Autowired
    AES aes;

    @GetMapping(value = "firstPageUser")
    public ModelAndView userGet() {
        ModelAndView modelAndView = new ModelAndView("user");

        modelAndView.addObject("Users", userService.findAll());
        return modelAndView;
    }

    @GetMapping(value = "create")
    public ModelAndView createUser() {
        ModelAndView modelAndView = new ModelAndView("createUser");

        return modelAndView;
    }

    @PostMapping(value = "create")
    public ModelAndView createDataUser(@Valid UserReqDto userReqDto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("createUser");

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("createUser");

            for (FieldError error:result.getFieldErrors()) {
                mav.addObject("result", error.getDefaultMessage());

                if (error.getField().equals("userName")) {
                    mav.addObject("errorName", error.getDefaultMessage());
                }

                if (error.getField().equals("balanceUser")) {
                    mav.addObject("errorBalance",error.getDefaultMessage());
                }
            }

            mav.addObject("Users", userReqDto);
            return mav;
        }

        userService.create(userReqDto);
        modelAndView.addObject("Users", userService.findAll());
        modelAndView.setViewName("redirect:/webUser/view");

        return modelAndView;
    }

    @GetMapping(value = "view")
    public ModelAndView viewUser() {
        ModelAndView modelAndView = new ModelAndView("viewUser");

        userService.findAll();
        modelAndView.addObject("Users", userService.findAll());

        return modelAndView;
    }

    @GetMapping(value = "view/{idUserEncrypt}")
    public ModelAndView detailUserId(@PathVariable("idUserEncrypt") String idUserEncrypt) {
        ModelAndView modelAndView = new ModelAndView("detailUser");

        Integer id = Integer.valueOf(aes.decrypt(idUserEncrypt));
        modelAndView.addObject("User", userService.findById(id));

        return modelAndView;

    }

    @GetMapping(value = "view/update/{idUserEncrypt}")
    public ModelAndView updateUserById(@PathVariable("idUserEncrypt") String idUserEncrypt) {
        ModelAndView modelAndView = new ModelAndView("updateUser");

        Integer id = Integer.valueOf(aes.decrypt(idUserEncrypt));
        modelAndView.addObject("User", userService.findById(id));

        return modelAndView;
    }

    @PostMapping(value = "view/update/{idUserEncrypt}")
    public ModelAndView updateUsers(@PathVariable("idUserEncrypt") String idUserEncrypt, @Valid UserReqDto userReqDto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("updateUser");

        Integer id = Integer.valueOf(aes.decrypt(idUserEncrypt));

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("updateUser");
            log.info(result.getFieldErrors().toString());
            for (FieldError error:result.getFieldErrors()) {
                mav.addObject("result", error.getDefaultMessage());
                if (error.getField().equals("userName")) {
                    mav.addObject("errorName", error.getDefaultMessage());
                    log.info("error nama  "+error.getDefaultMessage());
                }

                if (error.getField().equals("balanceUser")) {
                    log.info("error balance  "+error.getDefaultMessage());
                    mav.addObject("errorBalance",error.getDefaultMessage());
                }
            }

            mav.addObject("User", userReqDto);
            return mav;
        }

        modelAndView.addObject("User", userService.update(id, userReqDto));
        modelAndView.setViewName("redirect:/webUser/view");

        return modelAndView;
    }

    @GetMapping(value = "delete/{id}")
    public RedirectView deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);

        return new RedirectView("/agriculture/webUser/view");
    }

}
