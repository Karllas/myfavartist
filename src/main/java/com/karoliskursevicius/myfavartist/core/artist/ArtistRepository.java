package com.karoliskursevicius.myfavartist.core.artist;

import java.util.Optional;

/**
 * @author Karolis Kursevičius
 */
public interface ArtistRepository {
    Optional<Artist> findById(long id);

    Optional<Artist> findByName(String name);

    Artist save(Artist artist);

    boolean existsByExternalId(String externalId);
}
