import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;


public class WelcomeMessengerTest {

    @Test
    public void shouldGetDayPhase(){
        assertEquals("night", WelcomeMessenger.getPartOfDay(1));
        assertEquals("night", WelcomeMessenger.getPartOfDay(3));
        assertEquals("morning", WelcomeMessenger.getPartOfDay(8));
        assertEquals("day", WelcomeMessenger.getPartOfDay(15));
        assertEquals("evening", WelcomeMessenger.getPartOfDay(21));
    }

    @Test
    public void shouldGetCurrentHour(){
        GregorianCalendar calendar = new GregorianCalendar();
        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);

        assertEquals(hour, WelcomeMessenger.getCurrentHour());
    }

    @Test
    public void shouldGetGreetingMessageResourceByLocale(){
        Locale ru = new Locale("ru", "RU");
        ResourceBundle bundle = ResourceBundle.getBundle(WelcomeMessenger.MESSAGE_RESOURCES_NAME, ru);

        ResourceBundle bundleOriginal = WelcomeMessenger.getWelcomeMessageResource(WelcomeMessenger.MESSAGE_RESOURCES_NAME);
        assertThat(bundle, is(not(bundleOriginal)));
    }

    @Test(expected = MissingResourceException.class)
    public void shouldGetGreetingResource(){
        WelcomeMessenger.getWelcomeMessageResource("test");
    }


    @Test(expected = UnsupportedEncodingException.class)
    public void shouldConvertValue() throws UnsupportedEncodingException {
        WelcomeMessenger.convertValue("", "UTF-8859");
    }
}
