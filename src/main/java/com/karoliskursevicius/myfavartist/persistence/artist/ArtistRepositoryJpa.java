package com.karoliskursevicius.myfavartist.persistence.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Karolis Kursevičius
 */
@Repository
public interface ArtistRepositoryJpa extends JpaRepository<ArtistEntityJpa, Long> {
    Optional<ArtistEntityJpa> findByNameIgnoreCase(String name);

    boolean existsByExternalId(String externalId);
}
