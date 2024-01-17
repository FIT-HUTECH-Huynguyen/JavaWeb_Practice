package fit.hutech.core.lab122;

import java.util.Scanner;

public class Vehicle {
    private String yearOfManufacture;
    private String licensePlate;
    public static Scanner sc = new Scanner(System.in);
    public Vehicle(String yearOfManufacture, String licensePlate) {
        this.yearOfManufacture = yearOfManufacture;
        this.licensePlate = licensePlate;
    }
    public Vehicle(){
        this.yearOfManufacture = "";
        this.licensePlate = "";
    }
    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void input(){
        System.out.println("Enter year of manufacture : ");
        this.yearOfManufacture = sc.nextLine();
        System.out.println("Enter license plate : ");
        this.licensePlate = sc.nextLine();
    }

    public String output(){
       return """
               Year of manufacture : %s
               License plate : %s
               """.formatted(this.yearOfManufacture, this.licensePlate);
    }
}
