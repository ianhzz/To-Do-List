package tests;

import org.junit.jupiter.api.Test;
import people.People;
import people.PeopleOwe;
import people.Person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testPeople {

    @Test
    public void testAddPerson() {
        PeopleOwe p = new PeopleOwe();
        p.addPerson("aaa", 1);
        p.addPerson("bbb", 10);
        p.addPerson("ccc", 100);

        Person aaa = new Person("aaa", 1);
        Person bbb = new Person("bbb", 10);
        Person ccc = new Person("ccc", 100);
        List<String> expected = new ArrayList<>();
        expected.add("aaa");
        expected.add("bbb");
        expected.add("ccc");

        assertEquals(p.getAllNames(), expected);
    }

    @Test
    public void testTotalOwe() {
        PeopleOwe p = new PeopleOwe();
        p.addPerson("aaa", 1);
        p.addPerson("bbb", 10);
        p.addPerson("ccc", 100);

        assertEquals(p.showTotalOwe(), 111);
    }

//    @Test
//    public void testOweSummary() {
//        People p = new People();
//        p.addPerson("aaa", 1);
//        p.addPerson("bbb", 0);
//
//        List<String> statement = new ArrayList<>();
//        statement.add("You owe aaa $1");
//        statement.add("You do not owe bbb anything.");
//        for (String s : statement) {
//            System.out.println(s);
//        }
//        p.showOweSummary();
//    }

    @Test
    public void testRemove1Person() {
        PeopleOwe p = new PeopleOwe();
        p.addPerson("a",0);
        p.addPerson("b",10);
        p.removePerson("a");
        List<String> expected = new ArrayList<>();
        expected.add("b");

        assertEquals(expected, p.getAllNames());
    }

    @Test
    public void testRemoveNonExistentPerson() {
        PeopleOwe p = new PeopleOwe();
        p.addPerson("a",0);
        p.addPerson("b",10);
        p.removePerson("c");
        List<String> expected = new ArrayList<>();
        expected.add("a");
        expected.add("b");

        assertEquals(expected, p.getAllNames());
    }

    @Test
    public void testGetAllNames() {
        PeopleOwe p = new PeopleOwe();
        p.addPerson("aaa", 0);
        p.addPerson("bbb", 0);
        p.addPerson("ccc", 0);
        p.addPerson("ddd", 0);

        List<String> expected = new ArrayList<>();
        expected.add("aaa");
        expected.add("bbb");
        expected.add("ccc");
        expected.add("ddd");

        assertEquals(expected, p.getAllNames());


    }
}
