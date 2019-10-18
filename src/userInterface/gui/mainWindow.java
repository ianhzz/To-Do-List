package userInterface.gui;

import exceptions.InvalidInputException;
import exceptions.NonLetterNameException;
import exceptions.NotAnIntegerException;
import exceptions.PersonNotInListException;
import userInterface.ui.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;
import java.time.Duration;
import java.time.ZoneId;
import java.util.ArrayList;

public class mainWindow extends JFrame implements ActionListener {

    private JLabel label;
    private JLabel date;
    private JLabel time;
    private JLabel input1Label;
    private JLabel input2Label;
    private JLabel input3Label;
    private String input1;
    private String input2;
    private String input3;
    private JTextField inputField1;
    private JTextField inputField2;
    private JTextField inputField3;
    private JButton typedInput;
    private JButton backToMain;

    //main
    private JButton main1;
    private JButton main2;
    private JButton main3;
    private JButton main4;
    private JButton main5;
    //loans
    private JButton loans1;
    private JButton loans2;
    private JButton loans3;
    private JButton loanAdd;
    private JButton loanDelete;
    private JButton loanAddEnter;
    private JButton loanDeleteEnter;
    //events
    private JButton event1;
    private JButton event2;
    private JButton eventAdd;
    private JButton eventDelete;
    private JButton eventAddEnter;
    private JButton eventDeleteEnter;
    //tests
    private JButton test1;
    private JButton test2;
    private JButton test3;
    private JButton testAdd;
    private JButton testDelete;
    private JButton testAddEnter;
    private JButton testDeleteEnter;
    private JButton testAddScoreEnter;

    private JTextArea output;
    private Main main;
    private ArrayList<JButton> buttons;

    private Font font;
    private Clock clock;


    public mainWindow() {
        super("Ian's Project");
        Dimension Default = new Dimension(500,450);
//        Dimension max = new Dimension(500, 700);
//        Dimension  min = new Dimension(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setPreferredSize(Default);
//        super.setMaximumSize(max);
//        super.setMinimumSize(min);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(7, 7, 7, 7));
        setLayout(new FlowLayout());

        font = new Font("Serif", Font.BOLD, 14);
        label = new JLabel();
        time = new JLabel();
        date = new JLabel();

        time.setFont(font);
        date.setFont(font);


        inputField1 = new JTextField(35);
        inputField2 = new JTextField(35);
        inputField3 = new JTextField(35);
        input1Label = new JLabel("Your input: ");
        input2Label = new JLabel("Your input: ");
        input3Label = new JLabel("Your input: ");
        output = new JTextArea(13, 43);
        buttons = new ArrayList<>();
        clock = Clock.tick(Clock.system(ZoneId.of("America/Los_Angeles")), Duration.ofSeconds(1));

        add(output);
        add(input1Label);
        add(inputField1);
        inputField1.setVisible(false);
        add(input2Label);
        add(inputField2);
        inputField2.setVisible(false);
        add(input3Label);
        add(inputField3);
        inputField3.setVisible(false);

        // all buttons are set to invisible
        typedInput = new JButton("Enter");
        initializeButton(typedInput, "Enter");
        main1 = new JButton("Loans");
        initializeButton(main1, "main1");
        main2 = new JButton("Events");
        initializeButton(main2, "main2");
        main3 = new JButton("Test Scores");
        initializeButton(main3, "main3");
        main4 = new JButton("Weather");
        initializeButton(main4, "main4");
        main5 = new JButton("Save & Quit");
        initializeButton(main5, "main5");

        loans1 = new JButton("Detailed Summary");
        initializeButton(loans1, "loans1");
        loans2 = new JButton("Total Amount");
        initializeButton(loans2, "loans2");
        loans3 = new JButton("Add/Delete");
        initializeButton(loans3, "loans3");
//        loans4 = new JButton("loans4");
//        initializeButton(loans4, "loans4");
        loanAdd = new JButton("Add");
        initializeButton(loanAdd, "loanAdd");
        loanDelete = new JButton("Delete");
        initializeButton(loanDelete, "loanDelete");
        loanAddEnter = new JButton("Add");
        initializeButton(loanAddEnter, "loanAddEnter");
        loanDeleteEnter = new JButton("Delete");
        initializeButton(loanDeleteEnter, "loanDeleteEnter");

        event1 = new JButton("View All Events");
        initializeButton(event1, "event1");
        event2 = new JButton("Add/Delete");
        initializeButton(event2, "event2");
        eventAdd = new JButton("Add");
        initializeButton(eventAdd, "eventAdd");
        eventDelete = new JButton("Delete");
        initializeButton(eventDelete, "eventDelete");
        eventAddEnter = new JButton("Enter");
        initializeButton(eventAddEnter, "eventAddEnter");
        eventDeleteEnter = new JButton("Confirm");
        initializeButton(eventDeleteEnter, "eventDeleteEnter");

        test1 = new JButton("View Scores");
        initializeButton(test1, "test1");
        test2 = new JButton("Add/Delete");
        initializeButton(test2, "test2");
        test3 = new JButton("Add a Score");
        initializeButton(test3, "test3");
        testAdd = new JButton("Add");
        initializeButton(testAdd, "testAdd");
        testDelete = new JButton("Delete");
        initializeButton(testDelete, "testDelete");
        testAddEnter = new JButton("Enter");
        initializeButton(testAddEnter, "testAddEnter");
        testDeleteEnter = new JButton("Confirm");
        initializeButton(testDeleteEnter, "testDeleteEnter");
        testAddScoreEnter = new JButton("Add Score");
        initializeButton(testAddScoreEnter, "testAddScoreEnter");


        backToMain = new JButton("                                                     Return to Main Menu                                                     ");
        initializeButton(backToMain, "return");
        backToMain.setBackground(Color.darkGray);
        backToMain.setForeground(Color.white);


        add(label);
        add(date);
        add(time);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        main = new Main();
        returnToMain();
    }

