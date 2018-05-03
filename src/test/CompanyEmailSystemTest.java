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
      CompanyEmail email1 = new CompanyEmail("aa@","test","title","message");
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
  @ParameterizedTest
  @ValueSource(ints = {0,1,2})
  public void Test_3_4_1(int s) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
    String[] ans = new String[]{
        "Project Y [Feasibility]\n\n   From                Subject\n--------------------------------\n1) send5@send - title5\n2) send4@send - title4\n3) send3@send - title3\n",
        "Project Y [Feasibility]\n\n   From                Subject\n--------------------------------\n1) send5@send - title5\n2) send4@send - title4\n3) send3@send - title3\n",
        "Error: Unknown Phase\n"};
    Field fi = ces.getClass().getDeclaredField("AllProjects");
    fi.setAccessible(true);
    Field cps = ces.getClass().getDeclaredField("currentProjShowing");
    cps.setAccessible(true);
    cps.set(ces,2);
    //System.err.println(cps.get(ces));

    CompanyProject project3 = new CompanyProject("Project Y");
    CompanyEmail email3 = new CompanyEmail("send3@send","test3","title3","message3");
    CompanyEmail email4 = new CompanyEmail("send4@send","test4","title4","message4");
    CompanyEmail email5 = new CompanyEmail("send5@send","test5","title5","message5");
    project3.addEmail(email3);
    project3.addEmail(email4);
    project3.addEmail(email5);
    ArrayList<CompanyProject> array = (ArrayList<CompanyProject>) fi.get(ces);
    array.add(project3);
    Method m = ces.getClass().getDeclaredMethod("ListEmails", int.class);
    m.setAccessible(true);
    m.invoke(ces, s);
    //System.err.println("////Test "+s+"//////");
    System.err.println(output.toString());
    //System.err.println(ans[0]);
    //assertEquals(0,output.toString().compareTo(ans[0]));
    assertEquals(ans[s],output.toString());
  }
  @Test
  public void Test_3_3_2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

  }

}