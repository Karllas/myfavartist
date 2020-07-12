package com.karoliskursevicius.myfavartist.core.user;

import java.util.Optional;

/**
 * @author Karolis Kursevičius
 */
public interface UserRepository {
    Optional<User> findById(long id);

    User save(User user);
}
