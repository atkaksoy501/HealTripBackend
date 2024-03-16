package codewizards.heal_trip.entities;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity(name = "User")
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email"))
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "id", updatable = false)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "roles", nullable = false)
    private String Roles;

    @ToString.Exclude
    @Column(name = "password", nullable = false, columnDefinition = "text")
    private String password;

    @Column(name = "active")
    private boolean active;


}