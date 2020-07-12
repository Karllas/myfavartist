package com.karoliskursevicius.myfavartist.persistence.artist;

import com.karoliskursevicius.myfavartist.persistence.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Karolis Kursevičius
 */
@Getter
@Setter
@Entity
@Table(name = "album")
public class AlbumEntityJpa extends Auditable {
    @Id
    @Column(name = "album_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", unique = true, nullable = false)
    private String externalId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "rank", nullable = false)
    private int rank;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artist_id", referencedColumnName = "artist_id", nullable = false)
    private ArtistEntityJpa artist;
}
