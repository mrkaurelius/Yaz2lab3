package com.mrkaurelius.yazlabII3;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void shouldSerializeGraphFromFile() {
        assertTrue(true);
    }

    @Test
    public void shouldSerialiseFile() {
        TGFReader reader = new TGFReader("graphs/basicgraph.tgf");
        reader.parseFile();
    }

}
