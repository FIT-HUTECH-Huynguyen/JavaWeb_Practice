package fit.hutech.core.lab122;

import java.sql.Date;

public class Lab122 {

    public void run(){
        ManageVehicle manageVehicle = new ManageVehicle();
        manageVehicle.display();
        try{
            manageVehicle.exportFile("./Lab1/src/main/java/fit/hutech/core/lab122/files/vehicle.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        manageVehicle.findCarHaveMaxSeat();
        manageVehicle.sortTruckByPayload();
        manageVehicle.findBestPlate();
        Date date1 = new Date(2020,10,20);
        Date date2 = new Date (2024,1,2);
        System.out.println(manageVehicle.getTimeUsed(date1,date2));
        manageVehicle.periodicRegistration();
    }
}
