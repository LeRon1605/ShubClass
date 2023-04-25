package com.androidexam.shubclassroom.model.auth;

public class AccountCreateDto {
    private String email;
    private String password;
    private String name;
    private String dateOfBirth;
    private String school;
    private String grade;
    private String phoneNumber;
    private String address;
    private String gender;
    private String role;

    public AccountCreateDto() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean isValid() {
        if (
                email.isEmpty() || password.isEmpty() || name.isEmpty() || password.isEmpty() || dateOfBirth.isEmpty() ||
                school.isEmpty() || grade.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || gender.isEmpty() || role.isEmpty()
        ) return false;
        return true;
    }

    public AccountCreateDto toBody() {
        AccountCreateDto body = new AccountCreateDto();
        body.name = this.name;
        body.gender = this.gender.equalsIgnoreCase("nam") ? "true" : "false";
        body.email = this.email;
        body.phoneNumber = this.phoneNumber;
        body.grade = this.grade;
        body.role = this.role.equalsIgnoreCase("học sinh") ? "Student" : "Teacher";
        body.password = this.password;
        body.dateOfBirth = this.dateOfBirth;
        body.address = this.address;
        body.school = this.school;
        return body;
    }

}
