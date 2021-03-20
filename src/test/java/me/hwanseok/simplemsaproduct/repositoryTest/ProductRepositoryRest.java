package me.hwanseok.simplemsaproduct.repositoryTest;

import me.hwanseok.simplemsaproduct.config.JpaConfig;
import me.hwanseok.simplemsaproduct.entity.Product;
import me.hwanseok.simplemsaproduct.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("ItemRepositoryTest 테스트")
@Import({JpaConfig.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Product Create Test")
    public void create() {
        for (int i = 0; i < 30; i++) {
            Product product = new Product();
            product.setName("TestName"+i);
            Product newItem = productRepository.save(product);
            System.out.println(newItem);
            Assertions.assertNotNull(newItem);
        }
    }

    @Test
    public void read() {
        Long id = 473L;
        Optional<Product> product = productRepository.findById(id);
        Assertions.assertTrue(product.isPresent());
    }

    @Test
    @Transactional
    public void update() {
        Optional<Product> product = productRepository.findById(2L);
        product.ifPresent(selectedProduct -> {
            selectedProduct.setName("changedName");
            productRepository.save(selectedProduct);
        });
    }

    @Test
    @Transactional
    public void delete() {
        Long id = 3L;
        Optional<Product> product = productRepository.findById(id);
        Assertions.assertTrue(product.isPresent());    // false
        product.ifPresent(selectUser -> {
            productRepository.delete(selectUser);
        });
        Optional<Product> deleteProduct = productRepository.findById(3L);
        Assertions.assertFalse(deleteProduct.isPresent()); // false
    }

}
