package org.miage.placesearcher.model;

/**
 * Created by rapha on 08/01/2018.
 */

public class Place {

    private String street;
    private String zipCode;
    private String city;
    private int image;

    public Place(String street, String zipCode, String city, int image) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
