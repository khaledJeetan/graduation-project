package com.project.nextstep.services.account;

import com.project.nextstep.entity.Task;
import com.project.nextstep.entity.accounts.Vendor;
import com.project.nextstep.entity.construction.Service;
import com.project.nextstep.entity.construction.TaskNeed;
import com.project.nextstep.repositories.account.VendorRepository;
import com.project.nextstep.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class VendorService {
    private UserService userService;
    private TaskService taskService;
    private VendorRepository vendorRepository;

    @Autowired
    public VendorService(UserService userService, TaskService taskService, VendorRepository vendorRepository) {
        this.userService = userService;
        this.taskService = taskService;
        this.vendorRepository = vendorRepository;
    }

    @Transactional
    public Service addService(String email, Service service) {
        Vendor vendor = (Vendor) userService.getUser(email);
        vendor.setService(service);
        service.setVendor(vendor);
        return vendor.getService();
    }

    @Transactional
    public Service updateService(String email, Service service) {
        Vendor vendor = (Vendor) userService.getUser(email);
        Service oldService = vendor.getService();
        oldService.setDescription(service.getDescription());
        oldService.setPrice(service.getPrice());
        oldService.setUnit(service.getUnit());
        oldService.setName(service.getName());
        return vendor.getService();
    }

    @Transactional
    public TaskNeed addTaskNeed(Long vendorId, Long taskId, TaskNeed taskNeed) throws IllegalAccessException {
        Vendor vendor = (Vendor) userService.getUser(vendorId);
        Task task = taskService.getTask(taskId);
//        if(task.getVendor().getId().equals(vendor.getId())){
//            throw new IllegalAccessException("Vendor Has No Access To this Task.");
//        }
        if(task.getTaskNeeds().isEmpty()){
            task.setProgress(task.getProgress()+25);
        }
        return task.getTaskNeeds().add(taskNeed) ? taskNeed : null;
    }

    @Transactional
    public TaskNeed deleteTaskNeed(Long vendorId, Long taskId, TaskNeed taskNeed) throws IllegalAccessException {
        Vendor vendor = (Vendor) userService.getUser(vendorId);
        Task task = taskService.getTask(taskId);
        if(task.getVendor().getId().equals(vendor.getId())){
            throw new IllegalAccessException("Vendor Has No Access To this Task.");
        }
        return task.getTaskNeeds().remove(taskNeed) ? taskNeed : null;
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public List<Task> retrieveAllVendorTasks(Long vendorId) {
        List<Task> tasks = taskService.getAllVendorTasks(vendorId);
        tasks.forEach(task -> System.out.println(task.getVendor().getEmail()+ "\n"+task.getId()) );
        return tasks;
    }
}
