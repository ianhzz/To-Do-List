package people;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PeopleOwe extends People {
    //REQUIRES: String name and Integer amt
    //MODIFIES: the list people
    //EFFECTS: creates new Person with name and amt and add to the list people
    public void addPerson(String name, int amt) {
        Person addedPerson = new Person(name, amt);
        people.add(addedPerson);
    }

    //EFFECTS: creates list of owe statements from everyone in the list people
    protected StringBuilder summary() {
        StringBuilder statement = new StringBuilder();
        if (!people.isEmpty()) {
            for (Person p : people) {
                if (p.amountOwe <= 0)
                    statement.append("You do not owe " + p.name + " anything.\n");
                else
                    statement.append("You owe " + p.name + " $" + p.amountOwe + "\n");
            }
            return statement;
        }
        else statement.append("You do not owe anyone at the moment.");
        return statement;
    }

    //EFFECTS: return integer value of total amount of money owe
    private int totalOwe() {
        int total = 0;
        for (Person p : people) {
            total = p.amountOwe + total;
        }
        return total;
    }

    //MODIFIES: people
    //EFFECTS: create Persons from input file, and add said Person to people list
    public void make() throws IOException {
        List<String> persons = Files.readAllLines(Paths.get("peopleInfo.txt"));
        for (String p : persons) {
            ArrayList<String> elementsofP = splitOnColin(p);
            int amt = Integer.parseInt(elementsofP.get(1));  //changing the Amount of a Person from String to int
            people.add(new Person(elementsofP.get(0), amt));  //elementopP.get(0) as the person's name, and the amount owe
        }
    }

    //MODIFIES: output file
    //EFFECTS: make the people list back into a .txt file
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("peopleInfo.txt", "UTF-8");
        for (Person p: people){
            writer.println(p.name+":"+Integer.toString(p.amountOwe));
        }
        writer.close();
    }

    //EFFECTS: print out summary of amount owe to everyone
    public String showOweSummary() {
        return summary().toString();
    }

    //EFFECTS: return integer value of total amount of money owe
    public int showTotalOwe() {
        return totalOwe();
    }

//    //REQUIRES: a person's name that's in the list
//    //MODIFIES: person with that name
//    //EFFECTS: changes person's amount owe to user's input
//    public void changeAmount(String name) throws NotAValidAmountException {
//        Scanner scanner = new Scanner(System.in);
//        Person currentPerson = selectPerson(name);
//        System.out.println("You current owe " +currentPerson.name + " $"+currentPerson.amountOwe);
//        System.out.println("What would like to change the amount to?");
//        String changeToAmt = scanner.nextLine();
//        if (changeToAmt.matches("-?(0|[1-9]\\d*)")) {
//            int amt = Integer.parseInt(changeToAmt);
//            currentPerson.changeAmtYouOwe(amt);
//        }
//        else throw new NotAValidAmountException("That is not a valid amount! Please try again.");
//        System.out.println("You now owe "+ currentPerson.name + " $"+currentPerson.amountOwe + ". Press enter to continue.");
//    }
}
