package com.project.nextstep.services;

import com.project.nextstep.entity.Task;
import com.project.nextstep.entity.construction.Order;
import com.project.nextstep.entity.construction.Product;
import com.project.nextstep.entity.enums.OrderStatus;
import com.project.nextstep.entity.enums.Status;
import com.project.nextstep.repositories.OrderRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService{

    private final TaskService taskService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(TaskService taskService, ProductService productService, OrderRepository orderRepository) {
        this.taskService = taskService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllSupplierOrders(Long supplierId) {
        return orderRepository.findAllByProduct_Supplier_Id(supplierId);
    }
    public List<Order> getAllClientOrders(Long clientId) {
        return orderRepository.findAllByTask_Construction_Client_Id(clientId);
    }

    public Order createTaskOrder(Long taskId, Long productId, Order order) {
        Product p = productService.getProduct(productId);
        order.setTask(taskService.getTask(taskId));
        order.setProduct(p);
        return orderRepository.save(order);
    }

    public Order deleteTaskOrder(Long taskId, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ObjectNotFoundException("Order",orderId)
        );
        taskService.getTask(taskId).getOrder().remove(order);
        orderRepository.deleteById(orderId);
        return order;
    }

    @Transactional
    public Order updateTaskOrder(Long taskId,Long orderId,Long productId, Order order) {
        Task task = taskService.getTask(taskId);
        Product product = productService.getProduct(productId);
        Order oldOrder = orderRepository.findById(orderId).orElseThrow(
                () -> new ObjectNotFoundException("Order",orderId)
        );
        oldOrder.setTask(task);
        oldOrder.setProduct(product);
        oldOrder.setQuantity(order.getQuantity());
        oldOrder.setStatus(order.getStatus());

        return oldOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
