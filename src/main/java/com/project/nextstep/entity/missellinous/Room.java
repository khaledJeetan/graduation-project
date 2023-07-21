//package com.project.nextstep.entity.missellinous;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//public class Room {
//    @Id
//    private Long id;
//
//
//    @ElementCollection
//    @CollectionTable(name = "room_element", joinColumns = @JoinColumn(name = "room_id"))
//    private List<RoomElement> walls;
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//}
