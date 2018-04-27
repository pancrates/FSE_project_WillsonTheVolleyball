
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
    private ArrayList<String> testArrayS;
    private ArrayList<CompanyEmail> testArrayC;
    private CompanyEmail CompanyEmailObject;
    private CompanyEmailSystem ces;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() throws Exception {
        ces = new CompanyEmailSystem();
        project = new CompanyProject();

    }
    @AfterEach
    void tearDown(){
        project = null;
        ces=null;
        ces.GlobalProjectCounter=0;
    }

    @Test
    @DisplayName("test_2_1_1 constructor increments global counter  + id set to global counter")
    void test_2_1_1() throws IllegalAccessException, NoSuchFieldException {
        assertEquals(1,ces.GlobalProjectCounter);
        System.out.println(ces.GlobalProjectCounter);
        Field fi = project.getClass().getDeclaredField("PID");
        fi.setAccessible(true);
        assertEquals(ces.GlobalProjectCounter,fi.get(project));
    }
    @Test
    @DisplayName("test_2_1_2 constructor corectly creates a projectContacts arrayList<String>")
    void test_2_1_2() throws IllegalAccessException, NoSuchFieldException {
        Field fi = project.getClass().getDeclaredField("ProjectContacts");
        fi.setAccessible(true);
        System.out.println(fi.getGenericType().getTypeName());
        assertEquals("java.util.ArrayList<java.lang.String>",fi.getGenericType().getTypeName());
    }
    @Test
    @DisplayName("test_2_1_3 constructor corectly creates a projectContacts arrayList<String>")
    void test_2_1_3() throws IllegalAccessException, NoSuchFieldException {
        Field fi = project.getClass().getDeclaredField("ProjectEmails");
        fi.setAccessible(true);
        //System.out.println((fi.get(project)));

        Class stringArrayClass = fi.getClass();
        ArrayList[] aa = ((ArrayList[]) fi.get(project));
        aa[1].add(new CompanyEmail());
        System.out.println((ArrayList<CompanyEmail>) aa[1]);
        //System.out.println(fi.get(project));
        Class stringArrayComponentType = stringArrayClass.getComponentType();
        //System.out.println(stringArrayComponentType);
        //assertEquals("java.util.ArrayList<java.lang.String>",);
    }


}
/*
ParameterizedType stringListType = (ParameterizedType) fi.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.
        //assertEquals(ces.GlobalProjectCounter,fi.get(project));
*/