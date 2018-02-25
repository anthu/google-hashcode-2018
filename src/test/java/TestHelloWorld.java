import minions.HelloWorld;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestHelloWorld {

    @Test
    public void testHelloWorld() throws Exception {
        HelloWorld myWorld = new HelloWorld();

        int returnValue = myWorld.printTextProvidedToConsole("Hello World");
        assertEquals(0, returnValue);
    }

    @Test
    public void greetingTest() {
        assertThat(HelloWorld.greeting("friend"), is("Hello friend!"));
    }
}
