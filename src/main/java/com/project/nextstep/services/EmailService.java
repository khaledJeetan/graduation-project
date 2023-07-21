package com.project.nextstep.services;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface EmailService {

    // To send a simple email
    Optional<String> sendMail(String mailTo);
}
