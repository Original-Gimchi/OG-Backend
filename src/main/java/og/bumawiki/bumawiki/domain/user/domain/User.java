package og.bumawiki.bumawiki.domain.user.domain;

import lombok.*;
import og.bumawiki.bumawiki.domain.user.domain.type.Role;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue
    private Long id;

    @Column(length = 32)
    private String email; // 학교 email

    @Column(length = 4)
    private String name;

    @Column(length = 8)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 4)
    private String classNumber;

    @Column(length = 16)
    private String password;

    @Builder
    public User(String email, String name, String classNumber, String password,Role role) {
        this.email = email;
        this.name = name;
        this.classNumber = classNumber;
        this.password = password;
        this.role = role;
    }
}
