package com.jazva.challenge.repository;

import com.jazva.challenge.entity.Location;
import com.jazva.challenge.entity.Product;
import com.jazva.challenge.entity.ProductLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductLocationRepository extends JpaRepository<ProductLocation, Integer> {


    ProductLocation findByProductAndLocation(Product product, Location location);

    List<ProductLocation> findAllByProductId(Integer productId);

    @Query(value = "UPDATE PRODUCT_LOCATION SET COUNT = :count WHERE PRODUCT_ID = :productId",nativeQuery = true)
    @Modifying
    void updateCount(Integer productId, int count);

}
