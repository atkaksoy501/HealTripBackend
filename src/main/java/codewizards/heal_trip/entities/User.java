package codewizards.heal_trip.entities;

import jakarta.persistence.*;

import lombok.*;

@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    public User(String first_name, String last_name, String email, String phone_number, String user_password, String user_role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.user_password = user_password;
        this.user_role = user_role;
    }

}
