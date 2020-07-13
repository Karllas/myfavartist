-- artist without albums --
INSERT INTO artist(artist_id, external_id, name, genre, created_at)
VALUES (1, 'artist1', 'artist1', 'any', sysdate());

-- artist with albums --
INSERT INTO artist(artist_id, external_id, name, genre, created_at)
VALUES (2, 'artist2', 'artist2', 'any', sysdate());

INSERT INTO album(album_id, external_id, name, genre, rank, artist_id, created_at)
VALUES (1, 'album1', 'album1', 'any', 1, 2, sysdate());

-- user --
INSERT INTO user(user_id, email, artist_id, created_at)
VALUES (1, 'email@email.email', 1, sysdate());
