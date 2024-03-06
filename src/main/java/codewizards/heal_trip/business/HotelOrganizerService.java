package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.HotelOrganizerDao;
import codewizards.heal_trip.entities.Hotel;
import codewizards.heal_trip.entities.HotelOrganizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelOrganizerService implements IHotelOrganizerService {

    private HotelOrganizerDao hotelOrganizerDao;
    private IHotelService hotelService;

    @Autowired
    public HotelOrganizerService(HotelOrganizerDao hotelOrganizerDao, IHotelService hotelService) {
        super();
        this.hotelOrganizerDao = hotelOrganizerDao;
        this.hotelService = hotelService;
    }

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
    public void add(HotelOrganizer hotelOrganizer) {
        this.hotelOrganizerDao.save(hotelOrganizer);
    }

    @Override
    public HotelOrganizer getById(int id) {
        return this.hotelOrganizerDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        this.hotelOrganizerDao.deleteById(id);
    }

    @Override
    public void update(HotelOrganizer hotelOrganizer) {
        this.hotelOrganizerDao.save(hotelOrganizer);
    }
}
