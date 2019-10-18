package testScores;

import exceptions.NoTestRecordedException;

import java.util.List;

public class myTest {
    private String subject;
    private List<Integer> scores;


    public myTest(String subject, List<Integer> scores) {
        this.subject = subject;
        this.scores = scores;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void addScores(int score) {
        scores.add(score);
    }

    //EFFECTS: return number of test scores recorded
    public int getNumTests() {
        return scores.size();
    }

    //EFFECTS: return average of the subject
    public int getAverage() throws NoTestRecordedException {
        if (scores.size() != 0) {
            int numTests = 0;
            int totalScore = 0;
            for (int i = 0; i < getNumTests(); i++) {
                totalScore += scores.get(i);
                numTests++;
            }
            return totalScore / numTests;
        }
        else throw new NoTestRecordedException();
    }
}
