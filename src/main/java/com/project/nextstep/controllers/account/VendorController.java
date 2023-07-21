package com.project.nextstep.controllers.account;

import com.project.nextstep.entity.RatingReview;
import com.project.nextstep.entity.Task;
import com.project.nextstep.entity.accounts.Vendor;
import com.project.nextstep.entity.construction.Service;
import com.project.nextstep.entity.construction.TaskNeed;
import com.project.nextstep.services.RatingReviewService;
import com.project.nextstep.services.account.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;
    private final RatingReviewService reviewService;

    @Autowired
    public VendorController(VendorService vendorService, RatingReviewService reviewService) {
        this.vendorService = vendorService;
        this.reviewService = reviewService;
    }


    @GetMapping
    public List<Vendor> retrieveAllVendors() {
        return vendorService.getAllVendors();

    }


    @GetMapping("/{vendorId}/tasks")
    public List<Task> retrieveAllVendorTasks(
            @PathVariable("vendorId") Long vendorId) {
        return vendorService.retrieveAllVendorTasks(vendorId);

    }


    @PostMapping("/{email}/services")
    public ResponseEntity<Service> createServiceForVendor(
            @PathVariable String email,
            @RequestBody Service service
    ) {
        Service createdService = vendorService.addService(email,service);
        return ResponseEntity.ok().body(createdService);

    }


    @PutMapping("/{email}/services")
    public ResponseEntity<Service> updateServiceForVendor(
            @PathVariable String email,
            @RequestBody Service service
    ){
        Service updatedService = vendorService.updateService(email,service);
        return ResponseEntity.ok().body(updatedService);
    }


    @GetMapping("/{vendorId}/reviews")
    public ResponseEntity<List<RatingReview>> getAllReviews(
            @PathVariable("vendorId") Long vendorId
    ) {
        List<RatingReview> reviews = reviewService.getAllVendorReviews(vendorId);
        return ResponseEntity.ok().body(reviews);
    }


    @PostMapping("/vendors/{vendorId}/tasks/{taskId}")
    public ResponseEntity<TaskNeed> createTaskNeed(
            @PathVariable("vendorId") Long vendorId,
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskNeed taskNeed
    ) throws IllegalAccessException {
        TaskNeed createdTaskNeed = vendorService.addTaskNeed(vendorId, taskId, taskNeed);
        return ResponseEntity.ok(createdTaskNeed);
    }


    @DeleteMapping("/vendors/{vendorId}/tasks/{taskId}")
    public ResponseEntity<TaskNeed> deleteTaskNeed(
            @PathVariable("vendorId") Long vendorId,
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskNeed taskNeed
    ) throws IllegalAccessException {
        TaskNeed createdTaskNeed = vendorService.deleteTaskNeed(vendorId, taskId, taskNeed);
        return ResponseEntity.ok(createdTaskNeed);
    }

}
