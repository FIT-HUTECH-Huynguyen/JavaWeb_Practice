package fit.hutech.core.lab122;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class ManageVehicle {
    ArrayList<Vehicle> vehicles;

    public ManageVehicle() {
        this.vehicles = new ArrayList<>();
        insertData();
    }

    public void insertData() {
        this.vehicles.add(new Truck("2020", "47C 99354", 1000));
        this.vehicles.add(new Truck("2021", "43C 33346", 2000));
        this.vehicles.add(new Truck("2020", "48C 34239", 1000));
        this.vehicles.add(new Truck("2022", "49C 34123", 2000));
        this.vehicles.add(new Truck("2022", "59C 35123", 3000));
        this.vehicles.add(new Car(16, true, "2023", "59A 19923"));
        this.vehicles.add(new Car(4, false, "2021", "59A 19924"));
        this.vehicles.add(new Car(7, true, "2020", "59A 19925"));
        this.vehicles.add(new Car(5, false, "2023", "59A 9999"));
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

    public void FindBestPlate(){
        System.out.println("Best plate : ");
      vehicles.stream()
              .map(Vehicle::getLicensePlate)
              .filter(plate -> plate.matches("([0-9])\\1{3}.*"))
              .forEach(System.out::println);
    }

}
