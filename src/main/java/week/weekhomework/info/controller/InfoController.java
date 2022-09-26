package week.weekhomework.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import week.weekhomework.info.dto.InfoDto;
import week.weekhomework.info.service.InfoService;

import java.util.List;

@Controller
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService){
        this.infoService = infoService;
    }

    @GetMapping("/")
    public String list(Model model){
        List<InfoDto> infoDtoList = infoService.getInfoList();
        model.addAttribute("infoList", infoDtoList);
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

    @GetMapping("/info/{no}")
    public String detail(@PathVariable("no") Long id, Model model){
        InfoDto infoDto = infoService.getPost(id);

        model.addAttribute("infoDto", infoDto);
        return "info/detail.html";
    }

    @GetMapping("/info/edit/{no}")
    public String edit(@PathVariable("no") Long id, Model model){
        InfoDto infoDto = infoService.getPost(id);
        model.addAttribute("infoDto", infoDto);
        return "info/update.html";
    }

    @PutMapping("/info/edit/{no}")
    public String update(@PathVariable("no") Long id, InfoDto infoDto){
        infoService.updatePost(id, infoDto);
        return "redirect:/info/{no}";
    }

    @DeleteMapping("/info/delete/{no}")
    public String delete(@PathVariable("no") Long id){
        infoService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        List<InfoDto> infoDtoList = infoService.searchPosts(keyword);
        model.addAttribute("infoList", infoDtoList);

        return "info/list.html";
    }
}
