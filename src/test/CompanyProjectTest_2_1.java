import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.*;

import static jdk.nashorn.internal.runtime.JSType.isString;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.*;



public class CompanyProjectTest_2_1 {
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
  @DisplayName("Test 2_1_1 - Test if the globalcounter is incremented by 1 and PID is equal to the new globalcounter")
  @Test
  public void test_2_1_1() throws IllegalAccessException, NoSuchFieldException {
    assertEquals(1,ces.GlobalProjectCounter);
    System.out.println(ces.GlobalProjectCounter);
    Field fi = project.getClass().getDeclaredField("PID");
    fi.setAccessible(true);
    assertEquals(ces.GlobalProjectCounter,fi.get(project));
  }

  //Authors Primoz-Zain 27/04/2018
  @DisplayName("Test 2_1_2 - Test if ProjectContacts is set to an ArrayList<String>")
  @Test
  public void test_2_1_2() throws IllegalAccessException, NoSuchFieldException {
    Field fi = project.getClass().getDeclaredField("ProjectContacts");
    fi.setAccessible(true);
    System.out.println(fi.getGenericType().getTypeName());
    assertEquals("java.util.ArrayList<java.lang.String>",fi.getGenericType().getTypeName());
  }

  //Authors Primoz-Zain-Asad 28/04/2018
  @DisplayName("Test 2_1_3 - Test if ProjectEmails is set to an ArrayList[] of type <CompanyEmail>")
  @Test
  public void test_2_1_3() throws IllegalAccessException, NoSuchFieldException {
    Field fi = project.getClass().getDeclaredField("ProjectEmails");
    fi.setAccessible(true);
    ArrayList[] array = ((ArrayList[]) fi.get(project));
    array[0].add(new CompanyEmail());
    assertEquals("CompanyEmail", array[0].get(0).getClass().getTypeName());
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test 2_1_4 - Test if pTitle is set to 'New Project'")
  @Test
  public void test_2_1_4() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    Field fi = project.getClass().getDeclaredField("PTitle");
    fi.setAccessible(true);
    assertEquals("New Project", fi.get(project));
    System.out.println(fi.get(project));
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test 2_1_5 -  Test if ProjectPhase is set to 1")
  @Test
  public void test_2_1_5() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("ProjectPhase");
    fi.setAccessible(true);
    assertEquals(0, fi.get(project));
    System.out.println(fi.get(project));
  }
}