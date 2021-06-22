package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.exception.UsernameAlreadyTakenException;
import com.example.cityexplorer.model.User;
import com.example.cityexplorer.repository.UserRepository;
import com.example.cityexplorer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByUsername(username);

        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return optional.get();
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public List<User> getList() {
        log.info("Requested User list");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public User getById(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_USER_ID.getErrorMessage());

        log.info("Requested the User with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public User getByUsername(@NonNull String username) {
        Assert.notNull(username, ErrorMessages.NULL_USERNAME.getErrorMessage());

        log.info("Requested the User with username: {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Transactional
    @Override
    @NonNull
    public User save(@NonNull User user) {
        Assert.notNull(user, ErrorMessages.NULL_USER_OBJECT.getErrorMessage());

        User saved = save(null, user, false);
        log.info("Saved a new User with id: {}", saved.getId());
        return saved;
    }

    @Transactional
    @Override
    @NonNull
    public User update(@NonNull Long id, @NonNull User user) {
        Assert.notNull(id, ErrorMessages.NULL_USER_ID.getErrorMessage());
        Assert.notNull(user, ErrorMessages.NULL_USER_OBJECT.getErrorMessage());

        User updated = save(id, user, true);
        log.info("Updated the User with id: {}", id);
        return updated;
    }

    private User save(Long id, User user, boolean isUpdate) {
        if (isUpdate) {
            User userSaved = getById(id);
            user.setId(id);
        } else {
            Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
            if (byUsername.isPresent()) {
                throw new UsernameAlreadyTakenException(
                        ErrorMessages.USERNAME_ALREADY_TAKEN.getErrorMessage());
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_USER_ID.getErrorMessage());

        getById(id);
        userRepository.deleteById(id);
        log.info("Deleted the User with id: {}", id);
    }
}
