import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class Java8_DateTimeApi {

	/*
	 * There have been several problems with the existing date and time related
	 * classes in java, some of them are: 1.Java Date Time classes are not defined
	 * consistently, we have Date Class in both java.util as well as java.sql
	 * packages. Again formatting and parsing classes are defined in java.text
	 * package. 2.java.util.Date contains both date and time, whereas java.sql.Date
	 * contains only date. Having this in java.sql package doesn’t make sense. Also
	 * both the classes have same name, that is a very bad design itself. 3.There
	 * are no clearly defined classes for time, timestamp, formatting and parsing.
	 * We have java.text.DateFormat abstract class for parsing and formatting need.
	 * Usually SimpleDateFormat class is used for parsing and formatting 4.All the
	 * Date classes are mutable, so they are not thread safe. It’s one of the
	 * biggest problem with Java Date and Calendar classes. 5.Date class doesn’t
	 * provide internationalization, there is no timezone support. So
	 * java.util.Calendar and java.util.TimeZone classes were introduced, but they
	 * also have all the problems listed above.
	 * 
	 */

	/*
	 * Java 8 Date 1.Immutability: All the classes in the new Date Time API are
	 * immutable and good for multithreaded environments.
	 * 
	 * 2.Separation of Concerns: The new API separates clearly between human
	 * readable date time and machine time (unix timestamp). It defines separate
	 * classes for Date, Time, DateTime, Timestamp, Timezone etc.
	 * 
	 * 3.Clarity: The methods are clearly defined and perform the same action in all
	 * the classes. For example, to get the current instance we have now() method.
	 * There are format() and parse() methods defined in all these classes rather
	 * than having a separate class for them.
	 * 
	 * 4.All the classes use Factory Pattern and Strategy Pattern for better
	 * handling. Once you have used the methods in one of the class, working with
	 * other classes won’t be hard.
	 * 
	 * 5.Utility operations: All the new Date Time API classes comes with methods to
	 * perform common tasks, such as plus, minus, format, parsing, getting separate
	 * part in date/time etc. 6.Extendable: The new Date Time API works on ISO-8601
	 * calendar system but we can use it with other non ISO calendars as well.
	 * 
	 */

	/*
	 * java.time Package: This is the base package of new Java Date Time API. All
	 * the major base classes are part of this package, such as LocalDate,
	 * LocalTime, LocalDateTime, Instant, Period, Duration etc. All of these classes
	 * are immutable and thread safe. Most of the times, these classes will be
	 * sufficient for handling common requirements.
	 * 
	 * java.time.chrono Package: This package defines generic APIs for non ISO
	 * calendar systems. We can extend AbstractChronology class to create our own
	 * calendar system.
	 * 
	 * java.time.format Package: This package contains classes used for formatting
	 * and parsing date time objects. Most of the times, we would not be directly
	 * using them because principle classes in java.time package provide formatting
	 * and parsing methods.
	 * 
	 * 
	 * java.time.temporal Package: This package contains temporal objects and we can
	 * use it for find out specific date or time related to date/time object. For
	 * example, we can use these to find out the first or last day of the month. You
	 * can identify these methods easily because they always have format “withXXX”.
	 * 
	 * java.time.zone Package: This package contains classes for supporting
	 * different time zones and their rules.
	 */

	/*
	 * In all the three examples, we have seen that if we provide invalid arguments
	 * for creating Date/Time, then it throws java.time.DateTimeException that is a
	 * RuntimeException, so we don’t need to explicitly catch it.
	 * 
	 * We have also seen that we can get Date/Time data by passing ZoneId, you can
	 * get the list of supported ZoneId values from it’s javadoc.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		localDate();
		localTime();
		localDateTime();
		instant();
	}

	/*
	 * LocalDate is an immutable class that represents Date with default format of
	 * yyyy-MM-dd. We can use now() method to get the current date. We can also
	 * provide input arguments for year, month and date to create LocalDate
	 * instance. This class provides overloaded method for now() where we can pass
	 * ZoneId for getting date in specific time zone. This class provides the same
	 * functionality as java.sql.Date. Let’s look at a simple example for it’s
	 * usage.
	 */
	private static void localDate() {
		// Current Date
		LocalDate today = LocalDate.now();
		System.out.println("Current Date=" + today);// yyyy-MM-dd default format

		// Creating LocalDate by providing input arguments
		LocalDate firstDay_2014 = LocalDate.of(2014, Month.JANUARY, 1);
		System.out.println("Specific Date=" + firstDay_2014);

		// Try creating date by providing invalid inputs
		// LocalDate feb29_2014 = LocalDate.of(2014, Month.FEBRUARY, 29);
		// Exception in thread "main" java.time.DateTimeException:
		// Invalid date 'February 29' as '2014' is not a leap year

		// Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
		LocalDate todayKolkata = LocalDate.now(ZoneId.of("Asia/Kolkata"));
		System.out.println("Current Date in IST=" + todayKolkata);

		// java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
		// LocalDate todayIST = LocalDate.now(ZoneId.of("IST"));

		// Getting date from the base date i.e 01/01/1970
		LocalDate dateFromBase = LocalDate.ofEpochDay(365);
		System.out.println("365th day from base date= " + dateFromBase);

		LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 100);
		System.out.println("100th day of 2014=" + hundredDay2014);
	}

	/*
	 * LocalTime is an immutable class whose instance represents a time in the human
	 * readable format. It’s default format is hh:mm:ss.zzz. Just like LocalDate,
	 * this class provides time zone support and creating instance by passing hour,
	 * minute and second as input arguments. Let’s look at it’s usage with a simple
	 * program.
	 * 
	 */
	private static void localTime() {
		// Current Time
		LocalTime time = LocalTime.now();
		System.out.println("Current Time=" + time);

		// Creating LocalTime by providing input arguments
		LocalTime specificTime = LocalTime.of(12, 20, 25, 40);
		System.out.println("Specific Time of Day=" + specificTime);

		// Try creating time by providing invalid inputs
		// LocalTime invalidTime = LocalTime.of(25,20);
		// Exception in thread "main" java.time.DateTimeException:
		// Invalid value for HourOfDay (valid values 0 - 23): 25

		// Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
		LocalTime timeKolkata = LocalTime.now(ZoneId.of("Asia/Kolkata"));
		System.out.println("Current Time in IST=" + timeKolkata);

		// java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
		// LocalTime todayIST = LocalTime.now(ZoneId.of("IST"));

		// Getting date from the base date i.e 01/01/1970
		LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
		System.out.println("10000th second time= " + specificSecondTime);

	}

	/*
	 * LocalDateTime is an immutable date-time object that represents a date-time,
	 * with default format as yyyy-MM-dd-HH-mm-ss.zzz. It provides a factory method
	 * that takes LocalDate and LocalTime input arguments to create LocalDateTime
	 * instance. Let’s look it’s usage with a simple example.
	 */
	private static void localDateTime() {
		// Current Date
		LocalDateTime today = LocalDateTime.now();
		System.out.println("Current DateTime=" + today);

		// Current Date using LocalDate and LocalTime
		today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		System.out.println("Current DateTime=" + today);

		// Creating LocalDateTime by providing input arguments
		LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);
		System.out.println("Specific Date=" + specificDate);

		// Try creating date by providing invalid inputs
		// LocalDateTime feb29_2014 = LocalDateTime.of(2014, Month.FEBRUARY, 28,
		// 25,1,1);
		// Exception in thread "main" java.time.DateTimeException:
		// Invalid value for HourOfDay (valid values 0 - 23): 25

		// Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
		LocalDateTime todayKolkata = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
		System.out.println("Current Date in IST=" + todayKolkata);

		// java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
		// LocalDateTime todayIST = LocalDateTime.now(ZoneId.of("IST"));

		// Getting date from the base date i.e 01/01/1970
		LocalDateTime dateFromBase = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
		System.out.println("10000th second time from 01/01/1970= " + dateFromBase);

	}

	/*
	 * Instant class is used to work with machine readable time format, it stores
	 * date time in unix timestamp.
	 */
	private static void instant() {
		// Current timestamp
		Instant timestamp = Instant.now();
		System.out.println("Current Timestamp = " + timestamp);

		// Instant from timestamp
		Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
		System.out.println("Specific Time = " + specificTime);

		// Duration example
		Duration thirtyDay = Duration.ofDays(30);
		System.out.println(thirtyDay);

	}
}
