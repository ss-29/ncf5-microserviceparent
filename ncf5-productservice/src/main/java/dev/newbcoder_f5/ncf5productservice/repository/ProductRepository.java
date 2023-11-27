package dev.newbcoder_f5.ncf5productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.newbcoder_f5.ncf5productservice.model.Product;
import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByName(String name);
    
}
