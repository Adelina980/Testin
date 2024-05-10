package prosseses;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class SingletonFileTest {
    @Test
    public void testSingletonInstance() {
        SingletonFile instance1 = SingletonFile.getInstance();
        SingletonFile instance2 = SingletonFile.getInstance();
        assertSame(instance1, instance2);
    }
    @Test
    public void testPrivateConstructor() {
        try {
        Constructor<SingletonFile> constructor = SingletonFile.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonFile instance = constructor.newInstance();
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (!(cause instanceof IllegalAccessException)) {
                fail("Expected IllegalAccessException, but got " + cause);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            fail("Unexpected exception: " + e);
        }
    }
}