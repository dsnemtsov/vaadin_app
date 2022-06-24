package ru.dimas224.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dimas224.data.AbstractEntity;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends AbstractEntity {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotNull
    private String role;
}
