package com.karoliskursevicius.myfavartist.core.user;

import com.karoliskursevicius.myfavartist.core.artist.Artist;
import lombok.Data;

import java.time.Instant;

/**
 * @author Karolis Kursevičius
 */
@Data
public class User {
    private Long id;
    private String email;
    private Artist favouriteArtist;
    private Instant createdAt;
}
