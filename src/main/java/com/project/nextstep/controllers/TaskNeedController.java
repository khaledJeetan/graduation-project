//package com.project.nextstep.controllers;
//
//
//import com.project.nextstep.entity.construction.TaskNeed;
//import com.project.nextstep.services.TaskNeedService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/vendors/{vendorId}/tasks/{taskId}")
//public class TaskNeedController {
//
//    private final TaskNeedService taskNeedService;
//
//    @Autowired
//    public TaskNeedController(TaskNeedService taskNeedService) {
//        this.taskNeedService = taskNeedService;
//    }
//
//
//    @GetMapping
//    public ResponseEntity<List<TaskNeed>> getTaskNeeds(
//            @PathVariable("vendorId") Long vendorId,
//            @PathVariable("taskId") Long taskId
//    ) throws IllegalAccessException {
//        List<TaskNeed> taskNeedList = taskNeedService.getTaskNeedList(vendorId, taskId);
//        return ResponseEntity.ok(taskNeedList);
//    }
//
//
//    @PutMapping("/{taskNeedId}")
//    public ResponseEntity<TaskNeed> updateTaskNeed(
//            @PathVariable("vendorId") Long vendorId,
//            @PathVariable("taskId") Long taskId,
//            @PathVariable("taskNeedId") Long taskNeedId,
//            @RequestBody TaskNeed taskNeed
//    ) throws IllegalAccessException {
//        TaskNeed updatedTaskNeed = taskNeedService.updateTaskNeed(vendorId,taskId, taskNeedId, taskNeed);
//        return ResponseEntity.ok(updatedTaskNeed);
//    }
//
//    @PostMapping
//    public ResponseEntity<TaskNeed> createTaskNeed(
//            @PathVariable("vendorId") Long vendorId,
//            @PathVariable("taskId") Long taskId,
//            @RequestBody TaskNeed taskNeed
//    ) throws IllegalAccessException {
//        TaskNeed createdTaskNeed = taskNeedService.createTaskNeed(vendorId, taskId, taskNeed);
//        return ResponseEntity.ok(createdTaskNeed);
//    }
//
//    @DeleteMapping("/{taskNeedId}")
//    public ResponseEntity<String> deleteTaskNeed(
//            @PathVariable("vendorId") Long vendorId,
//            @PathVariable("taskId") Long taskId,
//            @PathVariable("taskNeedId") Long taskNeedId
//    ) throws IllegalAccessException {
//        taskNeedService.deleteTaskNeed(vendorId, taskId, taskNeedId);
//        return ResponseEntity.ok("TaskNeed Deleted Successfully");
//    }
//
//}
