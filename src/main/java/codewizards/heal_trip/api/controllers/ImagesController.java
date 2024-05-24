package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.DTOs.requests.images.AddImageRequestAsBase64;
import codewizards.heal_trip.business.DTOs.responses.images.GetImageResponseAsBase64;
import codewizards.heal_trip.business.abstracts.IImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Image Management", description = "Image Management APIs")
@RestController
@RequestMapping(value = "/image")
@CrossOrigin
@AllArgsConstructor
public class ImagesController {
    private final IImageService imageService;

    @Operation(summary = "Get Hotel Image by ID")
    @GetMapping(value = "/hotel/get/{image_id}")
    public ResponseEntity<GetImageResponseAsBase64> getHotelImageById(@PathVariable int image_id) {
        GetImageResponseAsBase64 hotelImage = imageService.getHotelImageAsBase64ById(image_id);
        if (hotelImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }

    @Operation(summary = "Get Hotel Image by Hotel ID")
    @GetMapping(value = "/hotel/get/hotel/{hotel_id}")
    public ResponseEntity<GetImageResponseAsBase64> getHotelImageByHotelId(@PathVariable int hotel_id) {
        GetImageResponseAsBase64 hotelImage = imageService.getHotelImageAsBase64ByHotelId(hotel_id);
        if (hotelImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hotelImage, HttpStatus.OK);
    }

    @Operation(summary = "Get Hospital Image by ID")
    @GetMapping(value = "/hospital/get/{image_id}")
    public ResponseEntity<GetImageResponseAsBase64> getHospitalImageById(@PathVariable int image_id) {
        GetImageResponseAsBase64 hospitalImage = imageService.getHospitalImageAsBase64ById(image_id);
        if (hospitalImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hospitalImage, HttpStatus.OK);
    }

    @Operation(summary = "Get Hospital Image by Hospital ID")
    @GetMapping(value = "/hospital/get/hospital/{hospital_id}")
    public ResponseEntity<GetImageResponseAsBase64> getHospitalImageByHospitalId(@PathVariable int hospital_id) {
        GetImageResponseAsBase64 hospitalImage = imageService.getHospitalImageAsBase64ByHospitalId(hospital_id);
        if (hospitalImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(hospitalImage, HttpStatus.OK);
    }

    @Operation(summary = "Get Retreat Image by ID")
    @GetMapping(value = "/retreat/get/{image_id}")
    public ResponseEntity<GetImageResponseAsBase64> getRetreatImageById(@PathVariable int image_id) {
        GetImageResponseAsBase64 retreatImage = imageService.getRetreatImageAsBase64ById(image_id);
        if (retreatImage == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(retreatImage, HttpStatus.OK);
    }

    @Operation(summary = "Save Hotel Image")
    @PostMapping(value = "/hotel/save")
    public ResponseEntity<Integer> saveHotelImage(@Valid @RequestBody AddImageRequestAsBase64 hotelImage) {
        return new ResponseEntity<>(imageService.saveHotelImage(hotelImage.getImage()), HttpStatus.OK);
    }

    @Operation(summary = "Save Hospital Image")
    @PostMapping(value = "/hospital/save")
    public ResponseEntity<Integer> saveHospitalImage(@Valid @RequestBody AddImageRequestAsBase64 hospitalImage) {
        return new ResponseEntity<>(imageService.saveHospitalImage(hospitalImage.getImage()), HttpStatus.OK);
    }

    @Operation(summary = "Save Retreat Image")
    @PostMapping(value = "/retreat/save")
    public ResponseEntity<Integer> saveRetreatImage(@Valid @RequestBody AddImageRequestAsBase64 retreatImage) {
        return new ResponseEntity<>(imageService.saveRetreatImage(retreatImage.getImage()), HttpStatus.OK);
    }

    @Operation(summary = "Delete Hotel Image by ID")
    @DeleteMapping(value = "/hotel/delete/{image_id}")
    public ResponseEntity<String> deleteHotelImage(@PathVariable int image_id) {
        imageService.deleteHotelImage(image_id);
        return new ResponseEntity<>("Hotel Image with id " + image_id + " has been deleted", HttpStatus.OK);
    }

    @Operation(summary = "Delete Hospital Image by ID")
    @DeleteMapping(value = "/hospital/delete/{image_id}")
    public ResponseEntity<String> deleteHospitalImage(@PathVariable int image_id) {
        imageService.deleteHotelImage(image_id);
        return new ResponseEntity<>("Hospital Image with id " + image_id + " has been deleted", HttpStatus.OK);
    }

    @Operation(summary = "Delete Retreat Image by ID")
    @DeleteMapping(value = "/retreat/delete/{image_id}")
    public ResponseEntity<String> deleteRetreatImage(@PathVariable int image_id) {
        imageService.deleteRetreatImage(image_id);
        return new ResponseEntity<>("Retreat Image with id " + image_id + " has been deleted", HttpStatus.OK);
    }
}
