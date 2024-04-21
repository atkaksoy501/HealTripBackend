package codewizards.heal_trip.business.DTOs.converters;

import codewizards.heal_trip.business.DTOs.requests.doctor.CreateDoctorRequest;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import codewizards.heal_trip.business.abstracts.IHospitalService;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.entities.Doctor;
import codewizards.heal_trip.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DoctorDbDtoConverter {
    private final ModelMapperService modelMapperService;
    private final IHospitalService hospitalService;
    private IDepartmentService departmentService;

    public DoctorDbDtoConverter(ModelMapperService modelMapperService, IHospitalService hospitalService) {
        this.modelMapperService = modelMapperService;
        this.hospitalService = hospitalService;
    }

    @Autowired
    @Lazy
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public Doctor toDbObj(CreateDoctorRequest dto) {
        Doctor newDoctor = modelMapperService.forRequest().map(dto, Doctor.class);
        newDoctor.setHospital(modelMapperService.forRequest()
                .map(hospitalService.getHospitalById(dto.getHospital_id()), Hospital.class));
        newDoctor.setDepartment(departmentService.getById(dto.getDepartment_id()));
        newDoctor.setCreateDate(LocalDateTime.now());
        newDoctor.setActive(true);
        return newDoctor;
    }
}
