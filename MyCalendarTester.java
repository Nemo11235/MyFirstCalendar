import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.*;
import java.util.Locale;


public class MyCalendarTester {

	public static void main(String[] args) {
		
		LocalDate cal = LocalDate.now(); 
		printMonth(cal); // print current month and indicates today
		System.out.println("\nSelect one of the following main menu options:\r\n" + "[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		MyCalendar calendar = new MyCalendar();
		
		Scanner scan = new Scanner(System.in);
		char choice = scan.next().charAt(0); 
		while(choice != 'Q'){
			if(choice == 'V'){
				
			}
			else if(choice == 'G'){
				
			}
			else if(choice == 'E'){
				
			}
			else if(choice == 'D'){
				
			}
			System.out.println("Select one of the following main menu options:\r\n" + "[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			choice = scan.next().charAt(0); 
		}
		System.out.println("Thanks for using the calendar, bye!");	
	}// end of main;
	
	/**
	 * This function prints the current month's calendar and indicate today's date with square brackets
	 * @param c - the current date information
	 */
	public static void printMonth(LocalDate c) {
		DayOfWeek dayOfWeek = c.getDayOfWeek();
		String dayOfWeekStr = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		System.out.println(dayOfWeekStr + " " + c.getYear());
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
}
