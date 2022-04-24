import java.util.*;

public class Cinema {
    public static void main(String[] args) {
        int option;
        ArrayList<Show> shows = new ArrayList<>();
        ArrayList<Theatre> theatres = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        Theatre testTheatre = new Theatre(1, "Main Theatre");
        testTheatre.createRows(1, 10, 7);
        theatres.add(testTheatre);
        shows.add(new Show("SpongeBob - Film", "21/03/2022", theatres.get(0)));

        System.out.println("Welcome to Cinema Booking System");
        System.out.println("Please Login");
        System.out.println("Enter your username : ");
        String username = sc.nextLine();
        System.out.println("Enter your password : ");
        String password = sc.next();
        sc.nextLine();
        System.out.println("Enter your account type (Customer / Admin) : ");
        String type = sc.nextLine();
        Customer userDetail = new Customer(username, type);

        if (userDetail.getType().equals("Customer") || userDetail.getType().equals("CUSTOMER") || userDetail.getType().equals("customer")) {
            do {
                System.out.println("------------------------------------");
                System.out.println("Welcome to Cinema Booking System " + userDetail.getUsername());
                System.out.println("------------------------------------\n");
                System.out.println("Please Enter 1 to Display Shows");
                System.out.println("Please Enter 2 to Make Booking");
                System.out.println("Please Enter 3 to Cancel Booking");
                System.out.println("Please Enter 4 to Exit");

                System.out.print("Enter Option: ");
                option = sc.nextInt();

                if (option == 1) {
                    System.out.println("Shows Available");
                    System.out.println("-------------------------\n");
                    for (int i = 0; i < shows.size(); i++) {
                        int showNumber = i + 1;
                        System.out.println("Show Number: " + showNumber);
                        System.out.println("Show Name: " + shows.get(i).getShowName());
                        System.out.println("Show Date: " + shows.get(i).getShowDate());
                        //System.out.println("Seat Status:" + shows.get(i).getFreeSeatsCount());
                        System.out.println("\n");
                    }
                    System.out.println("End of Show List.\n");
                }

                if (option == 2) {
                    System.out.println("Book Your Seats");
                    System.out.println("-------------------------\n");
                    for (int i = 0; i < shows.size(); i++) {
                        int showNumber = i + 1;
                        System.out.println("Show Number: " + showNumber);
                        System.out.println("Show Name:   " + shows.get(i).getShowName());
                        System.out.println("Show Date:   " + shows.get(i).getShowDate());
                        System.out.print("\n");
                    }
                    System.out.println("-------------------------");
                    System.out.print("Enter the show number: ");
                    int showNumber = sc.nextInt();
                    //sc.nextLine(); //temporary
                    ///System.out.println("Please enter your name : "); //temporary
                    ///String customerName = sc.nextLine(); //temporary
                    ///Customer customer1 = new Customer(customerName); //temporary
                    int repeat;
                    System.out.println();
                    Random rnd = new Random();
                    int bookingId = rnd.nextInt(999);
                    Customer customer2 = new Customer(bookingId);
                    customers.add(customer2);
                    do {
                        shows.get(showNumber - 1).getTheatre().printSeatPlan();
                        System.out.print("Enter the row: ");
                        int selectedRow = sc.nextInt();
                        System.out.print("Enter the seat: ");
                        int selectedSeat = sc.nextInt();
                        System.out.println();
                        Booking booking = new Booking(customer2, shows.get(showNumber - 1));
                        if (booking.reserveSeat(selectedRow - 1, selectedSeat - 1)) {
                            bookings.add(booking);
                            System.out.println("The seat is now reserved for you " + userDetail.getUsername()); //temporary
                        } else {
                            System.out.println("Sorry the seat is already reserved.");
                        }
                        System.out.println();
                        System.out.print("Enter 1 to reserve another seat or 2 to check out: ");
                        repeat = sc.nextInt();
                    } while (repeat == 1);
                    System.out.println();
                    System.out.println("Your Bill");
                    System.out.println("-------------------------");
                    int totalCost = 0;
                    for (Booking booking : bookings) {
                        if (booking.getCustomer().getId() == customer2.getId()) {
                            totalCost += booking.getCost();
                        }
                    }
                    System.out.println("\nBooking ID: " + customer2.getId());
                    System.out.println("Customer Name: " + userDetail.getUsername()); //temporary
                    System.out.println("Total costs: MYR " + totalCost);
                    System.out.println();
                }

                if (option == 3) {
                    System.out.println("Cancel Your Booking");
                    System.out.println("-------------------------\n");
                    System.out.print("Enter your booking id: ");
                    int bookingID = sc.nextInt();
                    for (Customer customer : customers) {
                        if (customer.getId() == bookingID) {
                            for (Booking booking : bookings) {
                                if (booking.getCustomer().getId() == customer.getId()) {
                                    booking.unreservedSeat();
                                }
                            }
                            System.out.println("Your reservation has been canceled!");
                        }
                    }
                    System.out.println();
                }

                if (option == 4) {
                    System.exit(0);
                }

            } while (true);

        } else if (userDetail.getType().equals("Admin") || userDetail.getType().equals("ADMIN") || userDetail.getType().equals("admin")) {

            do {
                System.out.println("------------------------------------");
                System.out.println("Welcome Back Admin!");
                System.out.println("------------------------------------\n");
                System.out.println("Please Enter 1 to Add Theatre");
                System.out.println("Please Enter 2 to Add Movie");
                System.out.println("Please Enter 3 to Exit");

                System.out.print("Enter Option: ");
                option = sc.nextInt();

                if (option == 1) {
                    System.out.println("Add Theatre");
                    System.out.println("-------------------------\n");
                    System.out.print("Enter a name for the theatre: \n");
                    sc.nextLine();
                    String theatreName = sc.nextLine();
                    System.out.print("Enter a number for the theatre: \n");
                    int theatreNumber = sc.nextInt();
                    System.out.println("Enter the number of rows:");
                    int rowCount = sc.nextInt();
                    Theatre theatre = new Theatre(theatreNumber, theatreName);
                    theatre.createRows(1, 10, rowCount);
                    theatre.createRows(1, 5, 5);
                    theatres.add(theatre);
                }

                if (option == 2) {
                    System.out.println("Add Show");
                    System.out.println("-------------------------\n");
                    System.out.println("Enter the date of the Show [DD/MM/YYYY]:");
                    String showDate = sc.next();
                    sc.nextLine();
                    System.out.print("Enter name of Show: \n");
                    String showName = sc.nextLine();
                    System.out.println("Select a theatre by typing the number:");
                    for (int i = 0; i < theatres.size(); i++) {
                        System.out.println(i + 1 + " " + theatres.get(i).getDescription());
                    }
                    int theatreNumber = sc.nextInt();
                    shows.add(new Show(showName, showDate, theatres.get(theatreNumber - 1)));
                }

                if (option == 3) {
                    System.exit(0);
                }

            } while (true);
        } else {
            System.out.println("Invalid account type");
        }
        /*do {
            System.out.println("------------------------------------");
            System.out.println(":Cinema Booking System :");
            System.out.println("------------------------------------\n");
            System.out.println("Please Enter 1 to Add Theatre");
            System.out.println("Please Enter 2 to Add Show");
            System.out.println("Please Enter 3 to Display Shows");
            System.out.println("Please Enter 4 to Make Booking");
            System.out.println("Please Enter 5 to Cancel Booking");
            System.out.println("Please Enter 6 to Exit\n");

            System.out.print("Enter Option: ");
            option = sc.nextInt();

            if (option == 1) {
                System.out.println("Add Theatre");
                System.out.println("-------------------------\n");
                System.out.print("Enter a name for the theatre: \n");
                sc.nextLine();
                String theatreName = sc.nextLine();
                System.out.print("Enter a number for the theatre: \n");
                int theatreNumber = sc.nextInt();
                System.out.println("Enter the number of rows:");
                int rowCount = sc.nextInt();
                Theatre theatre = new Theatre(theatreNumber, theatreName);
                theatre.createRows(1, 10, rowCount);
                theatre.createRows(1, 5, 5);
                theatres.add(theatre);
            }

            if (option == 2) {
                System.out.println("Add Show");
                System.out.println("-------------------------\n");
                System.out.println("Enter the date of the Show [DD/MM/YYYY]:");
                String showDate = sc.next();
                sc.nextLine();
                System.out.print("Enter name of Show: \n");
                String showName = sc.nextLine();
                System.out.println("Select a theatre by typing the number:");
                for (int i = 0; i < theatres.size(); i++) {
                    System.out.println(i + 1 + " " + theatres.get(i).getDescription());
                }
                int theatreNumber = sc.nextInt();
                shows.add(new Show(showName, showDate, theatres.get(theatreNumber - 1)));
            }

            if (option == 3) {
                System.out.println("Shows Available");
                System.out.println("-------------------------\n");
                for (int i = 0; i < shows.size(); i++) {
                    int showNumber = i + 1;
                    System.out.println("Show Number: " + showNumber);
                    System.out.println("Show Name: " + shows.get(i).getShowName());
                    System.out.println("Show Date: " + shows.get(i).getShowDate());
                    //System.out.println("Seat Status:" + shows.get(i).getFreeSeatsCount());
                    System.out.println("\n");
                }
                System.out.println("End of Show List.\n");
            }

            if (option == 4) {
                System.out.println("Book Your Seats");
                System.out.println("-------------------------\n");
                /*System.out.println("Please enter your name : ");
                String customerName = sc.nextLine();
                Customer customer1 = new Customer(customerName);
                Random rnd = new Random();
                int customerId = rnd.nextInt(999);
                Customer customer2 = new Customer(customerId);
                customers.add(customer2);*/
                /*for (int i = 0; i < shows.size(); i++) {
                    int showNumber = i + 1;
                    System.out.println("Show Number: " + showNumber);
                    System.out.println("Show Name:   " + shows.get(i).getShowName());
                    System.out.println("Show Date:   " + shows.get(i).getShowDate());
                    System.out.print("\n");
                }
                System.out.println("-------------------------");
                System.out.print("Enter the show number: ");
                int showNumber = sc.nextInt();
                sc.nextLine(); //temporary
                System.out.println("Please enter your name : "); //temporary
                String customerName = sc.nextLine(); //temporary
                Customer customer1 = new Customer(customerName); //temporary
                int repeat;
                System.out.println();
                Random rnd = new Random();
                int customerId = rnd.nextInt(999);
                Customer customer2 = new Customer(customerId);
                customers.add(customer2);
                do {
                    shows.get(showNumber - 1).getTheatre().printSeatPlan();
                    System.out.print("Enter the row: ");
                    int selectedRow = sc.nextInt();
                    System.out.print("Enter the seat: ");
                    int selectedSeat = sc.nextInt();
                    System.out.println();
                    Booking booking = new Booking(customer2, shows.get(showNumber - 1));
                    if (booking.reserveSeat(selectedRow - 1, selectedSeat - 1)) {
                        bookings.add(booking);
                        System.out.println("The seat is now reserved for you " + customer1.getName()); //temporary
                    } else {
                        System.out.println("Sorry the seat is already reserved.");
                    }
                    System.out.println();
                    System.out.print("Enter 1 to reserve another seat or 2 to check out: ");
                    repeat = sc.nextInt();
                } while (repeat == 1);
                System.out.println();
                System.out.println("Your Bill");
                System.out.println("-------------------------");
                int totalCost = 0;
                for (Booking booking : bookings) {
                    if (booking.getCustomer().getId() == customer2.getId()) {
                        totalCost += booking.getCost();
                    }
                }
                System.out.println("\nCustomer ID: " + customer2.getId());
                System.out.println("Customer Name: " + customer1.getName()); //temporary
                System.out.println("Total costs: MYR " + totalCost);
                System.out.println();
            }

            if (option == 5) {
                System.out.println("Cancel Your Booking");
                System.out.println("-------------------------\n");
                System.out.print("Enter the customer id: ");
                int customerId = sc.nextInt();
                for (Customer customer : customers) {
                    if (customer.getId() == customerId) {
                        for (Booking booking : bookings) {
                            if (booking.getCustomer().getId() == customer.getId()) {
                                booking.unreservedSeat();
                            }
                        }
                        System.out.println("Your reservation has been canceled!");
                    }
                }
                System.out.println();
            }

            if (option == 6) {
                System.exit(0);
            }

        } while (true);*/
    }
}