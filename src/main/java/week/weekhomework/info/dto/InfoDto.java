package week.weekhomework.info.dto;

import lombok.Builder;
import week.weekhomework.info.domain.info;

import java.time.LocalDateTime;

public class InfoDto {
    private Long id;
    private String name;
    private Integer age;
    private String department;
    private String introduction;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public info toEntity(){
        info build = info.builder()
                .id(id)
                .name(name)
                .age(age)
                .department(department)
                .introduction(introduction)
                .build();
        return build;
    }

    @Builder
    public InfoDto(Long id, String name, Integer age, String department, String introduction, LocalDateTime createdTime, LocalDateTime modifiedTime){
        this.id=id;
        this.name=name;
        this.age=age;
        this.department=department;
        this.introduction=introduction;
        this.createdTime=createdTime;
        this.modifiedTime=modifiedTime;
    }
}
