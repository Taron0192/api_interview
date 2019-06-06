package com.jazva.challenge.service;

import com.jazva.challenge.entity.Product;

public interface ProductService {
    void addProduct(Product product);

    Product get(Integer id);
}
