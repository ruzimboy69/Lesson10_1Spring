package com.example.lesson10_1spring.controller;

import com.example.lesson10_1spring.entity.ApiResponse;
import com.example.lesson10_1spring.entity.Room;
import com.example.lesson10_1spring.payload.RoomDto;
import com.example.lesson10_1spring.repository.RoomRepository;
import com.example.lesson10_1spring.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomService roomService;
    @GetMapping
    public ApiResponse getAll(){
        List<Room> all = roomRepository.findAll();
        return new ApiResponse("show",true,all);
    }
    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Integer id){
        boolean b = roomRepository.existsById(id);
        if(!b){
            return new ApiResponse("no exist",false);
        }
        Optional<Room> byId = roomRepository.findById(id);
        return new ApiResponse("success",true,byId);
    }
    @PostMapping
    public ApiResponse add(@RequestBody RoomDto roomDto){
        return roomService.add(roomDto);
    }


    @GetMapping("/byHotelId/{hotelId}")
    public Page<Room> getPageable(@RequestParam int page,@PathVariable Integer hotelId){
        return roomService.getRooms(page,hotelId);
    }
//    @GetMapping("/byHotelId/{id}")
//    public ApiResponse getByHotelId(@PathVariable Integer id){
//        List<Room> allByHotel_id = roomRepository.findAllByHotel_Id(id);
//        return new ApiResponse("success",true,allByHotel_id);
//    }

}
