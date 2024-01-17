package fit.hutech.core.lab122;

public class Lab122 {

    public void run(){
        ManageVehicle manageVehicle = new ManageVehicle();
        manageVehicle.display();
        try{
            manageVehicle.exportFile("./src/main/java/fit/hutech/core/lab122/files/output.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        manageVehicle.findCarHaveMaxSeat();
        manageVehicle.sortTruckByPayload();
        manageVehicle.FindBestPlate();
    }
}
