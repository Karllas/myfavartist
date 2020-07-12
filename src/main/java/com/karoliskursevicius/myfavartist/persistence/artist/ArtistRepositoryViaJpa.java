package com.karoliskursevicius.myfavartist.persistence.artist;

import com.karoliskursevicius.myfavartist.core.artist.Artist;
import com.karoliskursevicius.myfavartist.core.artist.ArtistRepository;
import com.karoliskursevicius.myfavartist.persistence.user.Mapper;
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
public class ArtistRepositoryViaJpa implements ArtistRepository {
    private final ArtistRepositoryJpa repository;

    @Override
    public Optional<Artist> findById(long id) {
        return repository.findById(id).map(Mapper::toArtist);
    }

    @Override
    public Optional<Artist> findByName(String name) {
        Objects.requireNonNull(name);
        return repository.findByNameIgnoreCase(name).map(Mapper::toArtist);
    }

    @Override
    public Artist save(Artist artist) {
        Objects.requireNonNull(artist);
        return Mapper.toArtist(repository.save(Mapper.toArtist(artist)));
    }

    @Override
    public boolean existsByExternalId(String externalId) {
        Objects.requireNonNull(externalId);
        return repository.existsByExternalId(externalId);
    }
}
