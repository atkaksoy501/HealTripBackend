package codewizards.heal_trip.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Department.class)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "department_name")
    private String departmentName;

    @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<HospitalDepartment> hospitals;

//    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Doctor> doctors;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Retreat> retreats;
}
