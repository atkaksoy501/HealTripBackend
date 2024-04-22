package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.DTOs.requests.images.AddImageRequestAsBase64;
import codewizards.heal_trip.business.DTOs.responses.images.GetImageResponseAsBase64;
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
    public ResponseEntity<GetImageResponseAsBase64> getHotelImageById(@PathVariable int image_id) {
        GetImageResponseAsBase64 hotelImage = imageService.getHotelImageAsBase64ById(image_id);
        if (hotelImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }

    @GetMapping(value = "/hospital/get/{image_id}")
    public ResponseEntity<GetImageResponseAsBase64> getHospitalImageById(@PathVariable int image_id) {
        GetImageResponseAsBase64 hospitalImage = imageService.getHospitalImageAsBase64ById(image_id);
        if (hospitalImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hospitalImage, HttpStatus.OK);
    }

    @GetMapping(value = "/retreat/get/{image_id}")
    public ResponseEntity<GetImageResponseAsBase64> getRetreatImageById(@PathVariable int image_id) {
        GetImageResponseAsBase64 retreatImage = imageService.getRetreatImageAsBase64ById(image_id);
        if (retreatImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(retreatImage, HttpStatus.OK);
    }

    @PostMapping(value = "/hotel/save")
    public ResponseEntity<Integer> saveHotelImage(@RequestBody AddImageRequestAsBase64 hotelImage) {
        imageService.saveHotelImage(hotelImage.getImage());
        return new ResponseEntity<>(-1, HttpStatus.OK);
    }

    @PostMapping(value = "/hospital/save")
    public ResponseEntity<Integer> saveHospitalImage(@RequestBody AddImageRequestAsBase64 hospitalImage) {
        imageService.saveHospitalImage(hospitalImage.getImage());
        return new ResponseEntity<>(-1, HttpStatus.OK);
    }

    @PostMapping(value = "/retreat/save")
    public ResponseEntity<Integer> saveRetreatImage(@RequestBody AddImageRequestAsBase64 retreatImage) {
        return new ResponseEntity<>(imageService.saveRetreatImage(retreatImage.getImage()), HttpStatus.OK);
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
