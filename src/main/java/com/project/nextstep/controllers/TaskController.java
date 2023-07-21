package com.project.nextstep.controllers;

import com.project.nextstep.entity.Task;
import com.project.nextstep.entity.construction.TaskNeed;
import com.project.nextstep.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clients/{clientId}/constructions/{constructionId}/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getConstructionTask(
            @PathVariable("clientId") Long clientId,
            @PathVariable("constructionId") Long constructionId,
            @PathVariable("taskId") Long taskId
    ) throws IllegalAccessException {
        Task constructionTask =  taskService.getConstructionTask(clientId,constructionId,taskId);
        return ResponseEntity.ok(constructionTask);
    }


    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateConstructionTask(
            @PathVariable("clientId") Long clientId,
            @PathVariable("constructionId") Long constructionId,
            @PathVariable("taskId") Long taskId,
            @RequestBody Task task
    ) throws IllegalAccessException {
        Task updatedConstructionTask = taskService.updateConstructionTask(clientId,constructionId,taskId,task);
        return ResponseEntity.ok(updatedConstructionTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> deleteConstructionTask(
            @PathVariable("clientId") Long clientId,
            @PathVariable("constructionId") Long constructionId,
            @PathVariable("taskId") Long taskId
    ) throws IllegalAccessException {
        Task deletedConstructionTask = taskService.deleteConstructionTask(clientId,constructionId,taskId);
        return ResponseEntity.ok(deletedConstructionTask);
    }

    @PostMapping("/{taskId}")
    public ResponseEntity<TaskNeed> updateTaskNeed(
        @PathVariable("clientId") Long clientId,
        @PathVariable("constructionId") Long constructionId,
        @PathVariable("taskId") Long taskId,
        @RequestBody TaskNeed taskNeed
    ) throws IllegalAccessException {
        TaskNeed createdTaskNeed = taskService.updateTaskNeed(clientId,constructionId, taskId, taskNeed);
        return ResponseEntity.ok(createdTaskNeed);
    }

}
