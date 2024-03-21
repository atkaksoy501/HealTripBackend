package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.abstracts.IImageService;
import codewizards.heal_trip.dataAccess.HospitalImageDao;
import codewizards.heal_trip.dataAccess.HotelImageDao;
import codewizards.heal_trip.dataAccess.RetreatImageDao;
import codewizards.heal_trip.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {
    private HotelImageDao hotelImageDao;
    private HospitalImageDao hospitalImageDao;
    private RetreatImageDao retreatImageDao;

    @Autowired
    public ImageService(HotelImageDao hotelImageDao, HospitalImageDao hospitalImageDao, RetreatImageDao retreatImageDao) {
        this.hotelImageDao = hotelImageDao;
        this.hospitalImageDao = hospitalImageDao;
        this.retreatImageDao = retreatImageDao;
    }
    public HotelImage getHotelImageById(int image_id) {
        return hotelImageDao.findById(image_id).orElse(null);
    }

    public List<HotelImage> getAllHotelImages() {
        return hotelImageDao.findAll();
    }

    public HospitalImage getHospitalImageById(int image_id) {
        return hospitalImageDao.findById(image_id).orElse(null);
    }

    public List<HospitalImage> getAllHospitalImages() {
        return hospitalImageDao.findAll();
    }


    public RetreatImage getRetreatImageById(int image_id) {
        return retreatImageDao.findById(image_id).orElse(null);
    }

    public void saveHotelImage(HotelImage hotelImage) {
        hotelImageDao.save(hotelImage);
    }

    public void saveHospitalImage(HospitalImage hospitalImage) {
        hospitalImageDao.save(hospitalImage);
    }

    public void saveRetreatImage(RetreatImage retreatImage) {
        retreatImageDao.save(retreatImage);
    }

    public void deleteHotelImage(int image_id) {
        hotelImageDao.deleteById(image_id);
    }

    public void deleteHospitalImage(int image_id) {
        hospitalImageDao.deleteById(image_id);
    }

    public void deleteRetreatImage(int image_id) {
        retreatImageDao.deleteById(image_id);
    }
}
