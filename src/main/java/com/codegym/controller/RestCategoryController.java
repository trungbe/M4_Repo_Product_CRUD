package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/category")
public class RestCategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("category/listCate");
        modelAndView.addObject("listCate", categoryService.findALl());
        return modelAndView;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Category>> getAll() {
        return new ResponseEntity<>(categoryService.findALl(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id){
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
