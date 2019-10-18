package people;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PeopleEvent extends People {

    //REQUIRES: String name and Integer amt
    //MODIFIES: the list people
    //EFFECTS: creates new Person with name and amt and add to the list people
    public void addPerson(String name, String event, String date) {

        Person addedPerson = new Person(name, event, date);
        people.add(addedPerson);
    }

    @Override
    //MODIFIES: people
    //EFFECTS: create Persons from input file, and add said Person to people list
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("eventInfo.txt", "UTF-8");
        for (Person p: people){
            writer.println(p.name+":"+p.event+":"+p.date+":");
        }
        writer.close();

    }

    @Override
    //MODIFIES: output file
    //EFFECTS: make the people list back into a .txt file
    public void make() throws IOException {
        List<String> persons = Files.readAllLines(Paths.get("eventInfo.txt"));
        for (String p : persons) {
            ArrayList<String> elementsofP = splitOnColin(p);
            people.add(new Person(elementsofP.get(0), elementsofP.get(1), elementsofP.get(2)));  //elementopP.get(0) as the person's name, and the amount owe
        }
    }

    @Override
    // EFFECTS: create list of events with corresponding name, event, and date
    protected StringBuilder summary() {
        StringBuilder statement = new StringBuilder();
        for (Person p: people){
            statement.append("You are: " + p.event + " on " + p.date + " with " + p.name + "\n");
        }
        statement.append("");
        return statement;
    }

    //EFFECTS: print out summary of amount owe to everyone
    public String showEventSummary() {
        return summary().toString();
    }

    //EFFECTS: return size of event list
    public int getSize(){
        return people.size();
    }

    //EFFECTS: return event at position i
    public String getEvent(int i){
        Person p = people.get(i);
        return "You have Event: " + p.event + " on " + p.date + " with " + p.name;
    }

    public void deleteEvent(int i){
        people.remove(i-1);

    }
}