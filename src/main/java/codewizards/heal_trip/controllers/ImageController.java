package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IImageService;
import codewizards.heal_trip.business.ImageService;
import codewizards.heal_trip.entities.HospitalImage;
import codewizards.heal_trip.entities.HotelImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
    private IImageService imageService;

    @Autowired
    public ImageController(ImageService imageService){
        this.imageService = imageService ;
    }
    @GetMapping(value = "/hotel/getById/{image_id}")
    public ResponseEntity<HotelImage> getHotelImageById(@PathVariable int image_id) {
        HotelImage hotelImage = imageService.getHotelImageById(image_id);
        if (hotelImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }

    @GetMapping(value = "/hospital/getById/{image_id}")
    public ResponseEntity<HotelImage> getHospitalImageById(@PathVariable int image_id) {
        HotelImage hotelImage = imageService.getHotelImageById(image_id);
        if (hotelImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }

    @PostMapping(value = "/hotel/save")
    public ResponseEntity<HotelImage> saveHotelImage(@RequestBody HotelImage hotelImage) {
        imageService.saveHotelImage(hotelImage);
        return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }

    @PostMapping(value = "/hospital/save")
    public ResponseEntity<HospitalImage> saveHospitalImage(@RequestBody HospitalImage hospitalImage) {
        imageService.saveHospitalImage(hospitalImage);
        return new ResponseEntity<>(hospitalImage, HttpStatus.OK);
    }

    @DeleteMapping(value = "/hotel/deleteById/{image_id}")
    public ResponseEntity<String> deleteHotelImage(@PathVariable int image_id) {
        imageService.deleteHotelImage(image_id);
        return new ResponseEntity<>("Hotel Image with id " + image_id + " has been deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/hospital/deleteById/{image_id}")
    public ResponseEntity<String> deleteHospitalImage(@PathVariable int image_id) {
        imageService.deleteHotelImage(image_id);
        return new ResponseEntity<>("Hospital Image with id " + image_id + " has been deleted", HttpStatus.OK);
    }

    //@GetMapping(value = "/hotel/getByHotelId/{hotel_id}")
    //public ResponseEntity<HotelImage> getHotelImageByHotelId(@PathVariable int hotel_id) {
    //    HotelImage hotelImage = imageService.getHotelImageByHotelId(hotel_id);
    //    if (hotelImage == null)
    //        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    //    else
    //        return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    //}

    //@GetMapping(value = "/hospital/getByHospitalId/{hospital_id}")
    //public ResponseEntity<HotelImage> getHospitalImageByHospitalId(@PathVariable int hospital_id) {
    //    HotelImage hotelImage = imageService.getHotelImageByHotelId(hospital_id);
    //    if (hotelImage == null)
    //        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    //    else
    //        return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    //}
}
