package com.example.lesson10_1spring.repository;

import com.example.lesson10_1spring.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
boolean existsByFloorNumberAndRoomNumber(Integer floorNum,String roomNum);
  Page<Room> findAllByHotel_Id(Integer hotelId,Pageable pageable);
//  List<Room> findAllByHotel_Id(Integer id);
}
