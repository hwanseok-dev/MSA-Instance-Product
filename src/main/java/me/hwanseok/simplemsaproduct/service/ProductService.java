package me.hwanseok.simplemsaproduct.service;

import lombok.RequiredArgsConstructor;
import me.hwanseok.simplemsaproduct.entity.Product;
import me.hwanseok.simplemsaproduct.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product create(Product request){
        return productRepository.save(
                Product.builder()
                        .name(request.getName())
                        .build());
    }

    public Product update(Long id, Product request){
        Optional<Product> prev = productRepository.findById(id);
        if(prev.isPresent()){
            Product product = prev.get();
            product.setName(request.getName());
            return productRepository.save(product);
        }else{
            return null;
        }
    }

    public HttpStatus delete(Long id){
        Optional<Product> prev = productRepository.findById(id);
        if(prev.isPresent()){
            productRepository.delete(prev.get());
            return HttpStatus.OK;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

}
