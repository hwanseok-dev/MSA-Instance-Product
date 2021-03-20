package me.hwanseok.simplemsaproduct.controller;


import me.hwanseok.simplemsaproduct.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id){
        return Product.builder().name("defaultProduct").build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product request){
        // TODO Change return value from Product to createdProductId
        return Product.builder().id(request.getId()).name(request.getName()).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Product request){
        // TODO
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        // TODO
    }
}
