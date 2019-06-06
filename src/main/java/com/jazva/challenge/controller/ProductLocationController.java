package com.jazva.challenge.controller;

import com.jazva.challenge.dto.AddProductLocationDTO;
import com.jazva.challenge.dto.GetProductCountsDTO;
import com.jazva.challenge.entity.Location;
import com.jazva.challenge.entity.Product;
import com.jazva.challenge.entity.ProductLocation;
import com.jazva.challenge.service.LocationService;
import com.jazva.challenge.service.ProductLocationService;
import com.jazva.challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/inventory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductLocationController {

    @Autowired
    private ProductService productService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ProductLocationService productLocationService;

    @PostMapping
    public ResponseEntity addInventory(@Valid @RequestBody AddProductLocationDTO productLocation) {
        final Product product = productService.get(productLocation.getProductId());
        if (product == null) {
            return ResponseEntity.badRequest().body("Could not find product by id");
        }

        final Location location = locationService.get(productLocation.getLocationId());
        if (location == null) {
            return ResponseEntity.badRequest().body("Could not find location by id");
        }

        productLocationService.addOrUpdateProductLocation(new ProductLocation(product, location, productLocation.getCount()));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/productCounts/{productId}")
    public ResponseEntity<List<GetProductCountsDTO>> getProductLocationsByProductId(@PathVariable Integer productId) {
        final List<ProductLocation> productLocations = productLocationService.getAllByProductId(productId);
        final List<GetProductCountsDTO> getProductCountsDTOS = new ArrayList<>(productLocations.size());

        for (ProductLocation productLocation : productLocations) {
            GetProductCountsDTO dto = new GetProductCountsDTO(productLocation.getLocation().getName(), productLocation.getCount());
            getProductCountsDTOS.add(dto);
        }

        return ResponseEntity.ok().body(getProductCountsDTOS);
    }

    @GetMapping("/{productId}")
    public ResponseEntity getTotalInventoryProductInAllLocations(@PathVariable Integer productId) {
        final List<ProductLocation> productLocations = productLocationService.getAllByProductId(productId);

        int count = 0;
        for (ProductLocation productLocation : productLocations) {
            count += productLocation.getCount();
        }

        return ResponseEntity.ok().body(count);
    }

    @PostMapping("/reset/{productId}")
    public ResponseEntity updateCount(@PathVariable Integer productId) {
        final Product product = productService.get(productId);
        if (product == null) {
            return ResponseEntity.badRequest().body("Could not find product by id");
        }
        productLocationService.updateCount(productId, 0);
        return ResponseEntity.ok().build();
    }

}
