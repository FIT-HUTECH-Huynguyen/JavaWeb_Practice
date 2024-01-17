package fit.hutech.core.lab121;

import java.util.*;

public class Lab121 {
    public void run(){
        List<Books> books = new ArrayList<>();
        var Scanner = new Scanner(System.in);

        var msg = """
                Program Book Manager
                1. Add book
                2. Delete book
                3. Update book
                4. Show book
                5. Find book by name
                6. Find book by price P 
                7. Find book by author
                0. Exit
                SELECT:
                """;
        int choice;

        do {
            System.out.println(msg);
            choice = Integer.parseInt(Scanner.nextLine());

            switch (choice){
                case 1 -> {
                    var book = new Books();
                    book.input();
                    books.add(book);
                }
                case 2 -> {
                    System.out.println("Enter id book : ");
                    var id = Integer.parseInt(Scanner.nextLine());
                    books.stream()
                            .filter(book -> book.getId() == id)
                            .findFirst()
                            .ifPresent(books::remove);
                    System.out.println("Delete success");
                }
                case 3 -> {
                    System.out.println("Enter id book : ");
                    var id = Integer.parseInt(Scanner.nextLine());
                    books.stream()
                            .filter(book -> book.getId() == id)
                            .findFirst()
                            .ifPresent(Books::input);
                    System.out.println("Update success");
                }
                case 4 -> {
                    System.out.println("List book : ");
                    books.forEach(Books::output);
                }
                case 5 ->{
                    System.out.println("Enter name book : ");
                    var name = Scanner.nextLine();
                    books.stream()
                            .filter(book -> book.getNameBook().equals(name))
                            .forEach(Books::output);
                    System.out.println("Find success");
                }
                case 6 ->{
                    System.out.println("Enter price book : ");
                    var price = Double.parseDouble(Scanner.nextLine());
                    System.out.println("Enter number book : ");
                    var number = Integer.parseInt(Scanner.nextLine());
                    books.stream()
                            .filter(book -> book.getPrice() <= price)
                            .limit(number)
                            .forEach(Books::output);
                }
                case 7 ->{
                    System.out.println("Enter author book : ");
                    var author = Scanner.nextLine();
                    Set<String> authors = new HashSet<>(Arrays.asList(author));
                    books.stream()
                            .filter(book -> authors.contains(book.getAuthor()))
                            .forEach(Books::output);
                }
                case 0 -> System.exit(0);
                default -> throw new IllegalStateException();
            }
        }while(choice != 0);
    }
}
