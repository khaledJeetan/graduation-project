package com.project.nextstep.controllers;

import com.project.nextstep.entity.*;
import com.project.nextstep.services.ConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients/{clientId}/constructions")
public class ConstructionController {

    private final ConstructionService constructionService;

    @Autowired
    public ConstructionController(ConstructionService constructionService) {
        this.constructionService = constructionService;
    }


    @GetMapping
    public ResponseEntity<Page<Construction>> getAllConstructions(
            @PathVariable("clientId") Long clientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String[] sort
            ){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort));
        Page<Construction> constructions =  constructionService.getAllConstructions(clientId,pageable);
        return ResponseEntity.ok(constructions);
    }

    @GetMapping("/{constructionId}")
    public ResponseEntity<Construction> getConstruction(
            @PathVariable("constructionId") Long constructionId,
            @PathVariable("clientId") Long clientId
    ) throws IllegalAccessException {
        Construction construction =  constructionService.getConstruction(clientId,constructionId);
        return ResponseEntity.ok(construction);
    }

    @GetMapping("/{constructionId}/plan")
    public ResponseEntity<Map<String, StepTaskList>> getConstructionPlan(
            @PathVariable("clientId") Long clientId,
            @PathVariable("constructionId") Long constructionId
    ) throws IllegalAccessException {
        Map<String, StepTaskList> constructionTaskList =  constructionService.getConstructionTaskList(clientId,constructionId);
        return ResponseEntity.ok(constructionTaskList);
    }

    @PostMapping
    public ResponseEntity<Construction> createConstruction(
            @PathVariable("clientId") Long clientId,
            @RequestBody ConstructionDTO constructionDTO
    ) {
        Construction createdConstruction = constructionService.createConstruction(clientId,constructionDTO);
        return ResponseEntity.ok(createdConstruction);
    }

    @PutMapping("/{constructionId}")
    public ResponseEntity<Construction> updateConstruction(
            @PathVariable("clientId") Long clientId,
            @PathVariable("constructionId") Long constructionId,
            @RequestBody Construction construction
    ) throws IllegalAccessException {
        Construction updatedConstruction = constructionService.updateConstruction(clientId,constructionId,construction);
        return ResponseEntity.ok(updatedConstruction);
    }

    @DeleteMapping("/{constructionId}")
    public ResponseEntity<Construction> deleteConstruction(
            @PathVariable("clientId") Long clientId,
            @PathVariable("constructionId") Long constructionId
    ) throws IllegalAccessException {
        Construction deletedConstruction = constructionService.deleteConstruction(clientId,constructionId);
        return ResponseEntity.ok(deletedConstruction);
    }

    @PostMapping("/request")
    public ResponseEntity<Construction> requestConstruction(
            @PathVariable("clientId") Long clientId,
            @RequestBody House house
    ) {

        Construction generatedConstruction = constructionService.requestConstruction(clientId, house);
        return ResponseEntity.ok(generatedConstruction);
    }
    @PutMapping("/{houseId}/request")
    public ResponseEntity<House> requestConstruction(
            @PathVariable("clientId") Long clientId,
            @PathVariable("houseId") Long houseId,
            @RequestBody House house
    ) {

        House updatedConstructionHouse = constructionService.updateConstructionHouse(clientId,houseId, house);
        return ResponseEntity.ok(updatedConstructionHouse);
    }


}
