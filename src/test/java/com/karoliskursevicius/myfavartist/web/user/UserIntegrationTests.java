package com.karoliskursevicius.myfavartist.web.user;

import com.karoliskursevicius.myfavartist.integration.WebIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Karolis Kursevičius
 */
class UserIntegrationTests extends WebIntegrationTest {
    @Test
    void getUser_exists_return() throws Exception {
        mvc.perform(get("/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getUser_missing_notFound() throws Exception {
        mvc.perform(get("/users/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void saveUser_valid_created() throws Exception {
        SaveUserRequest request = new SaveUserRequest();
        request.setEmail("any@any.any");

        mvc.perform(post("/users")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void saveUser_invalid_badRequest() throws Exception {
        SaveUserRequest request = new SaveUserRequest();
        request.setEmail("any");

        mvc.perform(post("/users")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUser_valid_updated() throws Exception {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setEmail("modified@modified.modified");
        request.setFavouriteArtistId(1L);

        mvc.perform(put("/users/{id}", 1)
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void updateUser_invalid_badRequest() throws Exception {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setEmail("any");
        request.setFavouriteArtistId(1L);

        mvc.perform(put("/users/{id}", 1)
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
