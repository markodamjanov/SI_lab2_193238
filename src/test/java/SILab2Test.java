import org.junit.jupiter.api.Test;

import java.security.Signature;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private List<Time> createList(Time elems){
        return new ArrayList<Time>(Arrays.asList(elems));
    }

    private List<Integer> returnList(int elems){
        return new ArrayList<Integer>(Arrays.asList(elems));
    }

    @Test
    void everyBranch() {
        RuntimeException ex;

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(-2,30,50))));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(25, 30, 50))));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(23, 70, 50))));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(20, 40, 80))));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(24, 20, 30))));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        assertEquals(returnList(73550), SILab2.function(createList(new Time(20,25,50))));

        assertEquals(returnList(86400), SILab2.function(createList(new Time(24,0,0))));

        List<Time> emptyList = new ArrayList<Time>(0);
        assertEquals(emptyList, SILab2.function(emptyList));
    }

    @Test
    void multipleCondition(){
        RuntimeException ex;

        //if (hr < 0 || hr > 24) { //33
        //T X
        //F T
        //F F

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(-10,30,50))));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(30,30,50))));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        assertEquals(returnList(73550), SILab2.function(createList(new Time(20,25,50))));

        //if (min < 0 || min > 59) //39
        //T X
        //F T
        //F F

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(10,-20,50))));
        assertTrue(ex.getMessage().contains("The minutes are not valid"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(14,75,50))));
        assertTrue(ex.getMessage().contains("The minutes are not valid"));

        assertEquals(returnList(73550), SILab2.function(createList(new Time(20,25,50))));

        //if (sec >= 0 && sec <= 59) //42
        //T T
        //T F
        //F X

        assertEquals(returnList(73550), SILab2.function(createList(new Time(20,25,50))));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(14,40,70))));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(14,40,-30))));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        //else if (hr == 24 && min == 0 && sec == 0) { //46
        //T T T
        //T T F
        //T F X
        //F X X

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(24,0,10))));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(new Time(24,15,16))));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        assertEquals(returnList(73550), SILab2.function(createList(new Time(20,25,50))));
    }
}