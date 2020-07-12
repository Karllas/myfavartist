package com.karoliskursevicius.myfavartist.web.artist;

import com.karoliskursevicius.myfavartist.integration.WebIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Karolis Kursevičius
 */
class ArtistIntegrationTests extends WebIntegrationTest {
    @Test
    void getArtist_exists_return() throws Exception {
        mvc.perform(get("/artists/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getArtist_artistWithoutAlbums_albumsReturnedFromExternalSourceAndSaved() throws Exception {
        mvc.perform(get("/artists/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.albums[*].externalId", contains("123456789")))
                .andExpect(jsonPath("$.[*].id", not(contains((Object) null))));
    }

    @Test
    void getArtist_missing_notFound() throws Exception {
        mvc.perform(get("/artists/{id}", 999)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void search_nameMatches_returnFromRepository() throws Exception {
        mvc.perform(get("/artists/search")
                .queryParam("query", "artist1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id", contains(1)));
    }

    @Test
    void search_nameDoesNotMatches_returnFromExternalSourceAndSave() throws Exception {
        mvc.perform(get("/artists/search")
                .queryParam("query", "123456789")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].externalId", contains("123456789")))
                .andExpect(jsonPath("$.[*].id", not(contains((Object) null))));
    }
}
