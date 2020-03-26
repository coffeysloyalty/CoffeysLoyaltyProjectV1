package com.alicearmstrong.coffeysloyaltyprojectv1.database;

public class Customers

{
    String firstName,surname, DOB, contactNumber, email, qrCode;
    Integer loyaltyScore;

    public Customers(String firstName, String surname, String DOB, String contactNumber, String email, String qrCode, Integer loyaltyScore)
    {
        this.firstName = firstName;
        this.surname = surname;
        this.DOB = DOB;
        this.contactNumber = contactNumber;
        this.email = email;
        this.qrCode = qrCode;
        this.loyaltyScore = loyaltyScore;

    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getDOB()
    {
        return DOB;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public String getQrCode()
    {
        return qrCode;
    }

    public Integer getLoyaltyScore()
    {
        return loyaltyScore;
    }
}
