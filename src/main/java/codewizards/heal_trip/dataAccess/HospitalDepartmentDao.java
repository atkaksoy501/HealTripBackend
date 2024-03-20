package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.HospitalDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalDepartmentDao extends JpaRepository<HospitalDepartment, Integer>{
}
