package com.vuhien.application.repository;

import com.vuhien.application.entity.Product;
import com.vuhien.application.model.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findAllByName(String name);

    @Query("select count(p.productId) from Product p")
    long getTotalProducts();

    @Query(value = "Select * from Product order by create_date desc limit 6",nativeQuery = true)
    List<Product> getListProductNew();

    @Query("SELECT p FROM Product p " +
            "WHERE (:categoryId IS NULL OR (p.category.categoryId = :categoryId))" +
            "AND (:productName IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")
    Page<Product> getListProductByCategoryOrProductNameContaining(Pageable pageable, @Param("categoryId") Integer categoryId, @Param("productName") String productName);
}
