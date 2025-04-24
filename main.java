import managers.UserManager;
import managers.Calendar;
import models.users;
import java.util.Scanner;   
                                    //File Handling imports     
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class main {

    public static void exitMethod(Scanner sc)           // function to exit the program 
    {
        sc.close(); 
        System.err.println("Exiting the program....");
        System.exit(0);
    }

    // function to save the user details in the files
    public static void saveUserToFile(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userInfo.txt", true))) {            // uses the basic buffer and file writer function to save to the file
            writer.write(username + "," + password);
            writer.newLine();                                           // prints it to the file
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }
    
    
    // function to load all the user details from useraManager.java
    public static void loadUsersFromFile(UserManager userManager) {
        try (BufferedReader reader = new BufferedReader(new FileReader("userInfo.txt"))) {
            String line;                                                                                        // uses simpla buffer and file reader methods to read/load from the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userManager.register(parts[0], parts[1]);                           // reads the user credentials, ignoring the comma using split
                }
            }
        } catch (IOException e) {
            System.out.println(" Could not load users from file.");             // use try-catch to find and error
        }
    }
    
    //Rewrite the file with all user details
    public static void rewriteUsersFile(UserManager userManager, String password) {
        try (PrintWriter writer = new PrintWriter("userInfo.txt")) {        
            for (users u : userManager.getAllUsers()) {
                writer.println(u.getUsername() + "," + u.getPassword());         // updates the user details within the current list 
            }
        } catch (IOException e) {
            System.out.println("Error rewriting user file.");
        }
    }

    //function to register the user
    public static void registerMethod(Scanner sc, UserManager userManager)
    {
        System.out.println("\nEnter new username: ");
        String username = sc.nextLine();

        System.out.println("Enter new password: ");                 // prompts the user for details
        String password = sc.nextLine();

        if(userManager.register(username, password))
        {
            saveUserToFile(username, password);
            System.out.println("Registration successfull.");                // save it
        }
        else
        {
            System.out.println("Username taken.");                      // if already in the file, return this
        }
    }

    // a function which is used by only the admin
    public static void printAllUsers(UserManager userManager) {
        System.out.println("\n--- User Information (Admin Access) ---");
        for (users u : userManager.getAllUsers()) {
            System.out.println("Username: " + u.getUsername() + ", Password: " + u.getPassword()); // prints the user details
        }
    }
    
       // method to login for the user 
       public static void loginMethod(Scanner sc, UserManager userManager, users currentUser, Calendar eventCalendar) {
        System.out.println("\nEnter username: ");
        String username = sc.nextLine();    //user input for username

        System.out.println("Enter password: ");
        String password = sc.nextLine();    //user input for password

        currentUser = userManager.login(username, password);        

        if(currentUser != null) {       //if current user is not empty and does have a valid username and password, we enter into the is statement
            System.out.println("Enter Community Access Code (or Admin code): ");
            String commounity_code = sc.nextLine();

            if(currentUser.adminCheck(commounity_code) == true) {       //checks if the community code input was an admincode, checks with the method in users.java
                while(true) {       //if true stay in this loop
                    System.out.println("\nAdmin Interface");
                    System.out.println("-------------------------");
                    System.out.println("\n1. View user info");
                    System.out.println("2. Go to user mode");
                    System.out.println("3. Exit Program");
                    System.out.println("Enter option: ");
                    int adminOption = sc.nextInt();     

                    if(adminOption == 1) {      //prints all user info from userInfo.txt in a nice format 
                        printAllUsers(userManager);
                    }
                    if(adminOption == 2) {      //if admin decides to return to user interface we break the if statements and prompts the login again to allow them to enter the community code
                        break;
                    }
                    if(adminOption == 3) {
                        exitMethod(sc);     //exits the entire program
                    }
                }
            }
            else {
            currentUser.codeCheck(commounity_code);         //checks the current user's commounity code to match their specific community 
            boolean userIn = true;      //Boolean to let the code know that the user is currently logged in
            while(userIn) {
                System.out.println("\nEvent Menu:");
                System.out.println("=============================");
                System.out.println("1. Add Event");
                System.out.println("2. View Events");
                System.out.println("3. Delete Event");
                System.out.println("4. Rsvp to event");
                System.out.println("5. Exit program");
                System.out.print("Choose an option-> ");
                
                try
                {
                    int option = sc.nextInt();

                    switch(option) {        //switch statments given for the options prompted above 
                        case 1:         //case 1 is for adding events into the array linked list
                        sc.nextLine();
                        System.out.println("Event Name: ");
                        String name = sc.nextLine();
                        System.out.println("Event Date (ex: 01/23/25): ");
                        String date = sc.nextLine();
                        System.out.println("Event Time (ex: 7:00pm): ");
                        String time = sc.nextLine();

                        eventCalendar.add(name, date, time, currentUser.getUsername());     //once user input is done add into list 
                        break;
                        
                        case 2:
                        eventCalendar.viewAll();    //prints all current events in the list, if empty will print message saying no current events
                        break;
                        
                        case 3: 
                        System.out.println("Enter event # to delete: ");       //if user wants to delete the event they have to view all first, then enter the event number in the order it was printed starting from 1
                        int ID = sc.nextInt();
                        sc.nextLine();
                        eventCalendar.delete(ID);   //gives the number as the parameter for delete method
                        break;
                        
                        case 4:
                        System.out.println("Enter the event # to send RSVP: ");         //same as delete but increments the RSVP count for that speicfic event
                        int rsvpID = sc.nextInt();
                        sc.nextLine();
                        eventCalendar.rsvpInEvent(rsvpID, currentUser.getUsername());
                        break;

                        case 5:
                        exitMethod(sc);         //exit program
                        
                        default:
                        System.out.println("Invalid Choice");
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Invalid format of choice, please choose again.");    //catching a non integer input 
                    sc.nextLine();
                }
            }

            }
            
        }
        else 
        {
            System.out.println("Invalid username or password");     //if username and password were not right or not in the userInfo file this will be prompted 
        }
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager userManager = new UserManager();    //Creates UserManager obj
        Calendar eventCalendar = new Calendar();        //Creates eventCalendar obj

        users currentUser = null;   //makes sure that the current user starts of empty before any regestration or login

        loadUsersFromFile(userManager);     //reads any data that is stored in the userInfo.txt file for returning users

        System.out.println("\n====================================================\n");
        System.out.println("Welcome to the Unite! The commounity calendar system!");
        System.out.println("\n====================================================\n");

        while(true) {
            System.out.println("\nMAIN MENU");
            System.out.println("---------------------");
            System.out.println("1. Exit");
            System.out.println("2. Register");
            System.out.println("3. Login ");
            System.out.println("Choose your option-> ");
            
            try
            {
                int choice  = sc.nextInt();
                sc.nextLine();

                switch(choice) {            //Switch statement allows for an easier read in the main function, 3 options with each option having a method called.
                    case 1:
                    exitMethod(sc);
                    break;
                    case 2:
                    registerMethod(sc, userManager);
                    break;
                    case 3:
                    loginMethod(sc, userManager, currentUser, eventCalendar);
                    break;
                    default:
                    System.out.println("Invalid choice, Try again.");
                }
            }
            catch(Exception e)      //Catch the input if it is not a number
            {
                System.out.println("Invalid format, please enter a number.");
                sc.nextLine();
            }

        }
        
    }
    
}