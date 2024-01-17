package fit.hutech.core.lab122;

public class Car extends Vehicle{
    private int numberOfSeats;
    private boolean isRegistration;

    public Car(){
        super();
        this.numberOfSeats = 0;
        this.isRegistration = false;
    }
    public Car(int numberOfSeats, boolean isRegistration){
        super();
        this.numberOfSeats = numberOfSeats;
        this.isRegistration = isRegistration;
    }
    public Car(int numberOfSeats, boolean isRegistration, String yearOfManufacture, String licensePlate){
        super(yearOfManufacture, licensePlate);
        this.numberOfSeats = numberOfSeats;
        this.isRegistration = isRegistration;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isRegistration() {
        return isRegistration;
    }

    public void setRegistration(boolean registration) {
        isRegistration = registration;
    }

    public void input(){
        super.input();
        System.out.println("Enter number of seats : ");
        this.numberOfSeats = Integer.parseInt(sc.nextLine());
        System.out.println("Enter registration : ");
        this.isRegistration = Boolean.parseBoolean(sc.nextLine());
    }

    public String output(){
        return super.output() + """
                Number of seats : %d
                Registration : %b
                """.formatted(this.numberOfSeats, this.isRegistration);
    }
}
