package mx.nach.price.service.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mx.nach.price.service.entity.Price;
import mx.nach.price.service.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/api/prices")
@EnableWebMvc
@RequiredArgsConstructor
@Tag(name = "price of products resources")
public class PriceController {

    private final PriceService priceService;

    @Operation(summary = "get price with idProduct")
    @GetMapping(value = "/{productId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Price> getPriceByProductId(@PathVariable Long productId) {
        return priceService.getPriceByProductId(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "post create a price of product with id")
    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Price createPrice(@RequestBody Price price) {
        return priceService.createPrice(price);
    }
}