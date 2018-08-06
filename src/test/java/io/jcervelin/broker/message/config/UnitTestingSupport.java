package io.jcervelin.broker.message.config;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;

@RunWith(MockitoJUnitRunner.class)
public class UnitTestingSupport {

    private static final String TEMPLATE_PACKAGE = "io.jcervelin.broker.message.templates";

    public final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    public final PrintStream originalOut = System.out;
    public final PrintStream originalErr = System.err;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setup() {
        loadTemplates(TEMPLATE_PACKAGE);
    }

}