package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IImageService;
import codewizards.heal_trip.business.ImageService;
import codewizards.heal_trip.entities.HotelImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
    private IImageService imageService;

    @Autowired
    public ImageController(ImageService imageService){
        this.imageService = imageService ;
    }
    @GetMapping(value = "/getById/{image_id}")
    public ResponseEntity<HotelImage> getHotelImageById(@PathVariable int image_id) {
        HotelImage hotelImage = imageService.getImageById(image_id);
        if (hotelImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }
}
