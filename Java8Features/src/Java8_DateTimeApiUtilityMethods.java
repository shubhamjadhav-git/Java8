import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/*
 *  most of the Date Time principle classes provide various utility methods such as plus/minus days, weeks, months etc. 
 *  There are some other utility methods for adjusting the date using TemporalAdjuster and to calculate the period between two dates.
 * 
 * */
public class Java8_DateTimeApiUtilityMethods {

	public static void main(String[] args) {

		temporalAdjustor();
		dateTimeFormmater();
		java8LegacyDateTimeSupport();

	}

	private static void temporalAdjustor() {
		LocalDate today = LocalDate.now();

		// Get the Year, check if it's leap year
		System.out.println("Year " + today.getYear() + " is Leap Year? " + today.isLeapYear());

		// Compare two LocalDate for before and after
		System.out.println("Today is before 01/01/2015? " + today.isBefore(LocalDate.of(2015, 1, 1)));

		// Create LocalDateTime from LocalDate
		System.out.println("Current Time=" + today.atTime(LocalTime.now()));

		// plus and minus operations
		System.out.println("10 days after today will be " + today.plusDays(10));
		System.out.println("3 weeks after today will be " + today.plusWeeks(3));
		System.out.println("20 months after today will be " + today.plusMonths(20));

		System.out.println("10 days before today will be " + today.minusDays(10));
		System.out.println("3 weeks before today will be " + today.minusWeeks(3));
		System.out.println("20 months before today will be " + today.minusMonths(20));

		// Temporal adjusters for adjusting the dates
		System.out.println("First date of this month= " + today.with(TemporalAdjusters.firstDayOfMonth()));
		LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
		System.out.println("Last date of this year= " + lastDayOfYear);

		Period period = today.until(lastDayOfYear);
		System.out.println("Period Format= " + period);
		System.out.println("Months remaining in the year= " + period.getMonths());

	}

	private static void dateTimeFormmater() {
		// Format examples
		LocalDate date = LocalDate.now();
		// default format
		System.out.println("Default format of LocalDate=" + date);
		// specific format
		System.out.println(date.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
		System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));

		LocalDateTime dateTime = LocalDateTime.now();
		// default format
		System.out.println("Default format of LocalDateTime=" + dateTime);
		// specific format
		System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));
		System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

		Instant timestamp = Instant.now();
		// default format
		System.out.println("Default format of Instant=" + timestamp);

		// Parse examples
		LocalDateTime dt = LocalDateTime.parse("27::Apr::2014 21::39::48",
				DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));
		System.out.println("Default format after parsing = " + dt);

	}

	/*
	 * Legacy Date/Time classes are used in almost all the applications, so having
	 * backward compatibility is a must. That’s why there are several utility
	 * methods through which we can convert Legacy classes to new classes and vice
	 * versa.
	 */
	private static void java8LegacyDateTimeSupport() {
		// Date to Instant
		Instant timestamp = new Date().toInstant();
		// Now we can convert Instant to LocalDateTime or other similar classes
		LocalDateTime date = LocalDateTime.ofInstant(timestamp, ZoneId.of(ZoneId.SHORT_IDS.get("PST")));
		System.out.println("Date = " + date);

		// Calendar to Instant
		Instant time = Calendar.getInstance().toInstant();
		System.out.println(time);
		// TimeZone to ZoneId
		ZoneId defaultZone = TimeZone.getDefault().toZoneId();
		System.out.println(defaultZone);

		//As you can see that legacy TimeZone and GregorianCalendar classes toString() methods are too verbose and not user friendly.
		// ZonedDateTime from specific Calendar
		ZonedDateTime gregorianCalendarDateTime = new GregorianCalendar().toZonedDateTime();
		System.out.println(gregorianCalendarDateTime);

		// Date API to Legacy classes
		Date dt = Date.from(Instant.now());
		System.out.println(dt);

		TimeZone tz = TimeZone.getTimeZone(defaultZone);
		System.out.println(tz);

		GregorianCalendar gc = GregorianCalendar.from(gregorianCalendarDateTime);
		System.out.println(gc);

	}

}
