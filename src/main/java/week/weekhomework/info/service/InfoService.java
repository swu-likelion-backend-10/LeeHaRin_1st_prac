package week.weekhomework.info.service;

import org.springframework.stereotype.Service;
import week.weekhomework.info.dto.InfoDto;
import week.weekhomework.info.repository.InfoRepository;

import javax.transaction.Transactional;

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
}
