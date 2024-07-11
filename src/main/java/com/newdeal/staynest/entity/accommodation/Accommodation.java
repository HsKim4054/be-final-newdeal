package com.newdeal.staynest.entity.accommodation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newdeal.staynest.entity.Host;
import com.newdeal.staynest.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "accommodation")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "accomm_id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "host_id")
    private Host host;

    @Column(name = "accomm_name", nullable = false)
    private String name;

    @Column(name = "accomm_category", nullable = false)
    private String category;

    @Column(name = "room_category", nullable = false)
    private String roomCategory;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "detail_address", nullable = true)
    private String detailAddress;

    @Column(name = "max_guests", nullable = false)
    private int maxGuests;

    @Column(name = "accomm_price", nullable = false)
    private String price;

    @Column(name = "check_in", nullable = false)
    private String checkIn;

    @Column(name = "check_out", nullable = false)
    private String checkOut;

    @Column(name = "accomm_content", nullable = false)
    private String content;

    @JsonIgnore
    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccommodationImg> images;

    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    private int avg;

    private double latitude;
    private double longitude;



}
