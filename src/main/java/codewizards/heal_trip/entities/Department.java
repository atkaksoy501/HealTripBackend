package codewizards.heal_trip.entities;

import codewizards.heal_trip.core.entities.BaseEntity;
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
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "department_name")
    private String departmentName;

//    @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private List<HospitalDepartment> hospitals;

//    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Doctor> doctors;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Retreat> retreats;
}
