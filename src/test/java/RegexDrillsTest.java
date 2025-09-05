import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RegexDrillsTest {

    @Test void removeNonAlnum() {
        assertEquals("Aman12", RegexDrills.removeNonAlnum("A man, 12!"));
        assertEquals("HelloWorld2025", RegexDrills.removeNonAlnum("Hello-World_2025").replace("_",""));
    }

    @Test void removeWhitespace() {
        assertEquals("abcd", RegexDrills.removeWhitespace("a b\tc\nd"));
        assertEquals("xy",   RegexDrills.removeWhitespace("  x  y  "));
    }

    @Test void keepOnlyDigits() {
        assertEquals("1239", RegexDrills.keepOnlyDigits("abc123x9"));
        assertEquals("",     RegexDrills.keepOnlyDigits("no-digits"));
    }

    @Test void isAllDigits() {
        assertTrue(RegexDrills.isAllDigits("12345"));
        assertFalse(RegexDrills.isAllDigits("12a45"));
        assertFalse(RegexDrills.isAllDigits(""));
    }

    @Test void isSimpleEmail() {
        assertTrue(RegexDrills.isSimpleEmail("user.name+tag@sub.domain.com"));
        assertFalse(RegexDrills.isSimpleEmail("bad@@domain..com"));
        assertFalse(RegexDrills.isSimpleEmail("a@b"));
    }

    @Test void splitTokens() {
        String[] t = RegexDrills.splitTokens("Hello, world; this.is  regex 101!");
        assertEquals(6, t.length); // ["Hello","world","this","is","regex","101"]
    }

    @Test void normalizeSpaces() {
        assertEquals("Hello world Java regex", RegexDrills.normalizeSpaces("Hello    world   Java   regex"));
    }

    @Test void extractEmails() {
        List<String> out = RegexDrills.extractEmails("Contact: a@x.com, b.c-d+z@sub.y.org; invalid: x@@y, a@b");
        assertEquals(2, out.size());
        assertTrue(out.contains("a@x.com"));
        assertTrue(out.contains("b.c-d+z@sub.y.org"));
    }

    @Test void maskPhoneKeepLast4() {
        String masked = RegexDrills.maskPhoneKeepLast4("Call 9123-4567 or 65 8123 9999.");
        assertTrue(masked.contains("4567"));
        assertTrue(masked.contains("9999"));
    }

    @Test void findAllIntegers() {
        assertEquals(List.of(101, 202, 303, 4), RegexDrills.findAllIntegers("IDs: 101, 202 and 303; rev=04"));
    }

    @Test void collapseRepeatingPunct() {
        assertEquals("What? Really! No. way-", RegexDrills.collapseRepeatingPunct("What??? Really!! No.... way---"));
    }

    @Test void isDateYYYYMMDD() {
        assertTrue(RegexDrills.isDateYYYYMMDD("2025-09-05"));
        assertFalse(RegexDrills.isDateYYYYMMDD("25-9-5"));
    }
}
