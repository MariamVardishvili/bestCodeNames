package com.example.demo.services;

import com.example.demo.models.Player;
import com.example.demo.models.Room;
import com.example.demo.repositories.PlayerRepository;
import com.example.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    RoomRepository roomRepo;

    public Player connectPlayerAndRoom(String name, Room room, Boolean owner){
      //  Optional<Room> room = roomRepo.findByRoomNumber(roomNumber);
        try{
            Player pl = new Player();
          //  pl.setActive(true);
            pl.setRoom(room);
            pl.setCreator(true);
            pl.setUsername(name);
            playerRepo.save(pl);
            return pl;
        }catch(Exception e){
            System.out.println("FLSAELLASLSADSAD");
            return null;
        }
    }
    public List<Player> getActivePlayersFromRoom(int roomNumber) {
        List<Player> plList = playerRepo.findAll();
        return plList;
    }


}
