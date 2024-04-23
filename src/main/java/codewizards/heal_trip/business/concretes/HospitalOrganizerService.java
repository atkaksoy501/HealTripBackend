package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.abstracts.IEmailService;
import codewizards.heal_trip.business.abstracts.IHospitalOrganizerService;
import codewizards.heal_trip.business.messages.EmailMessages;
import codewizards.heal_trip.business.rules.OrganizerBusinessRules;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.HospitalOrganizerDao;
import codewizards.heal_trip.entities.HospitalOrganizer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class HospitalOrganizerService implements IHospitalOrganizerService {

    private final HospitalOrganizerDao hospitalOrganizerDao;
    private final OrganizerBusinessRules organizerBusinessRules;
    private final IEmailService emailService;

    @Override
    public List<HospitalOrganizer> getAll() {
        return this.hospitalOrganizerDao.findAll();
    }

    @Override
    public List<HospitalOrganizer> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        return this.hospitalOrganizerDao.findAll(pageable).getContent();
    }

    @Override
    public Integer add(HospitalOrganizer hospitalOrganizer) {

        hospitalOrganizer.setPassword(new BCryptPasswordEncoder().encode(hospitalOrganizer.getPassword()));
        hospitalOrganizer.setCreateDate(LocalDateTime.now());
        HospitalOrganizer savedHospitalOrganizer = this.hospitalOrganizerDao.save(hospitalOrganizer);
        try {
            emailService.sendWelcomeEmail(savedHospitalOrganizer.getEmail(), savedHospitalOrganizer.getFirst_name());
        } catch (Exception e) {
            throw new BusinessException(EmailMessages.EMAIL_COULD_NOT_BE_SENT);
        }
        return savedHospitalOrganizer.getId();
    }

    @Override
    public HospitalOrganizer getById(int id) {
        organizerBusinessRules.checkIfHospitalOrganizerExists(id);
        return this.hospitalOrganizerDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        organizerBusinessRules.checkIfHospitalOrganizerExists(id);
        this.hospitalOrganizerDao.deleteById(id);
    }

    @Override
    public void update(HospitalOrganizer hospitalOrganizer) {
        organizerBusinessRules.checkIfHospitalOrganizerExists(hospitalOrganizer.getId());
        this.hospitalOrganizerDao.save(hospitalOrganizer); //todo: implement
    }
}
