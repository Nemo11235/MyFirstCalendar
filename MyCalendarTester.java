import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MyCalendar {
	
	// parallel array list for time and event
	private Vector<Event> events = new Vector<Event>();
	
	/**
	 * Default constructor of MyCalendar, it should read the event.txt file and load them into the two array lists.
	 */
	public MyCalendar() {
		try {
			File FN = new File("events.txt");
			Scanner sc = new Scanner(FN);
			while(sc.hasNextLine()) {
				String name = sc.nextLine();
				String time = sc.nextLine();
				addEvent(name, time);
			}
		}catch(IOException e){
			System.out.println("File not found");
		}
	}
	
	/**
	 * This function will add one-time or recurring event to the events vector
	 * @param name name of the event
	 * @param time time information of the event
	 */
	public void addEvent(String name, String time) {
		Event toAdd = new Event(name, time);
		if (toAdd.isRecurring()) {
			for(LocalDate date = toAdd.getStartDate(); date.isBefore(toAdd.getEndDate()) || date.isEqual(toAdd.getEndDate()); date = date.plusDays(1)) {
				for (int i = 0; i < toAdd.getRepeatDays().size(); i++) {
					if (date.getDayOfWeek().getValue() == toAdd.getRepeatDays().get(i)) {
						toAdd.getInterval().setDate(date);
						events.add(toAdd);
						// System.out.println(toAdd.getName() + " added to " + toAdd.getInterval().getDate());
					}
				}
			}
		} else {
			events.add(toAdd);
			// System.out.println(toAdd.getName() + " added to "+  toAdd.getInterval().getDate());
		}
	}
	
	/**
	 * This function will delete the event with the given event name
	 * @param name name of the event to delete
	 */
	public void deleteEvent(String name) {
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getName().equals(name))
				events.remove(i);
		}
		System.out.println(name + " has been removed from the calendar.");
	}
	
	/**
	 * This function will remove all the one time events of the given LocalDate
	 * @param date date of the one time events to delete
	 */
	public void deleteAllOneTime(LocalDate date) {
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getInterval().getDate().isEqual(date) && !events.get(i).isRecurring()) {
				System.out.println(events.get(i).getName() + " has been removed from calendar.");
				events.remove(i);
			}
		}
	}
	
	/**
	 * This function will remove the recurring events of name given
	 * @param name name of the recurring event to delete
	 */
	public void deleteEventName(String name) {
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getName().equals(name) && events.get(i).isRecurring())
				events.remove(i);
		}
		System.out.println(name + " has been removed from calendar.");
	}
	
	/**
	 * This function will print all the events of a given date
	 * @param date the date input by user
	 */
	public void printEventsOfDate(LocalDate date) {
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getInterval().getDate().isEqual(date))
				Event.printEvent(events.get(i));
		}
	}
	
	/**
	 * Function that prints all events in a day for day view option in main menu
	 * @param date date of which the events will be printed
	 */
	public void printDayView(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getInterval().getDate().isEqual(date)) {
				System.out.println(formatter.format(date));
				System.out.println(events.get(i).getName() + " : " + events.get(i).getInterval().getStartTime() + " - " + events.get(i).getInterval().getEndTime());
			}
		}
	}
	
	/**
	 * This function will print all the events of the given date for user to select on to delete
	 * @param date the date of which the user wants to select an event to delete
	 */
	public void printEventsOfDayForDelete(LocalDate date) {
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getInterval().getDate().isEqual(date))
				System.out.println(events.get(i).getInterval().getStartTime() + " - " + events.get(i).getInterval().getEndTime() + " " + events.get(i).getName());
		}
	}
	
	/**
	 * This function will check if there's any event on a certain date
	 * @param date the date that the user wants to check
	 * @return hasEvent if there's an event on the date, it will be set to true
	 */
	public boolean checkEventOfDate(LocalDate date) {
		boolean hasEvent = false;
		for(int i = 0; i < events.size(); i++) {
			if (events.get(i).getInterval().getDate().isEqual(date))
				hasEvent = true;
		}
		return hasEvent;
	}
	
	/**
	 * Function that prints all the events in certain format
	 */
	public void printEventList() {
		boolean notSorted = true;
		int size = events.size();
		while(notSorted) {
			notSorted = false;
			for(int i = 0; i < size - 1; i++) {
				if (events.get(i).getInterval().getDate().isAfter(events.get(i+1).getInterval().getDate())) {
					notSorted = true;
					Event temp = events.get(i);
					events.set(i, events.get(i+1));
					events.set(i+1, temp);
				}
			}
			size--;
		}
		
		Vector<Event> oneTime = new Vector<Event>();
		Vector<Event> recurring = new Vector<Event>();
		for(int i = 0; i < events.size(); i++) {
			if(events.get(i).isRecurring()) recurring.add(events.get(i));
			else oneTime.add(events.get(i));
		}
		
		// print the one-time events
		int curYear = 0;
		System.out.println("ONE TIME EVENTS\n");
		for(int j = 0; j < oneTime.size(); j++) {
			if (oneTime.get(j).getInterval().getDate().getYear() != curYear) {
				curYear = oneTime.get(j).getInterval().getDate().getYear();
				System.out.println(curYear);
			}
			Event.printEvent(oneTime.get(j));
		}
		System.out.println();
		
		// print the recurring events
		System.out.println("RECURRING EVENTS");
		Event.printEvent(recurring.get(0));
		String lastOne = recurring.get(0).getName();
		for (int k = 1; k < recurring.size(); k++) {
			if(recurring.get(k).getName() != lastOne) {
				Event.printEvent(recurring.get(k));
				lastOne = recurring.get(k).getName();
			}
		}
	}
	
	/**
	* Function that saves the calendar to a file called filename.txt
	* @param filename name of file 
	*/
	public void saveToFile(String filename) {
		try 
		{
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy"); 
			File file = new File(filename + ".txt");
			FileWriter myWriter = new FileWriter(filename + ".txt");
			for(int i = 0; i < events.size(); i++) {
				myWriter.write(events.get(i).getName() + "\n");
				if (events.get(i).isRecurring()) {
					myWriter.write(events.get(i).repeatStr + " " + events.get(i).getInterval().getStartTime() + " " + events.get(i).getInterval().getEndTime() + " " + dateFormat.format(events.get(i).getStartDate()) + " " + dateFormat.format(events.get(i).getEndDate()) + "\n");
					while(i + 1 < events.size() && events.get(i + 1).getName().equals(events.get(i).getName())) {
						i+=1;
					}
				} else {
					myWriter.write(dateFormat.format(events.get(i).getInterval().getDate()) + " " + events.get(i).getInterval().getStartTime() + " " + events.get(i).getInterval().getEndTime() + "\n");
				}
			}
			myWriter.close();
		}
		catch(IOException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
		
}


