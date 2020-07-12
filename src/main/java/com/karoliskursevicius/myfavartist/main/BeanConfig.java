package com.karoliskursevicius.myfavartist.main;

import com.karoliskursevicius.myfavartist.core.artist.*;
import com.karoliskursevicius.myfavartist.core.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Karolis Kursevičius
 */
@Configuration
public class BeanConfig {
    @Bean
    public UserRegistration userRegistration(UserRepository userRepository) {
        return new UserRegistrationViaRepository(userRepository);
    }

    @Bean
    public Users users(UserRepository userRepository) {
        return new UsersViaRepository(userRepository);
    }

    @Bean
    public UserUpdating userUpdating(UserRepository userRepository, ArtistRepository artistRepository) {
        return new UserUpdatingViaRepository(userRepository, artistRepository);
    }

    @Bean
    public Artists artists(ArtistRepository artistRepository, ExternalAlbumProvider externalAlbumProvider) {
        return new ArtistsViaRepository(artistRepository, externalAlbumProvider);
    }

    @Bean
    public ArtistSearch artistSearch(ArtistRepository artistRepository, ExternalArtistSearch externalArtistSearch) {
        return new ArtistFinder(artistRepository, externalArtistSearch);
    }
}
