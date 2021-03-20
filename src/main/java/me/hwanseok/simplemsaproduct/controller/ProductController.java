package me.hwanseok.simplemsaproduct.controller;


import io.swagger.annotations.ApiModel;
import lombok.RequiredArgsConstructor;
import me.hwanseok.simplemsaproduct.entity.Product;
import me.hwanseok.simplemsaproduct.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id){
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product request){
        return productService.create(request);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@RequestBody Product request){
        return productService.update(request);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        return productService.delete(id);
    }
}
