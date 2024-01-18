package fit.hutech.core.lab122;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.sql.Date;
import java.util.function.*;
import java.util.stream.*;

public class ManageVehicle {
    ArrayList<Vehicle> vehicles;
    Date now = new Date(2024,1,19);
    double rate = 0;
    public ManageVehicle() {
        this.vehicles = new ArrayList<>();
        insertData();
    }

    public void insertData() {
        this.vehicles.add(new Truck(new Date(2010,1,10), "47C 99354", 1000));
        this.vehicles.add(new Truck(new Date(2022,2,19), "43C 33346", 2000));
        this.vehicles.add(new Truck(new Date(2000,9,10), "48C 34239", 1000));
        this.vehicles.add(new Truck(new Date(2013,5,2), "49C 34123", 2000));
        this.vehicles.add(new Truck(new Date(2023,12,12), "59C 35123", 3000));
        this.vehicles.add(new Car(16, true, new Date(2023,1,2), "59A 19923"));
        this.vehicles.add(new Car(4, false, new Date(2021,2,4), "59A 19924"));
        this.vehicles.add(new Car(7, true, new Date(2020,6,3), "59A 19925"));
        this.vehicles.add(new Car(5, false, new Date(2023,1,10), "59A 9999"));
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle instanceof Truck) {
            this.vehicles.add((Truck) vehicle);
        } else if (vehicle instanceof Car) {
            this.vehicles.add((Car) vehicle);
        }
    }

    public void display() {
        vehicles.stream()
                .filter(v -> v instanceof Truck)
                .forEach(v -> System.out.println(v.output()));

        vehicles.stream()
                .filter(v -> v instanceof Car)
                .forEach(v -> System.out.println(v.output()));

    }
    public void exportFile(String fileName) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            vehicles.stream()
                    .filter(v -> v instanceof Truck)
                    .forEach(v -> {
                        try {
                            writer.write(v.output());
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            vehicles.stream()
                    .filter(v -> v instanceof Car)
                    .forEach(v -> {
                        try {
                            writer.write(v.output());
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println("Export file success");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void findCarHaveMaxSeat(){
        vehicles.stream()
                .filter(Car.class::isInstance)
                .map( vehicle-> (Car) vehicle)
                .max(Comparator.comparing(Car::getNumberOfSeats))
                .ifPresentOrElse(
                        car -> System.out.println(car.output()),
                        () -> System.out.println("Not found")
                );
    }

    public void sortTruckByPayload(){
        vehicles.stream()
                .filter(Truck.class::isInstance)
                .map( vehicle -> (Truck) vehicle)
                .sorted(Comparator.comparing(Truck::getPayload))
                .forEach(truck -> System.out.println(truck.output()));
    }

    public void findBestPlate(){
        System.out.println("Best plate : ");
      vehicles.stream()
              .map(Vehicle::getLicensePlate)
              .filter(plate -> plate.matches("([0-9])\\1{3}.*"))
              .forEach(System.out::println);
    }
    public double getTimeUsed(Date manufacture, Date nowTime){
        // initialize
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        // set time
        calendar1.setTime(manufacture);
        calendar2.setTime(nowTime);

        // initialize variable
        int manufactureYear = calendar1.get(Calendar.YEAR);
        int manufactureMonth = calendar1.get(Calendar.MONTH);
        int manufactureDay = calendar1.get(Calendar.DATE);
        int nowYear = calendar2.get(Calendar.YEAR);
        int nowMonth = calendar2.get(Calendar.MONTH);
        int nowDay = calendar2.get(Calendar.DATE);

        // set condition
        if(manufactureDay > nowDay){
            nowMonth--;
        }
        if(manufactureMonth > nowMonth){
            nowYear--;
            nowMonth += 12;
        }

        int year = nowYear - manufactureYear;
        int month = nowMonth - manufactureMonth;

        return year + (double) month /12;
    }

    public void getTimeRegistration(){

    }
    public void carRegistrationFee(){
        Stream<Car> cars = vehicles.stream()
                .filter(Car.class::isInstance)
                .map(vehicle -> (Car) vehicle);

        cars.forEach(car -> {
            double timeUsed = getTimeUsed(car.getYearOfManufacture(),now);
            if( timeUsed <= 7){
                if(car.getNumberOfSeats() <= 9){
                    if(car.isRegistration()){
                        rate = 1;
                        outputWithFee(car,timeUsed,240000,rate);
                    }else{
                        rate = 2;
                        outputWithFee(car,timeUsed,240000,rate);
                    }
                }
                else{
                    rate = 1;
                    classificationCarByNumberSeat(car,timeUsed,rate);
                }
            }else{
                rate = 0.5;
                classificationCarByNumberSeat(car,timeUsed,rate);
            }
        });
    }

    public void truckRegistrationFee(){
        Stream<Truck> trucks = vehicles.stream()
                .filter(Truck.class::isInstance)
                .map(vehicle -> (Truck) vehicle);

        trucks.forEach(truck -> {
            double timeUsed = getTimeUsed(truck.getYearOfManufacture(),now);
            if( timeUsed <= 20){
                rate = 0.5;
                classificationTruckByPayload(truck,timeUsed,rate);
            }else{
                rate = 0.25;
                classificationTruckByPayload(truck,timeUsed,rate);
            }
        });
    }

    public void outputWithFee( Vehicle vehicle , double timeUsed , int price , double rate ){
        String msg = """
                Time : %.3f
                Time Registered : %d
                Rate : %.2f
                Price : %d
                Fee : %d
                """.formatted(timeUsed,((int)(timeUsed/rate))
                ,rate,price,((int)(timeUsed / rate)) * price);
        System.out.print(vehicle.output());
        System.out.println(msg);
    }

    public void classificationTruckByPayload(Truck truck , double timeUsed , double rate){
        if(truck.getPayload() <= 7){
            outputWithFee(truck,timeUsed,320000,rate);
        }
        else if(truck.getPayload() <= 20){
            outputWithFee(truck,timeUsed,350000,rate);
        }
        else{
            outputWithFee(truck,timeUsed,560000,rate);
        }
    }

    public void classificationCarByNumberSeat(Car car, double timeUsed, double rate){
        if(car.getNumberOfSeats() == 10){
            outputWithFee(car,timeUsed,240000,rate);
        }else{
            outputWithFee(car,timeUsed,320000,rate);
        }
    }

    public void periodicRegistration(){
       carRegistrationFee();
       truckRegistrationFee();
    }

}
