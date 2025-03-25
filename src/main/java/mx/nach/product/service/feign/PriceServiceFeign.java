package mx.nach.product.service.feign;

import mx.nach.product.service.dto.Price;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "priceServicesClient", url = "http://localhost:8080/api/prices")
public interface PriceServiceFeign {

    @GetMapping(value = "/{productId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    Price getPriceByProductId(@PathVariable Long productId);

}
