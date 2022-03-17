package com.example.lesson10_1spring.controller;

import com.example.lesson10_1spring.entity.ApiResponse;
import com.example.lesson10_1spring.entity.Hotel;
import com.example.lesson10_1spring.repository.HotelRepository;
import com.example.lesson10_1spring.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    HotelService hotelService;
    @PostMapping
    public ApiResponse add(@RequestBody Hotel hotel){
        return hotelService.add(hotel);
    }
    @GetMapping
    public ApiResponse getAll(){
        List<Hotel> all = hotelRepository.findAll();
        return new ApiResponse("all",true,all);
    }
    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Integer id){
        return hotelService.getById(id);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Integer id){
        return hotelService.deleteById(id);
    }
    @PutMapping("/{id}")
    public ApiResponse editById(@PathVariable Integer id,@RequestBody Hotel hotel){
        return hotelService.editById(hotel,id);
    }

}
