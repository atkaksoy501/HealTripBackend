package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.DTOs.requests.hospital.AddHospitalRequest;
import codewizards.heal_trip.business.DTOs.requests.hospital.UpdateHospitalRequest;
import codewizards.heal_trip.business.DTOs.requests.images.AddImageRequest;
import codewizards.heal_trip.business.DTOs.responses.address.AddressForHospitalResponse;
import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTO;
import codewizards.heal_trip.business.DTOs.responses.hospital.AddedHospitalResponse;
import codewizards.heal_trip.business.DTOs.responses.hospital.GotHospitalByIdResponse;
import codewizards.heal_trip.business.DTOs.responses.hospital.GotHospitalsByDepartmentIdResponse;
import codewizards.heal_trip.business.DTOs.responses.hospital.UpdatedHospitalResponse;
import codewizards.heal_trip.business.DTOs.responses.hospitalDepartment.DepartmentForHospitalDepartmentResponse;
import codewizards.heal_trip.business.abstracts.IAddressService;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import codewizards.heal_trip.business.abstracts.IHospitalService;
import codewizards.heal_trip.business.abstracts.IImageService;
import codewizards.heal_trip.business.rules.HospitalBusinessRules;
import codewizards.heal_trip.core.converter.ByteToBase64Converter;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.dataAccess.HospitalDao;
import codewizards.heal_trip.dataAccess.HospitalDepartmentDao;
import codewizards.heal_trip.entities.Department;
import codewizards.heal_trip.entities.Hospital;
import codewizards.heal_trip.entities.HospitalDepartment;
import codewizards.heal_trip.entities.HospitalImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService implements IHospitalService {
    private final HospitalDao hospitalDao;
    private final HospitalDepartmentDao hospitalDepartmentDao;
    private final ModelMapperService modelMapperService;
    private final IImageService imageService;
    private final IAddressService addressService;
    private final HospitalBusinessRules hospitalBusinessRules;
    private IDepartmentService departmentService;
    @Autowired
    public HospitalService(HospitalDao hospitalDao, HospitalDepartmentDao hospitalDepartmentDao, ModelMapperService modelMapperService,
                           IImageService imageService, IAddressService addressService, HospitalBusinessRules hospitalBusinessRules) {
        this.hospitalDao = hospitalDao;
        this.hospitalDepartmentDao = hospitalDepartmentDao;
        this.modelMapperService = modelMapperService;
        this.imageService = imageService;
        this.addressService = addressService;
        this.hospitalBusinessRules = hospitalBusinessRules;
    }

    @Autowired
    @Lazy
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public GotHospitalByIdResponse getHospitalById(int hospital_id) {
        hospitalBusinessRules.checkIfhospitalExists(hospital_id);
        Hospital hospital = hospitalDao.findById(hospital_id).orElse(null);
        GotHospitalByIdResponse response = new GotHospitalByIdResponse();
        response.setId(hospital.getId());
        response.setHospitalName(hospital.getHospitalName());
        response.setAddress(modelMapperService.forRequest().map(hospital.getAddress(), AddressForHospitalResponse.class));
        response.setContactPhone(hospital.getContactPhone());
        response.setBed_capacity(hospital.getBed_capacity());
        response.setDepartments(hospital.getDepartments().stream().map(hospitalDepartment -> {
            Department department = hospitalDepartment.getDepartment();
            return modelMapperService.forRequest().map(department, DepartmentForHospitalDepartmentResponse.class);
        }).toList());
        response.setDoctors(hospital.getDoctors().stream().map(
                doctor -> {
                    DoctorDTO doctorDTO = new DoctorDTO();
                    doctorDTO.setId(doctor.getId());
                    doctorDTO.setDoctorName(doctor.getDoctorName());
                    doctorDTO.setExperience_year(doctor.getExperience_year());
                    doctorDTO.setDoctorImage(ByteToBase64Converter.convert(doctor.getDoctorImage()));
                    doctorDTO.setDepartment(modelMapperService.forRequest().map(doctor.getDepartment(), DepartmentDTO.class));
                    return doctorDTO;
                }
        ).toList());
        response.setHospitalImages(hospital.getHospitalImages());
        return response;
    }

    @Override
    public AddedHospitalResponse registerHospital(AddHospitalRequest hospital) {
        List<AddImageRequest> hospitalImages = hospital.getHospitalImages();
        Hospital dbHospital = modelMapperService.forRequest().map(hospital, Hospital.class);
        if (hospitalImages != null && !hospitalImages.isEmpty()) {
            for (AddImageRequest hospitalImage : hospitalImages) {
                HospitalImage dbHospitalImage = modelMapperService.forRequest().map(hospitalImage, HospitalImage.class);
                dbHospitalImage.setHospital(dbHospital);
                imageService.saveHospitalImage(dbHospitalImage);
            }
        }
        dbHospital.setCreateDate(LocalDateTime.now());
        dbHospital.setActive(true);
        dbHospital.setAddress(addressService.add(hospital.getAddress()));
        return modelMapperService.forResponse().map(hospitalDao.save(dbHospital), AddedHospitalResponse.class);
    }

    @Override
    public boolean deleteHospital(int hospital_id) {
        hospitalBusinessRules.checkIfhospitalExists(hospital_id);
        Hospital dbHospital = hospitalDao.findById(hospital_id).orElse(null);
        if (dbHospital != null) {
            dbHospital.setActive(false);
            hospitalDao.save(dbHospital);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public UpdatedHospitalResponse updateHospital(UpdateHospitalRequest hospital, int id) {
        hospitalBusinessRules.checkIfhospitalExists(id);
        Hospital dbHospital = hospitalDao.findById(id).orElse(null);
        assert dbHospital != null;
        modelMapperService.forUpdate().map(hospital, dbHospital);
        List<HospitalDepartment> departmentList = new ArrayList<>();
        if (hospital.getDepartment_ids() != null) {
            hospitalDepartmentDao.deleteAllByHospitalId(id);
            hospital.getDepartment_ids().forEach(departmentId -> {
                HospitalDepartment hospitalDepartment = new HospitalDepartment();
                hospitalDepartment.setHospital(dbHospital);
                hospitalDepartment.setDepartment(modelMapperService.forRequest().map(departmentService.getById(departmentId), Department.class));
                hospitalDepartment.setCreateDate(LocalDateTime.now());
                hospitalDepartmentDao.save(hospitalDepartment);
                departmentList.add(hospitalDepartment);
            });
        }
        dbHospital.setUpdateDate(LocalDateTime.now());
        dbHospital.setDepartments(departmentList);
        return modelMapperService.forResponse().map(hospitalDao.save(dbHospital), UpdatedHospitalResponse.class);
    }

    //get all
    public List<GotHospitalByIdResponse> getAllHospitals() {
        return hospitalDao.findAll().stream().map(
                        hospital1 -> modelMapperService.forRequest().map(hospital1, GotHospitalByIdResponse.class)).toList();
    }

    // get all by departmentId
    public List<GotHospitalsByDepartmentIdResponse> getAllHospitalsByDepartmentId(int departmentId) {
        hospitalBusinessRules.checkIfHospitalsDepartmentExists(departmentId);
        List<HospitalDepartment> hospitalDepartments = hospitalDepartmentDao.getAllByDepartmentId(departmentId);
        return hospitalDepartments.stream().map(HospitalDepartment::getHospital).toList().stream().map(hospital -> {
            GotHospitalsByDepartmentIdResponse response = new GotHospitalsByDepartmentIdResponse();
            response.setId(hospital.getId());
            response.setHospitalName(hospital.getHospitalName());
            response.setHospitalImages(hospital.getHospitalImages());
            return response;
        }).toList();
    }
}