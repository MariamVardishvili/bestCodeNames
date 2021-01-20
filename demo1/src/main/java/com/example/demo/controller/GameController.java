package com.example.demo.controller;

import com.example.demo.models.Player;
import com.example.demo.models.Room;
import com.example.demo.services.PlayerService;
import com.example.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@SessionAttributes("player")
public class GameController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private PlayerService playerService;


    @RequestMapping("/")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @RequestMapping(value = "/newRoom")
    public ModelAndView newRoom(@RequestParam("userName") String userName, ModelMap modelMap) {
        Player currentPlayer =  roomService.createNewRoom(userName);
        modelMap.put("player", currentPlayer);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/lobby");
        return modelAndView;
    }
    @RequestMapping("/lobby")
    public ModelAndView lobby(ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test.html");
        return modelAndView;
    }

    @RequestMapping(value ="/checkMembers", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Player> checkMembers(ModelMap modelMap) {
        Player currentPlayer = (Player) modelMap.get("player");
        List<Player> activePlayersFromRoom = playerService.getActivePlayersFromRoom(currentPlayer.getRoom().getRoomNumber());

        return activePlayersFromRoom;
    }

    @RequestMapping(value = "/joinRoom")
    public RedirectView joinRoom(@RequestParam("userName") String userName, @RequestParam("roomNumber") long roomNumber, ModelMap modelMap) {
        Room joinRoom = roomService.getRoom(roomNumber);
        Player currentPlayer = playerService.connectPlayerAndRoom(userName, joinRoom, false);
        if(currentPlayer!=null) {
            modelMap.put("player", currentPlayer);
            RedirectView view = new RedirectView("/lobby");
            return view;
        }
        RedirectView r = new RedirectView( "/roomerror");
        return r;
    }

    @RequestMapping(value = "/roomerror")
    public ModelAndView myerror() {
        System.out.println("here hererer I am jereeeeeeeee");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        modelAndView.addObject("ErMessage", "Room not Found");

        return modelAndView;
    }
}
