package com.project.nextstep.services;

import com.project.nextstep.entity.Request;
import com.project.nextstep.entity.Task;
import com.project.nextstep.entity.accounts.Vendor;
import com.project.nextstep.repositories.RequestRepository;
import com.project.nextstep.services.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public RequestService(RequestRepository requestRepository, UserService userService, TaskService taskService) {
        this.requestRepository = requestRepository;
        this.userService = userService;
        this.taskService = taskService;
    }

    public List<Request> getAllVendorRequests(Long vendorId) {
        return requestRepository.findAllByVendor_Id(vendorId);
    }

    public Request creatTaskRequest(Long taskId, Long vendorId) {
        Request request = new Request();
        request.setTask(taskService.getTask(taskId));
        request.setVendor((Vendor) userService.getUser(vendorId));
        return  requestRepository.save(request);
    }

    public List<Request> getTaskRequests(Long taskId) {
        return requestRepository.findAllByTask_Id(taskId);
    }

    public Task acceptTaskRequest(Long requestId, Long taskId, Long vendorId) {
        taskService.assignTaskToVendor(taskId,vendorId);
        rejectTaskRequest(requestId);
        return taskService.getTask(taskId);
    }

    public void rejectTaskRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }
}
