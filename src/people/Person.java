package people;

import java.util.Date;

public class Person implements PersonThatYouOwe, PersonWithEvents {
    String name;
    int amountOwe;
    String event;
    String date;

    //REQUIRES: String name and Integer amt
    //MODIFIES: this
    //EFFECTS: create new Person with name and amount owe to them
    //         FOR peopleOwe CLASS
    public Person(String name, int amt) {
        this.name = name;
        this.amountOwe = amt;
    }

    //REQUIRES: String name, String event, and Date date
    //MODIFIES: this
    //EFFECTS: create new Person with name, event, and date of event
    //         FOR peopleEvent CLASS
    public Person(String name, String event, String date){
        this.name = name;
        this.event = event;
        this.date = date;
    }

    @Override
    public String showName() {
        return this.name;
    }

    @Override
    public void changeAmtYouOwe(int amt) {
        this.amountOwe = amt;
    }

    @Override
    public int showAmountOwe() {
        return this.amountOwe;
    }

    @Override
    public String getEvent() {return this.event;}

    @Override
    public String getDate() {return this.date;}
}