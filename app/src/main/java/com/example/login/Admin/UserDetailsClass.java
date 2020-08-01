package com.example.login.Admin;

public class UserDetailsClass {

    String FullName,UserName,Emailid,Password,Age,Gender,MobileNo;
    public UserDetailsClass(){}


    public UserDetailsClass(String fullName, String userName, String emailid, String password, String age, String gender, String mobileNo) {
        FullName = fullName;
        UserName = userName;
        Emailid = emailid;
        Password = password;
        Age = age;
        Gender = gender;
        MobileNo = mobileNo;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmailid() {
        return Emailid;
    }

    public void setEmailid(String emailid) {
        Emailid = emailid;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}
