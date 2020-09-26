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
		ArrayList<TimeInterval> time = new ArrayList<TimeInterval>();
		ArrayList<Event> event = new ArrayList<Event>();
		
		try {
			File FN = new File("events.txt");
			Scanner sc = new Scanner(FN);
			while(sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		
		try {
			File file = new File("events.txt");
			if(file.createNewFile()) {
				System.out.println("New file created");
			}
		}catch (IOException e){
			System.out.println("Error occored");
		}
	}
}


