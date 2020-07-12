package com.karoliskursevicius.myfavartist.itunes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Karolis Kursevičius
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private static final String ARTIST_WRAPPER_TYPE = "artist";
    private static final String ALBUM_WRAPPER_TYPE = "collection";

    private String wrapperType;
    private String artistType;
    private String artistName;
    private String collectionName;
    private String amgArtistId;
    private String collectionId;
    private String primaryGenreName;

    public boolean isArtist() {
        return wrapperType.equals(ARTIST_WRAPPER_TYPE);
    }

    public boolean isAlbum() {
        return wrapperType.equals(ALBUM_WRAPPER_TYPE);
    }
}
