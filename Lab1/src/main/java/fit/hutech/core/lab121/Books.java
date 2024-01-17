package fit.hutech.core.lab121;

import java.util.Scanner;

public class Books {
    private int id;
    private String nameBook;
    private String author;
    private double price;

    public Books(){
        this.id = 0;
        this.nameBook = "";
        this.author = "";
        this.price = 0;
    }
    public Books(int id , String nameBook, String author, double price){
        this.id = id;
        this.nameBook = nameBook;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void input(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id : ");
        this.id = Integer.parseInt(sc.nextLine());
        System.out.println("Enter name book : ");
        this.nameBook = sc.nextLine();
        System.out.println("Enter author : ");
        this.author = sc.nextLine();
        System.out.println("Enter price : ");
        this.price = sc.nextDouble();
    }

    public void output(){
        var msg = """
                BOOK: id = %d, nameBook = %s, author = %s, price = %f
                """.formatted(id, nameBook, author, price);
        System.out.println(msg);
    }
}
