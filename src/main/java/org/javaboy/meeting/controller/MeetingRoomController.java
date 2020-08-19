package org.javaboy.meeting.controller;

import org.javaboy.meeting.domain.MeetingRoom;
import org.javaboy.meeting.service.MeetingRoomService;
import org.javaboy.meeting.validatedGroup.Group1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    @RequestMapping("/meetingrooms")
    public String meetingRooms(Model model) {
        List<MeetingRoom> mrs = meetingRoomService.getAllMrs();
        model.addAttribute("mrs", mrs);
        return "meetingrooms";
    }

    @RequestMapping("/roomdetails")
    public String roomDetail(Integer roomId, Model model, String error) {
        MeetingRoom meetingRoom = meetingRoomService.getMeetingRoomById(roomId);
        model.addAttribute("mr", meetingRoom);
        model.addAttribute("error", error);
        System.out.println(model);
        return "roomdetails";
    }

    @PostMapping("/updateMeetingRoom")
    public String roomDetail(@Validated(Group1.class) MeetingRoom meetingRoom, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            model.addAttribute("error", message);
            model.addAttribute("roomId", meetingRoom.getRoomId());
            return "redirect:/roomdetails";
        }
        Integer result = meetingRoomService.updateMeetingRoom(meetingRoom);
        if (result.equals(1)) {
            return "redirect:/meetingrooms";
        } else {
            model.addAttribute("error", "修改失败");
            model.addAttribute("roomId", meetingRoom.getRoomId());
            return "redirect:/roomdetails";
        }
    }

    @RequestMapping("/admin/addmeetingroom")
    public String addmeetingroom() {
        return "addmeetingroom";
    }

    @PostMapping("/admin/doAddMR")
    public String addMeetingRoom(@Validated MeetingRoom mr, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            model.addAttribute("error", message);
            return "addmeetingroom";
        }
        Integer result = meetingRoomService.addMeetingRoom(mr);
        if (result.equals(1)) {
            return "redirect:/meetingrooms";
        } else {
            model.addAttribute("error", "添加失败");
            return "addmeetingroom";
        }
    }
}
