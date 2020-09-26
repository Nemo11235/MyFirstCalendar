
public class Event {
	private String name;
	private TimeInterval time = new TimeInterval(0,0,0,0,0,0,0,0,0,0);
	private boolean recurring;
	
	/**
	 * Constructor 
	 * @param n name of event
	 * @param t time interval
	 */
	public Event(String n, TimeInterval t) {
		name = n;
		time = t;
		recurring = true;
	}
	
	public Event(String n, String t) {
		name = n;
		if(t.charAt(0) >= 'A' && t.charAt(0) <= 'Z') { // check if it is a recurring event
			recurring = true;
			String repeat_days;
			int start;
			int counter = 0;
			for(int i = 0; i < t.length(); i++) { if(t.charAt(i) == ' ' && counter++ == 0) repeat_days = t.substring(0, i); start = i; } // get repeated days
		} else { // for one time event
			TimeInterval time = new TimeInterval(2000 + Integer.parseInt(t.substring(5,8)), Integer.parseInt(t.substring(0,2)), Integer.parseInt(t.substring(2,5)), Integer.parseInt(t.substring(9, 11)),Integer.parseInt(t.substring(11, 14)), 
					2000 + Integer.parseInt(t.substring(5,8)), Integer.parseInt(t.substring(0,2)), Integer.parseInt(t.substring(2,5)), Integer.parseInt(t.substring(15,17)), Integer.parseInt(t.substring(18)));
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
	public void setTime(TimeInterval t) { time = t; }
	
	public String getName() { return name; }
	public TimeInterval getTime() { return time; }
}

