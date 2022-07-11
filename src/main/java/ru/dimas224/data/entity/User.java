package ru.dimas224.data.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     *     todo: @MappedSuperclass
     *     @EntityListeners(AuditingEntityListener.class)
     *     public abstract class DateAudit implements Serializable {
     *         @CreatedDate
     *         @Column(name = "created_at", nullable = false, updatable = false)
     *         private Date createdAt;
     *
     *         @LastModifiedDate
     *         @Column(name = "updated_at")
     *         private LocalDateTime updatedAt;
     *     }
     */


}
