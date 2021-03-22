package me.hwanseok.simplemsaproduct.controller;


import io.swagger.annotations.ResponseHeader;
import lombok.RequiredArgsConstructor;
import me.hwanseok.simplemsaproduct.model.Product;
import me.hwanseok.simplemsaproduct.model.dto.request.ProductRequestDto;
import me.hwanseok.simplemsaproduct.model.dto.response.ProductResponseDto;
import me.hwanseok.simplemsaproduct.service.ProductService;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable("id") Long id){
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto request){
        return productService.create(request);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponseDto> update(@RequestBody ProductRequestDto request){
        return productService.update(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return productService.delete(id);
    }
}
