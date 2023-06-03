package com.androidexam.shubclassroom.model.profile;

public class ProfileIn4 {
    private String name;
    private String email;
    private String dateOfBirth;
    private String school;
    private int grade;
    private String phoneNumber;
    private String address;
    private boolean gender;

    public ProfileIn4(String name, String email, String dateOfBirth, String school, int grade, String phoneNumber, String address, boolean gender) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.school = school;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth.substring(0, 10);
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return String.valueOf(grade);
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
