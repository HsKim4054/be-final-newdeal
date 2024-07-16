package com.newdeal.staynest.service;

import com.newdeal.staynest.dto.Acoomodation.AccommodationDto;
import com.newdeal.staynest.entity.Host;
import com.newdeal.staynest.entity.accommodation.Accommodation;
import com.newdeal.staynest.entity.accommodation.AccommodationImg;

import com.newdeal.staynest.repository.AccommodationImgRepository;
import com.newdeal.staynest.repository.AccommodationRepository;
import com.newdeal.staynest.exception.ResourceNotFoundException;
import com.newdeal.staynest.repository.HostRepository;
import com.newdeal.staynest.repository.ReviewRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final AccommodationImgRepository accommodationImgRepository;
    private final ReviewRepository reviewRepository;

    //<숙소 정보 변경(호스트만)>
//    public Accommodation updateAccomm(Long id, AccommodationDto accommDto) {
//        Accommodation existingAccomm = accommRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("숙소를 찾을수 없습니다."));
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long currentHostId = Long.valueOf(authentication.getName());
//
//        if (!existingAccomm.getHost().getId().equals(currentHostId)) {
//            throw new IllegalArgumentException("숙소 정보를 변경할 권한이 없습니다.");
//        }
//
//        existingAccomm.setName(accommDto.getName());
//        existingAccomm.setCategory(accommDto.getCategory());
//        existingAccomm.setRoomCategory(accommDto.getRoomCategory());
//        existingAccomm.setAddress(accommDto.getAddress());
//        existingAccomm.setDetailAddress(accommDto.getDetailAddress());
//        existingAccomm.setMaxGuests(accommDto.getMaxGuests());
//        existingAccomm.setPrice(accommDto.getPrice());
//        existingAccomm.setCheckIn(accommDto.getCheckIn());
//        existingAccomm.setCheckOut(accommDto.getCheckOut());
//        existingAccomm.setContent(accommDto.getContent());
//        existingAccomm.setLatitude(accommDto.getLatitude());
//        existingAccomm.setLongitude(accommDto.getLongitude());
//
//        return accommRepository.save(existingAccomm);
//    }
//      <숙소 등록 삭제(호스트만)>
//    public void deleteAccomm(Long id) {
//        Accommodation existingAccomm = accommRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("숙소를 찾을수 없습니다."));
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long currentHostId = Long.valueOf(authentication.getName());
//
//        if (!existingAccomm.getHost().getId().equals(currentHostId)) {
//            throw new IllegalArgumentException("숙소를 삭제할 권한이 없습니다.");
//        }
//
//        accommRepository.delete(existingAccomm);
//    }


    // <숙소 등록>
    @Transactional
    public Accommodation registerAccomm(AccommodationDto accommDto) {

        // Accommodation 엔티티 생성
        Accommodation accomm = Accommodation.builder()
                .host(accommDto.getHost())
                .name(accommDto.getName())
                .category(accommDto.getCategory())
                .roomCategory(accommDto.getRoomCategory())
                .address(accommDto.getAddress())
                .detailAddress(accommDto.getDetailAddress())
                .maxGuests(accommDto.getMaxGuests())
                .price(accommDto.getPrice())
                .checkIn(accommDto.getCheckIn())
                .checkOut(accommDto.getCheckOut())
                .content(accommDto.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        // Accommodation 엔티티를 데이터베이스에 저장
        accomm = accommodationRepository.save(accomm);

        // AccommodationImg 엔티티 생성 및 저장
        Accommodation Accomm = accomm;
//        List<AccommodationImg> images = accommDto.getImages().stream()
//                .map(url -> AccommodationImg.builder()
//                        .accommodation(Accomm)
//                        .imgUrl(String.valueOf(url))
//                        .createdAt(LocalDateTime.now())
//                        .build())
//                .collect(Collectors.toList());
//
//        accommodationImgRepository.saveAll(images);

        return Accomm;
    }

    //<숙소 전체 리스트 조회>
    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }
    //<숙소 상세 조회>
    public Optional<Accommodation> getAccommodationById(Long id) {
        return accommodationRepository.findById(id);
    }

    //----------------------<아래부터는 호스트만 가능해야되는 부분>---------------------------
    //<숙소 정보 수정>
    @Transactional
    public Accommodation updateAccomm(Long id, AccommodationDto accommDto) {
        Accommodation existingAccomm = accommodationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Accommodation not found"));

        // 기존 이미지 삭제
        accommodationImgRepository.deleteByAccommodation(existingAccomm);

        // 새로운 이미지 추가
        List<AccommodationImg> images = accommDto.getImages().stream()
                .map(url -> AccommodationImg.builder()
                        .accommodation(existingAccomm)
                        .imgUrl(String.valueOf(url))
                        .createdAt(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());

        // Accommodation 엔티티 업데이트
        existingAccomm.setName(accommDto.getName());
        existingAccomm.setCategory(accommDto.getCategory());
        existingAccomm.setRoomCategory(accommDto.getRoomCategory());
        existingAccomm.setAddress(accommDto.getAddress());
        existingAccomm.setDetailAddress(accommDto.getDetailAddress());
        existingAccomm.setMaxGuests(accommDto.getMaxGuests());
        existingAccomm.setPrice(accommDto.getPrice());
        existingAccomm.setCheckIn(accommDto.getCheckIn());
        existingAccomm.setCheckOut(accommDto.getCheckOut());
        existingAccomm.setContent(accommDto.getContent());
        existingAccomm.setLatitude(accommDto.getLatitude());
        existingAccomm.setLongitude(accommDto.getLongitude());
        existingAccomm.setImages(images);  // 이미지 설정

        return accommodationRepository.save(existingAccomm);
    }





    //<숙소 등록 삭제>
    public void deleteAccomm(Long id) {
        Accommodation existingAccomm = accommodationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Accommodation not found"));

        accommodationRepository.delete(existingAccomm);
    }

    public Accommodation getAccomById(Long id) {
        return accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found with id " + id));
    }

    // 로그인한 Host의 숙소 조회
    public List<Accommodation> getAllAccommodationsByHostId(Long id) {
        return accommodationRepository.findByHostId(id);
    }
}