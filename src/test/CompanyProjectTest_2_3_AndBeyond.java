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

public class CompanyProjectTest_2_3_AndBeyond {
  private CompanyProject project;
  private CompanyEmailSystem ces;
  private CompanyEmail invalidCO;
  private CompanyEmail validCO;

  @BeforeEach
  public void setUp() throws Exception {
    ces = new CompanyEmailSystem();
    project = new CompanyProject("Software Project");
    invalidCO = new CompanyEmail();
    validCO = new CompanyEmail("hi@gmail.com", "bye@gmail.com", "subject", "message");
  }

  @AfterEach
  public void tearDown(){
    project = null;
    ces=null;
    ces.GlobalProjectCounter=0;
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test 2_3_1 - Test if getPID function returns the PID that was set. Which, in this case, was set to 1 when object was created")
  @Test
  public void test_2_3_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("PID");
    fi.setAccessible(true);
    assertEquals(1, fi.get(project));
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test 2_4_1 - Test if getPTitle function returns the PTitle that was set. Which, in this case, was set to 'Software Project' when object was created")
  @Test
  public void test_2_4_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("PTitle");
    fi.setAccessible(true);
    assertEquals("Software Project", fi.get(project));
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test_2_5_1 - Test if setPTitle function sets pTitle to what string is passed in, in this case its 'ProjectNew'")
  @Test
  public void test_2_5_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("PTitle");
    fi.setAccessible(true);
    project.setPTitle("ProjectNew");
    assertEquals("ProjectNew", fi.get(project));
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test_2_5_2 - Test if the setPTitle sets the a string that is more than 10 characters long")
  @Test
  public void test_2_5_2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("PTitle");
    fi.setAccessible(true);
    project.setPTitle("ProjectNew123");
    assertEquals("ProjectNew123", fi.get(project));
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test_2_5_3 - Test if the setPTitle method will set the string if it's less than 10 characters")
  @Test
  public void test_2_5_3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("PTitle");
    fi.setAccessible(true);
    project.setPTitle("Project");
    assertEquals("Software Project", fi.get(project));
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test_2_6_1 - Test if the isContact function returns false when a non existing email is passed into it")
  @Test
  public void test_2_6_1() {
    boolean contact = project.isContact("thisemaildoesntexist@gmail.com");
    assertFalse(contact);
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test_2_6_2 - Test if the isContact function returns true when an existing email is passed into it")
  @Test
  public void test_2_6_2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("ProjectContacts");
    fi.setAccessible(true);
    ArrayList<String> array = (ArrayList<String>) fi.get(project);
    array.add("thisemailexists@gmail.com");
    boolean contact = project.isContact("thisemailexists@gmail.com");
    assertTrue(contact);
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test_2_7_1 - Test if the addContact function adds an email that doesn't already exist")
  @Test
  public void test_2_7_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    project.addContact("thisemaildoesntexist@gmail.com");
    Field fi = project.getClass().getDeclaredField("ProjectContacts");
    fi.setAccessible(true);
    ArrayList<String> array = (ArrayList<String>) fi.get(project);
    assertEquals("thisemaildoesntexist@gmail.com", array.get(0));
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test_2_7_2 - Test if the addContact function adds an email that already exists")
  @Test
  public void test_2_7_2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    project.addContact("thisemaildoesntexist@gmail.com");
    project.addContact("thisemaildoesntexist@gmail.com");
    project.addContact("avoidingoutofbound@gmail.com");
    Field fi = project.getClass().getDeclaredField("ProjectContacts");
    fi.setAccessible(true);
    ArrayList<String> array = (ArrayList<String>) fi.get(project);
    boolean var;
    if (array.get(0) == array.get(1)) {
      var = false;
    } else {
      var = true;
    }
    assertTrue(var);
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test_2_7_3 - Test if the addContact function adds an invalid email address")
  @Test
  public void test_2_7_3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    project.addContact("this@.com");
    project.addContact("thisemaildoesntexist@gmail.com");
    Field fi = project.getClass().getDeclaredField("ProjectContacts");
    fi.setAccessible(true);
    ArrayList<String> array = (ArrayList<String>) fi.get(project);
    boolean var;
    if (array.get(0) == "this@.com") {
      var = false;
    } else {
      var = true;
    }
    assertTrue(var);
  }

  //Authors Zain-Asad 28/04/2018
  @DisplayName("Test_2_8_1 - Test if the addEmail function adds an invalid CompanyEmail object")
  @Test
  public void test_2_8_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    project.addEmail(invalidCO);
    project.addEmail(validCO);
    Field pp = project.getClass().getDeclaredField("ProjectPhase");
    pp.setAccessible(true);
    Field fi = project.getClass().getDeclaredField("ProjectEmails");
    fi.setAccessible(true);
    int projectphase = (int) pp.get(project);
    ArrayList[] array = (ArrayList[]) fi.get(project);
    boolean var;
    if (array[projectphase].get(0) == invalidCO) {
      var = false;
    } else {
      var = true;
    }
    assertTrue(var);
  }

