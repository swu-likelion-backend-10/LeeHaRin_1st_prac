package week.weekhomework.info.service;

import org.springframework.stereotype.Service;
import week.weekhomework.info.domain.info;
import week.weekhomework.info.dto.InfoDto;
import week.weekhomework.info.repository.InfoRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InfoService {
    private final InfoRepository infoRepository;

    public InfoService(InfoRepository infoRepository){
        this.infoRepository = infoRepository;
    }

    @Transactional
    public Long savePost(InfoDto infoDto){
        return infoRepository.save(infoDto.toEntity()).getId();
    }

    @Transactional
    public List<InfoDto> getInfoList(){
        List<info> infos = infoRepository.findAll();
        List<InfoDto> infoDtoList = new ArrayList<>();

        for (info info : infos){
            InfoDto infoDto = InfoDto.builder()
                    .id(info.getId())
                    .name(info.getName())
                    .age(info.getAge())
                    .department(info.getDepartment())
                    .introduction(info.getIntroduction())
                    .createdTime(info.getCreatedTime())
                    .build();

            infoDtoList.add(infoDto);
        }

        return infoDtoList;
    }

    @Transactional
    public InfoDto getPost(Long id){
        Optional<info> infoWrapper = infoRepository.findById(id);
        info info = infoWrapper.get();

        InfoDto infoDto = InfoDto.builder()
                .id(info.getId())
                .name(info.getName())
                .age(info.getAge())
                .department(info.getDepartment())
                .introduction(info.getIntroduction())
                .createdTime(info.getCreatedTime())
                .modifiedTime(info.getModifiedTime())
                .build();

        return infoDto;
    }

    @Transactional
    public Long updatePost(Long id, InfoDto infoDto){
        info info = infoRepository.findById(id).orElseThrow(()
        -> new IllegalArgumentException("?????? ???????????? ???????????? ????????????. " + id));
        info.update(infoDto);

        return id;
    }

    @Transactional
    public void deletePost(Long id){
        infoRepository.deleteById(id);
    }


    @Transactional
    public List<InfoDto> searchPosts(String keyword){
        List<info> infos = infoRepository.findByNameContaining(keyword);
        List<InfoDto> infoDtoList = new ArrayList<>();

        if(infos.isEmpty()) return infoDtoList;

        for (info info : infos){
            infoDtoList.add(this.convertEntityToDto(info));
        }

        return infoDtoList;
    }

    private InfoDto convertEntityToDto(info info){
        return InfoDto.builder()
                .id(info.getId())
                .name(info.getName())
                .age(info.getAge())
                .department(info.getDepartment())
                .introduction(info.getIntroduction())
                .createdTime(info.getCreatedTime())
                .modifiedTime(info.getModifiedTime())
                .build();
    }
}
