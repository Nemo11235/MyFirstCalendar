
public class Event {
	private String name;
	private TimeInterval time = new TimeInterval(0,0,0,0,0,0,0,0,0,0);
	
	/**
	 * Constructor 
	 * @param n name of event
	 * @param t time interval
	 */
	public Event(String n, TimeInterval t) {
		name = n;
		time = t;
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

