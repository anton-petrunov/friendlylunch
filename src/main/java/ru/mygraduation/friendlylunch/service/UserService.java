package ru.mygraduation.friendlylunch.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mygraduation.friendlylunch.AuthorizedUser;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.repository.UserRepository;

import java.util.List;

import static ru.mygraduation.friendlylunch.util.Util.prepareToSave;
import static ru.mygraduation.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static ru.mygraduation.friendlylunch.util.ValidationUtil.checkNew;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User get(int id) {
        return userRepository.get(id);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }

    public User create(User user) {
        checkNew(user);
        return prepareAndSave(user);
    }

    public void updateWithPasswordEncoding(User user, int id) {
        assureIdConsistent(user, id);
        prepareAndSave(user);
    }

    private User prepareAndSave(User user) {
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }

    public void updateWithoutPasswordEncoding(User user, int id) {
        assureIdConsistent(user, id);
        userRepository.save(user);
    }
}
