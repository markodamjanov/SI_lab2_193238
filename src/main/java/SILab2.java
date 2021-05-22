import java.util.ArrayList;
import java.util.List;

class Time {
    int hours;
    int minutes;
    int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
};

public class SILab2 {

    public static List<Integer> function(List<Time> timesList) { //30
        List<Integer> result = new ArrayList<>(); //30

        for (int i = 0; i < timesList.size(); i++) { //31, 31.1, 31.2
            int hr = timesList.get(i).getHours(); //32
            int min = timesList.get(i).getMinutes(); //32
            int sec = timesList.get(i).getSeconds(); //32
            if (hr < 0 || hr > 24){ //33
                if (hr < 0){ //34
                    throw new RuntimeException("The hours are smaller than the minimum"); //35
                }
                else { //36
                    throw new RuntimeException("The hours are grater than the maximum"); //37
                }
            }
            else if (hr < 24) { //38
                if (min < 0 || min > 59) //39
                    throw new RuntimeException("The minutes are not valid!"); //40
                else { //41
                    if (sec >= 0 && sec <= 59) //42
                        result.add(hr * 3600 + min * 60 + sec); //43
                    else //44
                        throw new RuntimeException("The seconds are not valid"); //45
                }
            }
            else if (hr == 24 && min == 0 && sec == 0) { //46
                result.add(hr * 3600 + min * 60 + sec); //47
            }
            else { //48
                throw new RuntimeException("The time is greater than the maximum"); //49
            }
        }
        return result; //50
    }
}