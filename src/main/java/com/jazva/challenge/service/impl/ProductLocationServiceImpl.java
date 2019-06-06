package com.jazva.challenge.service.impl;

import com.jazva.challenge.entity.ProductLocation;
import com.jazva.challenge.repository.ProductLocationRepository;
import com.jazva.challenge.service.ProductLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductLocationServiceImpl implements ProductLocationService {

    @Autowired
    private ProductLocationRepository repository;

    @Transactional
    @Override
    public void addOrUpdateProductLocation(ProductLocation productLocation) {
        final ProductLocation pl = repository.findByProductAndLocation(productLocation.getProduct(), productLocation.getLocation());

        if (pl == null) {
            repository.save(productLocation);
        } else {
            pl.setCount(productLocation.getCount());
            repository.save(pl);
        }
    }

    @Override
    public List<ProductLocation> getAllByProductId(Integer productId) {
        return repository.findAllByProductId(productId);
    }

    @Transactional
    @Override
    public void updateCount(Integer productId, int count) {
        repository.updateCount(productId, count);
    }

}
