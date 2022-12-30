package javaOOP;

import ch.qos.logback.core.net.SyslogOutputStream;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

public class CarPop {
    private String carCompany;
    private String carType;
    private String fuelType;
    private Double carPrice;

    public CarPop() {

    }

    public CarPop(String carCompany, String carType, String fuelType, Double carPrice) {
        this.carCompany = carCompany;
        this.carType = carType;
        this.fuelType = fuelType;
        this.carPrice = carPrice;
    }

    public String getCarCompany() {
        return carCompany;
    }

    public void setCarCompany(String carCompany) {
        this.carCompany = carCompany;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public void showCarInfo(){
        System.out.println(getCarCompany());
        System.out.println(getCarPrice());
        System.out.println(getFuelType());
        System.out.println(getCarType());
    }
    public static void main(String[] args) {
        CarPop honda = new CarPop();
        honda.setCarCompany("LCS");
        honda.setCarPrice(5000000d);
        honda.setFuelType("petrol");
        honda.setCarType("SUV");
        honda.showCarInfo();

        CarPop Bmw = new CarPop("cmc","sedan","gas",650000d);



    }
}
