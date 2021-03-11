package com.codegym.service.product;

import com.codegym.model.Product;
import com.codegym.service.IService;

import java.util.List;

public interface IProductService extends IService<Product> {
    List<Product> findByCategoryName(Long id);

}
