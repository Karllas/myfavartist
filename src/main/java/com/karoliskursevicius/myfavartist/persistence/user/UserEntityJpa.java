package com.karoliskursevicius.myfavartist.persistence.user;

import com.karoliskursevicius.myfavartist.persistence.Auditable;
import com.karoliskursevicius.myfavartist.persistence.artist.ArtistEntityJpa;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Karolis Kursevičius
 */
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntityJpa extends Auditable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "artist_id")
    private ArtistEntityJpa favouriteArtist;
}
