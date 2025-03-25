package mx.nach.product.service.service;

import mx.nach.product.service.dto.Product;
import mx.nach.product.service.dto.ProductPrice;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<ProductPrice> getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
