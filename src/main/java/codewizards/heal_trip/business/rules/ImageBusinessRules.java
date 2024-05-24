package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.ImageMessages;
import codewizards.heal_trip.dataAccess.HospitalImageDao;
import codewizards.heal_trip.dataAccess.HotelImageDao;
import codewizards.heal_trip.dataAccess.RetreatImageDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageBusinessRules {
    private final HospitalImageDao hospitalImageDao;
    private final HotelImageDao hotelImageDao;
    private final RetreatImageDao retreatImageDao;
    private final HotelBusinessRules hotelBusinessRules;
    private final HospitalBusinessRules hospitalBusinessRules;

    public void checkIfHospitalImageExists(int imageId) {
        if (!hospitalImageDao.existsById(imageId)) {
            throw new IllegalArgumentException(ImageMessages.IMAGE_NOT_FOUND);
        }
    }

    public void checkIfHotelImageExists(int imageId) {
        if (!hotelImageDao.existsById(imageId)) {
            throw new IllegalArgumentException(ImageMessages.IMAGE_NOT_FOUND);
        }
    }

    public void checkIfRetreatImageExists(int imageId) {
        if (!retreatImageDao.existsById(imageId)) {
            throw new IllegalArgumentException(ImageMessages.IMAGE_NOT_FOUND);
        }
    }

    public void checkIfHotelImageExistsByHotelId(int hotelId) {
        hotelBusinessRules.checkIfHotelExists(hotelId);
    }

    public void checkIfHospitalImageExistsByHospitalId(int hospitalId) {
        hospitalBusinessRules.checkHospitalExists(hospitalId);
    }
}
