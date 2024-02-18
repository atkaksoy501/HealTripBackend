package codewizards.heal_trip.entities;

import jakarta.persistence.*;

import lombok.*;

@Data
@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
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
}
