package com.karoliskursevicius.myfavartist.persistence.user;

import com.karoliskursevicius.myfavartist.core.user.User;
import com.karoliskursevicius.myfavartist.core.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Karolis Kursevičius
 */
@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryViaJpa implements UserRepository {
    private final UserRepositoryJpa repository;

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id).map(Mapper::toUser);
    }

    @Override
    public User save(User user) {
        Objects.requireNonNull(user);
        return Mapper.toUser(repository.save(Mapper.toUser(user)));
    }


}
