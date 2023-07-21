//package com.project.nextstep.services;
//
//import com.project.nextstep.entity.Task;
//import com.project.nextstep.entity.accounts.Vendor;
//import com.project.nextstep.entity.construction.TaskNeed;
//import com.project.nextstep.repositories.TaskNeedRepository;
//import com.project.nextstep.services.account.UserService;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class TaskNeedService {
//
//    private final TaskNeedRepository taskNeedRepository;
//    private final UserService userService;
//    private final TaskService taskService;
//    @Autowired
//    public TaskNeedService(TaskNeedRepository taskNeedRepository, UserService userService, TaskService taskService) {
//        this.taskNeedRepository = taskNeedRepository;
//        this.userService = userService;
//        this.taskService = taskService;
//    }
//
//    public List<TaskNeed> getTaskNeedList(Long vendorId, Long taskId) throws IllegalAccessException {
//        Vendor vendor = (Vendor) userService.getUser(vendorId);
//        Task task = taskService.getTask(taskId);
//        if(task.getVendor().getId().equals(vendor.getId())){
//            throw new IllegalAccessException("Vendor Has No Access To this Task.");
//        }
//        return taskNeedRepository.findAllByTask_Id(taskId);
//    }
//
//    @Transactional
//    public TaskNeed updateTaskNeed(Long vendorId, Long taskId, Long taskNeedId, TaskNeed taskNeed) throws IllegalAccessException {
//        Vendor vendor = (Vendor) userService.getUser(vendorId);
//        Task task = taskService.getTask(taskId);
//        if(task.getVendor().getId().equals(vendor.getId())){
//            throw new IllegalAccessException("Vendor Has No Access To this Task.");
//        }
//        TaskNeed oldTaskNeed = taskNeedRepository.findById(taskNeedId).orElseThrow(
//                () -> new EntityNotFoundException("No TaskNeed Found for Id: "+taskNeedId)
//        );
//        oldTaskNeed.setName(taskNeed.getName());
//        oldTaskNeed.setQuantity(taskNeed.getQuantity());
//        return oldTaskNeed;
//    }
//
//    public TaskNeed createTaskNeed(Long vendorId, Long taskId, TaskNeed taskNeed) throws IllegalAccessException {
//        Vendor vendor = (Vendor) userService.getUser(vendorId);
//        Task task = taskService.getTask(taskId);
//        if(task.getVendor().getId().equals(vendor.getId())){
//            throw new IllegalAccessException("Vendor Has No Access To this Task.");
//        }
//        return taskNeedRepository.save(taskNeed);
//    }
//
//    public void deleteTaskNeed(Long vendorId, Long taskId, Long taskNeedId) throws IllegalAccessException {
//        Vendor vendor = (Vendor) userService.getUser(vendorId);
//        Task task = taskService.getTask(taskId);
//        if(task.getVendor().getId().equals(vendor.getId())){
//            throw new IllegalAccessException("Vendor Has No Access To this Task.");
//        }
//         taskNeedRepository.deleteById(taskNeedId);
//    }
//
//}
