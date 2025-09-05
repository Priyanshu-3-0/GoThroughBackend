package Services;

import entity.User;
import repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole() == null ? entity.Role.USER : user.getRole());
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void activateMembership(User user, int days){
        user.setMember(true);
        user.setMembershipExpiry(LocalDateTime.now().plusDays(days));
        userRepository.save(user);
    }

}
