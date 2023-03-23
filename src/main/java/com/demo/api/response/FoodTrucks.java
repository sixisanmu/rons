package com.demo.api.response;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class FoodTrucks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id
     */
    @Column(name="id")
    private Long id;

    /**
     * locationid
     */
    @Column(name="locationid")
    private Integer locationid;

    /**
     * applicant
     */
    @Column(name="applicant")
    private String applicant;

    @Column(name="food_items")
    private String foodItems;

    /**
     * facility_type
     */
    @Column(name="facility_type")
    private String facilityType;

    /**
     * cnn
     */
    @Column(name="cnn")
    private Integer cnn;

    /**
     * location_description
     */
    @Column(name="location_description")
    private String locationDescription;

    /**
     * address
     */
    @Column(name="address")
    private String address;

    @Column(name="distince")
    private Long distince;




}
