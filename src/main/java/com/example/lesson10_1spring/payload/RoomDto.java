package com.example.lesson10_1spring.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto {
    private String roomNumber;
    private Integer floorNumber;
    private double size;
    private String hotelName;
}
