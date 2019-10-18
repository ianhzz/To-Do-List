package userInterface.ui;


import currentWeather.WeatherFromWeb;
import exceptions.*;
import org.json.JSONException;
import people.PeopleEvent;
import people.PeopleOwe;
import testScores.listofTests;
import testScores.myTest;
import userInterface.gui.mainWindow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    static PeopleOwe peopleOwe;
    static PeopleEvent peopleEvent;
    static listofTests testScores;
    public static mainWindow iw;
    public WeatherFromWeb weather;

    public static void main(String[] args) throws IOException {

        peopleOwe = new PeopleOwe();
        peopleOwe.make();     // Make input file into Person objects (name, amt)

        peopleEvent = new PeopleEvent();
        peopleEvent.make();   // Make input file into Person objects (name, event, date)

        testScores = new listofTests();
        testScores.make();    // Make input file into myTest objects

        iw = new mainWindow();
        new Main();

    }

    public Main() {
        weather = new WeatherFromWeb();
    }

    public void loansSelection() {
        iw.changeOutput("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆ \n" +
                " LOANS - What would you like to do?                             \n" +
                " 1) View Detailed Summary!                                      \n" +
                " 2) View Total Amount You Owe!                                  \n" +
                " 3) Add/Delete a Person!                                        \n" +
//                " 4) Edit a Person's Amount!                                     \n" +
                "★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
    }

    public void loansShowSummary() {iw.changeOutput(peopleOwe.showOweSummary());}

    public void loansShowTotalOwe() {iw.changeOutput("Total: $" + peopleOwe.showTotalOwe());}

    public void loanAdd(String name, String amt) throws NonLetterNameException, NotAnIntegerException {
        int amtNum;
        if (notValidCharacters(name)) {
            throw new NonLetterNameException("A name can only contain letters! Please try again.");
        }
        if (amt.matches("-?(0|[1-9]\\d*)")) {
            amtNum = Integer.parseInt(amt);
            peopleOwe.addPerson(name, amtNum);
            iw.changeOutput("The person has been added. Please return to Main Menu.");
        }
        else throw new NotAnIntegerException("The amount you put in is not an integer! Please try again.");
    }

    public void loanDelete(String name) throws PersonNotInListException {
        if (peopleOwe.getAllNames().contains(name)) {
                peopleOwe.removePerson(name);
                iw.changeOutput("The person has been deleted. Please return to main menu.");
            }
            else throw new PersonNotInListException(" The person is not in the list! Please try again. \n" +
                "People currently in the list:\n "+ allLoanNames());
    }


    public void eventSelection(){
        iw.changeOutput("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆ \n" +
                                    " UPCOMING EVENTS - What would you like to do?                   \n" +
                                    " 1) View All Events!                                            \n" +
                                    " 2) Add/Delete An Event!                                        \n" +
                                    "★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
    }

    public void eventShowAllEvents(){
        iw.changeOutput(peopleEvent.showEventSummary());
    }

    public void eventAdd(String person, String des, String date) throws NonLetterNameException {
        if (notValidCharacters(person)){
            throw new NonLetterNameException("A name can only contain letters! PLease try again.");
        } else
        peopleEvent.addPerson(person, des, date);
        iw.changeOutput("The event has been added. Please return to Main Menu.");
    }

    public void eventDelete (String eventStringNum) throws InvalidInputException {
        int eventNum;
        if (eventStringNum.matches("[1-9]")){
        eventNum = Integer.parseInt(eventStringNum);
        } else throw new InvalidInputException("\n \nYour input is not an integer! Please try again.");
        if (eventNum <= peopleEvent.getSize()){
            peopleEvent.deleteEvent(eventNum);
            iw.changeOutput("The event has been deleted. Please return to main menu.");
        }
        else throw new InvalidInputException("\n \nYour input is invalid. Please try again.");
    }

    public void testSelection(){
        iw.changeOutput("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆ \n" +
                            " TEST SCORES - What would you like to do?                       \n" +
                            " 1) View Scores                                                 \n" +
                            " 2) Add/ Delete a Subject                                       \n" +
                            " 3) Add A Score To Existing Subject                             \n" +
                            "★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
    }

    public void testsShowTests(){
        try {
            iw.changeOutput(testScores.summary());
        } catch (NoTestRecordedException e) {
            e.printStackTrace();
        }
    }

    public void testAdd(String subject, String score) throws InvalidInputException {
        if (score.matches("^[0-9,]*$")){
            List<String> scorelist = splitOnComma(score);
            List<Integer> list = new ArrayList<>();
            for (String s : scorelist) {
                int split = Integer.parseInt(s);
                list.add(split);
            }
            testScores.addSubject(new myTest(subject,list));
            iw.changeOutput("The test has been add. Please return to Main Menu.");
        } else throw new InvalidInputException("The Score you entered is not a number! Please try again");
    }

    public void testDelete(String subject) throws InvalidInputException {
        if (testScores.getAllSubjects().contains(subject)){
            testScores.deleteSubject(testScores.getTest(subject));
            iw.changeOutput("The subject has been deleted. Please return to main menu.");
        } else throw new InvalidInputException("The subject you entered does not exist! Please try again.");
    }

    public void testAddScore(String subject, String score) throws InvalidInputException {
        int scoreNum;
        if (allSubjects().contains(subject)) {
            if (score.matches("^[0-9]*$")) {
                scoreNum = Integer.parseInt(score);
                testScores.getTest(subject).addScores(scoreNum);
                iw.changeOutput("The score has been recorded. Please return to Main Menu.");
            } else throw new InvalidInputException("The Score you entered is either 1)not a number or 2)not a single score! Please try again.");
        } else throw new InvalidInputException("The subject you entered does not exist! Please try again.");
    }

    public void showWeather() {
        try {
            iw.changeOutput("Today's weather in " + weather.getLocation().toUpperCase() + " : \n\n" +
                    "Temperature: \n"+
                    "Current           : " + weather.weatherCurrentTemp() + " °C \n" +
                    "Today's High : " + weather.weatherHighestTemp() + " °C\n" +
                    "Today's Low  : " + weather.weatherLowestTemp() + " °C \n\n" +
            "Weather Description: \n" + weather.weatherDescription().toUpperCase());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void saveAndQuit() {
        try {
            peopleEvent.save();
            peopleOwe.save();
            testScores.save();
            iw.changeOutput("Save!");
            TimeUnit.SECONDS.sleep(2);
            System.exit(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String allLoanNames(){
        List<String> names = peopleOwe.getAllNames();
        StringBuilder nameString = new StringBuilder();
        for(String s: names){
            nameString.append(s + "\n");
        }
        return nameString.toString();
    }

    public String allEvents(){
        StringBuilder events = new StringBuilder();
        for (int i = 0; i < peopleEvent.getSize(); i++ ){
            events.append((i + 1) + ") " + peopleEvent.getEvent(i) + "\n");
        } return events.toString();
    }

    public String allSubjects(){
        StringBuilder subjects = new StringBuilder();
        for (String s: testScores.getAllSubjects()){
            subjects.append(s + "   ");
        }

        return subjects.toString();
    }

    private ArrayList<String> splitOnComma(String line){
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

    private boolean notValidCharacters(String input) {
        return !input.matches("[a-zA-Z ]+");
    }
}