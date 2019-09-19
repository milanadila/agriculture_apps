package com.restapi.agriculture.controller;


import com.restapi.agriculture.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/webTransaction")
public class WebTransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping(value = "transactionPage")
    public ModelAndView transactionGet() {
        ModelAndView modelAndView = new ModelAndView("transaction");

        modelAndView.addObject("allTransaction", transactionService.findAll());

        return modelAndView;
    }
}
