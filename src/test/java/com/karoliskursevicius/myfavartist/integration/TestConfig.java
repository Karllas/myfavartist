package com.karoliskursevicius.myfavartist.integration;

import com.karoliskursevicius.myfavartist.core.artist.Album;
import com.karoliskursevicius.myfavartist.core.artist.Artist;
import com.karoliskursevicius.myfavartist.itunes.ITunesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

/**
 * @author Karolis Kursevičius
 */
@Configuration
public class TestConfig {
    @Bean
    public ITunesClient iTunesClient() {
        return new ITunesClient(null) {
            @Override
            public List<Artist> searchArtists(String query) {
                Artist artist = new Artist();
                artist.setExternalId("123456789");
                artist.setName("123456789");
                artist.setGenre("any");
                return List.of(artist);
            }

            @Override
            public Set<Album> getTopAlbums(String artistId) {
                Album album = new Album();
                album.setExternalId("123456789");
                album.setName("123456789");
                album.setGenre("any");
                album.setRank(1);
                return Set.of(album);
            }
        };
    }
}
