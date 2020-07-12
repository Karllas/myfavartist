package com.karoliskursevicius.myfavartist.core.user;

import com.karoliskursevicius.myfavartist.core.artist.Artist;
import com.karoliskursevicius.myfavartist.core.artist.ArtistRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * @author Karolis Kursevičius
 */
@RequiredArgsConstructor
public final class UserUpdatingViaRepository implements UserUpdating {
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;

    @Override
    public User update(UserUpdate userUpdate) {
        Objects.requireNonNull(userUpdate);
        User user = getUser(userUpdate.getUserId());
        user.setEmail(userUpdate.getEmail());
        user.setFavouriteArtist(getArtist(userUpdate.getFavouriteArtistId()));
        return userRepository.save(user);
    }

    private User getUser(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    private Artist getArtist(long id) {
        return artistRepository.findById(id).orElseThrow();
    }
}
