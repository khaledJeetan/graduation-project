package com.project.nextstep.services;

import com.project.nextstep.entity.House;
import com.project.nextstep.repositories.HouseRepository;
import com.project.nextstep.services.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HouseService {

    private HouseRepository houseRepository;
    private UserService userService;

    @Autowired
    public HouseService(HouseRepository houseRepository, UserService userService) {
        this.houseRepository = houseRepository;
        this.userService = userService;
    }

    public House getHouse(Long houseId){
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new IllegalStateException("House Not Found!!"));
    }

    @Transactional
    public House updateHouse(Long houseId, House house){
        House oldHouse = getHouse(houseId);
        oldHouse.setLocation(house.getLocation());
        oldHouse.setProjectName(house.getProjectName());
        oldHouse.setMaxBudget(house.getMaxBudget());
        oldHouse.setMinBudget(house.getMinBudget());
        oldHouse.setSize(house.getSize());
        oldHouse.setRoomsNumber(house.getRoomsNumber());
        oldHouse.setStartDate(house.getStartDate());
        return oldHouse;
    }

    public House addHouse(House house) {
        return houseRepository.save(house);
    }

}
