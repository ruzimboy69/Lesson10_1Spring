package com.example.lesson10_1spring.service;

import com.example.lesson10_1spring.entity.ApiResponse;
import com.example.lesson10_1spring.entity.Hotel;
import com.example.lesson10_1spring.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;
    public ApiResponse add(Hotel hotel) {
        Hotel hotel1=new Hotel();
        if(hotelRepository.existsByName(hotel.getName())){
            return new ApiResponse("This hotel already exist",false);
        }
        hotel1.setName(hotel.getName());
        Hotel save = hotelRepository.save(hotel1);
        return new ApiResponse("added",true,save);
    }

    public ApiResponse getById(Integer id) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if(!byId.isPresent()){
            return new ApiResponse("failed",false);
        }
        return new ApiResponse("success",true,byId);
    }

    public ApiResponse deleteById(Integer id) {
        boolean b = hotelRepository.existsById(id);
        if(!b){
            return new ApiResponse("No hotel",false);
        }
        hotelRepository.deleteById(id);
        return new ApiResponse("deleted",true);
    }

    public ApiResponse editById(Hotel hotel,Integer id) {
        boolean b = hotelRepository.existsById(id);
        if(!b){
            return new ApiResponse("no hotel",false);
        }
        Optional<Hotel> byId = hotelRepository.findById(id);
        Hotel hotel1=byId.get();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return new ApiResponse("edited",true);
    }

}
