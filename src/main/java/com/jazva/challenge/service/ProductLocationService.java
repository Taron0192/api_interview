package com.jazva.challenge.service;

import com.jazva.challenge.entity.ProductLocation;

import java.util.List;

public interface ProductLocationService {

    void addOrUpdateProductLocation(ProductLocation productLocation);

    List<ProductLocation> getAllByProductId(Integer productId);

    void updateCount(Integer productId, int count);
}
