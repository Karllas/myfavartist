package com.karoliskursevicius.myfavartist.core.user;

import java.util.Optional;

/**
 * @author Karolis Kursevičius
 */
public interface Users {
    Optional<User> byId(long id);
}
