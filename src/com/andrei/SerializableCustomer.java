package com.andrei;

import java.io.Serializable;

// Serializable interface does not have methods, only tell jvm that the class can be serialized.
// All the class fields must be serializable as well. Not the case in this example because we use primitive fields.
public class SerializableCustomer  implements Serializable {
    private String firstName;
    private String lastName;
    private int id;

    //must have exactly this name
    //not mandatory, but is a good practice
    private long serialVersionUID = 55L;

    public SerializableCustomer(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id_number=" + id +
                '}';
    }
}
