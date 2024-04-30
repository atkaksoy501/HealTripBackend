package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.DTOs.responses.images.GetImageResponseAsBase64;
import codewizards.heal_trip.business.abstracts.IImageService;
import codewizards.heal_trip.business.rules.ImageBusinessRules;
import codewizards.heal_trip.core.converter.Base64ToByteConverter;
import codewizards.heal_trip.core.converter.ByteToBase64Converter;
import codewizards.heal_trip.dataAccess.HospitalImageDao;
import codewizards.heal_trip.dataAccess.HotelImageDao;
import codewizards.heal_trip.dataAccess.RetreatImageDao;
import codewizards.heal_trip.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageService implements IImageService {
    private final HotelImageDao hotelImageDao;
    private final HospitalImageDao hospitalImageDao;
    private final RetreatImageDao retreatImageDao;
    private final ImageBusinessRules imageBusinessRules;

    public HotelImage getHotelImageById(int image_id) {
        imageBusinessRules.checkIfHotelImageExists(image_id);
        return hotelImageDao.findById(image_id).orElse(null);
    }

    public List<HotelImage> getAllHotelImages() {
        return hotelImageDao.findAll();
    }

    public HospitalImage getHospitalImageById(int image_id) {
        imageBusinessRules.checkIfHositalImageExists(image_id);
        return hospitalImageDao.findById(image_id).orElse(null);
    }

    public List<HospitalImage> getAllHospitalImages() {
        return hospitalImageDao.findAll();
    }

    public GetImageResponseAsBase64 getHotelImageAsBase64ById(int image_id) {
        imageBusinessRules.checkIfHotelImageExists(image_id);
        HotelImage image = hotelImageDao.findById(image_id).orElse(null);
        GetImageResponseAsBase64 response = new GetImageResponseAsBase64();
        response.setId(image.getId());
        response.setImage(ByteToBase64Converter.convert(image.getImage()));
        return response;
    }

    public GetImageResponseAsBase64 getHotelImageAsBase64ByHotelId(int hotel_id) {
        imageBusinessRules.checkIfHotelImageExistsByHotelId(hotel_id);
        HotelImage image = hotelImageDao.findByHotelHotelId(hotel_id);
        GetImageResponseAsBase64 response = new GetImageResponseAsBase64();
        response.setId(image.getId());
        response.setImage(ByteToBase64Converter.convert(image.getImage()));
        return response;
    }

    public List<String> getAllHotelImagesAsBase64() {
        List<HotelImage> images = hotelImageDao.findAll();
        return images.stream().map(image -> ByteToBase64Converter.convert(image.getImage())).toList();
    }

    public GetImageResponseAsBase64 getHospitalImageAsBase64ById(int image_id) {
        imageBusinessRules.checkIfHositalImageExists(image_id);
        HospitalImage image = hospitalImageDao.findById(image_id).orElse(null);
        GetImageResponseAsBase64 response = new GetImageResponseAsBase64();
        response.setId(image.getId());
        response.setImage(ByteToBase64Converter.convert(image.getImage()));
        return response;
    }

    public List<String> getAllHospitalImagesAsBase64() {
        List<HospitalImage> images = hospitalImageDao.findAll();
        return images.stream().map(image -> ByteToBase64Converter.convert(image.getImage())).toList();
    }

    public List<RetreatImage> getAllRetreatImages() {
        return retreatImageDao.findAll();
    }

    public List<String> getAllRetreatImagesAsBase64() {
        List<RetreatImage> images = retreatImageDao.findAll();
        return images.stream().map(image -> ByteToBase64Converter.convert(image.getImage())).toList();
    }


    public RetreatImage getRetreatImageById(int image_id) {
        imageBusinessRules.checkIfRetreatImageExists(image_id);
        return retreatImageDao.findById(image_id).orElse(null);
    }

    public GetImageResponseAsBase64 getRetreatImageAsBase64ById(int image_id) {
        imageBusinessRules.checkIfRetreatImageExists(image_id);
        RetreatImage image = retreatImageDao.findById(image_id).orElse(null);
        GetImageResponseAsBase64 response = new GetImageResponseAsBase64();
        response.setId(image.getId());
        response.setImage(ByteToBase64Converter.convert(image.getImage()));
        return response;
    }

    public void saveHotelImage(HotelImage hotelImage) {
        hotelImage.setCreateDate(LocalDateTime.now());
        hotelImageDao.save(hotelImage);
    }

    public void saveHospitalImage(HospitalImage hospitalImage) {
        hospitalImage.setCreateDate(LocalDateTime.now());
        hospitalImageDao.save(hospitalImage);
    }

    public void saveRetreatImage(RetreatImage retreatImage) {
        retreatImage.setCreateDate(LocalDateTime.now());
        retreatImageDao.save(retreatImage);
    }

    public int saveHotelImage(String base64HotelImage) {
        HotelImage hotelImage = new HotelImage();
        hotelImage.setImage(Base64ToByteConverter.convert(base64HotelImage));
        hotelImage.setCreateDate(LocalDateTime.now());
        return hotelImageDao.save(hotelImage).getId();
    }

    public int saveHospitalImage(String base64HospitalImage) {
        HospitalImage hospitalImage = new HospitalImage();
        hospitalImage.setImage(Base64ToByteConverter.convert(base64HospitalImage));
        hospitalImage.setCreateDate(LocalDateTime.now());
        return hospitalImageDao.save(hospitalImage).getId();
    }

    public int saveRetreatImage(String base64RetreatImage) {
        RetreatImage retreatImage = new RetreatImage();
        retreatImage.setImage(Base64ToByteConverter.convert(base64RetreatImage));
        retreatImage.setCreateDate(LocalDateTime.now());
        return retreatImageDao.save(retreatImage).getId();
    }

    public void deleteHotelImage(int image_id) {
        imageBusinessRules.checkIfHotelImageExists(image_id);
        hotelImageDao.deleteById(image_id);
    }

    public void deleteHospitalImage(int image_id) {
        imageBusinessRules.checkIfHositalImageExists(image_id);
        hospitalImageDao.deleteById(image_id);
    }

    public void deleteRetreatImage(int image_id) {
        imageBusinessRules.checkIfRetreatImageExists(image_id);
        retreatImageDao.deleteById(image_id);
    }
}
