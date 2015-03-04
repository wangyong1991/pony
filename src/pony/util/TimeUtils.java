package pony.util;

public class TimeUtils {
	
	public final static int MAX_DAYS_IN_MONTH = 31;
	public final static int DAY_IN_HOURS = 24;
	public final static int HOUR_IN_MINUTES = 60;
	public final static int MINUTE_IN_MINUTES = 1;
	public final static int HOUR_IN_SECONDS = 60 * 60;
	public final static int MINUTE_IN_SECONDS = 60;
	public final static int SECOND_IN_SECONDS = 1;
	public final static int DAY_IN_MILLISECONDS = 86400000;
	public final static int HOUR_IN_MILLISECONDS = 3600000;
	public final static int MINUTE_IN_MILLISECONDS = 60 * 1000;
	public final static int SECOND_IN_MILLISECONDS = 1000;
	
	private TimeUtils(){}
	
	public static long currentTimeMillis(){
		return System.currentTimeMillis();
	}

	/**
     * Converts seconds to milliseconds, with a precision of 1 second
     * @param timeInSecs the time in seconds
     * @return The equivalent time in milliseconds
     */
    public static long secondsToMillis(int timeInSecs) {
        return timeInSecs * SECOND_IN_MILLISECONDS;
    }

    /**
     * Converts a long seconds value to an int seconds value and takes into account overflow
     * from the downcast by switching to Integer.MAX_VALUE.
     * @param seconds Long value
     * @return Same int value unless long > Integer.MAX_VALUE in which case MAX_VALUE is returned
     */
    public static int convertTimeToInt(long seconds) {
        if (seconds > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) seconds;
        }
    }
}
