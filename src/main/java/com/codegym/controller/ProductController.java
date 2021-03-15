package com.codegym.controller;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findALl();
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showNotFound() {
        return new ModelAndView("error-404");
    }

    @GetMapping("")
    public ModelAndView getAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<Product> products = productService.findALl(pageable);
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("products", products);
//        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Validated @ModelAttribute Product product, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("create", "product", new Product());
        modelAndView.addObject("mess", "Tao moi thanh cong product ten la " + product.getName());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) throws NotFoundException {
        Product products = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("product", products);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.remove(id);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewDetail(@PathVariable long id) throws NotFoundException {
        return new ModelAndView("view", "product", productService.findById(id));
    }

    @PostMapping("/searchName")
    public ModelAndView searchProductByName(@RequestParam String name) {
        name = "%" + name + "%";
        List<Product> products = productService.findByName(name);
        return new ModelAndView("list", "products", products);
    }

    @PostMapping("/searchCategory")
    public ModelAndView searchProductByCategory(@RequestParam Long id) {
        List<Product> products = productService.findByCategoryName(id);
        return new ModelAndView("list", "products", products);
    }

    @GetMapping("/top5pricemax")
    public ModelAndView find5PriceMax() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("products", productService.top5ProductPriceMax());
        return modelAndView;
    }
    @GetMapping("/top5productnewest")
    public ModelAndView find5ProductNew() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("products", productService.top5ProductNewest());
        return modelAndView;
    }

    @GetMapping("/sumprice")
    public ModelAndView sumPrice() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("sumprice", productService.sumPrice());
        modelAndView.addObject("products", productService.findALl());
        return modelAndView;
    }
}
