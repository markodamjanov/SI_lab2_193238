# Втора лабораториска вежба по Софтверско инженерство
## Марко Дамјанов, бр. на индекс 193238
### Control Flow Graph
![image](https://user-images.githubusercontent.com/59368142/119220931-1a86f600-baed-11eb-9e32-3aca4d600268.png)
### Цикломатска комплексност
Цикломатската комплексност е 8. Ја добив преку формулата P+1, каде што P е бројот на предикатни јазли.
### Тест случаи според критериумот Every branch  

30-31  
31-31.1  
31.1-32  
31.1-50  
32-33  
33-34  
33-38  
34-35  
34-36  
36-37  
38-39  
39-40  
39-41  
41-42  
42-44  
42-43  
43-31.2  
44-45  
38-46  
46-47  
47-31.2  
46-48  
48-49  

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
  
### Тест случаи според критериумот Multiple Condition  
  
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
  
### Објаснување на напишаните unit tests
**Every Branch**  
Овие unit тестови ги напишав на начин за да ги исполнам сите услови, да можам да ги поминам сите exceptions. 
Тука употребувам assertThrows за да можам да фатам некој exception кој го праќам во ex, за потоа да можам да проверам дали програмата го фрлила точниот exception со assertTrue за дадениот input. Исто така употребувам и assertEquals за да можам да проверам дали програмата го враќа точниот резултат за дадениот input.  
**Multiple Condition**  
Со multiple condition ги проверувам сите if услови каде што имам повеќе од еден услов. Исто така и тука користам assertThrows и assertTrue за фаќање и проверување дали е точен даден exception и assertEquals за проверување дали програмата враќа точен резултат за даден input.  
