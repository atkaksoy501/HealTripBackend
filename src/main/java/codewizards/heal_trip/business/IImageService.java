package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.HotelImage;

public interface IImageService {
   HotelImage getImageById(int image_id);
}
