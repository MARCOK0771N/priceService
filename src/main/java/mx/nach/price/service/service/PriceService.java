package mx.nach.price.service.service;

import mx.nach.price.service.entity.Price;

import java.util.Optional;

public interface PriceService {

    Optional<Price> getPriceByProductId(Long productId);

    Price createPrice(Price price);
}
