package com.karoliskursevicius.myfavartist.core.artist;

import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Karolis Kursevičius
 */
@Data
public class Artist {
    private Long id;
    private String externalId;
    private String name;
    private String genre;
    private Set<Album> albums = new HashSet<>();
    private Instant createdAt;
}
