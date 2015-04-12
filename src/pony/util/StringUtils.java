package pony.util;

public final class StringUtils {
	public final static String BLANK = "";
	public final static String TRUE = "true";
	public final static String FALSE = "false";
	
	public static boolean isEmpty(final String str){
		if(null == str || BLANK.equals(str.trim())){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(final String str){
		return ! isEmpty(str);
	}
	
	public static boolean isNumber(final String str){
		Args.notNull(str, "str");
		return str.matches("[0-9]{1,}");
	}
	
	public static boolean isBoolean(final String str){
		Args.notNull(str, "str");
		return str.equalsIgnoreCase(TRUE) || str.equalsIgnoreCase(FALSE);
	}
	
	public static boolean isEmpty(final CharSequence str) {
        if (str == null) {
            return true;
        }
        return str.length() == 0;
    }

    public static boolean isBlank(final CharSequence str) {
        if (str == null) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
