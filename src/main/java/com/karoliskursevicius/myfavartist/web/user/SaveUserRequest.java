package com.karoliskursevicius.myfavartist.web.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Karolis Kursevičius
 */
@Data
class SaveUserRequest {
    @Email
    @NotBlank
    private String email;
}
