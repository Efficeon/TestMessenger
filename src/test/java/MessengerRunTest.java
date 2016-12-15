import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 * Main class of definition phase of the day and print message that defined for this time.
 * @author Leonid Dubravsky
 */

public class MessengerRunTest {
    @Test
    public void shouldGetDayPhase(){
        assertEquals("night", MessengerRun.getPhaseOfDay(2));
        assertEquals("morning", MessengerRun.getPhaseOfDay(7));
        assertEquals("day", MessengerRun.getPhaseOfDay(17));
        assertEquals("evening", MessengerRun.getPhaseOfDay(20));
    }

    @Test
    public void shouldGetCurrentTime(){
        GregorianCalendar calendar = new GregorianCalendar();
        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);

        assertEquals(hour, MessengerRun.getCurrentTime());
    }

    @Test(expected = UnsupportedEncodingException.class)
    public void shouldConvertValue() throws UnsupportedEncodingException {
        MessengerRun.convertMessage("", "UTF-8859-1");
    }

    @Test(expected = MissingResourceException.class)
    public void shouldGetWelcomeResource(){
        MessengerRun.getMessageResource("test");
    }
}
