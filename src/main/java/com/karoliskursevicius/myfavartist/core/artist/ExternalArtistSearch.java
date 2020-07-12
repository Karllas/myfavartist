package com.karoliskursevicius.myfavartist.core.artist;

import java.util.List;

/**
 * @author Karolis Kursevičius
 */
public interface ExternalArtistSearch {
    List<Artist> search(String query);
}
