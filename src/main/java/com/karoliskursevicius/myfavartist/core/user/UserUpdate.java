package com.karoliskursevicius.myfavartist.core.user;

import lombok.Value;

/**
 * @author Karolis Kursevičius
 */
@Value
public class UserUpdate {
    long userId;
    String email;
    Long favouriteArtistId;
}
