package tests;

import org.junit.Test;
import tests.JsonBracketChecker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JsonBracketCheckerTest {

    @Test
    public void testNullFilename() {
        assertThrows(NullPointerException.class, () -> JsonBracketChecker.checkBrackets(null));
    }

    @Test
    public void testFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> JsonBracketChecker.checkBrackets("nonexistent_file.json"));
    }

    @Test
    public void testInvalidCharacter() {
        String invalidJson = "{ \"name\": \"John\", \"age\": 30, \"city\": Москва }";
        assertThrows(IOException.class, () -> JsonBracketChecker.checkBrackets("invalidJson"));
    }

    @Test
    public void testCorrectBrackets() throws IOException {
        assertEquals(0, JsonBracketChecker.checkBrackets("correct_brackets.json"));
    }

    @Test
    public void testMissingClosingBracket() throws IOException {
        assertEquals(63, JsonBracketChecker.checkBrackets("missing_closing_bracket.json"));
    }
}
