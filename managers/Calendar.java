package managers;

import models.events;                                   // bring the data from events class to do operations

import java.util.ArrayList;
import java.util.List;

public class Calendar
{
    private List<events> newList = new ArrayList<>();             // create a array list to store events

    // method/function to add a new event
    public void add(String eventName, String date, String time, String createBy) 
    {
        events newEvent = new events(eventName, date, time, createBy);                  // creates a new event using the parameters from the input given by the user
        newList.add(newEvent);                                                          // adds it to the list
        System.out.println("Event added.");                                           // printout statement for the user
    }

    // method/function to view all the events that are added/created to the list
    public void viewAll()
    {
        if(newList.isEmpty())   
        {
            System.out.println("No current events.");
        }
        else
        {
            System.err.println("Upcoming events: \n");
            for(events i: newList)
            {                                                           
                System.out.println(i);                                  // prints out all the events from the list
            }
        }
    }

    //method/function to delete the event given the number
    public void delete(int eventNo)
    {

        events delEvents = null;
        for(events event: newList)
        {
            if(event.getEventNo() == eventNo)       // traverse through the list array to match the number input
            {
                delEvents = event;                 // stores it to delete
                break;
            }
        }
        if(delEvents != null)
        {
            newList.remove(delEvents);                  // deletes the event
            System.out.println("Event " + "is deleted.");
        }
        else
        {
            System.out.println("Event "+ "not found.");
        }
    }
    //method/function to send RSVP to an event
    public void rsvpInEvent(int eventNo, String username)
    {
        for(events event: newList)
        {
            if(event.getEventNo() == eventNo)                           //find the event with the id number
            {
                if(event.rsvp(username))
                {
                    System.out.println("Rsvp'd to the event!");        // out -> the RSPV notification
                }
                else
                {
                    System.out.println("You have already Rsvp'd to the event.");        // out -> already did notification
                }
                return;
            }
        }
        System.out.println("Invalid event.");
    }
    
}