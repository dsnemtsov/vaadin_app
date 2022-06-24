package ru.dimas224.data.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dimas224.data.AbstractEntity;
import ru.dimas224.util.Role;

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

    @Enumerated(EnumType.STRING)
    private Role role;
}
