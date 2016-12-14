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

 public class MessengerRun {

    //Message settings
    public static final String LOCALE_RESOURCES_NAME = "WelcomeMessages";
    public static final String WELCOME_MESSAGE_MORNING = "morning";
    public static final String WELCOME_MESSAGE_DAY = "day";
    public static final String WELCOME_MESSAGE_EVENING = "evening";
    public static final String WELCOME_MESSAGE_NIGHT = "night";

    public static final String ENCODING = "UTF-8";

    //Logger
    final static Logger logger = Logger.getLogger(MessengerRun.class);

    public static void main(String[] args) {

        try {
            logger.info("Application run");

            ResourceBundle resourceBundle = getMessageResource(LOCALE_RESOURCES_NAME);
            String bundleKey = getWelcomeMessage();
            printWelcomeMessage(resourceBundle, bundleKey);

        } catch (UnsupportedEncodingException e) {
            System.out.println("You have encoding error.");
            logger.error("Unknown encoding", e);

        } catch (Exception e) {
            System.out.println("You have error. Execution stopped.");
            logger.error("Some exception", e);
        }

        logger.info("Application finished successfully.");
    }

    //Print message to console
    private static void printWelcomeMessage(ResourceBundle resourceBundle, String bundleKey) throws UnsupportedEncodingException {
        String welcomeMessage = getExactMessage(resourceBundle, bundleKey);
        String convertedMessage = convertMessage(welcomeMessage, ENCODING);

        System.out.println(convertedMessage + "\n");
        logger.info("Print to console: " + convertedMessage);
    }

    //Return real time
    private static String getExactMessage(ResourceBundle resourceBundle, String bundleKey) {
        return resourceBundle.getString(bundleKey);
    }

    //Message converted to encoding UTF-8
    static String convertMessage(String welcomeMessage, String encoding) throws UnsupportedEncodingException {
        String text = new String(welcomeMessage.getBytes("ISO-8859-1"), encoding);
        logger.info("Welcome message converted to encoding: " + encoding);
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

    //Receives current local hour
    public static ResourceBundle getMessageResource(String bundleName){
        Locale locale = Locale.getDefault();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName, locale);

        logger.info("Set location: " + locale.getCountry());

        return resourceBundle;
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
