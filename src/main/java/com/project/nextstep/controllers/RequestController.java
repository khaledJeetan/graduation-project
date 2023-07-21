package com.project.nextstep.controllers;


import com.project.nextstep.entity.Request;
import com.project.nextstep.entity.Task;
import com.project.nextstep.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("vendors/{vendorId}/requests")
    public List<Request> getAllVendorRequests(
            @PathVariable("vendorId") Long vendorId){
        return requestService.getAllVendorRequests(vendorId);
    }

    @PostMapping("tasks/{taskId}/requests/vendors/{vendorId}")
    public ResponseEntity<Request> createTaskRequest(
            @PathVariable("taskId") Long taskId,
            @PathVariable("vendorId") Long vendorId
    ){
        Request createdRequest = requestService.creatTaskRequest(taskId,vendorId);
        return ResponseEntity.ok(createdRequest);
    }

    @GetMapping("tasks/{taskId}/requests")
    public List<Request> getTaskRequests(
            @PathVariable("taskId") Long taskId
    ){
        return requestService.getTaskRequests(taskId);
    }

    @PutMapping("tasks/{taskId}/requests/{requestId}/vendors/{vendorId}")
    public ResponseEntity<Task> acceptTaskRequest(
            @PathVariable("taskId") Long taskId,
            @PathVariable("vendorId") Long vendorId,
            @PathVariable("requestId") Long requestId
    ){
        Task acceptTaskRequest = requestService.acceptTaskRequest(requestId,taskId,vendorId);
        return ResponseEntity.ok(acceptTaskRequest);
    }

    @DeleteMapping("/requests/{requestId}")
    public ResponseEntity<String> rejectTaskRequest(
            @PathVariable("requestId") Long requestId
    ){
         requestService.rejectTaskRequest(requestId);
        return ResponseEntity.ok("Request Deleted Successfully");
    }


}
