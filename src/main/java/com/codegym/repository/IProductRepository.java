package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    //Show category
    List<Product> findAllByCategory(Category category);

    Page<Product> findAllByOrderByIdAsc(Pageable pageable);

    //Tìm kiếm sản phẩm theo tên
    @Query(value = "select  * from product where product.name like ?", nativeQuery = true)
    List<Product> findProductName(String name);

    //Tìm kiếm sản phẩm theo category
    @Query(value = "select * from product where product.category_id = ?", nativeQuery = true)
    List<Product> findProductByCategoryName(Long id);

    //Top5 Product Newest
    List<Product> findTop5ByOrderByDatetimeDesc();

//    @Query(value = "select * from product order by date desc limit ?", nativeQuery = true)
//    List<Product> findTop5ByOrderByDatetimeDesc(int num);

    //Top5 price max
//    List<Product> findTop5ByOrderByPriceDesc();

    @Query(value = "select * from product order by price desc limit ?", nativeQuery = true)
    List<Product> findTop5ByOrderByPriceDesc(int num);


}
