package codewizards.heal_trip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "department_name")
    private String departmentName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @JsonManagedReference
    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors;


    @JsonManagedReference
    @OneToMany(mappedBy = "department")
    private List<Retreat> retreats;
}
