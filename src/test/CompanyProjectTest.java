
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.*;
import java.lang.reflect.*;

import static jdk.nashorn.internal.runtime.JSType.isString;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.*;


public class CompanyProjectTest {
    private CompanyProject project;
    private CompanyEmailSystem ces;

    @BeforeEach
    public void setUp() throws Exception {
        ces = new CompanyEmailSystem();
        project = new CompanyProject();

    }

    @AfterEach
    public void tearDown(){
        project = null;
        ces=null;
        ces.GlobalProjectCounter=0;
    }

    //Authors Primoz-Zain 27/04/2018
    @Test //Test 2.1.1 Check if globalcounter is incremented and PID is the new globalcounter
    public void test_2_1_1() throws IllegalAccessException, NoSuchFieldException {
        assertEquals(1,ces.GlobalProjectCounter);
        System.out.println(ces.GlobalProjectCounter);
        Field fi = project.getClass().getDeclaredField("PID");
        fi.setAccessible(true);
        assertEquals(ces.GlobalProjectCounter,fi.get(project));
    }

    //Authors Primoz-Zain 27/04/2018
    @Test //Test 2.1.2 Check if ProjectContacts is set to an ArrayList<String>
    public void test_2_1_2() throws IllegalAccessException, NoSuchFieldException {
        Field fi = project.getClass().getDeclaredField("ProjectContacts");
        fi.setAccessible(true);
        System.out.println(fi.getGenericType().getTypeName());
        assertEquals("java.util.ArrayList<java.lang.String>",fi.getGenericType().getTypeName());
    }

    //Authors Primoz-Zain-Asad 28/04/2018
    @Test //Test 2.1.3 Check if ProjectEmails is set to an ArrayList[] of type <CompanyEmail>
    public void test_2_1_3() throws IllegalAccessException, NoSuchFieldException {
        Field fi = project.getClass().getDeclaredField("ProjectEmails");
        fi.setAccessible(true);
        ArrayList[] array = ((ArrayList[]) fi.get(project));
        array[1].add(new CompanyEmail());
        assertEquals("CompanyEmail", array[1].get(0).getClass().getTypeName());
    }

    //Authors Zain-Asad 28/04/2018
    @Test //Test 2.1.4 Check if pTitle is set to "New Project"
    public void test_2_1_4() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        Field fi = project.getClass().getDeclaredField("PTitle");
        fi.setAccessible(true);
        assertEquals("New Project", fi.get(project));
        System.out.println(fi.get(project));
    }

    //Authors Zain-Asad 28/04/2018
    @Test //Test 2.1.5 Check if ProjectPhase is set to 1
    public void test_2_1_5() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field fi = project.getClass().getDeclaredField("ProjectPhase");
        fi.setAccessible(true);
        assertEquals(1, fi.get(project));
        System.out.println(fi.get(project));
    }
}