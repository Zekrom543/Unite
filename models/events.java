package models;

import java.util.ArrayList;
import java.util.List;


public class events {
    
    private String date;    
    private String eventName;
    private String time;
    private static int counter = 1;    //Starting the eventNo at 1 to make tracking easier 
    private int eventNo;
    private int rsvpCount;
    private String createdBy;
    private List<String> rsvpmembers = new ArrayList<>();

    //Encapsualtion is used by creating the public events constructer, and by making get methods which get the data. In this program we don't need setters.


    public events(String eventName, String date, String time, String createdBy) {  //constructer in order to handle the attributes of the event 
        this.eventNo = counter++;   //incrementing the eventNo as this will be completely created by the code 
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.createdBy = createdBy;
    }

    public String getDate() {       // every "get" function are getters for returning the respective attrivute of the event 
        return date;
    }

    public String getEventName() {              // getter function to get the name of the event
        return eventName;
    }

    public String getTime() {                   // getter function to get the time of the event
        return time;
    }

    public boolean rsvp(String username)
    {
        if(rsvpmembers.contains(username))                  // function that returns T/F based one if user rsvp'd or not
        {
            return false;
        }
        else
        {
            rsvpmembers.add(username);
            rsvpCount++;
            return true;
        }
    }

    public boolean hasRsvpd(String username)
    {
        return rsvpmembers.contains(username);                // saves the user to the list
    }

    public int getEventNo() {                              // return the event number choice       
        return eventNo;
    }

    public int getRsvpCount()                       // return the count after incrementing and saving
    {
        return rsvpCount;
    }

    public void incRsvpCount() {     //Setting RSVP count at zero just in case the event does not need an RSVP
        rsvpCount++;
    }

    public String getCreatedBy() {     //"Created By" is useful for searching through the file which carries all the events
        return createdBy;
    }

    public String toString() {      //This is the format of the event the user will recieve terminal wise 
        return "Event Name: " + eventName + "\n" + "Date: " + date + "\n" +  "Time: " + time + "\n" + "Created by: " + createdBy + "\n" +  "RSVP: " + rsvpCount + "\n";
    }

}
