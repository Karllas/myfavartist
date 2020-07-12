package com.karoliskursevicius.myfavartist.core.artist;

import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * @author Karolis Kursevičius
 */
@RequiredArgsConstructor
public final class ArtistsViaRepository implements Artists {
    private final ArtistRepository artistRepository;
    private final ExternalAlbumProvider externalAlbumProvider;

    @Override
    public Optional<Artist> byId(long id) {
        Optional<Artist> artistOptional = artistRepository.findById(id);
        if (artistOptional.isEmpty()) {
            return Optional.empty();
        }

        setOrRefreshAlbumsIfNeeded(artistOptional.get());
        return artistOptional;
    }

    private void setOrRefreshAlbumsIfNeeded(Artist artist) {
        Optional<Instant> createdAtOptional = artist.getAlbums().stream().findAny().map(Album::getCreatedAt);
        if (artist.getAlbums().isEmpty()
                || (createdAtOptional.isPresent() && ChronoUnit.DAYS.between(createdAtOptional.get(), Instant.now()) > 0)) {
            artist.setAlbums(externalAlbumProvider.getTopAlbums(artist.getExternalId()));
            artistRepository.save(artist);
        }
    }
}
