package codewizards.heal_trip.entities;

import jakarta.persistence.*;

import lombok.*;

@MappedSuperclass
@Data
//@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "user_password")
    private String user_password;

    @Column(name = "user_role")
    private String user_role;

    @Column(name = "active")
    private boolean active;


}
