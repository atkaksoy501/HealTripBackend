package codewizards.heal_trip.business.DTOs.converters;

import codewizards.heal_trip.business.DTOs.requests.doctor.CreateDoctorRequest;
import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTO;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTOWithHospital;
import codewizards.heal_trip.business.DTOs.responses.doctor.HospitalForDoctorResponse;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import codewizards.heal_trip.business.abstracts.IHospitalService;
import codewizards.heal_trip.core.converter.Base64ToByteConverter;
import codewizards.heal_trip.core.converter.ByteToBase64Converter;
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
        Doctor newDoctor = new Doctor();
        newDoctor.setExperience_year(dto.getExperience_year());
        newDoctor.setDoctorName(dto.getDoctorName());
        newDoctor.setDoctorImage(Base64ToByteConverter.convert(dto.getDoctorImage()));
        newDoctor.setHospital(modelMapperService.forRequest()
                .map(hospitalService.getHospitalById(dto.getHospital_id()), Hospital.class));
        newDoctor.setDepartment(departmentService.getById(dto.getDepartment_id()));
        newDoctor.setCreateDate(LocalDateTime.now());
        newDoctor.setActive(true);
        return newDoctor;
    }

    public DoctorDTOWithHospital toDto(Doctor dbObj) {
        DoctorDTOWithHospital dto = new DoctorDTOWithHospital();
        dto.setId(dbObj.getId());
        dto.setExperience_year(dbObj.getExperience_year());
        dto.setDoctorName(dbObj.getDoctorName());
        dto.setDoctorImage(ByteToBase64Converter.convert(dbObj.getDoctorImage()));
        dto.setHospital(modelMapperService.forResponse().map(dbObj.getHospital(), HospitalForDoctorResponse.class));
        dto.setDepartment(modelMapperService.forResponse().map(dbObj.getDepartment(), DepartmentDTO.class));
        return dto;
    }
}
