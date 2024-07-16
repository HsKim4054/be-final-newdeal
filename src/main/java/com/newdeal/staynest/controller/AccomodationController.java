package com.newdeal.staynest.controller;

import com.newdeal.staynest.dto.Acoomodation.AccommodationDto;
import com.newdeal.staynest.entity.accommodation.Accommodation;
import com.newdeal.staynest.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/accommodation")
public class AccomodationController {
    private final AccommodationService accommodationService;
    @GetMapping("/detailAccom")
    public ModelAndView detailAccom() {
        return new ModelAndView("accommodation/detailAccom");
    }

    @GetMapping("/detailAccom/{id}")
    public ModelAndView detailAccom(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("accommodation/detailAccom");
        Accommodation accommodation = accommodationService.getAccommodationById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid accommodation Id:" + id));
        mav.addObject("accommodation", accommodation);
        return mav;
    }


//--------기능 --------------------------------------------------
//    @PostMapping
//    public ResponseEntity<Accommodation> registerAccomm(@RequestBody AccommodationDto accommodationDto) {
//        Accommodation accomm = accommodationService.registerAccomm(accommodationDto);
//        return ResponseEntity.ok(accomm);
//    }
    @GetMapping
    public ResponseEntity<List<Accommodation>> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationService.getAllAccommodations();
        return ResponseEntity.ok(accommodations);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable Long id) {
        return accommodationService.getAccommodationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Accommodation> updateAccommodation(@PathVariable Long id, @RequestBody AccommodationDto accommDto) {
        Accommodation updatedAccomm = accommodationService.updateAccomm(id, accommDto);
        return ResponseEntity.ok(updatedAccomm);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccomm(id);
        return ResponseEntity.noContent().build();
    }

}