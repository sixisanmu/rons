package com.demo.api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @description food_facility
 * @author rons
 * @date 2023-03-22
 */
@Entity
@Data
@Table(name="food_facility")
public class FoodFacility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id
     */
    @Column(name = "id", nullable = true, length = 11)
    private Integer id;

    /**
     * locationid
     */
    @Column(name="locationid", nullable = true, length = 50)
    private String locationid;

    /**
     * applicant
     */
    @Column(name="applicant", nullable = true, length = 200)
    private String applicant;

    /**
     * facility_type
     */
    @Column(name="facility_type", nullable = true, length = 50)
    private String facilityType;

    /**
     * cnn
     */
    @Column(name="cnn", nullable = true, length = 50)
    private String cnn;

    /**
     * location_description
     */
    @Column(name="location_description", nullable = true, length = 500)
    private String locationDescription;

    /**
     * address
     */
    @Column(name="address", nullable = true, length = 300)
    private String address;

    /**
     * blocklot
     */
    @Column(name="blocklot", nullable = true, length = 50)
    private String blocklot;

    /**
     * block
     */
    @Column(name="block", nullable = true, length = 50)
    private String block;

    /**
     * lot
     */
    @Column(name="lot", nullable = true, length = 50)
    private String lot;

    /**
     * permit
     */
    @Column(name="permit", nullable = true, length = 50)
    private String permit;

    /**
     * status
     */
    @Column(name="status", nullable = true, length = 50)
    private String status;

    /**
     * food_items
     */
    @Column(name="food_items", nullable = true, length = 1500)
    private String foodItems;

    /**
     * x
     */
    @Column(name="x", nullable = true, length = 100)
    private String x;

    /**
     * y
     */
    @Column(name="y", nullable = true, length = 100)
    private String y;

    /**
     * latitude
     */
    @Column(name="latitude", nullable = true, length = 50)
    private String latitude;

    /**
     * longitude
     */
    @Column(name="longitude", nullable = true, length = 50)
    private String longitude;

    /**
     * schedule
     */
    @Column(name="schedule", nullable = true, length = 300)
    private String schedule;

    /**
     * dayshours
     */
    @Column(name="dayshours", nullable = true, length = 60)
    private String dayshours;

    /**
     * noi_sent
     */
    @Column(name="noi_sent", nullable = true, length = 100)
    private String noiSent;

    /**
     * approved
     */
    @Column(name="approved", nullable = true, length = 200)
    private String approved;

    /**
     * received
     */
    @Column(name="received", nullable = true, length = 100)
    private String received;

    /**
     * prior_permit
     */
    @Column(name="prior_permit", nullable = true, length = 100)
    private String priorPermit;

    /**
     * expiration_date
     */
    @Column(name="expiration_date", nullable = true, length = 100)
    private String expirationDate;

    /**
     * location
     */
    @Column(name="location", nullable = true, length = 100)
    private String location;

    /**
     * fire_prevention_districts
     */
    @Column(name="fire_prevention_districts", nullable = true, length = 200)
    private String firePreventionDistricts;

    /**
     * police_districts
     */
    @Column(name="police_districts", nullable = true, length = 200)
    private String policeDistricts;

    /**
     * supervisor_districts
     */
    @Column(name="supervisor_districts", nullable = true, length = 100)
    private String supervisorDistricts;

    /**
     * zip_codes
     */
    @Column(name="zip_codes", nullable = true, length = 100)
    private String zipCodes;

    /**
     * neighborhoods_old
     */
    @Column(name="neighborhoods_old", nullable = true, length = 100)
    private String neighborhoodsOld;

    public FoodFacility() {

    }

}