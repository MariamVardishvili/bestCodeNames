package com.example.demo.repositories;

import com.example.demo.models.Player;
import com.example.demo.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    //List<Player> findPlayerByRoomAndActiveIsTrue(Room room);
}
