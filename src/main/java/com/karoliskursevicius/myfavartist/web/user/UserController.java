package com.karoliskursevicius.myfavartist.web.user;

import com.karoliskursevicius.myfavartist.core.artist.Artist;
import com.karoliskursevicius.myfavartist.core.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author Karolis Kursevičius
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final Users users;
    private final UserUpdating userUpdating;
    private final UserRegistration userRegistration;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return users.byId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/favourite-artist")
    public ResponseEntity<Artist> getUsersFavouriteArtist(@PathVariable long id) {
        return users.byId(id)
                .map(User::getFavouriteArtist)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@Valid @RequestBody SaveUserRequest request) {
        RegistrableUser registrableUser = new RegistrableUser(request.getEmail());
        User user = userRegistration.register(registrableUser);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @Valid @RequestBody UpdateUserRequest request) {
        UserUpdate userUpdate = new UserUpdate(id, request.getEmail(), request.getFavouriteArtistId());
        userUpdating.update(userUpdate);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
