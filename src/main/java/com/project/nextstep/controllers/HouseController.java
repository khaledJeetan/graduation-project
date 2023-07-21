//package com.project.nextstep.controllers;
//
//import com.project.nextstep.entity.Budget;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/request_construction")
//public class HouseController {
//
//
//
//    @GetMapping
//    public ResponseEntity<Budget> getTaskBudget(
//            @PathVariable("taskId") Long taskId
//    ){
//        Budget budget =  budgetService.getTaskBudget(taskId);
//        return ResponseEntity.ok(budget);
//    }
//
//
//    @PostMapping
//    public ResponseEntity<Budget> createTaskBudget(
//            @PathVariable("taskId") Long taskId,
//            @RequestBody Budget budget
//    ) {
//        Budget createdBudget =  budgetService.createTaskBudget(taskId,budget);
//        return ResponseEntity.ok(createdBudget);
//    }
//
//    @PutMapping
//    public ResponseEntity<Budget> updateTaskBudget(
//            @PathVariable("taskId") Long taskId,
//            @RequestBody Budget budget
//    ) {
//        Budget updatedBudget =  budgetService.updateTaskBudget(taskId,budget);
//        return ResponseEntity.ok(updatedBudget);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Budget> deleteTaskBudget(
//            @PathVariable("taskId") Long taskId
//    ) {
//        Budget deletedBudget =  budgetService.deleteTaskBudget(taskId);
//        return ResponseEntity.ok(deletedBudget);
//    }
//}
