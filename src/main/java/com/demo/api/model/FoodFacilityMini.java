package com.demo.api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @description food_facility  main  data
 * @author rons
 * @date 2023-03-22
 */
@Entity
@Data
@Table(name="food_facility")
public class FoodFacilityMini implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     * food_items
     */
    @Column(name="food_items")
    private String foodItems;

    /**
     * latitude
     */
    @Column(name="latitude")
    private Double latitude;

    /**
     * longitude
     */
    @Column(name="longitude")
    private Double longitude;

}