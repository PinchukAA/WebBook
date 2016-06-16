package model;


public class Person implements java.io.Serializable{

    private String firstName;
    private String lastName;
    private String surName;
    private String FIO;
    private PersonAddress address;

    public Person() {
        this.firstName = new String();
        this.lastName = new String();
        this.surName = new String();
        this.address = new PersonAddress();

        this.FIO = new String();
    }

    public Person(String firstName, String lastName, String surName, PersonAddress address) {
        this.firstName = new String(firstName);
        this.lastName = new String(lastName);
        this.surName = new String(surName);
        this.address = address;

        this.FIO = new String(surName + ' ' + firstName + ' ' + lastName);
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

    public void setLastName(String firstName) {
        this.lastName= firstName;
    }



    public String getSurName() {
        return surName;
    }

    public void setSurName(String firstName) {
        this.surName = firstName;
    }


    public PersonAddress getAddress(){
        return address;
    }


    public String getFIO() {
        return FIO;
    }

    public void setFIO(String lastName, String firstName, String surName) {
        this.FIO = surName + ' ' + firstName + ' ' + lastName;
    }

    public void setFIO() {
        this.FIO = surName + ' ' + firstName + ' ' + lastName;
    }

}