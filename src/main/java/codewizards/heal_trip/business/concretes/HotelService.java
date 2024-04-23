package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.abstracts.IHotelService;
import codewizards.heal_trip.business.abstracts.IImageService;
import codewizards.heal_trip.business.rules.HotelBusinessRules;
import codewizards.heal_trip.dataAccess.HotelDao;
import codewizards.heal_trip.entities.Hotel;
import codewizards.heal_trip.entities.HotelImage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class HotelService implements IHotelService {

    private final HotelDao hotelDao;
    private final HotelBusinessRules hotelBusinessRules;
    private final IImageService imageService;

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
        List<HotelImage> hotelImages = hotel.getHotelImages();
        if (hotelImages != null && !hotelImages.isEmpty()) {
            for (HotelImage hotelImage : hotelImages) {
                hotelImage.setHotel(hotel);
                imageService.saveHotelImage(hotelImage);
            }
        }
        hotel.setCreateDate(LocalDateTime.now());
        return this.hotelDao.save(hotel);
    }

    @Override
    public Hotel getById(int id) {
        this.hotelBusinessRules.checkIfHotelExists(id);
        return this.hotelDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        this.hotelBusinessRules.checkIfHotelExists(id);
        this.hotelDao.deleteById(id);
    }

    @Override
    public void update(Hotel hotel) {
        this.hotelDao.save(hotel); //todo: implement
    }
}
