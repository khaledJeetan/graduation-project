package com.project.nextstep.services.account;

import com.project.nextstep.entity.accounts.User;
import com.project.nextstep.entity.accounts.Vendor;
import com.project.nextstep.entity.enums.Role;
import com.project.nextstep.repositories.ChatRepository;
import com.project.nextstep.repositories.account.UserRepository;
import com.project.nextstep.services.EmailService;
import com.project.nextstep.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final EmailService emailService;
    private final ImageService imageService;

    @Autowired
    public UserService(UserRepository userRepository, ChatRepository chatRepository, EmailService emailService, ImageService imageService) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.emailService = emailService;
        this.imageService = imageService;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<Vendor> getAllVendorsById(List<Long> ids){

       return userRepository
               .findAllById(ids)
               .stream()
               .map(user -> (Vendor) user)
               .collect(Collectors.toList());
    }

    public User getUser(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "User with id " + userId + " does not exists")
                );
    }

    public User getUser(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(()->
                        new IllegalStateException( "User with Email: " + email + " does not exists")
                );
    }

    public User addUser(User user) {

        userRepository.save(user);
        return user;
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        boolean isFolderDeleted = true;
       User user = getUser(userId);
       if(user.getRole().matches(Role.SUPPLIER.name())) {
           isFolderDeleted = imageService.deleteFolder(user.getEmail());

       }
       if(user.getRole().matches(Role.VENDOR.name())) {
           chatRepository.updateTaskVendorIdToNull(userId);
       }
        userRepository.deleteById(userId);
       return isFolderDeleted;
    }

    public boolean deleteUser(String email) {
        boolean isFolderDeleted = true;
        User user = getUser(email);
        if(user.getRole().matches(Role.SUPPLIER.name())) {
            isFolderDeleted = imageService.deleteFolder(email);
        }
        return isFolderDeleted && userRepository.deleteByEmail(email);
    }

    @Transactional
    public User updateUser(Long userId, User user) {
        User oldUser = getUser(userId);
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setPhone(user.getPhone());
        oldUser.setLocation(user.getLocation());
        oldUser.setUrl(user.getUrl());
        return oldUser;
    }
    @Transactional
    public User updateUser(String email, User user) {
        User oldUser = getUser(email);
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setPhone(user.getPhone());
        return oldUser;
    }

    public Optional<String> sendVerificationEmail(String email) {
       return emailService.sendMail(email);
    }

    public User verifyUser(String email, String password) {
        User user = getUser(email);
        return user.getPassword().matches(password) ? user : null;
    }
}
