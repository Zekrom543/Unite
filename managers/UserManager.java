package managers;

import models.users;    //importing the users.java to be used here
import java.util.List;
import java.util.ArrayList;



public class UserManager {
    private List<users>User;    

    public UserManager() {
        User = new ArrayList<>();   //creates the list called "User"
    }

    public boolean register(String username, String password) {         //Method to allow new people to regesiter an account 
        for (users u: User) {
            if(u.getUsername().equals(username)) {
                return false;
            }
        }
        User.add(new users(username, password));    //once failing the check we allow them to enter their username and password and add it to the Array list
        return true;
    }

    public users login(String username, String password) {      //Checks if the user is the user and that their password matches 
        for(users u : User) {
            if(u.getUsername().equalsIgnoreCase(username)) {
                if(u.PasswordCheck(password)) {
                    return u;
                }
            }
        }
        return null;   //return null if the login was invalid 
    }

    public List<users> getAllUsers() {          //Gives all the users for the admin
        return User;
    }
    
    


}
