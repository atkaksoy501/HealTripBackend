package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.abstracts.IHotelService;
import codewizards.heal_trip.dataAccess.HotelDao;
import codewizards.heal_trip.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelService implements IHotelService {

    private HotelDao hotelDao;

    @Autowired
    public HotelService(HotelDao hotelDao) {
        super();
        this.hotelDao = hotelDao;
    }

    @Override
    public List<Hotel> getAll() {
        return this.hotelDao.findAll();
    }

    @Override
    public List<Hotel> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC,"hotelName");
        return this.hotelDao.findAll(sort);
    }

    @Override
    public List<Hotel> getAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        return this.hotelDao.findAll(pageable).getContent();
    }

    @Override
    public Hotel add(Hotel hotel) {
        return this.hotelDao.save(hotel);
    }

    @Override
    public Hotel getById(int id) {
        return this.hotelDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        this.hotelDao.deleteById(id);
    }

    @Override
    public void update(Hotel hotel) {
        this.hotelDao.save(hotel);
    }
}
