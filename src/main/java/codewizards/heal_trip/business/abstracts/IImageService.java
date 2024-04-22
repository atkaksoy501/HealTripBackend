package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.entities.HospitalImage;
import codewizards.heal_trip.entities.HotelImage;
import codewizards.heal_trip.entities.RetreatImage;

import java.util.List;

public interface IImageService {
   HotelImage getHotelImageById(int image_id);

   List<HotelImage> getAllHotelImages();

    HospitalImage getHospitalImageById(int image_id);

    List<HospitalImage> getAllHospitalImages();

    RetreatImage getRetreatImageById(int image_id);

    String getRetreatImageAsBase64ById(int image_id);

    void saveHotelImage(HotelImage hotelImage);

    void saveHospitalImage(HospitalImage hospitalImage);

    void saveRetreatImage(String base64RetreatImage);

    void deleteHotelImage(int image_id);

    void deleteHospitalImage(int image_id);

    void deleteRetreatImage(int image_id);

 String getHotelImageAsBase64ById(int image_id);

    String getHospitalImageAsBase64ById(int image_id);

    List<String> getAllHotelImagesAsBase64();

    List<String> getAllHospitalImagesAsBase64();

    List<RetreatImage> getAllRetreatImages();

    List<String> getAllRetreatImagesAsBase64();

    void saveRetreatImage(RetreatImage retreatImage);

 void saveHotelImage(String base64HotelImage);

 void saveHospitalImage(String base64HospitalImage);
}
