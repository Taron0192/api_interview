package com.jazva.challenge.dto;

public class GetProductCountsDTO {

    private String location;

    private Integer count;

    public GetProductCountsDTO(String location, Integer count) {
        this.location = location;
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
