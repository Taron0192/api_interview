package com.jazva.challenge.service.impl;

import com.jazva.challenge.entity.Product;
import com.jazva.challenge.repository.ProductRepository;
import com.jazva.challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void addProduct(Product product) {
        productRepository.save(product);

    }

    @Override
    public Product get(Integer id) {
        return productRepository.getOne(id);
    }
}
