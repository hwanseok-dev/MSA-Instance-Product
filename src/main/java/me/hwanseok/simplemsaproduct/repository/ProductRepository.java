package me.hwanseok.simplemsaproduct.repository;

import me.hwanseok.simplemsaproduct.entity.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
