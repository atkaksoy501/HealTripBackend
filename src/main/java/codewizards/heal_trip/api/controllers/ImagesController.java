package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.abstracts.IImageService;
import codewizards.heal_trip.business.concretes.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/image")
@CrossOrigin
public class ImagesController {
    private IImageService imageService;

    @Autowired
    public ImagesController(ImageService imageService){
        this.imageService = imageService ;
    }
    @GetMapping(value = "/hotel/get/{image_id}")
    public ResponseEntity<String> getHotelImageById(@PathVariable int image_id) {
        String hotelImage = imageService.getHotelImageAsBase64ById(image_id);
        if (hotelImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }

    @GetMapping(value = "/hospital/get/{image_id}")
    public ResponseEntity<String> getHospitalImageById(@PathVariable int image_id) {
        String hospitalImage = imageService.getHospitalImageAsBase64ById(image_id);
        if (hospitalImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hospitalImage, HttpStatus.OK);
    }

    @GetMapping(value = "/retreat/get/{image_id}")
    public ResponseEntity<String> getRetreatImageById(@PathVariable int image_id) {
        String retreatImage = imageService.getRetreatImageAsBase64ById(image_id);
        if (retreatImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(retreatImage, HttpStatus.OK);
    }

    @PostMapping(value = "/hotel/save")
    public ResponseEntity<String> saveHotelImage(@RequestBody String hotelImage) {
        imageService.saveHotelImage(hotelImage);
        return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }

    @PostMapping(value = "/hospital/save")
    public ResponseEntity<String> saveHospitalImage(@RequestBody String hospitalImage) {
        imageService.saveHospitalImage(hospitalImage);
        return new ResponseEntity<>(hospitalImage, HttpStatus.OK);
    }

    @PostMapping(value = "/retreat/save")
    public ResponseEntity<String> saveRetreatImage(@RequestBody String retreatImage) {
        imageService.saveRetreatImage(retreatImage);
        return new ResponseEntity<>(retreatImage, HttpStatus.OK);
    }

    @DeleteMapping(value = "/hotel/delete/{image_id}")
    public ResponseEntity<String> deleteHotelImage(@PathVariable int image_id) {
        imageService.deleteHotelImage(image_id);
        return new ResponseEntity<>("Hotel Image with id " + image_id + " has been deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/hospital/delete/{image_id}")
    public ResponseEntity<String> deleteHospitalImage(@PathVariable int image_id) {
        imageService.deleteHotelImage(image_id);
        return new ResponseEntity<>("Hospital Image with id " + image_id + " has been deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/retreat/delete/{image_id}")
    public ResponseEntity<String> deleteRetreatImage(@PathVariable int image_id) {
        imageService.deleteRetreatImage(image_id);
        return new ResponseEntity<>("Retreat Image with id " + image_id + " has been deleted", HttpStatus.OK);
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
