package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import people.Person;
import people.PersonThatYouOwe;

public class testPerson {

    @Test
    public void testPersonThatYouOwe() {
        PersonThatYouOwe p = new Person("", 0);
        p.changeAmtYouOwe(200);

        assertEquals(p.showAmountOwe(),200);
    }
}
