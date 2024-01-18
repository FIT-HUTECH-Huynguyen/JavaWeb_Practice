package fit.hutech.core.lab122;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;

public class Vehicle {
    private Date yearOfManufacture;
    private String licensePlate;
    public static Scanner sc = new Scanner(System.in);
    public Vehicle(Date yearOfManufacture, String licensePlate) {
        this.yearOfManufacture = yearOfManufacture;
        this.licensePlate = licensePlate;
    }
    public Vehicle(){
        this.yearOfManufacture = new Date(0,0,0);
        this.licensePlate = "";
    }
    public Date getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Date yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void input(){
        System.out.print("Enter year of manufacture : ");
        System.out.print("Enter Day: ");
        int Day = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Month: ");
        int Month = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Year: ");
        int Year = Integer.parseInt(sc.nextLine());
        this.yearOfManufacture = new Date(Year-1900, Month-1, Day);

        System.out.print("Enter license plate : ");
        this.licensePlate = sc.nextLine();
    }

    public String output(){
        Date date  = new Date(this.yearOfManufacture.getYear() - 1900 , this.yearOfManufacture.getMonth() - 1, this.yearOfManufacture.getDate());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
       return """
               Year of manufacture : %s
               License plate : %s
               """.formatted(strDate, this.licensePlate);
    }
}
