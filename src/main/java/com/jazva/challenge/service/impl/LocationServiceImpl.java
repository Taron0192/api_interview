package com.jazva.challenge.service.impl;

import com.jazva.challenge.entity.Location;
import com.jazva.challenge.repository.LocationRepository;
import com.jazva.challenge.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Transactional
    @Override
    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public Location get(Integer id) {
        return locationRepository.getOne(id);
    }
}
