package com.karoliskursevicius.myfavartist.core.user;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * @author Karolis Kursevičius
 */
@RequiredArgsConstructor
public final class UserRegistrationViaRepository implements UserRegistration {
    private final UserRepository userRepository;

    @Override
    public User register(RegistrableUser registrableUser) {
        Objects.requireNonNull(registrableUser);
        User user = new User();
        user.setEmail(registrableUser.getEmail());
        return userRepository.save(user);
    }
}
