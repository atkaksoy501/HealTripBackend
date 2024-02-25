package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.HotelImageDTO;
import codewizards.heal_trip.entities.HotelImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    private HotelImageDTO hotelImageDTO;

    @Autowired
    public ImageService(HotelImageDTO hotelImageDTO) {
        this.hotelImageDTO = hotelImageDTO;
    }
    public HotelImage getImageById(int image_id) {
        return hotelImageDTO.findById(image_id).orElse(null);
    }
}
