package com.karoliskursevicius.myfavartist.core.artist;

import java.util.Optional;

/**
 * @author Karolis Kursevičius
 */
public interface Artists {
    Optional<Artist> byId(long id);
}