  //Author Asad 29/04/2018
  @DisplayName("Test_2_8_2 - Test if addEmail function adds a valid email with an already existing person in the contact list")
  @Test
  public void test_2_8_2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field em = project.getClass().getDeclaredField("ProjectContacts");
    em.setAccessible(true);
    ArrayList<String> array2 = (ArrayList<String>) em.get(project);
    array2.add("hi@gmail.com");

    project.addEmail(validCO);
    project.addEmail(invalidCO);
    Field pp = project.getClass().getDeclaredField("ProjectPhase");
    pp.setAccessible(true);
    Field fi = project.getClass().getDeclaredField("ProjectEmails");
    fi.setAccessible(true);
    ArrayList[] array = (ArrayList[]) fi.get(project);
    int projectphase = (int) pp.get(project);
    array2.add("IT DIDN'T PASS!");
    boolean var;
    if (array[projectphase].get(0) == validCO && array2.get(1)!=("hi@gmail.com")) {
      var = true;
    } else {
      var = false;
    }
    assertTrue(var);
  }

  //Author Asad 29/04/2018
  @DisplayName("Test_2_8_3 - Test if the addEmail function adds a valid email with a non existing contact")
  @Test
  public void test_2_8_3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    project.addEmail(validCO);
    project.addEmail(invalidCO);
    Field pp = project.getClass().getDeclaredField("ProjectPhase");
    pp.setAccessible(true);
    Field fi = project.getClass().getDeclaredField("ProjectEmails");
    fi.setAccessible(true);
    Field em = project.getClass().getDeclaredField("ProjectContacts");
    em.setAccessible(true);
    ArrayList[] array = (ArrayList[]) fi.get(project);
    ArrayList<String> array2 = (ArrayList<String>) em.get(project);
    int projectphase = (int) pp.get(project);
    array2.add("IT DIDN'T PASS!");
    boolean var;
    if (array[projectphase].get(0) == validCO && array2.get(0)==("hi@gmail.com")) {
      var = true;
    } else {
      var = false;
    }
    assertTrue(var);
  }

  //Author Asad 29/04/2018
  @DisplayName("Test_2_9_1 - Test if getEmailsForPhase function returns the array list of CompanyEmail objects for the current phase of the project")
  @Test
  public void test_2_9_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("ProjectEmails");
    fi.setAccessible(true);
    Field pp = project.getClass().getDeclaredField("ProjectPhase");
    pp.setAccessible(true);
    int projectphase = (int) pp.get(project);
    ArrayList[] array = (ArrayList[]) fi.get(project);
    array[projectphase].add(validCO);
    ArrayList<CompanyEmail> value = project.getEmailsForPhase();
    boolean var;
    if (array[projectphase].get(0) == validCO) {
      var = true;
    } else {
      var = false;
    }
    assertTrue(var);
  }

  //Author Asad 29/04/2018
  @DisplayName("Test_2_10_1 - Test if the getEmailsForPhase function with parameter of '1' returns arraylist that matches the projectphase")
  @Test
  public void test_2_10_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("ProjectEmails");
    fi.setAccessible(true);
    Field pp = project.getClass().getDeclaredField("ProjectPhase");
    pp.setAccessible(true);
    ArrayList[] array = (ArrayList[]) fi.get(project);
    array[0].add(validCO);
    ArrayList<CompanyEmail> value1 = project.getEmailsForPhase(1);
    boolean var;
    if (array[0].get(0) == validCO) {
      var = true;
    } else {
      var = false;
    }
    assertTrue(var);
  }

  //Author Asad 29/04/2018
  @DisplayName("Test_2_10_2 - Test if getEmailsForPhase function with parameter of '5' returns arraylist that matches the last projectphase")
  @Test
  public void test_2_10_2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("ProjectEmails");
    fi.setAccessible(true);
    Field pp = project.getClass().getDeclaredField("ProjectPhase");
    pp.setAccessible(true);
    ArrayList[] array = (ArrayList[]) fi.get(project);
    array[0].add(validCO);
    ArrayList<CompanyEmail> value1 = project.getEmailsForPhase(5);
    boolean var;
    if (array[0].get(0) == validCO) {
      var = true;
    } else {
      var = false;
    }
    assertTrue(var);
  }


  //Author Asad 29/04/2018
  @DisplayName("Test_2_11_1 - Test if the nextPhase function returns false if it tries to increment whilst on the last phase")
  @Test
  public void test_2_11_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("ProjectEmails");
    fi.setAccessible(true);
    Field pp = project.getClass().getDeclaredField("ProjectPhase");
    pp.setAccessible(true);
    int max = ces.ProjectPhases.length + 1;
    int x = 0;
    boolean var = true;

    for(x=1; x<=max; x++) {
      var = project.nextPhase();
    }

    if(var == false) {
      assertTrue(true);
    }
    else {
      assertTrue(false);
    }
  }

  //Author Asad 29/04/2018
  @DisplayName("Test_2_11_2 - Test if the nextPhase function increments the ProjectPhase if its on the current phase")
  @Test
  public void test_2_11_2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field pp = project.getClass().getDeclaredField("ProjectPhase");
    pp.setAccessible(true);
    int before = (int) pp.get(project);
    boolean var = project.nextPhase();
    int after = (int) pp.get(project);
    if(after == ++before && var == true) {
      assertTrue(true);
    }
    else {
      assertTrue(false);
    }
  }

  //Author Asad 29/04/2018
  @DisplayName("Test_2_12_1 - Test if the getPhaseByName function returns the correct corresponding phase")
  @Test
  public void test_2_12_1() {
    String first = ces.ProjectPhases[0];
    String test = project.getPhaseByName();
    assertEquals(first,test);
  }

  //Author Asad 29/04/2018
  @DisplayName("Test_2_13_1 - Test if the getPhaseID function returns the correct phase")
  @Test
  public void test_2_13_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field pp = project.getClass().getDeclaredField("ProjectPhase");
    pp.setAccessible(true);
    int actual = (int) pp.get(project);
    int test = project.getPhaseByID();
    assertEquals(actual,test);
  }

  //Author Asad 29/04/2018
  @DisplayName("Test_2_14_1 - Test if the getProjectContacts function return the correct variable")
  @Test
  public void test_2_14_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("ProjectContacts");
    fi.setAccessible(true);
    assertEquals(fi.get(project),project.getProjectContacts());
  }

  //Author Asad 29/04/2018
  @DisplayName("Test_2_15_1 - Test if toString return the correct string")
  @Test
  public void test_2_15_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Field fi = project.getClass().getDeclaredField("PTitle");
    fi.setAccessible(true);
    Field pp = project.getClass().getDeclaredField("ProjectPhase");
    pp.setAccessible(true);
    int projectphase = (int) pp.get(project);

    String testvalue1 = (String) fi.get(project);
    String testvalue2 = ces.ProjectPhases[projectphase];
    String testString = testvalue1 + " [" + testvalue2 + "]";
    assertEquals(testString, project.toString());
  }
}



