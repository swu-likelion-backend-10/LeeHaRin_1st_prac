package week.weekhomework.info.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String email;

    @Column
    private String password;

    @Builder
    Member(Long id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
