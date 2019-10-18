package testScores;

import exceptions.NoTestRecordedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class listofTests {
    private List<myTest> myTests;

    //constructor
    public listofTests() {
        myTests = new ArrayList<>();
    }

    public void addSubject(myTest m) { myTests.add(m); }

    public void deleteSubject(myTest m) {myTests.remove(m);}

    public List<String> getAllSubjects(){
        List<String> subjects = new ArrayList<>();
        for (myTest t: myTests){
            subjects.add(t.getSubject());
        }
        return subjects;
    }

    public myTest getTest(String subject){
        for (myTest t: myTests){
            if (t.getSubject().equals(subject)){
                return t;
            }
        }
        return null;
    }


    //REQUIRES: name of subject
    //EFFECTS: return list of scores for given subject
    public List<Integer> getScores(String subject) {
        List<Integer> scores = new ArrayList<>();
        for (myTest m: myTests){
            if (subject == m.getSubject()){
                scores = m.getScores();
            }
        } return scores;
    }

    public void addScore(String subject, int score){
        for (myTest m :myTests){
            if (subject == m.getSubject()){
                m.addScores(score);
            }
        }
    }

    //MODIFIES: people
    //EFFECTS: create Persons from input file, and add said Person to people list
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("testScore.txt", "UTF-8");
        for (myTest m: myTests){
            String scores = separateScores(m);
            writer.println(m.getSubject()+":"+ scores +":");
        }
        writer.close();

    }

    private String separateScores(myTest m){
        List<Integer> scores = m.getScores();
        StringBuilder scoreString = new StringBuilder();
        for (int score: scores){
            scoreString.append(Integer.toString(score) + ",");
        }
        return scoreString.toString();
    }

    //MODIFIES: output file
    //EFFECTS: make the people list back into a .txt file
    public void make() throws IOException {
        List<String> tests = Files.readAllLines(Paths.get("testScore.txt"));
        for (String p : tests) {
            ArrayList<String> elementsofTest = splitOnColin(p);
            String subject = elementsofTest.get(0);
            List<Integer> scores = makeScoreList(elementsofTest.get(1));
            myTests.add(new myTest(subject,scores));  //elementopP.get(0) as the person's name, and the amount owe
        }
    }

    private List<Integer> makeScoreList(String scores) {
        List<String> s = splitOnComma(scores);
        List<Integer> returnThis = new ArrayList<>();
        for (String score: s){
            returnThis.add(Integer.parseInt(score));
        }
        return returnThis;
    }

    private ArrayList<String> splitOnColin(String line){
        String[] splits = line.split(":");
        return new ArrayList<>(Arrays.asList(splits));
    }

    private ArrayList<String> splitOnComma(String line){
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public String summary() throws NoTestRecordedException {
        StringBuilder summary = new StringBuilder();
        if (!myTests.isEmpty()) {
            for (myTest m : myTests) {
                summary.append("Your scores for " + m.getSubject() + " is " + m.getScores() + "\n");
                summary.append("Your average for " + m.getSubject() + " is " + m.getAverage() + "\n" + "\n");
            }
        } else throw new NoTestRecordedException();
        return summary.toString();
    }
}
