import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Event {
	private String name;
	private LocalDate startDate, endDate;
	private TimeInterval interval;
	private Vector<Integer> repeat = new Vector<Integer>();
	public String repeatStr;
	private boolean recurring;
	
	/**
	 * Constructor 
	 * @param n name of event
	 * @param t time interval
	 */
	public Event(String n, String t) {
		name = n;
		Vector<String> tokens = new Vector<String>();
		StringTokenizer st = new StringTokenizer(t);
	    while (st.hasMoreTokens()) {
	        tokens.add(st.nextToken());
	    }
	    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy"); 
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
	    // here initiate the time interval for the event
		if(tokens.size() == 5) { // check if it is a recurring event
			recurring = true;
			repeatStr = tokens.get(0);
			// create an integer array that marks all the repeated days
			for(int i = 0; i < tokens.get(0).length(); i++) {
				if (tokens.get(0).charAt(i) == 'M') repeat.add(1);
				else if (tokens.get(0).charAt(i) == 'T') repeat.add(2);
				else if (tokens.get(0).charAt(i) == 'W') repeat.add(3);
				else if (tokens.get(0).charAt(i) == 'R') repeat.add(4);
				else if (tokens.get(0).charAt(i) == 'F') repeat.add(5);
				else if (tokens.get(0).charAt(i) == 'S') repeat.add(6);
				else if (tokens.get(0).charAt(i) == 'U') repeat.add(7);
			}
			startDate = LocalDate.parse(tokens.get(3), dateFormat);
			endDate = LocalDate.parse(tokens.get(4), dateFormat);
			LocalTime s = LocalTime.parse(tokens.get(1), timeFormat);
			LocalTime e = LocalTime.parse(tokens.get(2), timeFormat);
			interval = new TimeInterval(startDate, s, e);
		} else { // for one time event
			recurring = false;
			LocalDate d = LocalDate.parse(tokens.get(0), dateFormat);
			LocalTime s = LocalTime.parse(tokens.get(1), timeFormat);
			LocalTime e = LocalTime.parse(tokens.get(2), timeFormat);
			interval = new TimeInterval(d, s, e);
		}
	}
	
	/**
	 * Function that prints the event in format that fits the required format for printEventList function in MyCalendar class
	 * @param e event to print
	 */
	public static void printEvent(Event e) {
		if(e.isRecurring()) {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			System.out.println(e.getName());
			System.out.println(e.repeatStr + " " + e.getInterval().getStartTime() + " " + e.getInterval().getEndTime() + " " + dateFormat.format(e.getStartDate()) + " " + dateFormat.format(e.getEndDate()));
		} else {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM d yyyy");
			System.out.print(" " + dateFormat.format(e.getInterval().getDate()));
			System.out.println(" " + e.getInterval().getStartTime() + " - " + e.getInterval().getEndTime() + " " + e.getName());
		}
	}
	
	
	/**
	 * Setter function for event name
	 * @param n new name of event 
	 */
	public void setName(String n) { name = n; }
	
	/**
	 * Setter function for event time
	 * @param t new time interval of event
	 */
	public void setTime(TimeInterval t) { interval = t; }
	/**
	 * Getter function for event name
	 * @return name name of event
	 */
	public String getName() { return name; }
	
	/**
	 * Getter function for event time
	 * @return time time interval of event
	 */
	public TimeInterval getInterval() { return interval; }
	
	/**
	 * Getter function for event time
	 * @return startDate start date of event
	 */
	public LocalDate getStartDate() { return startDate; }
	
	/**
	 * Getter function for event time
	 * @return endDate end date of event
	 */
	public LocalDate getEndDate() { return endDate; }
	
	/**
	 * Function that returns if an event is recurring
	 * @param recurring indicates if an event is a recurring event
	 */
	public boolean isRecurring() { return recurring; }
	
	/**
	 * Getter function for repeat days
	 * @param repeat integer vector that holds all the repeated days of week
	 */
	public Vector<Integer> getRepeatDays() { return repeat; }
}

