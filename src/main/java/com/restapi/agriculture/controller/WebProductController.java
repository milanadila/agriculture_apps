package com.restapi.agriculture.controller;


import com.restapi.agriculture.config.AES;
import com.restapi.agriculture.dto.ProductReqDto;
import com.restapi.agriculture.dto.TransactionReqDto;
import com.restapi.agriculture.service.ProductService;
import com.restapi.agriculture.service.TransactionService;
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
@RequestMapping(value = "/webProduct")
public class WebProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AES aes;

    @GetMapping(value = "firstPageProduct")
    public ModelAndView productGet() {
        ModelAndView modelAndView = new ModelAndView("product");

        modelAndView.addObject("Products", productService.findAll());

        return modelAndView;
    }

    @GetMapping(value = "addProduct")
    public ModelAndView productAdd() {
        ModelAndView modelAndView = new ModelAndView("addProducts");

        return modelAndView;
    }

    @PostMapping(value = "addProduct")
    public ModelAndView addNewProduct(@Valid ProductReqDto productReqDto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("addProducts");

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("addProducts");

            for (FieldError error:result.getFieldErrors()) {
                mav.addObject("result", error.getDefaultMessage());

                if (error.getField().equals("productName")) {
                    mav.addObject("errorName", error.getDefaultMessage());
                }

                if (error.getField().equals("productPrice")) {
                    mav.addObject("errorPrice",error.getDefaultMessage());
                }

                if (error.getField().equals("stockProduct")) {
                    mav.addObject("errorStock", error.getDefaultMessage());
                }
            }

            mav.addObject("Products", productReqDto);
            return mav;
        }

        productService.create(productReqDto);
        modelAndView.addObject("Products", productService.findAll());
        modelAndView.setViewName("redirect:/webProduct/viewProduct");

        return modelAndView;
    }

    @GetMapping(value = "viewProduct")
    public ModelAndView viewAllProduct() {
        ModelAndView modelAndView = new ModelAndView("viewProducts");

        productService.findAll();
        modelAndView.addObject("Products", productService.findAll());

        return modelAndView;
    }

    @GetMapping(value = "viewProduct/{idProductEncrypt}")
    public ModelAndView viewDetailProduct(@PathVariable("idProductEncrypt") String idProductEncrypt) {
        ModelAndView modelAndView = new ModelAndView("detailProduct");

        Integer id = Integer.valueOf(aes.decrypt(idProductEncrypt));
        modelAndView.addObject("Product", productService.findById(id));

        return modelAndView;
    }

    @GetMapping(value = "viewProduct/updateProduct/{idProductEncrypt}")
    public ModelAndView updateNewProduct(@PathVariable("idProductEncrypt") String idProductEncrypt) {
        ModelAndView modelAndView = new ModelAndView("updateProducts");

        Integer id = Integer.valueOf(aes.decrypt(idProductEncrypt));
        modelAndView.addObject("Product", productService.findById(id));

        return modelAndView;
    }

    @PostMapping(value = "viewProduct/updateProduct/{idProductEncrypt}")
    public ModelAndView updateProductByID(@PathVariable("idProductEncrypt") String idProductEncrypt, ProductReqDto productReqDto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        Integer id = Integer.valueOf(aes.decrypt(idProductEncrypt));

log.info(result.toString());
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("updateProducts");

            for (FieldError error : result.getFieldErrors()) {
                log.info(error.toString());
                mav.addObject("result", error.getDefaultMessage());

                if (error.getField().equals("productName")) {
                    mav.addObject("errorNameUp", error.getDefaultMessage());
                }

                if (error.getField().equals("productPrice")) {
                    mav.addObject("errorPriceUp", error.getDefaultMessage());
                }

                if (error.getField().equals("stockProduct")) {
                    mav.addObject("errorStockUp", error.getDefaultMessage());
                }
            }
            mav.addObject("Product", productReqDto);
            return mav;
        }

        modelAndView.addObject("Product", productService.update(id,productReqDto));
        modelAndView.setViewName("redirect:/webProduct/viewProduct");

        return modelAndView;
    }

    @GetMapping(value = "delete/{id}")
    public RedirectView deleteProductItem(@PathVariable("id") Integer id) {
       productService.delete(id);

       return new RedirectView("/agriculture/webProduct/viewProduct");
    }


    @GetMapping(value = "buyProduct")
    public ModelAndView buyProductItem() {
        ModelAndView modelAndView = new ModelAndView("buyProducts");

        productService.findAll();
        userService.findAll();
        modelAndView.addObject("Products", productService.findAll());
        modelAndView.addObject("Users", userService.findAll());

        return modelAndView;
    }

    @PostMapping(value = "buyProduct")
    public ModelAndView buyProduct(@Valid TransactionReqDto transactionReqDto) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("SUCCESS CREATE");

        try {
            transactionService.create(transactionReqDto);
            modelAndView.addObject("allTransaction", transactionService.findAll());
            modelAndView.setViewName("redirect:/webTransaction/transactionPage");

        } catch (Exception e) {
            log.info("masuk catch " + e.getMessage());
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("buyProducts");
        }

        return modelAndView;
    }
}
