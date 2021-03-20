package me.hwanseok.simplemsaproduct.controller;


import me.hwanseok.simplemsaproduct.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id){
        return Product.builder().name("defaultProduct").build();
    }
}
