package com.karoliskursevicius.myfavartist.core.artist;

import java.util.List;

/**
 * @author Karolis Kursevičius
 */
public interface ArtistSearch {
    List<Artist> search(String query);
}
