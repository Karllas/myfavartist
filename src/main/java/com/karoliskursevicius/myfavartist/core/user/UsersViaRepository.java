package com.karoliskursevicius.myfavartist.core.user;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * @author Karolis Kursevičius
 */
@RequiredArgsConstructor
public final class UsersViaRepository implements Users {
    private final UserRepository userRepository;

    @Override
    public Optional<User> byId(long id) {
        return userRepository.findById(id);
    }
}