    private void initializeButton(JButton button, String commandName) {
        buttons.add(button);
        button.setActionCommand(commandName);
        button.addActionListener(this);
        button.setVisible(false);
        add(button);
    }

    public void returnToMain() {
        String currenttime = clock.offset(clock, Duration.ofHours(-8)).instant().toString();
        changeOutput("MAIN MENU: \n" +
                "What would you like to do? \n" +
                "1) View Loans with Other People \n" +
                "2) View Upcoming Events \n" +
                "3) View Test scores \n" +
                "4) View Today's weather \n" +
                "5) Save and Quit");
        makeInputInvis();
        makeButtonVisible(main1);
        makeButtonVisible(main2);
        makeButtonVisible(main3);
        makeButtonVisible(main4);
        makeButtonVisible(main5);
        makeButtonInvis(backToMain);
        date.setVisible(true);
        time.setVisible(true);
        date.setText(" Current Date in Vancouver : "  + currenttime.substring(0,10) + "     ");
        time.setText("            Current Time : " + currenttime.substring(11,16) + "                ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            case "Enter":
                input1 = inputField1.getText();
                label.setText("You entered: " + inputField1.getText());
                inputField1.setText(null);


            case "return":
                label.setText(null);
                inputField1.setText(null);
                inputField2.setText(null);
                inputField3.setText(null);
                makeAllButtonsInvis();
                returnToMain();
                makeButtonVisible(main1);
                makeButtonVisible(main2);
                makeButtonVisible(main3);
                makeButtonVisible(main4);
                makeButtonVisible(main5);
                break;

            case "main1":
                label.setText("You selected: View Loans with Other People");
                makeAllButtonsInvis();
                makeButtonVisible(loans1);
                makeButtonVisible(loans2);
                makeButtonVisible(loans3);
//                makeButtonVisible(loans4);
                makeButtonVisible(backToMain);
                main.loansSelection();

                break;
            case "main2":
                label.setText("You selected: View Upcoming Events");
                makeAllButtonsInvis();
                makeButtonVisible(event1);
                makeButtonVisible(event2);
                makeButtonVisible(backToMain);
                main.eventSelection();
                break;

            case "main3":
                label.setText("You selected: View Test scores");
                makeAllButtonsInvis();
                makeButtonVisible(test1);
                makeButtonVisible(test2);
                makeButtonVisible(test3);
                makeButtonVisible(backToMain);
                main.testSelection();
                break;

            case "main4":
                label.setText("You selected: View Today's weather");
                makeAllButtonsInvis();
                makeButtonVisible(backToMain);
                main.showWeather();
                break;

            case "main5":
                label.setText("You selected: Save & Quit");
                main.saveAndQuit();
                break;

            //Loans start here
            case "loans1":
                label.setText("You selected: View Detailed Summary");
                makeAllButtonsInvis();
                main.loansShowSummary();
                makeButtonVisible(backToMain);
                break;

            case "loans2":
                label.setText("You selected: View Total Amount");
                makeAllButtonsInvis();
                main.loansShowTotalOwe();
                makeButtonVisible(backToMain);
                break;

            case "loans3":
                inputField1.setText(null);
                inputField2.setText(null);
                inputField3.setText(null);
                label.setText("You selected: Add/Delete a Person");
                makeAllButtonsInvis();
                makeButtonVisible(backToMain);
                changeOutput("Would you like to add or delete a person?");
                makeButtonVisible(loanAdd);
                makeButtonVisible(loanDelete);


                break;

            case "loanAdd":
                label.setText("You selected: Add");
                makeAllButtonsInvis();
                makeButtonVisible(backToMain);
                showInput1(loanAddEnter);
                input1Label.setText("Name: ");
                showInput2();
                input2Label.setText("Amount: ");
                changeOutput("Please enter the name of the person and the amount that you owe \n" +
                        "1st row: Name \n" +
                        "2nd row: Amount Owe (in whole numbers)");
                break;

            case "loanAddEnter":
                try {
                    input1 = inputField1.getText();
                    input2 = inputField2.getText();
                    main.loanAdd(input1, input2);
                    label.setText(null);
                    inputField1.setText(null);
                    makeInputInvis();
                    makeButtonInvis(loanAddEnter);
                } catch (NonLetterNameException e1) {
                    e1.printStackTrace();
                } catch (NotAnIntegerException e1) {
                    e1.printStackTrace();
                }
                break;

            case "loanDelete":
                label.setText("You selected: Delete");
                makeAllButtonsInvis();
                makeButtonVisible(backToMain);
                showInput1(loanDeleteEnter);
                input1Label.setText("Name: ");
                changeOutput("Please enter the name of the person you wish to delete. \n" +
                        "People currently in the list: \n" + main.allLoanNames());
                break;

            case "loanDeleteEnter":
                try {
                    input1 = inputField1.getText();
                    main.loanDelete(input1);
                    label.setText("You selected: " + inputField1.getText());
                    inputField1.setText(null);
                    makeInputInvis();
                    makeAllButtonsInvis();
                    makeButtonVisible(backToMain);
                } catch (PersonNotInListException e1) {
                    e1.printStackTrace();
                }
                break;

            // events buttons
            case "event1":
                label.setText("You selected: View All Events");
                makeAllButtonsInvis();
                main.eventShowAllEvents();
                makeButtonVisible(backToMain);
                break;

            case "event2":
                inputField1.setText(null);
                inputField2.setText(null);
                inputField3.setText(null);
                label.setText("You selected: Add/Delete An Event");
                makeAllButtonsInvis();
                makeButtonVisible(eventAdd);
                makeButtonVisible(eventDelete);
                makeButtonVisible(backToMain);
                changeOutput("Would you like to add or delete an event?");
                break;

            case "eventAdd":
                label.setText("You selected: Add");
                makeAllButtonsInvis();
                showInput1(eventAddEnter);
                input1Label.setText("Person:      ");
                showInput2();
                input2Label.setText(" Description:");
                showInput3();
                input3Label.setText("Date:         ");
                makeButtonVisible(backToMain);
                changeOutput("Please enter the following info: \n \n1) PERSON YOU'RE GOING WITH \n \n2) EVENT DESCRIPTION \n \n 3) DATE \n" +
                        "(dd/mm/yyyy)");
                break;

            case "eventAddEnter":
                try {
                    input1 = inputField1.getText();
                    input2 = inputField2.getText();
                    input3 = inputField3.getText();
                    main.eventAdd(input1, input2, input3);
                    label.setText(null);
                    inputField1.setText(null);
                    makeInputInvis();
                    makeButtonInvis(eventAddEnter);
                } catch (NonLetterNameException e1) {
                    e1.printStackTrace();
                }
                break;

            case "eventDelete":
                label.setText("You selected: Delete");
                makeAllButtonsInvis();
                makeButtonVisible(backToMain);
                showInput1(eventDeleteEnter);
                input1Label.setText("Number of the Event: ");
                changeOutput("Please enter the NUMBER of the event you wish to delete: \n" +
                        main.allEvents());
                break;

            case "eventDeleteEnter":
                try {
                    input1 = inputField1.getText();
                    main.eventDelete(input1);
                    label.setText("You selected: " + inputField1.getText());
                    inputField1.setText(null);
                    makeInputInvis();
                    makeAllButtonsInvis();
                    makeButtonVisible(backToMain);
                } catch (InvalidInputException e1) {
                    e1.printStackTrace();
                }
                break;

            //tests buttons
            case "test1":
                label.setText("You selected: View Scores");
                makeAllButtonsInvis();
                makeButtonVisible(backToMain);
                main.testsShowTests();
                break;

            case "test2":
                inputField1.setText(null);
                inputField2.setText(null);
                inputField3.setText(null);
                label.setText("You selected: Add/Delete a Subject");
                makeAllButtonsInvis();
                makeButtonVisible(testAdd);
                makeButtonVisible(testDelete);
                makeButtonVisible(backToMain);
                changeOutput("Would you like to add or delete a subject?");
                break;

            case "test3":
                inputField1.setText(null);
                inputField2.setText(null);
                inputField3.setText(null);
                label.setText("You select: Add A Score To Existing Subject");
                makeAllButtonsInvis();
                makeButtonVisible(backToMain);
                changeOutput("Which subject would you like to add a score to? \n" + "" +
                        "Current Subjects: " + main.allSubjects() +
                        " \n \n Please only enter *one* score.");
                showInput1(testAddScoreEnter);
                input1Label.setText("Subject: ");
                showInput2();
                input2Label.setText("Score: ");
                break;

            case "testAdd":
                label.setText("You selected: Add");
                makeAllButtonsInvis();
                showInput1(testAddEnter);
                showInput2();
                input1Label.setText("Subject: ");
                input2Label.setText("Scores: ");
                makeButtonVisible(backToMain);
                changeOutput("Please enter: \n" +
                        "1st row: Subject name \n" +
                        "2nd row: Score (please separate each score with a comma and NO space in between) \n" +
                        "         (E.X. 50,60,70)");
                break;

            case "testAddEnter":
                try {

                    input1 = inputField1.getText();
                    input2 = inputField2.getText();
                    main.testAdd(input1, input2);
                    inputField1.setText(null);
                    inputField2.setText(null);
                    label.setText(null);
                    makeInputInvis();
                    makeButtonInvis(testAddEnter);
                } catch (InvalidInputException e1) {
                    e1.printStackTrace();
                }

                break;

            case "testDelete":
                label.setText("You selected: delete");
                makeAllButtonsInvis();
                makeButtonVisible(backToMain);
                showInput1(testDeleteEnter);
                input1Label.setText("Name of the subject: ");
                changeOutput("Please enter the subject that you wish to delete: \n" +
                        main.allSubjects());
                break;

            case "testDeleteEnter":
                try {
                    input1 = inputField1.getText();
                    main.testDelete(input1);
                    label.setText("You selected: " + inputField1.getText());
                    inputField1.setText(null);
                    makeInputInvis();
                    makeAllButtonsInvis();
                    makeButtonVisible(backToMain);
                } catch (InvalidInputException e1) {
                    e1.printStackTrace();
                }
                break;

            case "testAddScoreEnter":
                input1 = inputField1.getText();
                input2 = inputField2.getText();
                try {
                    main.testAddScore(input1, input2);
                    inputField1.setText(null);
                    inputField2.setText(null);
                    makeAllButtonsInvis();
                    makeInputInvis();
                    makeButtonVisible(backToMain);
                    label.setText(null);
                } catch (InvalidInputException e1) {
                    e1.printStackTrace();
                }
        }
    }

    public void changeOutput(String showThis) {
        output.setText(showThis);
    }

    public void addToOutput(String s) {
        output.setText(output.getText() + s);
    }

    private void makeButtonInvis(JButton button) {
        button.setVisible(false);
    }

    private void makeButtonVisible(JButton button) {
        button.setVisible(true);
    }

    private void makeAllButtonsInvis() {
        for (JButton b : buttons) {
            makeButtonInvis(b);
        }
        date.setVisible(false);
        time.setVisible(false);
    }

    private void makeInputInvis() {
        input1Label.setVisible(false);
        input2Label.setVisible(false);
        input3Label.setVisible(false);
        inputField1.setVisible(false);
        inputField2.setVisible(false);
        inputField3.setVisible(false);
        typedInput.setVisible(false);
    }

    private void showInput1(JButton enterButton) {
        input1Label.setVisible(true);
        inputField1.setVisible(true);
        enterButton.setVisible(true);
    }

    private void showInput2() {
        input2Label.setVisible(true);
        inputField2.setVisible(true);
    }

    private void showInput3() {
        input3Label.setVisible(true);
        inputField3.setVisible(true);
    }
}
