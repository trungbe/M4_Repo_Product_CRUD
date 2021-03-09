//package com.codegym.controller;
//
//import com.codegym.service.category.ICategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping("/products")
//public class CategoryController {
//    @Autowired
//    private ICategoryService categoryService;
//
//    @GetMapping("")
//    public ModelAndView showList() {
//        ModelAndView modelAndView = new ModelAndView("list", "category", categoryService.findALl());
//        return modelAndView;
//    }
//}
