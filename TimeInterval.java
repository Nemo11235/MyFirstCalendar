import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class TimeInterval {
	private LocalDate date;
	private LocalTime start;
	private LocalTime end;
	
	/**
	 * Constructor
	 * @param d date
	 * @param s staring time
	 * @param e ending time
	 */
	public TimeInterval(LocalDate d, LocalTime s, LocalTime e) {
		date = d;
		start = s;
		end = e;
	}
	
	/**
	 * This function check if two time intervals is conflict
	 * @param t1
	 * @param t2
	 * @return weather t1 and t2 is conflict
	 */
	public static boolean conflict(TimeInterval t1, TimeInterval t2){
		// if the year, month and day of month is the same
		if(t1.date.equals(t2.date)) {
			if(t1.equals(t2)) return true;
			if(t1.start.isBefore(t2.start))
				return t1.end.isAfter(t2.start);
			else
				return t2.end.isAfter(t1.start);
		} else {
			return false;
		}
	}
	
	/**
	 * Function that prints the interval in a certain format
	 */
	public void printInterval() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
		System.out.println("From: " + formatter.format(start) + "\nTo: " + formatter.format(end));
	}
	
	/**
	 * Setter function to set the date
	 * @param d new date to replace
	 */
	public void setDate (LocalDate d) { date = d; }
	
	/**
	 * Getter function that returns the date
	 * @return date date of the interval
	 */
	public LocalDate getDate () { return date; }
	
	/**
	 * Getter function that returns the starting time
	 * @return start staring time of event
	 */
	public LocalTime getStartTime () { return start; }
	
	/**
	 * Getter function that returns the ending time
	 * @return end end time of the event
	 */
	public LocalTime getEndTime () { return end; }
}

