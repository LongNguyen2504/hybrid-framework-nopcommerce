package utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private Locale local = new Locale("en");
    private Faker faker = new Faker();
    public static DataHelper getData(){
        return new DataHelper();
    }

    public String getFirstName(){
        return faker.address().firstName();
    }

    public String getLastName(){
        return faker.address().lastName();
    }

    public String getEmailAddress(){
        return faker.internet().emailAddress();
    }

    public String getCityName(){
        return faker.address().cityName();
    }

    public String getPhoneNumber(){
        return faker.phoneNumber().phoneNumber();
    }

    public String getAddress(){
        return faker.address().streetAddress();
    }

    //Password at lease 6 digits included Uppercases,special character
    public String getPassword(){
        return faker.internet().password(7,8,true,true);
    }


}
