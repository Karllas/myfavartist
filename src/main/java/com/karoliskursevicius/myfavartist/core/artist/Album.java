package com.karoliskursevicius.myfavartist.core.artist;

import lombok.Data;

import java.time.Instant;

/**
 * @author Karolis Kursevičius
 */
@Data
public class Album {
    private Long id;
    private String externalId;
    private String name;
    private String genre;
    private int rank;
    private Instant createdAt;
}
