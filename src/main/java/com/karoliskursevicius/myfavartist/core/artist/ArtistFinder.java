package com.karoliskursevicius.myfavartist.core.artist;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Karolis Kursevičius
 */
@RequiredArgsConstructor
public final class ArtistFinder implements ArtistSearch {
    private final ArtistRepository artistRepository;
    private final ExternalArtistSearch externalArtistSearch;

    @Override
    public List<Artist> search(String query) {
        Objects.requireNonNull(query);
        Optional<Artist> artistOptional = artistRepository.findByName(query);
        return artistOptional.map(List::of).orElseGet(() -> searchExternally(query));
    }

    private List<Artist> searchExternally(String query) {
        return externalArtistSearch.search(query).stream()
                .map(this::saveIfDoesNotExist)
                .collect(Collectors.toUnmodifiableList());
    }

    private Artist saveIfDoesNotExist(Artist artist) {
        return artistRepository.existsByExternalId(artist.getExternalId()) ? artist : artistRepository.save(artist);
    }
}
