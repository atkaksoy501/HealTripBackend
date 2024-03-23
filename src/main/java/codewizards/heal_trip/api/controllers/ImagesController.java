package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.abstracts.IImageService;
import codewizards.heal_trip.business.concretes.ImageService;
import codewizards.heal_trip.entities.HospitalImage;
import codewizards.heal_trip.entities.HotelImage;
import codewizards.heal_trip.entities.RetreatImage;
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
    public ResponseEntity<HotelImage> getHotelImageById(@PathVariable int image_id) {
        HotelImage hotelImage = imageService.getHotelImageById(image_id);
        if (hotelImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }

    @GetMapping(value = "/hospital/get/{image_id}")
    public ResponseEntity<HotelImage> getHospitalImageById(@PathVariable int image_id) {
        HotelImage hotelImage = imageService.getHotelImageById(image_id);
        if (hotelImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }

    @GetMapping(value = "/retreat/get/{image_id}")
    public ResponseEntity<RetreatImage> getRetreatImageById(@PathVariable int image_id) {
        RetreatImage retreatImage = imageService.getRetreatImageById(image_id);
        if (retreatImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(retreatImage, HttpStatus.OK);
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

    @PostMapping(value = "/retreat/save")
    public ResponseEntity<RetreatImage> saveRetreatImage(@RequestBody RetreatImage retreatImage) {
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
