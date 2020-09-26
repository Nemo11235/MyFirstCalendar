import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MyCalendar {
	/**
	 * Default constructor of MyCalendar, it should read the event.txt file and load them into the two array lists.
	 */
	public MyCalendar() {
		// parallel array list for time and event
		ArrayList<TimeInterval> times = new ArrayList<TimeInterval>();
		ArrayList<Event> events = new ArrayList<Event>();
		
		try {
			File FN = new File("events.txt");
			Scanner sc = new Scanner(FN);
			while(sc.hasNextLine()) {
				String name = sc.nextLine();
				String time = sc.nextLine();
			}
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		
	}
}


