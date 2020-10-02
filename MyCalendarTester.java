import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.*;
import java.util.Locale;


public class MyCalendarTester {

	public static void main(String[] args) {
		
		LocalDate cal = LocalDate.now(); 
		printMonth(cal); // print current month and indicates today
		System.out.println("\nSelect one of the following main menu options:\r\n" + "[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		MyCalendar calendar = new MyCalendar();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy"); 
		
		Scanner scan = new Scanner(System.in);
		char choice = scan.nextLine().charAt(0); 
		char ViewChoice;
		char nextChoice;
		boolean notDone;
		while(choice != 'Q'){
			if(choice == 'V'){
				scan.nextLine();
				LocalDate today = LocalDate.now();
				notDone = true;
				System.out.println("[D]ay view or [M]onth view ? ");
				ViewChoice = scan.next().charAt(0);
				if (ViewChoice == 'D') {
					calendar.printDayView(today);
					System.out.println("[P]revious or [N]ext or [G]o back to the main menu ?");
					nextChoice = scan.next().charAt(0);
					if (nextChoice == 'G') notDone = false;
					while(notDone) { 
						if (nextChoice == 'P') {
							today = today.plusDays(-1);
							calendar.printEventsOfDate(today);
						} else if (nextChoice == 'N') {
							today = today.plusDays(1);
							calendar.printEventsOfDate(today);
						}
						System.out.println("[P]revious or [N]ext or [G]o back to the main menu ?");
						nextChoice = scan.next().charAt(0);
						if (nextChoice == 'G')
							notDone = false;
					}
				} else {
					printEventsOfMonth(today, calendar);
					System.out.println("[P]revious or [N]ext or [G]o back to the main menu ?");
					nextChoice = scan.next().charAt(0);
					if (nextChoice == 'G') notDone = false;
					while(notDone) { 
						if (nextChoice == 'P') {
							today = today.plusMonths(-1);
							printEventsOfMonth(today, calendar);
						} else if (nextChoice == 'N') {
							today = today.plusMonths(1);
							printEventsOfMonth(today, calendar);
						}
						System.out.println("[P]revious or [N]ext or [G]o back to the main menu ?");
						nextChoice = scan.next().charAt(0);
						if (nextChoice == 'G')
							notDone = false;
					}
					printEventsOfMonth(today, calendar);
				}
			}
			else if(choice == 'C'){
				scan.nextLine();
				String name, date, start, end, time;
				System.out.println("Name of event: ");
				name = scan.nextLine();
				System.out.println("Date(MM/DD/YY): ");
				date = scan.nextLine();
				System.out.println("Start Time(HH:MM): ");
				start = scan.nextLine();
				System.out.println("End Time(HH:MM): ");
				end = scan.nextLine();
				time = date + " " + start + " " + end;
				calendar.addEvent(name, time);
			}
			else if(choice == 'E'){
				calendar.printEventList();
			}
			else if(choice == 'G'){
				System.out.println("Please enter the date you want to check(MM/DD/YY): ");
				scan.nextLine();
				String check = scan.nextLine();
				LocalDate date = LocalDate.parse(check, dateFormat);
				calendar.printEventsOfDate(date);
			}
			else if(choice == 'D'){
				scan.nextLine();
				System.out.println("Enter [S] to enter a specific ONE TIME event on a specific date to delete\n"
						+ "Enter [A] to enter a date which all the ONE TIMEevent of that date will be deleted\n"
						+ "Enter [DR] to enter the name of a RECURRING event to delete");
				String input = scan.nextLine();
				if (input.charAt(0) == 'S') {
					scan.nextLine();
					System.out.println("Enter the date (MM/DD/YY): ");
					String check = scan.nextLine();
					LocalDate date = LocalDate.parse(check, dateFormat);
					calendar.printEventsOfDayForDelete(date);
					System.out.println("Enter the name of the event to delete: ");
					String name = scan.nextLine();
					calendar.deleteEvent(name);
				} else if (input.charAt(0) == 'A') {
					scan.nextLine();
					System.out.println("Enter the date of which you want to delte all the ONE TIME event(MM/DD/YY): ");
					String dateStr = scan.nextLine();
					LocalDate date = LocalDate.parse(dateStr, dateFormat);
					calendar.deleteAllOneTime(date);
				} else if (input.charAt(0) == 'D') {
					scan.nextLine();
					System.out.println("Enter the name of the RECURRING event you want to delete: ");
					String name = scan.nextLine();
					calendar.deleteEventName(name);
				}
			}
			System.out.println("Select one of the following main menu options:\r\n" + "[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			choice = scan.next().charAt(0); 
		}
		System.out.println("Thanks for using the calendar, bye!");	
		calendar.saveToFile("output");
	}// end of main;
	
	/**
	 * This function prints the current month's calendar and indicate today's date with square brackets
	 * @param c - the current date information
	 */
	public static void printMonth(LocalDate c) {
		Month month = c.getMonth();
		String monthStr = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		System.out.println(monthStr + " " + c.getYear());
		System.out.println("Su Mo Tu We Th Fr Sa");
		int first_day_of_month = LocalDate.of(c.getYear(), c.getMonth(), 1).getDayOfWeek().getValue(); 
		YearMonth yearMonthObject = YearMonth.of(c.getYear(), c.getMonthValue());
		int daysInMonth = yearMonthObject.lengthOfMonth();
		
		// print the first line of dates
		int start;
		if (first_day_of_month == 7) start = 0;
		else start = first_day_of_month;
		
		for(int i = 0; i < start; i++) System.out.print("   ");
		for(int i = 0; i < 7-start; i++) {
			if(i+1 == c.getDayOfMonth()) System.out.print("[");
			System.out.print(i+1);
			if(i+1 == c.getDayOfMonth()) System.out.print("]");
			if(i+1 > 9) System.out.print(" ");
			else System.out.print("  ");
		}
		System.out.println();
		
		// prints the rest of the days in the month;
		int switchLine = 1;
		for(int i = 7-start; i < daysInMonth; i++){
			if(i+1 == c.getDayOfMonth()) System.out.print("[");
			System.out.print(i+1);
			if(i+1 == c.getDayOfMonth()) System.out.print("]");
			if(i+1 > 9) System.out.print(" ");
			else System.out.print("  ");
			if(switchLine == 7){
				System.out.println();
				switchLine = 0;
			}
			switchLine++;
		}
		System.out.println();
	} // end of printMonth();
	
	/**
	 * Print the current month and the days that has event will be within {}
	 * @param c date of the month to be print
	 * @param calendar object that holds all the events
	 */
	public static void printEventsOfMonth(LocalDate c, MyCalendar calendar) {
		Month month = c.getMonth();
		String monthStr = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		System.out.println(monthStr + " " + c.getYear());
		System.out.println("Su Mo Tu We Th Fr Sa");
		int first_day_of_month = LocalDate.of(c.getYear(), c.getMonth(), 1).getDayOfWeek().getValue(); 
		YearMonth yearMonthObject = YearMonth.of(c.getYear(), c.getMonthValue());
		int daysInMonth = yearMonthObject.lengthOfMonth();
		
		// print the first line of dates
		int start;
		if (first_day_of_month == 7) start = 0;
		else start = first_day_of_month;
		
		for(int i = 0; i < start; i++) System.out.print("   ");
		for(int i = 0; i < 7-start; i++) {
			if(calendar.checkEventOfDate(LocalDate.of(c.getYear(),c.getMonth().getValue() , i+1))) System.out.print("{");
			System.out.print(i+1);
			if(calendar.checkEventOfDate(LocalDate.of(c.getYear(),c.getMonth().getValue() , i+1))) System.out.print("}");
			if(i+1 > 9) System.out.print(" ");
			else System.out.print("  ");
		}
		System.out.println();
		
		// prints the rest of the days in the month;
		int switchLine = 1;
		for(int i = 7-start; i < daysInMonth; i++){
			if(calendar.checkEventOfDate(LocalDate.of(c.getYear(),c.getMonth().getValue() , i+1))) System.out.print("{");
			System.out.print(i+1);
			if(calendar.checkEventOfDate(LocalDate.of(c.getYear(),c.getMonth().getValue() , i+1))) System.out.print("}");
			if(i+1 > 9) System.out.print(" ");
			else System.out.print("  ");
			if(switchLine == 7){
				System.out.println();
				switchLine = 0;
			}
			switchLine++;
		}
		System.out.println();
	}
}
