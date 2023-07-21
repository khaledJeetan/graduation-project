package com.project.nextstep.services;

import com.project.nextstep.entity.RatingReview;
import com.project.nextstep.entity.construction.Service;
import com.project.nextstep.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> getAllServices() {
        System.out.println("service:: getAllServices");
        return serviceRepository.findAll();
    }

    public List<Service> getFilteredServices(String serviceType,
                                     double minPrice,
                                     double maxPrice,
                                     double rating) {

        return serviceRepository.findServicesByNameAndPriceBetween(serviceType,minPrice,maxPrice)
                .stream()
                .filter(
                        service ->
                                service
                                        .getVendor()
                                        .getRatingReviews()
                                        .stream()
                                        .mapToDouble(RatingReview::getRating)
                                        .average().orElse(0.0) <= rating
                ).collect(Collectors.toList());
    }

}
