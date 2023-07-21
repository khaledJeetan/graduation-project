package com.project.nextstep.controllers;

import com.project.nextstep.entity.construction.Service;
import com.project.nextstep.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService service;

    @Autowired
    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Service> retrieveAllServices(){
        System.out.println("Service Controller:: retrieveAllServices() ");
        return service.getAllServices();
    }

    @GetMapping("/filter")
    public List<Service> retrieveServices(
            @RequestParam("Type") String serviceType,
            @RequestParam(value = "minPrice", defaultValue = "0.0",required = false) double minPrice,
            @RequestParam(value = "minPrice", defaultValue = "999999999999.99",required = false) double maxPrice,
            @RequestParam(value = "rating", defaultValue = "0.0",required = false) double rating
    ){
        ;
        return service.getFilteredServices(serviceType,minPrice,maxPrice,rating);
    }

}
