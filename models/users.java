package models;

public class users
{
    // declaring private variables to protect the data inside
    private String user_name;           // no one can access this from outside
    private String password; 
    private String code = "0808";   //The code given will be specific to a community/residence
    private String adminCode = "1930";



    // a new constructor to use the input and save them 

    //Encapsualtion is used by creating the public user constructer, and by making get methods which get the data. In this program we don't need setters.

    public users(String name, String password)
    {
        this.user_name  = name;                     // saves the info into the object using this
        this.password = password;
        
    }

    public String getUsername()                 // simple function to return/save the username the user inputs
    {
        return user_name;
    }

    public String getPassword()
    {
        return password;
    }

    public String getcode()
    {
        return code;
    }

    public boolean PasswordCheck(String input)                 // password check with a void type function so there wont be any errors while returning
    {
        if(password.equals(input))                          // uses .equals to check if the input matches
        {
            System.out.println("Login successful.");
            return true;
        }
        else
        {
            System.out.println("Incorrect password.");  
            return false;        // if not, prints incorrect password
        }
    }

    public boolean adminCheck(String adkey) {           //checks if the admin code matches with the instance variable set (1930)
        if(adkey.equals(adminCode)) {
            System.out.println("Admin Mode");
            return true;
        }
        else {
            
            return false;
        }
    }

    public void codeCheck(String key)
    {
        if(key.equals(code))     //key is the user inputing a code, the condition checks if it equals to the community code 
        {
            System.out.println("Permission granted  :) ");      //if matched the user gets access
        }
        else
        {
            System.out.println("Permission denied.  :( ");      //if not the user is denied access
        }
    }

}