package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.HospitalImageDao;
import codewizards.heal_trip.dataAccess.HotelImageDao;
import codewizards.heal_trip.entities.HospitalImage;
import codewizards.heal_trip.entities.Hotel;
import codewizards.heal_trip.entities.HotelImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    private HotelImageDao hotelImageDao;
    private HospitalImageDao hospitalImageDao;

    @Autowired
    public ImageService(HotelImageDao hotelImageDao, HospitalImageDao hospitalImageDao) {
        this.hotelImageDao = hotelImageDao;
        this.hospitalImageDao = hospitalImageDao;
    }
    public HotelImage getHotelImageById(int image_id) {
        return hotelImageDao.findById(image_id).orElse(null);
    }

    public HospitalImage getHospitalImageById(int image_id) {
        return hospitalImageDao.findById(image_id).orElse(null);
    }

    public void saveHotelImage(HotelImage hotelImage) {
        hotelImageDao.save(hotelImage);
    }

    public void saveHospitalImage(HospitalImage hospitalImage) {
        hospitalImageDao.save(hospitalImage);
    }

    public void deleteHotelImage(int image_id) {
        hotelImageDao.deleteById(image_id);
    }

    public void deleteHospitalImage(int image_id) {
        hospitalImageDao.deleteById(image_id);
    }

    public HotelImage getHotelImageByHotelId(int hotel_id) {
        return hotelImageDao.findByHotelId(hotel_id);
    }

    public HospitalImage getHospitalImageByHospitalId(int hospital_id) {
        return hospitalImageDao.findByHospitalId(hospital_id);
    }
}
