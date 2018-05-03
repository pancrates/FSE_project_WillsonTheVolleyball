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
      ///////////////
      ces = new CompanyEmailSystem();
      sin = new Scanner(System.in);
      Field fi = ces.getClass().getDeclaredField("AllProjects");
      fi.setAccessible(true);
      fi.set(ces, new ArrayList<CompanyProject>());
      CompanyProject project = new CompanyProject("Project X");
      CompanyEmail email1 = new CompanyEmail("realdonaldtrump@asshole.ass","test","title","message");
      project.addEmail(email1);
      CompanyProject project2 = new CompanyProject("Project Mayhem");
      ArrayList<CompanyProject> array = (ArrayList<CompanyProject>) fi.get(ces);
      array.add(project);
      array.add(project2);

      //////////////
    }

    @AfterEach
    public void tearDown(){
        System.setOut(null);
        ces = null;

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
    //@Disabled
    @Test
    public void Test_3_2_1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ces.getClass().getDeclaredMethod("ListProjects");
        method.setAccessible(true);
        method.invoke(ces);
        System.err.println(output.toString());
        assertEquals("1) Project X [Design] - 1 emails\n2) Project Mayhem [Design] - 0 emails\n",output.toString());
    }
    @Test
    public void Test_3_3_1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
      String input = "\nProject title\n";
      InputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);
      sin = new Scanner(System.in);
      //pushInput("hello laal");
      Field fi = ces.getClass().getDeclaredField("AllProjects");
      fi.setAccessible(true);
      Method method = ces.getClass().getDeclaredMethod("AddProject", Scanner.class);
      method.setAccessible(true);
      method.invoke(ces,sin);
      ArrayList<CompanyProject> al = (ArrayList<CompanyProject>) fi.get(ces);
      System.err.println(al.get(al.size()-1));
      assertEquals(0,(al.get(al.size()-1).toString().compareTo("Project title [Design]")));
      //System.err.println(output.toString());

    }
  @Test
  public void Test_3_3_2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
    String input = "\n\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    sin = new Scanner(System.in);
    //pushInput("hello laal");
    Field fi = ces.getClass().getDeclaredField("AllProjects");
    fi.setAccessible(true);
    Method method = ces.getClass().getDeclaredMethod("AddProject", Scanner.class);
    method.setAccessible(true);
    method.invoke(ces,sin);
    ArrayList<CompanyProject> al = (ArrayList<CompanyProject>) fi.get(ces);
    System.err.println(al.get(al.size()-1));
    assertEquals(0,(al.get(al.size()-1).toString().compareTo("New Project [Design]")));
    //System.err.println(output.toString());
  }
  @Test
  public void Test_3_4_1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

  }

}