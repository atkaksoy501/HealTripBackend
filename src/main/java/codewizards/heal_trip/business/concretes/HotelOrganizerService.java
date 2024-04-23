package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.abstracts.IHotelOrganizerService;
import codewizards.heal_trip.business.rules.OrganizerBusinessRules;
import codewizards.heal_trip.dataAccess.HotelOrganizerDao;
import codewizards.heal_trip.entities.HotelOrganizer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class HotelOrganizerService implements IHotelOrganizerService {

    private final HotelOrganizerDao hotelOrganizerDao;
    private final OrganizerBusinessRules organizerBusinessRules;

    @Override
    public List<HotelOrganizer> getAll() {
        return this.hotelOrganizerDao.findAll();
    }

    @Override
    public List<HotelOrganizer> getAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        return this.hotelOrganizerDao.findAll(pageable).getContent();
    }

    @Override
    public Integer add(HotelOrganizer hotelOrganizer) {
        hotelOrganizer.setCreateDate(LocalDateTime.now());
        return this.hotelOrganizerDao.save(hotelOrganizer).getId();
    }

    @Override
    public HotelOrganizer getById(int id) {
        organizerBusinessRules.checkIfHotelOrganizerExists(id);
        return this.hotelOrganizerDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        organizerBusinessRules.checkIfHotelOrganizerExists(id);
        this.hotelOrganizerDao.deleteById(id);
    }

    @Override
    public void update(HotelOrganizer hotelOrganizer) {
        organizerBusinessRules.checkIfHotelOrganizerExists(hotelOrganizer.getId());
        this.hotelOrganizerDao.save(hotelOrganizer); //todo: implement
    }
}
