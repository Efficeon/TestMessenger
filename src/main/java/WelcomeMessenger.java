import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Main class of application that defines phase of the day and
 * returns message defined for this exactly time (for example, Good morning, World!)
 *
 * @author Leonid Dubravsky
 * */

 public class WelcomeMessenger {

    //Message settings
    public static final String MESSAGE_RESOURCES_NAME = "WelcomeMessages";
    public static final String ENCODING = "UTF-8";
    public static final String WELCOME_MESSAGE_NIGHT = "night";
    public static final String WELCOME_MESSAGE_MORNING = "morning";
    public static final String WELCOME_MESSAGE_DAY = "day";
    public static final String WELCOME_MESSAGE_EVENING = "evening";

    //Logger
    final static Logger logger = Logger.getLogger(WelcomeMessenger.class);

    public static void main(String[] args) {
        try {
            logger.info("Application started");

            ResourceBundle bundle = getWelcomeMessageResource(MESSAGE_RESOURCES_NAME);
            String bundleKey = getWelcomeMessage();
            printWelcomeMessage(bundle, bundleKey);

        } catch (UnsupportedEncodingException e) {
            System.out.println("We have encoding error.");
            logger.error("Unknown encoding", e);

        } catch (Exception e) {
            System.out.println("We have critical error. Execution stopped.");
            logger.error("Some exception", e);
        }

        logger.info("Application finished successfully.");
    }

    private static void printWelcomeMessage(ResourceBundle bundle, String bundleKey) throws UnsupportedEncodingException {
        String initialValue = getExactMessage(bundle, bundleKey);
        String convertedValue = convertValue(initialValue, ENCODING);

        System.out.println(convertedValue + "\n");
        logger.info("Console data : " + convertedValue);

    }

    private static String getExactMessage(ResourceBundle bundle, String bundleKey) {
        return bundle.getString(bundleKey);
    }

    static String convertValue(String value, String encoding) throws UnsupportedEncodingException {
        String text = new String(value.getBytes("ISO-8859-1"), encoding);
        logger.info("WelcomeMessenger message converted to encoding: " + encoding);
        return text;
    }

    //It returns the phase associated with the time of the day
    public static String getWelcomeMessage(){
        Integer currentTime = getCurrentTime();
        String  bundleKey = getPhaseOfDay(currentTime);

        logger.info("Welcome message: " + currentTime);

        return bundleKey;
    }

    //Receives real time
    public static Integer getCurrentTime(){
        GregorianCalendar calendar = new GregorianCalendar();
        Integer currentTime = calendar.get(Calendar.HOUR_OF_DAY);

        logger.info("Get real time: " + currentTime);

        return currentTime;
    }

    public static ResourceBundle getWelcomeMessageResource(String bundleName) {
        Locale locate = getSystemDefaultLocate();
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locate);
        logger.info("Location: " + locate.getCountry());
        return bundle;
    }

    private static Locale getSystemDefaultLocate() {
        return Locale.getDefault();
    }

    //Receives phase of day
    public static String getPhaseOfDay(Integer currentTime){
        String phaseOfDay;

        if ((currentTime < 6) || (currentTime == 23)) {
            phaseOfDay = WELCOME_MESSAGE_NIGHT;
        } else if (currentTime < 9) {
            phaseOfDay = WELCOME_MESSAGE_MORNING;
        } else if (currentTime < 19) {
            phaseOfDay = WELCOME_MESSAGE_DAY;
        } else {
            phaseOfDay = WELCOME_MESSAGE_EVENING;
        }

        return phaseOfDay;
    }
}
