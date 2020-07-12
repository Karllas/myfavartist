package com.karoliskursevicius.myfavartist.persistence.user;

import com.karoliskursevicius.myfavartist.core.artist.Album;
import com.karoliskursevicius.myfavartist.core.artist.Artist;
import com.karoliskursevicius.myfavartist.core.user.User;
import com.karoliskursevicius.myfavartist.persistence.artist.AlbumEntityJpa;
import com.karoliskursevicius.myfavartist.persistence.artist.ArtistEntityJpa;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Karolis Kursevičius
 */
public final class Mapper {
    public static UserEntityJpa toUser(User source) {
        if (source == null) {
            return null;
        }

        UserEntityJpa target = new UserEntityJpa();
        target.setId(source.getId());
        target.setEmail(source.getEmail());
        target.setFavouriteArtist(toArtist(source.getFavouriteArtist()));
        return target;
    }

    public static User toUser(UserEntityJpa source) {
        if (source == null) {
            return null;
        }

        User target = new User();
        target.setId(source.getId());
        target.setEmail(source.getEmail());
        target.setFavouriteArtist(toArtist(source.getFavouriteArtist()));
        target.setCreatedAt(source.getCreatedAt());
        return target;
    }

    public static ArtistEntityJpa toArtist(Artist source) {
        if (source == null) {
            return null;
        }

        ArtistEntityJpa target = new ArtistEntityJpa();
        target.setId(source.getId());
        target.setExternalId(source.getExternalId());
        target.setName(source.getName());
        target.setGenre(source.getGenre());
        Set<AlbumEntityJpa> albums = source.getAlbums().stream()
                .map(Mapper::toAlbum)
                .collect(Collectors.toUnmodifiableSet());
        for (AlbumEntityJpa album : albums) {
            album.setArtist(target);
        }

        target.setAlbums(albums);
        return target;
    }

    public static Artist toArtist(ArtistEntityJpa source) {
        if (source == null) {
            return null;
        }

        Artist target = new Artist();
        target.setId(source.getId());
        target.setExternalId(source.getExternalId());
        target.setName(source.getName());
        target.setGenre(source.getGenre());
        Set<Album> albums = source.getAlbums().stream()
                .map(Mapper::toAlbum)
                .collect(Collectors.toUnmodifiableSet());
        target.setAlbums(albums);
        target.setCreatedAt(source.getCreatedAt());
        return target;
    }

    public static AlbumEntityJpa toAlbum(Album source) {
        if (source == null) {
            return null;
        }

        AlbumEntityJpa target = new AlbumEntityJpa();
        target.setId(source.getId());
        target.setExternalId(source.getExternalId());
        target.setName(source.getName());
        target.setGenre(source.getGenre());
        target.setRank(source.getRank());
        return target;
    }

    public static Album toAlbum(AlbumEntityJpa source) {
        if (source == null) {
            return null;
        }

        Album target = new Album();
        target.setId(source.getId());
        target.setExternalId(source.getExternalId());
        target.setName(source.getName());
        target.setGenre(source.getGenre());
        target.setRank(source.getRank());
        target.setCreatedAt(source.getCreatedAt());
        return target;
    }

    public Mapper() {
    }
}
