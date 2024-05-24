package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.responses.images.GetImageResponseAsBase64;
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

    GetImageResponseAsBase64 getRetreatImageAsBase64ById(int image_id);

    void saveHotelImage(HotelImage hotelImage);

    void saveHospitalImage(HospitalImage hospitalImage);

    int saveRetreatImage(String base64RetreatImage);

    void deleteHotelImage(int image_id);

    void deleteHospitalImage(int image_id);

    void deleteRetreatImage(int image_id);

    GetImageResponseAsBase64 getHotelImageAsBase64ById(int image_id);

    GetImageResponseAsBase64 getHotelImageAsBase64ByHotelId(int hotel_id);

    GetImageResponseAsBase64 getHospitalImageAsBase64ById(int image_id);

    GetImageResponseAsBase64 getHospitalImageAsBase64ByHospitalId(int hospital_id);

    List<String> getAllHotelImagesAsBase64();

    List<String> getAllHospitalImagesAsBase64();

    List<RetreatImage> getAllRetreatImages();

    List<String> getAllRetreatImagesAsBase64();

    void saveRetreatImage(RetreatImage retreatImage);

    int saveHotelImage(String base64HotelImage);

    int saveHospitalImage(String base64HospitalImage);
}
