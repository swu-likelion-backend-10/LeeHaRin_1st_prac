package week.weekhomework.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import week.weekhomework.info.dto.InfoDto;
import week.weekhomework.info.service.InfoService;

@Controller
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService){
        this.infoService = infoService;
    }

    @GetMapping("/")
    public String list(){
        return "info/list.html";
    }

    @GetMapping("/info")
    public String write(){
        return "info/write.html";
    }

    @PostMapping("/info")
    public String write(InfoDto infoDto){
        infoService.savePost(infoDto);
        return "redirect:/";
    }
}
