package com.karoliskursevicius.myfavartist.web.artist;

import com.karoliskursevicius.myfavartist.core.artist.Artist;
import com.karoliskursevicius.myfavartist.core.artist.ArtistSearch;
import com.karoliskursevicius.myfavartist.core.artist.Artists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Karolis Kursevičius
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
public class ArtistController {
    private final Artists artists;
    private final ArtistSearch artistSearch;

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtist(@PathVariable long id) {
        return artists.byId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Artist>> search(@RequestParam("query") String query) {
        return ResponseEntity.ok(artistSearch.search(query));
    }
}
