package com.karoliskursevicius.myfavartist.persistence.artist;

import com.karoliskursevicius.myfavartist.persistence.Auditable;
import com.karoliskursevicius.myfavartist.persistence.user.UserEntityJpa;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Karolis Kursevičius
 */
@Getter
@Setter
@Entity
@Table(name = "artist")
public class ArtistEntityJpa extends Auditable {
    @Id
    @Column(name = "artist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", unique = true, nullable = false)
    private String externalId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "genre", nullable = false)
    private String genre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private Set<AlbumEntityJpa> albums = new HashSet<>();

    @OneToMany(mappedBy = "favouriteArtist")
    private Set<UserEntityJpa> users = new HashSet<>();
}
