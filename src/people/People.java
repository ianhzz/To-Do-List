package people;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class People {
    protected List<Person> people = new ArrayList<>();

    protected abstract void save() throws FileNotFoundException, UnsupportedEncodingException;
    protected abstract void make() throws IOException;
    protected abstract StringBuilder summary();

    //REQUIRES: a String
    //EFFECTS: helper method to split input file into individual elements
    //         (name is 2n, amt is 2n+1; n is int >=0)
    static ArrayList<String> splitOnColin(String line){
        String[] splits = line.split(":");
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRES: given name matches a Person in list People
    //EFFECTS: returns Person with corresponding name
    Person selectPerson(String name) {
        Person selected = new Person("",0);
        for (Person p : people) {
            if (p.name.equals(name)) {
                selected = p;
            }
        }
        return selected;
    }

    //REQUIRES: name of Person
    //MODIFIES: People
    //EFFECTS: remove Person from list People
    public void removePerson(String name){
        people.remove(selectPerson(name));
    }

    //EFFECTS: create list of everyone's names
    private List<String> allNames() {
        List<String> names = new ArrayList<>();
        for (Person p : people) {
            names.add(p.name);
        }
        return names;
    }

    //EFFECTS: prints everyone's names in the list
    public List<String> getAllNames() {
        return allNames();
    }
}