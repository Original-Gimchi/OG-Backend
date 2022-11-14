package og.bumawiki.bumawiki.domain.user.domain;

import lombok.*;
import og.bumawiki.bumawiki.domain.user.domain.type.Role;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 32)
    private String email; // 학교 email

    @Column(length = 4)
    private String name;

    @Column(length = 8)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 16)
    private String password;

    @Column(length = 32)
    private String bsmToken;

    public void updateUserName(String name){
        this.name = name;
    }
}
