package com.project.nextstep.services;

import com.project.nextstep.entity.Construction;
import com.project.nextstep.entity.Notification;
import com.project.nextstep.entity.StepTaskList;
import com.project.nextstep.entity.Task;
import com.project.nextstep.entity.accounts.Vendor;
import com.project.nextstep.entity.construction.TaskNeed;
import com.project.nextstep.entity.enums.NotificationType;
import com.project.nextstep.entity.enums.Status;
import com.project.nextstep.repositories.TaskRepository;
import com.project.nextstep.services.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ConstructionService constructionService;
    private final UserService userService;
    private final NotificationService notificationService;

    @Autowired
    public TaskService(TaskRepository taskRepository, ConstructionService constructionService, UserService userService, NotificationService notificationService) {
        this.taskRepository = taskRepository;
        this.constructionService = constructionService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public Map<String, StepTaskList>  getConstructionPlan(Long clientId, Long constructionId) throws IllegalAccessException {
        return constructionService
                .getConstruction(clientId,constructionId)
                .getPlan();
    }

    public Task getConstructionTask(Long clientId, Long constructionId, Long taskId) throws IllegalAccessException {
        constructionService.getConstruction(clientId,constructionId);
        return taskRepository
                .findById(taskId)
                .orElseThrow(
                        () -> new IllegalStateException(
                                "Task with Id " + taskId + " doesn't Exists"
                        )
                );
    }

    public Task getTask(Long id){
        return taskRepository
                .findById(id)
                .orElseThrow(
                () ->
                        new IllegalStateException(
                                "Task with ID = " + id + " doesn't exists"
                        )
        );
    }

    @Transactional
    public Task updateConstructionTask(Long clientId, Long constructionId, Long taskId, Task task) throws IllegalAccessException {
        Task oldTask = getConstructionTask(clientId,constructionId,taskId);
        oldTask.setStartDate(task.getStartDate());
        oldTask.setEndDate(task.getEndDate());
        oldTask.setBudget(task.getBudget());
        oldTask.setProgress(task.getProgress());
        oldTask.setStatus(task.getStatus());
        if(oldTask.getProgress()==75 && oldTask.getStatus().equals(Status.COMPLETED)) {
            oldTask.setProgress(oldTask.getProgress() + 25);
        }else if(oldTask.getStatus().equals(Status.COMPLETED)){
            oldTask.setProgress(100);
        }
        constructionService
                .getConstruction(clientId,constructionId)
                .setProgress(taskRepository.calculateConstructionProgress(constructionId));
        return oldTask;
    }

    public Task deleteConstructionTask(Long clientId, Long constructionId, Long taskId) throws IllegalAccessException {
        Task deletedTask = getConstructionTask(clientId,constructionId,taskId);
        taskRepository.deleteById(taskId);
        return deletedTask;
    }

    public void assignTaskToVendor(Map<Long,Long> taskVendor ){
       List<Task> taskList = taskRepository.findAllById(taskVendor.keySet());
       List<Vendor> vendorList = userService.getAllVendorsById(taskVendor.values().stream().toList());
       taskVendor.forEach(
               (taskId,vendorId) ->
                       taskList
                               .stream()
                               .filter(
                                       task ->
                                               task.getId().equals(taskId)
                               )
                               .findFirst()
                               .orElseThrow(
                                       () -> new IllegalStateException(
                                               "Error finding task in the list!!"
                                       )
                               )
                               .setVendor(
                                       vendorList
                                               .stream()
                                               .filter(vendor -> vendor.getId().equals(vendorId))
                                               .findFirst()
                                               .orElseThrow(
                                                       () -> new IllegalStateException(
                                                               "Error finding Vendor in the list!!"
                                                       )
                                               )
                               )
       );

    }

    @Transactional
    public void assignTaskToVendor( Long taskId,Long vendorId ){
        Task task = getTask(taskId);
        Vendor vendor = (Vendor) userService.getUser(vendorId);
        task.setVendor(vendor);
        task.setProgress(task.getProgress()+25);

        Notification notification = Notification.builder()
                .type(NotificationType.TASK_REQUEST)
                .url(task.getConstruction().getClient().getUrl())
                .message(task.getConstruction().getClient().getFirstName() + " wants You to work on his Task")
                .user(vendor)
                .build();
        notificationService.createUserNotification(vendorId,notification);
    }

    @Transactional
    public TaskNeed updateTaskNeed(Long clientId, Long constructionId, Long taskId, TaskNeed taskNeed) throws IllegalAccessException {
        Task task = getTask(taskId);
        Construction construction = constructionService.getConstruction(clientId,constructionId);
//        task.getTaskNeeds().get(task.getTaskNeeds().indexOf(taskNeed)).setStatus(taskNeed.getStatus());
        task.getTaskNeeds()
                .stream()
                .filter(taskNeed1 ->
                        taskNeed1.getName().matches(taskNeed.getName())
                                &&
                        taskNeed1.getQuantity()== taskNeed.getQuantity()
                )
                .forEach(taskNeed1 -> taskNeed1.setStatus(taskNeed.getStatus()));

        //if the taskNeed List is empty then
        boolean isTaskNeedListCompleted = task.getTaskNeeds()
                .stream()
                .filter(
                        taskNeed1 -> !taskNeed1.getStatus().equals(Status.COMPLETED)
                ).collect(Collectors.toList()).isEmpty();

        if (isTaskNeedListCompleted) {
            task.setProgress(task.getProgress() + 25);
        }
        return taskNeed;
    }

    public List<Task> getAllVendorTasks(Long vendorId) {
        return taskRepository.findAllByVendor_Id(vendorId);
    }
}
