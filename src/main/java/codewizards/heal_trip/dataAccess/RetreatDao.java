package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.Retreat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetreatDao extends JpaRepository<Retreat, Integer> {
    // get retreats by department id
    List<Retreat> findByDepartmentId(int departmentId);
    List<Retreat> findByDepartmentHospitalsHospitalId(int hospitalId);
}
