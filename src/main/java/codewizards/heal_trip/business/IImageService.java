package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.HospitalImage;
import codewizards.heal_trip.entities.HotelImage;
import codewizards.heal_trip.entities.RetreatImage;

public interface IImageService {
   HotelImage getHotelImageById(int image_id);

    HospitalImage getHospitalImageById(int image_id);

    RetreatImage getRetreatImageById(int image_id);

    void saveHotelImage(HotelImage hotelImage);

    void saveHospitalImage(HospitalImage hospitalImage);

    void saveRetreatImage(RetreatImage retreatImage);

    void deleteHotelImage(int image_id);

    void deleteHospitalImage(int image_id);

    void deleteRetreatImage(int image_id);
}
