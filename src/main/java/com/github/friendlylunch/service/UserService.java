package com.github.friendlylunch.service;

import com.github.friendlylunch.AuthorizedUser;
import com.github.friendlylunch.model.User;
import com.github.friendlylunch.repository.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.friendlylunch.util.Util.prepareToSave;
import static com.github.friendlylunch.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

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

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.findById(id).orElse(null), id);
    }

    public List<User> getAll() {
        return userRepository.findAll(SORT_NAME_EMAIL);
    }

    public User create(User user) {
        return prepareAndSave(user);
    }

    public void updateWithPasswordEncoding(User user) {
        prepareAndSave(user);
    }

    private User prepareAndSave(User user) {
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }
}
