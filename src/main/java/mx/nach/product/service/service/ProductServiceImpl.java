package mx.nach.product.service.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.nach.product.service.dto.Product;
import mx.nach.product.service.entity.ProductEntity;
import mx.nach.product.service.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().map(this::toProduct).toList();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id).map(this::toProduct);
    }

    @Override
    public Product createProduct(Product product) {
        return toProduct(productRepository.save(ProductEntity.builder()
                .description(product.getDescription())
                .id(product.getId())
                .name(product.getName())
                .build()));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    return toProduct(productRepository.save(existingProduct));
                }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product toProduct(ProductEntity entity){
        return Product.builder()
                .description(entity.getDescription())
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

}
