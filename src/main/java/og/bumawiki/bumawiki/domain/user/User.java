package og.bumawiki.bumawiki.domain.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue
    private Long id;

    private String email; // 학교 email

    @Column(length = 4)
    private String name;

    @Column(length = 4)
    private String classNumber;

    @Column(length = 16)
    private String password;

    @Builder
    public User(Long id, String email, String name, String classNumber, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.classNumber = classNumber;
        this.password = password;
    }
}
