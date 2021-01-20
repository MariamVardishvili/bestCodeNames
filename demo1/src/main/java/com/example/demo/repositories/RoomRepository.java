package com.example.demo.repositories;

import com.example.demo.models.Player;
import com.example.demo.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Long> {
   //int countRoom();
    public Optional<Room> findByRoomNumber(int roomNumber);
    public List<Room> findAllByActiveIsTrue();
    public Optional<Room> findByIdAndActiveIsTrue(Long id);

}