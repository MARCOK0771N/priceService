package mx.nach.product.service.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.nach.product.service.dto.BasePrice;
import mx.nach.product.service.dto.Price;
import mx.nach.product.service.dto.Product;
import mx.nach.product.service.dto.ProductPrice;
import mx.nach.product.service.entity.ProductEntity;
import mx.nach.product.service.feign.PriceServiceFeign;
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

    private final PriceServiceFeign priceServiceFeign;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().map(this::toProduct).toList();
    }

    @Override
    public Optional<ProductPrice> getProductById(Long id) {

        BasePrice basePrice = Optional.ofNullable(priceServiceFeign.getPriceByProductId(id)).map(this::toBasePrice).orElseThrow(()-> new RuntimeException("price not found"));

        return productRepository.findById(id).map(product-> toProductPrice(product,basePrice));
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

    public BasePrice toBasePrice(Price price){
        return BasePrice.builder()
                .amount(price.getAmount())
                .currency(price.getCurrency())
                .build();
    }

    public ProductPrice toProductPrice(ProductEntity product, BasePrice basePrice){
        return ProductPrice.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(basePrice)
                .build();
    }
}
