package com.project.nextstep.services;

import com.project.nextstep.entity.*;
import com.project.nextstep.entity.accounts.Client;
import com.project.nextstep.entity.enums.HouseFinishingTask;
import com.project.nextstep.entity.enums.Role;
import com.project.nextstep.entity.enums.Status;
import com.project.nextstep.repositories.ConstructionRepository;
import com.project.nextstep.services.account.UserService;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConstructionService {

    private final ConstructionRepository constructionRepository;
    private final UserService userService;
    private final HouseService houseService;

    @Autowired
    public ConstructionService(ConstructionRepository constructionRepository, UserService userService, HouseService houseService) {
        this.constructionRepository = constructionRepository;
        this.userService = userService;
        this.houseService = houseService;
    }

    public Page<Construction> getAllConstructions(Long clientId, Pageable pageable) {
        userService.getUser(clientId);
        return constructionRepository.findAllByClientId(clientId,pageable);
    }

    public Construction getConstruction(Long clientId, Long constructionId) throws IllegalAccessException {
        Client client = (Client) userService.getUser(clientId);
        Construction construction = constructionRepository.findById(constructionId).orElseThrow(
                () -> new IllegalStateException("Construction with id = "+constructionId+" doesn't exisits")
        );
        if(!client.equals(construction.getClient())){
            throw new IllegalAccessException("Client Has No Accesss to the provided Construction");
        }
        return construction;
    }

    @Transactional
    public Construction createConstruction(Long clientId, ConstructionDTO constructionDTO) {
        Client client = (Client) userService.getUser(clientId);
        House savedHouse = houseService.addHouse(constructionDTO.getHouse());
        Construction customConstruction = Construction
                .builder()
                .client(client)
                .startDate(savedHouse.getStartDate())
                .status(Status.WAITING)
                .progress(0)
                .endDate(savedHouse.getStartDate().plusYears(1))
                .house(savedHouse)
                .isCustom(constructionDTO.getPlan() != null )
                .build();
        customConstruction.setPlan(generateConstructionPlan(customConstruction,constructionDTO.getPlan()));
        client.getConstructionList().add(customConstruction);
        customConstruction.setClient(client);
        constructionRepository.save(customConstruction);
        return customConstruction;
    }

    @Transactional
    public Construction updateConstruction(Long clientId, Long constructionId, Construction construction) throws IllegalAccessException {
        Construction oldConstruction = getConstruction(clientId,constructionId);
        oldConstruction.setStartDate(construction.getStartDate());
        oldConstruction.setEndDate(construction.getEndDate());
        oldConstruction.setProgress(construction.getProgress());
        oldConstruction.setStatus(construction.getStatus());
        oldConstruction.setPlan(construction.getPlan());
        oldConstruction.setClient(construction.getClient());
        return oldConstruction;
    }

    public Construction deleteConstruction(Long clientId, Long constructionId) throws IllegalAccessException {
        Construction deletedConstruction = getConstruction(clientId,constructionId);
        constructionRepository.deleteById(constructionId);
        return deletedConstruction;
    }


    public Construction requestConstruction(Long clientId, House house) {
        return createConstruction(clientId,new ConstructionDTO(house,null));
    }


    // here there is some error. I realized that there is NO Task List
    // The Steps are clear 1,2,3,...
    // but each step has list of tasks
    private Map<String, StepTaskList> generateConstructionPlan(Construction construction,Map<HouseFinishingTask,Boolean> plan) {
        Map<String, StepTaskList> map = new HashMap<>();
        map.put("STEP 1", getTaskList(1, construction, plan));
        map.put("STEP 2", getTaskList(2, construction, plan));
        map.put("STEP 3", getTaskList(3, construction, plan));
        map.put("STEP 4", getTaskList(4, construction, plan));
        map.put("STEP 5", getTaskList(5, construction, plan));
        map.put("STEP 6", getTaskList(6, construction, plan));


        Iterator<Map.Entry<String, StepTaskList>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, StepTaskList> entry = iterator.next();
            if (entry.getValue()==null) {
                iterator.remove(); // Safely remove the entry
            }
        }
        return map;
    }

    private StepTaskList getTaskList(int stepNumber,Construction construction,Map<HouseFinishingTask,Boolean> plan) {

        System.out.println("\n\nfunction 1 ****************\n\n*************");
        List<HouseFinishingTask> stepTasks;

        if(plan == null) {
            stepTasks = Arrays.stream(HouseFinishingTask
                            .values())
                    .filter(houseFinishingStep -> houseFinishingStep.getOrder() == stepNumber)
                    .collect(Collectors.toList());
        }else{
            stepTasks = plan
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() && entry.getKey().getOrder() == stepNumber)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }

       List<Task> taskList = new ArrayList<>();
        for (HouseFinishingTask houseTask :
                stepTasks) {
            Task t = new Task();
            t.setTaskName(houseTask);
            t.setStatus(Status.WAITING);
            t.setProgress(0);
            t.setStartDate(construction.getStartDate().plusMonths(stepNumber-1 * 2));
            // this should be null and not set
            // because the Vendor/worker should assign the end date
            t.setEndDate(construction.getStartDate().plusMonths(2));
            t.setConstruction(construction);
            taskList.add(t);
        }

        if(taskList.isEmpty()){
            System.out.println("\n\nSending Null  **********************\n\n");
            return null;
        }
       StepTaskList stepTaskList = new StepTaskList();
       stepTaskList.setTasks(taskList);
        System.out.println("\n\nSending Normal value  **********************\n\n");

        return stepTaskList;
    }

    public Map<String, StepTaskList> getConstructionTaskList(Long clientId, Long constructionId) throws IllegalAccessException {
        return getConstruction(clientId,constructionId).getPlan();
    }

    public House updateConstructionHouse(Long clientId, Long houseId, House house) {
        return userService.getUser(clientId).getRole().equals(Role.CLIENT) ? houseService.updateHouse(houseId,house) : null;
    }
}
