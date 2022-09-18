package week.weekhomework.info.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class info extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 100, nullable = false)
    private String department;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String introduction;

    @Builder
    public info(Long id, String name, Integer age, String department, String introduction){
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.introduction = introduction;
    }


}
