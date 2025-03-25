package mx.nach.price.service.service;

import lombok.RequiredArgsConstructor;
import mx.nach.price.service.entity.Price;
import mx.nach.price.service.repository.PriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceServiceImpl implements  PriceService{

    private final PriceRepository priceRepository;

    @Override
    public Optional<Price> getPriceByProductId(Long productId) {
        return priceRepository.findByProductId(productId);
    }

    @Override
    public Price createPrice(Price price) {
        return priceRepository.save(price);
    }

}
