package og.bumawiki.bumawiki.domain.user.facade;

import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import leehj050211.bsmOauth.dto.response.BsmStudentResponse;
import lombok.RequiredArgsConstructor;
import og.bumawiki.bumawiki.domain.user.domain.User;
import og.bumawiki.bumawiki.domain.user.domain.type.Role;
import og.bumawiki.bumawiki.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserFacade {
    private final UserRepository userRepository;

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    private User bsmSignUp(BsmResourceResponse dto, String bsmToken){
        BsmStudentResponse student = dto.getStudent();

        User user = User.builder()
                .name(student.getName())
                .email(dto.getEmail())
                .role(Role.USER)
                .bsmToken(bsmToken)
                .build();

        save(user);

        return user;
    }

    @Transactional
    private User bsmUserNameUpdate(User user, BsmResourceResponse dto) {
        BsmStudentResponse student = dto.getStudent();
        user.updateUserName(student.getName());

        return userRepository.save(user);
    }

    private User getOrElseSignUp(BsmResourceResponse resource, String token){
        Optional<User> userOptional = userRepository.findByEmail(resource.getEmail());

        if(userOptional.isEmpty()){
            return bsmSignUp(resource, token);
        }
        User user = userOptional.get();
        return bsmUserNameUpdate(user    .getName());
    }
}
