package com.nopcommerce.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;

import java.io.File;
import java.util.List;

//Class này dùng để đọc và store data vào các biến từ file UserData.json trong folder resource
public class UserDataMapper {

    //Hàm này để get dữ liệu từ file json -> để map dc data từ json sang các biến global trong class này ta sẽ dùng annotation @JsonProperty
    public static UserDataMapper getUserData( ){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // line này báo lỗi khi có 1 value nào đó chưa dc lấy ra từ json
            return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + "/resources/UserData.json"), UserDataMapper.class);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonProperty("password")
    private String password;

    @JsonProperty("date")
    private String date;

    @JsonProperty("month")
    private String month;

    @JsonProperty("year")
    private String year;

    //Cách đọc file json có sub-value
    @JsonProperty("Login")
    private Login login;


    //Cách đọc file json có sub-value
    static class Login {
        @JsonProperty("username")
        private String username;
        @JsonProperty("pass")
        private String pass;
    }




    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getUsername() {
        return login.username;
    }

    public String getPass() {
        return login.pass;
    }


    //Tương tự ta cũng có thể lưu vào 1 list
/*    @JsonProperty("subjects")
    private List<Subject> subjects ;

    public List<Subject> getSubjects(){
        return subjects;
    }

    public static class Subject{
        @JsonProperty("name")
        private String name;

        @JsonProperty("point")
        private String point;

        public String getName(){
            return name;
        }

        public String getpoint(){
            return point;
        }
    }*/





}
