package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IHotelService;
import codewizards.heal_trip.business.IImageService;
import codewizards.heal_trip.entities.HospitalImage;
import codewizards.heal_trip.entities.Hotel;
import codewizards.heal_trip.entities.HotelImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class HotelsController {

    private IHotelService hotelService;
    private IImageService imageService;

    @Autowired
    public HotelsController(IHotelService hotelService, IImageService imageService) {
        super();
        this.hotelService = hotelService;
        this.imageService = imageService;
    }

    @GetMapping("/getAll")
    public List<Hotel> getAll(){
        return this.hotelService.getAll();
    }

    @GetMapping("/getAllDesc")
    public List<Hotel> getAllSorted() {
        return this.hotelService.getAllSorted();
    }

    @GetMapping("/getAllByPage")
    public List<Hotel> getAll(int pageNo, int pageSize){
        return this.hotelService.getAll(pageNo, pageSize);
    }

    @PostMapping("/add")
    public ResponseEntity<Hotel> add(@RequestBody Hotel hotel) {
        List<HotelImage> hotelImages = hotel.getHotelImages();
        if (hotelImages != null && !hotelImages.isEmpty()) { // todo: servis içinde yazılacak
            for (HotelImage hotelImage : hotelImages) {
                hotelImage.setHotel(hotel);
                imageService.saveHotelImage(hotelImage);
            }
        }
        return new ResponseEntity<>(this.hotelService.add(hotel), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public Hotel getById(@PathVariable int id) {
        return this.hotelService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        this.hotelService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Hotel hotel, @PathVariable int hotelId){
        this.hotelService.update(hotel);
    }
}
