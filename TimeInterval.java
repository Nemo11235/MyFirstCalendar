import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class TimeInterval {
	LocalDateTime start;
	LocalDateTime end;
	

	public TimeInterval(int year, int month, int dayOfMonth, int hour, int min, int eyear, int emonth, int edayOfMonth, int ehour, int emin){
		start = LocalDateTime.of(year, month, dayOfMonth, hour, min);
		end = LocalDateTime.of(eyear, emonth, edayOfMonth, ehour, emin);
	}
	
	public static double timeToDouble(int h, int m){ return (double)h + (double)m / 60; }
	
	public static boolean conflict(TimeInterval t1, TimeInterval t2){
		// if the year, month and day of month is the same
		if(t1.start.getYear() == t2.start.getYear() && t1.start.getMonth() == t2.start.getMonth() && t1.start.getDayOfMonth() == t2.start.getDayOfMonth()){
			// compare the starting times. if t1 starts earlier than t2, see if end time of t1 is later than starting time of t2, if it does, there's an overlap
			if(timeToDouble(t1.start.getHour(), t1.start.getMinute()) < timeToDouble(t2.start.getHour(), t2.start.getMinute())){
				return (timeToDouble(t1.end.getHour(), t1.end.getMinute()) > timeToDouble(t2.start.getHour(), t2.start.getMinute()));
			}else { return (timeToDouble(t2.end.getHour(), t2.end.getMinute()) > timeToDouble(t1.start.getHour(), t1.start.getMinute())); }
		}else { return false; }
	}
	
	public void printInterval() {
		System.out.println();
	}
}

