package com.jags.model;

/**
 * Created by jparvathaneni on 12/20/15.
 */
public class Address implements java.io.Serializable {

    private String firstName, lastName, line1, line2, city, state, zip, phoneNumber, email;
    private int addressId, userId;

    public boolean matchName(String name){
        return name != null && (firstName.startsWith(name) || lastName.startsWith(name));
    }
    public boolean matchEmail(String email){
        return email != null && this.email.equalsIgnoreCase(email);
    }
    public boolean matchPhone(String phone){
        return phone != null && this.phoneNumber.equalsIgnoreCase(phone);
    }

    public String getDisplayName(){
        return  new StringBuffer(this.getFirstName()).append(", ").append(this.getLastName()).toString();
    }
    @Override
    public String toString() {
        return "Address{" +
                "id='" + addressId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
