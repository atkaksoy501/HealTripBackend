package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.HospitalDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalDepartmentDao extends JpaRepository<HospitalDepartment, Integer>{
    List<HospitalDepartment> getAllByDepartmentId(int departmentId);
}
