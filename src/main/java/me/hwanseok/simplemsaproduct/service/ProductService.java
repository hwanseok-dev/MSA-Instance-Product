package me.hwanseok.simplemsaproduct.service;

import io.swagger.annotations.ResponseHeader;
import lombok.RequiredArgsConstructor;
import me.hwanseok.simplemsaproduct.exception.ProductConstraintViolationException;
import me.hwanseok.simplemsaproduct.exception.ProductNotFoundException;
import me.hwanseok.simplemsaproduct.model.Product;
import me.hwanseok.simplemsaproduct.model.dto.request.ProductRequestDto;
import me.hwanseok.simplemsaproduct.model.dto.response.ProductResponseDto;
import me.hwanseok.simplemsaproduct.repository.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ResponseEntity<ProductResponseDto> findById(Long id) {
        Optional<Product> option = productRepository.findById(id);
        return option.map(product -> ResponseEntity
                .status(HttpStatus.OK)
                .body(ProductResponseDto.builder()
                        .id(product.getId())
                        .description(product.getDescription()).build()))
                .orElseThrow(ProductNotFoundException::new);
    }

    public ResponseEntity<ProductResponseDto> create(ProductRequestDto request) {
        Product newProduct = productRepository.save(
                Product.builder()
                        .description(request.getDescription())
                        .build());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductResponseDto.builder()
                        .id(newProduct.getId())
                        .description(newProduct.getDescription())
                        .build()
                );
    }

    public ResponseEntity<ProductResponseDto> update(ProductRequestDto request) {
        Optional<Product> option = productRepository.findById(request.getId());
        return option.map(product -> {
            product.setDescription(request.getDescription());
            productRepository.save(product);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ProductResponseDto.builder()
                            .id(product.getId())
                            .description(product.getDescription())
                            .build());
        })
                .orElseThrow(ProductNotFoundException::new);
    }

    public ResponseEntity<String> delete(Long id) {
        Optional<Product> option = productRepository.findById(id);
        try {
            if (option.isPresent()) {
                productRepository.deleteById(option.get().getId());
                return ResponseEntity.ok().body("Successfully deleted");
            } else {
                throw new ProductNotFoundException();
            }
        }catch (DataIntegrityViolationException e){
            throw new ProductConstraintViolationException();
        }

    }

}
