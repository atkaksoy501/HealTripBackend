package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.HospitalImage;
import codewizards.heal_trip.entities.HotelImage;

public interface IImageService {
   HotelImage getHotelImageById(int image_id);

    HospitalImage getHospitalImageById(int image_id);

    void saveHotelImage(HotelImage hotelImage);

    void saveHospitalImage(HospitalImage hospitalImage);

    void deleteHotelImage(int image_id);

    void deleteHospitalImage(int image_id);

    //HotelImage getHotelImageByHotelId(int hotel_id);

    //HospitalImage getHospitalImageByHospitalId(int hospital_id);
}
