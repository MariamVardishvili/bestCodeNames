package com.example.demo.services;

import com.example.demo.models.Player;
import com.example.demo.models.Room;
import com.example.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomrepo;
    @Autowired
    PlayerService playerService;

    int getRoomNumber(){
        int roomnumber;
        List<Room> list = roomrepo.findAll();
        if(list.size()>0) {
            roomnumber = list.get(list.size() - 1).getRoomNumber() + 1;
        }
        else{
            roomnumber = 1;
        }
        return roomnumber;
    }
    public Player createNewRoom(String userName) {
        Room g = new Room();
        g.setRoomNumber(getRoomNumber());
        roomrepo.save(g);
        Player player = playerService.connectPlayerAndRoom(userName, g, true);
        return player;
    }

    public Room getRoom(long roomNumber) {
        return roomrepo.getOne(roomNumber);
    }
}