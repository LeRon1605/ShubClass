package com.androidexam.shubclassroom.model.profile;

public class ProfileIn4Update {
    private String name;
    private String dateOfBirth;
    private String school;
    private int grade;
    private String phoneNumber;
    private String address;
    private boolean gender;

    public ProfileIn4Update(String name, String dateOfBirth, String school, int grade, String phoneNumber, String address, boolean gender) {
        this.name = name;
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

    public String getDateOfBirth() {
        return dateOfBirth;
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

    public int getGrade() {
        return grade;
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
