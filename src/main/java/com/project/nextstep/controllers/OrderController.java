package com.project.nextstep.controllers;

import com.project.nextstep.entity.construction.Order;
import com.project.nextstep.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {


    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/suppliers/{supplierId}/orders")
    public List<Order> getAllSupplierOrders(
            @PathVariable("supplierId") Long supplierId
    ){
        List<Order> orders =  orderService.getAllSupplierOrders(supplierId);
        return orders;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        List<Order> orders =  orderService.getAllOrders();
        return orders;
    }

    @GetMapping("/clients/{clientId}/orders")
    public List<Order> getAllClientOrders(
            @PathVariable("clientId") Long clientId
    ){
        List<Order> orders =  orderService.getAllClientOrders(clientId);
        return orders;
    }

    @PostMapping("/tasks/{taskId}/orders/products/{productId}")
    public ResponseEntity<Order> createTaskOrder(
            @PathVariable("taskId") Long taskId,
            @PathVariable("productId") Long productId,
            @RequestBody Order order
            ) {
        Order createdOrder =  orderService.createTaskOrder(taskId, productId, order);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/tasks/{taskId}/orders/{orderId}/products/{productId}")
    public ResponseEntity<Order> updateTaskOrder(
            @PathVariable("taskId") Long taskId,
            @PathVariable("orderId") Long orderId,
            @PathVariable("productId") Long productId,
            @RequestBody Order order
            ) {
        Order updatedOrder =  orderService.updateTaskOrder(taskId,orderId,productId,order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("tasks/{taskId}/orders/{orderId}")
    public ResponseEntity<Order> deleteTaskOrder(
            @PathVariable("taskId") Long taskId,
            @PathVariable("orderId") Long orderId

    ) {
        Order deletedOrder =  orderService.deleteTaskOrder(taskId,orderId);
        return ResponseEntity.ok(deletedOrder);
    }


}
