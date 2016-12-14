import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
/**
 * Main class of definition phase of the day and print message that defined for this time.
 * @author Leonid Dubravsky
 */

public class WelcomeMessengerTest {
    @Test
    public void shouldGetDayPhase(){
        assertEquals("night", WelcomeMessenger.getPhaseOfDay(2));
        assertEquals("morning", WelcomeMessenger.getPhaseOfDay(7));
        assertEquals("day", WelcomeMessenger.getPhaseOfDay(17));
        assertEquals("evening", WelcomeMessenger.getPhaseOfDay(20));
    }

    @Test
    public void shouldGetCurrentTime(){
        GregorianCalendar calendar = new GregorianCalendar();
        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);

        assertEquals(hour, WelcomeMessenger.getCurrentTime());
    }

    @Test
    public void shouldGetWelcomeMessageResourceByLocale(){
        Locale ru = new Locale("ru", "RU");
        ResourceBundle bundle = ResourceBundle.getBundle(WelcomeMessenger.LOCALE_RESOURCES_NAME, ru);

        ResourceBundle bundleOriginal = WelcomeMessenger.getMessageResource(WelcomeMessenger.LOCALE_RESOURCES_NAME);
        assertThat(bundle, is(not(bundleOriginal)));
    }

    @Test(expected = UnsupportedEncodingException.class)
    public void shouldConvertValue() throws UnsupportedEncodingException {
        WelcomeMessenger.convertMessage("", "UTF-8859-1");
    }

    @Test(expected = MissingResourceException.class)
    public void shouldGetWelcomeResource(){
        WelcomeMessenger.getMessageResource("test");
    }
}
