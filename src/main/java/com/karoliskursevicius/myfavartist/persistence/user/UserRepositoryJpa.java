package com.karoliskursevicius.myfavartist.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karolis Kursevičius
 */
@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntityJpa, Long> {
}
