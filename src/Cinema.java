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
        shows.add(new Show("Spiderman The Movie", "21/03/2022", theatres.get(0)));

        System.out.println("Welcome to Cinema Booking System");
        System.out.println("Please Login");
        System.out.println("Enter your username : ");
        String username = sc.nextLine();
        System.out.println("Enter your password : ");
        String password = sc.next();
        sc.nextLine();
        System.out.println("Enter your account type (Customer / Admin) : ");
        String type = sc.nextLine();
        Customer userDetail = new Customer(username, password, type);

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


                    System.out.println("-------------------------\n");
                    String strPayType = null;
                    int end = 1;
                    while (end != 0) {
                        //problem : start loop after settle e-wallet
                        System.out.println("Please select your payment method. \n1. Cash \n2. Online \n3. Card");
                        int payType = sc.nextInt();
                        if (payType == 1) {
                            System.out.println();
                            strPayType = "Cash";
                            end = 0;
                        } else if (payType == 2) {
                            System.out.println();
                            strPayType = "Online Payment";
                            end = 0;
                        } else if (payType == 3) {
                            System.out.println();
                            strPayType = "Card";
                            end = 0;
                        } else {
                            end = 1;
                        }

                        Payment payment = new Payment(strPayType);
                        Cash cash = new Cash(strPayType);
                        Online online = new Online(strPayType, "");

                        switch (payType) {
                            case 1:
                                System.out.println(cash.toString() + customer2.getId());
                                break;
                            case 2:
                                int rpt = 1;
                                while (rpt != 0) {
                                    System.out.println("Pick your online payment type. \n1. e-Wallet \n2. FPX (Online Banking)");
                                    int onType = sc.nextInt();

                                    int transID = rnd.nextInt(999999);
                                    int otpNo = rnd.nextInt(9999);
                                    Online eWallet = new eWallet(strPayType, "", Integer.toString(transID));
                                    Online fpx = new FPX(strPayType, "", otpNo);
                                    if (onType == 1) {
                                        online.setType("e-Wallet");
                                        System.out.println(online);
                                        System.out.println(eWallet);

                                        rpt = 0;
                                    } else if (onType == 2) {
                                        online.setType("FPX (Online Banking)");
                                        System.out.println(online);
                                        System.out.println(fpx);
                                        rpt = 0;
                                    } else {
                                        System.out.println("Wrong Method Selection! Please select between 1 and 2.");
                                        rpt = 1;
                                    }
                                }
                                break;
                            case 3:
                                online.setType("Card");
                                Card card = new Card("", "");
                                int cardNo = sc.nextInt();
                                System.out.println("Your card has been charged! Thank you.");
                                break;
                        }
                    }
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
                System.out.println("Please Enter 3 to Display Show");
                System.out.println("Please Enter 4 to Exit");

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
                    System.exit(0);
                }

            } while (true);
        } else {
            System.out.println("Invalid account type");
        }
    }
}