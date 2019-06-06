package com.jazva.challenge.service;

import com.jazva.challenge.entity.Location;

public interface LocationService {
    void addLocation(Location location);

    Location get(Integer id);
}
