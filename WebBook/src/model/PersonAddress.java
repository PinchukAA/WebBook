package model;

public class PersonAddress implements java.io.Serializable{
    private String country;
    private String region;
    private String city;
    private String street;
    private Integer houseNumber;
    private Integer pavilionNumber;
    private Integer flatNumber;

    public PersonAddress(){
        this.country = new String();
        this.region = new String();
        this.city = new String();
        this.street = new String();
        this.houseNumber = new Integer(0);
        this.pavilionNumber = new Integer(0);
        this.flatNumber = new Integer(0);
    }

    public PersonAddress(String country, String region, String city, String street, Integer houseNumber, Integer pavilionNumber, Integer flatNumber){
        this.country = new String(country);
        this.region = new String(region);
        this.city = new String(city);
        this.street = new String(street);
        this.houseNumber = new Integer(houseNumber);
        this.pavilionNumber = new Integer(pavilionNumber);
        this.flatNumber = new Integer(flatNumber);
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region= region;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city= city;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }


    public Integer getPavilionNumber() {
        return pavilionNumber;
    }

    public void setPavilionNumber(Integer pavilionNumber) {
        this.pavilionNumber = pavilionNumber;
    }


    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

}
