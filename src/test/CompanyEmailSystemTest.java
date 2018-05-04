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

public class CompanyEmailSystemTest {
  private final ByteArrayOutputStream output = new ByteArrayOutputStream();
  private CompanyEmailSystem ces;
  private Scanner sin;
  public void pushInput(String input){
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
  }
    @BeforeEach
    public void setUp() throws Exception {
      System.setOut(new PrintStream(output));
      ces = new CompanyEmailSystem();
    }

    @AfterEach
    public void tearDown(){
      System.setOut(null);
      System.setIn(null);
      ces = null;
      System.err.println("/////////// Test ended");

    }
    //@Disabled
    @Test
    public void Test_3_1_1() {
      pushInput("X\r\n");
      CompanyEmailSystem.main(null);
      System.err.println(output.toString());
      //System.err.println(output.toString());
      String b = output.toString();
      String ans = "What do you want to do?\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it\nGoodbye!\n";
      assertEquals(0, output.toString().compareTo(ans));
    }
  @Test
  public void Test_3_1_2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s =
        "1) Proj1 [Feasibility] - 4 emails\n" +

        "2) Proj2 [Feasibility] - 3 emails\n" +

        "3) Proj3 [Feasibility] - 3 emails";
    final InputStream in = new ByteArrayInputStream("P\r\nX\r\n".getBytes());
    System.setIn(in);
    ces.main(null);
    assertEquals(s,output.toString().substring(104, 205));
  }
  @Test
  public void Test_3_1_3() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "[Project added]";
    final InputStream in = new ByteArrayInputStream("A\r\nthisislongerthanten\r\n".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(138, 153));
  }
  @Test
  public void Test_3_1_4() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "[The title is less than 10 characters long, default title was set.]";
    final InputStream in = new ByteArrayInputStream("A\r\nthisisnot\r\n".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(138, 205));
  }
  @Test
  @DisplayName("Test_3_1_5 - Test if project is added corectly if title is set to empty string")
  public void Test_3_1_5() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "[Project added]";
    final InputStream in = new ByteArrayInputStream("A\r\n\r\n".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(138, 153));
  }

  @Test
  @DisplayName("Test_3_1_6 - Test if the main enters the corect project menu")
  public void Test_3_1_6() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "What do you want to do?\n" +
        " L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project";
    final InputStream in = new ByteArrayInputStream("1".getBytes());
    System.setIn(in);
    ces.main(null);
	    	Field fi = ces.getClass().getDeclaredField("currentProjShowing");
	        fi.setAccessible(true);
	        if((int) fi.get(ces) != 0) {
	        	assertTrue(false);
	        }
	        else {
    assertEquals(s,output.toString().substring(104, output.toString().length()-1));
    }
  }

  @Test
  @DisplayName("Test_3_1_7 - INPUT={\"2\"} Test if the main enters the corect project menu")
  public void Test_3_1_7() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "What do you want to do?\n" +
        " L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project";
    final InputStream in = new ByteArrayInputStream("2\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(104, 293));
  }
  @Test
  @DisplayName("Test_3_1_8 - Test if the opened project displays all emails for the current project phase")
  public void Test_3_1_8() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Proj1 [Feasibility]\n\n   From                Subject\n--------------------------------\n1) me9@me.com - this is a test subject for email9\n2) me6@me.com - this is a test subject for email6\n3) me3@me.com - this is a test subject for email3\n4) me0@me.com - this is a test subject for email0\nWhat do you want to do?\n L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project";
    final InputStream in = new ByteArrayInputStream("1\nL".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, output.toString().length()-1));
  }
  @Test
  @DisplayName("Test_3_1_9 - Test if the add email works on an open project (valid input)")
  public void Test_3_1_9() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Which email address is it from?\nWhich email address is it to?\nWhat is the Subject?\nWhat is the Message?\n[Email added to Proj2 [Feasibility]]";
    final InputStream in = new ByteArrayInputStream("2\r\nA\nvalid@gmail.com\nvalid2@gmail.com2\nSubject\nBody4\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, 434));
  }
  @Test
  @DisplayName("Test_3_1_10 - Test if the add email works on an open project (invalid input)")
  public void Test_3_1_10() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "[Email is not valid, not added to the current Project]";
    final InputStream in = new ByteArrayInputStream("2\r\nA\ninvalid@com\ninvalid2@com2\nSubject\nBody4\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(398, 452));
  }
  @Test
  @DisplayName("Test_3_1_11 - Test if the add email actually adds the emails (not only prints right) (valid input)")
  public void Test_3_1_11() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "1) Proj1 [Feasibility] - 4 emails\n2) Proj2 [Feasibility] - 3 emails\n3) Proj3 [Feasibility] - 5 emails";
    final InputStream in = new ByteArrayInputStream("3\rA\rvalid@gmail.com\rvalid2@gmail.com\rSubject\rBody\rA\rvalid3@gmail.com\rvalid4@gmail.com\rSubject2\rBody2\rX\rP".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(1069, 1170));
  }
  @Test
  @DisplayName("Test_3_1_12 - Test if the existing project phases are correctly printed")
  public void Test_3_1_12() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "1) Feasibility - 3 Emails";
    final InputStream in = new ByteArrayInputStream("2\nF\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, 319));
  }
  @Test
  @DisplayName("Test_3_1_13 - Test if the selected project moves to the nex phase on N")
  public void Test_3_1_13() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "[Phase changed: Proj2 [Design]";
    final InputStream in = new ByteArrayInputStream("2\nN\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, 324));
  }
  @Test
  @DisplayName("Test_3_1_14 - Select a project then press N twice to move the project Phase twice AND check whether its changed to that phase by displaying the projects.")
  public void Test_3_1_14() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "1) Proj1 [Feasibility] - 4 emails\n2) Proj2 [Implementation] - 0 emails\n3) Proj3 [Feasibility] - 3 emails";
    final InputStream in = new ByteArrayInputStream("2\nN\nN\nX\nP\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(857, 961));
  }
  @Test
  @DisplayName("Test_3_1_15 - Select a project then move to the last phase AND press N again to see the error message")
  public void Test_3_1_15() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Project already in last phase.";
    final InputStream in = new ByteArrayInputStream("2\nN\nN\nN\nN\nN\nN\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(1415, 1445));
  }
  @Test
  @DisplayName("Test_3_1_16 - Select a project then pass in 0 to show the list of emails of the current phase")
  public void Test_3_1_16() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Proj3 [Feasibility]\n\n   From                Subject\n--------------------------------\n1) me8@me.com - this is a test subject for email8\n2) me5@me.com - this is a test subject for email5\n3) me2@me.com - this is a test subject for email2";
    final InputStream in = new ByteArrayInputStream("3\n0\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, 528));
  }
  @Test
  @DisplayName("Test_3_1_17 - Select a project then go to a next phase TWICE, then press 0 to check the list of emails, which should be 0")
  public void Test_3_1_17() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Proj3 [Implementation]\n\n   From                Subject\n--------------------------------\n";
    final InputStream in = new ByteArrayInputStream("3\nN\nN\n0\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(744, 832));
  }
  @Test
  @DisplayName("Test_3_1_18 - Select a project then go to the next phase THRICE, then add a valid email to it by using A then press 0 to show to emails of the phase")
  public void Test_3_1_18() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Proj1 [Testing]\n\n   From                Subject\n--------------------------------\n1) valid@gmail.com - Subject";
    final InputStream in = new ByteArrayInputStream("1\nN\nN\nN\nA\nvalid@gmail.com\nvalid2@gmail.com\nSubject\nBody\n0\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(1293, 1402));
  }
  @Test
  @DisplayName("Test_3_1_19 - Select a Project then increment its phase 4 times and input the actual phase ID to show its emails")
  public void Test_3_1_19() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Proj2 [Deployment]\n\n   From                Subject\n--------------------------------\n";
    final InputStream in = new ByteArrayInputStream("2\nN\nN\nN\nN\n5\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(1191, 1275));
  }
  @Test
  @DisplayName("Test_3_1_20 - Select a Project and try to input a number of a Phase that Project is not yet in.")
  public void Test_3_1_20() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Error: Unknown Phase";
    final InputStream in = new ByteArrayInputStream("1\n5\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, 314));
  }
  @Test
  @DisplayName("Test_3_1_21 - Select a Project then go to 3rd phase phase, add an email to it. THEN press that phases number ID to show its emails")
  public void Test_3_1_21() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Proj3 [Implementation]\n\n   From                Subject\n--------------------------------\n1) valid@gmail.com - Subject\n";
    final InputStream in = new ByteArrayInputStream("3\nN\nN\nA\nvalid@gmail.com\nvalid2@gmail.com\nSubject\nBody\n3\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(1078, 1195));
  }
  @Test
  @DisplayName("Test_3_1_22 - Select project 1 and then press C to see the emails of that list")
  public void Test_3_1_22() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "1) you0@you.com\n2) you3@you.com\n3) you6@you.com\n4) you9@you.com";
    final InputStream in = new ByteArrayInputStream("1\nC\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, 357));
  }
  @Test
  @DisplayName("Test_3_1_23 - Select Project 2 and then press C to see the emails of that list")
  public void Test_3_1_23() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "1) you1@you.com\n2) you4@you.com\n3) you7@you.com";
    final InputStream in = new ByteArrayInputStream("2\nC\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, 341));
  }
  @Test
  @DisplayName("Test_3_1_24 - Select Project 3 and then press C to see the emails of that list")
  public void Test_3_1_24() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "1) you2@you.com\n2) you5@you.com\n3) you8@you.com";
    final InputStream in = new ByteArrayInputStream("3\nC\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, 341));
  }
  @Test
  @DisplayName("Test_3_1_25 - Select a project, add an email to it and then press C to see all the contacts")
  public void Test_3_1_25() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "1) you1@you.com\n2) you4@you.com\n3) you7@you.com\n4) valid2@gmail.com";
    final InputStream in = new ByteArrayInputStream("2\nA\nvalid@gmail.com\nvalid2@gmail.com\nSubject\nBody\nC\nX\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(625, 692));
  }
  @Test
  @DisplayName("Test_3_1_26 - Select a project then X to exit")
  public void Test_3_1_26() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "What do you want to do?\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it Software";
    final InputStream in = new ByteArrayInputStream("2\nX".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, output.toString().length()-1));
  }
  @Test
  @DisplayName("Test_3_1_27 - Input an invalid command character")
  public void Test_3_1_27() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Command not recognised";
    final InputStream in = new ByteArrayInputStream("Z".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(104, 126));
  }
  @Test
  @DisplayName("Test_3_1_28 - Select a project then input an invalid character")
  public void Test_3_1_28() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
    String s = "Command not recognised";
    final InputStream in = new ByteArrayInputStream("2\nI".getBytes());
    System.setIn(in);
    ces.main(null);
    System.err.println(output.toString());
    assertEquals(s,output.toString().substring(294, 316));
  }







}