package tests;

import org.junit.Assert;
import org.junit.Test;
import tests.FileCombiner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileCombinerTest {

    @Test
    public void testCombineBinaryFiles() throws IOException {
        FileCombiner.main(new String[]{"f1.txt", "f2.txt", "f3.txt"});
        InputStream is1 = new FileInputStream("f1.txt");
        InputStream is2 = new FileInputStream("f2.txt");
        InputStream is3 = new java.io.SequenceInputStream(is1,is2);
        Assert.assertArrayEquals(new FileInputStream("f3.txt").readAllBytes(), is3.readAllBytes());
    }
}