package com.example.lesson10_1spring.service;

import com.example.lesson10_1spring.entity.ApiResponse;
import com.example.lesson10_1spring.entity.Hotel;
import com.example.lesson10_1spring.entity.Room;
import com.example.lesson10_1spring.payload.RoomDto;
import com.example.lesson10_1spring.repository.HotelRepository;
import com.example.lesson10_1spring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    public ApiResponse add(RoomDto roomDto) {
        boolean b = roomRepository.existsByFloorNumberAndRoomNumber(roomDto.getFloorNumber(), roomDto.getRoomNumber());
        if(b){
           return new ApiResponse("already exist",true);
        }
        Room room1=new Room();
        room1.setRoomNumber(roomDto.getRoomNumber());
        room1.setFloorNumber(roomDto.getFloorNumber());
        room1.setSize(roomDto.getSize());
        Optional<Hotel> byName = hotelRepository.findByName(roomDto.getHotelName());

        if (!byName.isPresent()){
            return new ApiResponse("hotel doesn't exist",false);
        }
        Hotel hotel = byName.get();
        room1.setHotel(hotel);
        Room save = roomRepository.save(room1);
        return new ApiResponse("success",true,save);
    }

    public Page<Room> getRooms(int page, Integer hotelId) {
        Pageable pageable= PageRequest.of(page,2);
        return roomRepository.findAllByHotel_Id(hotelId,pageable);
    }

    public ApiResponse editById(RoomDto roomDto, Integer id) {
        boolean b = roomRepository.existsById(id);
        if(!b){
            return new ApiResponse("fail",false);
        }
        Optional<Room> byId = roomRepository.findById(id);
        Room room1 = byId.get();
        room1.setRoomNumber(roomDto.getRoomNumber());
        room1.setSize(roomDto.getSize());
        room1.setFloorNumber(roomDto.getFloorNumber());
        Optional<Hotel> byName = hotelRepository.findByName(roomDto.getHotelName());
        Hotel hotel = byName.get();
        room1.setHotel(hotel);
        Room save = roomRepository.save(room1);
        return new ApiResponse("success",true);
    }
    public  ApiResponse deleteById(Integer id){
        boolean b = roomRepository.existsById(id);
        if(!b){
            return new ApiResponse("fail",false);
        }
        roomRepository.deleteById(id);
        return new ApiResponse("deleted",true);

    }
}
