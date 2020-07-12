package com.karoliskursevicius.myfavartist.core.artist;

import java.util.Set;

/**
 * @author Karolis Kursevičius
 */
public interface ExternalAlbumProvider {
    Set<Album> getTopAlbums(String artistId);
}
