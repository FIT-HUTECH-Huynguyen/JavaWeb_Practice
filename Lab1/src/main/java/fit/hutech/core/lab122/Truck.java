package fit.hutech.core.lab122;

import java.sql.Date;

public class Truck extends Vehicle{
    private int payload;

    public Truck(Date yearOfManufacture, String licensePlate, int payload){
        super(yearOfManufacture, licensePlate);
        this.payload = payload;
    }
    public Truck(){
        super();
        this.payload = 0;
    }
    public Truck(int payload){
        super();
        this.payload = payload;
    }

    public double getPayload() {
        return payload;
    }

    public void setPayload(int payload) {
        this.payload = payload;
    }

    public void input(){
        super.input();
        System.out.println("Enter payload : ");
        this.payload = Integer.parseInt(sc.nextLine());
    }

    public String output(){
        return super.output() + """
                Payload : %d
                """.formatted(this.payload);
    }
}
