package com.jazva.challenge.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class AddProductLocationDTO implements Serializable {

    @NotNull
    private Integer productId;

    @NotNull
    private Integer locationId;

    @NotNull
    private Integer count;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
