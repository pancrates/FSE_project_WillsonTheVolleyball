import org.junit.jupiter.api.*;
import java.lang.reflect.*;

import static jdk.nashorn.internal.runtime.JSType.isString;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.*;


class CompanyEmailTest {
        CompanyEmail ce;

    @BeforeEach
    void setUp(){
        ce = new CompanyEmail("jackwillson@companyname.com","jasonBo@companyname.com","I have something you need","There was an item left in the office that I think belongs to you");
    }
    @AfterEach
    void cleanUp(){
        ce = null;
    }
    @Test
    @DisplayName("test_1_1_1 constructor without arguments")
    void test_1_1_1() throws IllegalAccessException, NoSuchFieldException {
        ce = new CompanyEmail();
        Field[] fields = ce.getClass().getDeclaredFields();
        for (Field fi : fields){
            fi.setAccessible(true);
            assertEquals(null,fi.get(ce));

        }
        System.out.println("Test 1.1.1 passed");
    }
    @Test
    @DisplayName("test_1_2_1 constructor with values")
    void test_1_2_1() throws NoSuchFieldException, IllegalAccessException {
        Field fAddress = ce.getClass().getDeclaredField("fromAddress");
        fAddress.setAccessible(true);
        Field tAddress = ce.getClass().getDeclaredField("toAddress");
        tAddress.setAccessible(true);
        Field subLine = ce.getClass().getDeclaredField("subjectLine");
        subLine.setAccessible(true);
        Field eAddress = ce.getClass().getDeclaredField("emailMessage");
        eAddress.setAccessible(true);
        assertEquals("jackwillson@companyname.com",fAddress.get(ce));
        assertEquals("jasonBo@companyname.com",tAddress.get(ce));
        assertEquals("I have something you need",subLine.get(ce));
        assertEquals("There was an item left in the office that I think belongs to you",eAddress.get(ce));
        System.out.println("Test 1.2.1 passed");
    }
    @Test
    @DisplayName("test_1_3_1 fromAddress with valid field")
    void test_1_3_1() throws NoSuchFieldException, IllegalAccessException {
        Field fAddress = ce.getClass().getDeclaredField("fromAddress");
        fAddress.setAccessible(true);
        assertEquals(fAddress.get(ce),ce.fromAddress());
        System.out.println("Test 1.3.1 passed");
    }
    @Test
    @DisplayName("test_1_3_2 fromAddress with empty field")
    void test_1_3_2() throws NoSuchFieldException, IllegalAccessException {
        ce = new CompanyEmail();
        Field fAddress = ce.getClass().getDeclaredField("fromAddress");
        fAddress.setAccessible(true);
        assertEquals(fAddress.get(ce),  ce.fromAddress());
        System.out.println("Test 1.3.2 passed");
    }
    @Test
    @DisplayName("test_1_4_1 toAddress with valid field")
    void test_1_4_1() throws NoSuchFieldException, IllegalAccessException {
        Field tAddress = ce.getClass().getDeclaredField("toAddress");
        tAddress.setAccessible(true);
        assertEquals(tAddress.get(ce),ce.toAddress());
        System.out.println("Test 1.4.1 passed");
    }
    @Test
    @DisplayName("test_1_4_2 toAddress with empty field")
    void test_1_4_2() throws NoSuchFieldException, IllegalAccessException {
        ce = new CompanyEmail();
        Field tAddress = ce.getClass().getDeclaredField("toAddress");
        tAddress.setAccessible(true);
        assertEquals(tAddress.get(ce),ce.toAddress());
        System.out.println("Test 1.4.2 passed");
    }
    @Test
    @DisplayName("test_1_5_1 subjectLine with valid field")
    void test_1_5_1() throws NoSuchFieldException, IllegalAccessException {
        Field fi = ce.getClass().getDeclaredField("subjectLine");
        fi.setAccessible(true);
        assertEquals(fi.get(ce),ce.subjectLine());
        System.out.println("Test 1.5.1 passed");
    }
    @Test
    @DisplayName("test_1_5_2 subjectLine with empty field")
    void test_1_5_2() throws NoSuchFieldException, IllegalAccessException {
        ce = new CompanyEmail();
        Field fi = ce.getClass().getDeclaredField("subjectLine");
        fi.setAccessible(true);
        assertEquals(fi.get(ce),ce.subjectLine());
        System.out.println("Test 1.5.2 passed");
    }

    @Test
    @DisplayName("test_1_6_1 emailMessage with valid field")
    void test_1_6_1() throws NoSuchFieldException, IllegalAccessException {
        Field fi = ce.getClass().getDeclaredField("emailMessage");
        fi.setAccessible(true);
        assertEquals(fi.get(ce),ce.emailMessage());
        System.out.println("Test 1.6.1 passed");
    }
    //@Disabled //FOUND BUG
    @Test
    @DisplayName("test_1_6_2 emailMessage with empty field")
    void test_1_6_2() throws NoSuchFieldException, IllegalAccessException {
        ce = new CompanyEmail();
        Field fi = ce.getClass().getDeclaredField("emailMessage");
        fi.setAccessible(true);
        assertEquals(fi.get(ce),ce.emailMessage());
        System.out.println("Test 1.6.2 passed");
    }

    @ParameterizedTest
    @ValueSource(strings = { "dianakendrick@companyemail.com","diana@com","mynameishamletandthistobeornototbe@gmail.com","keyboard",""  })
    void test_1_7(String s) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Method m;
        String out;
        String prev;
        Field fi = ce.getClass().getDeclaredField("fromAddress");
        fi.setAccessible(true);
        prev= (String)fi.get(ce);
        Class[] methodParameters = new Class[]{ String.class};
        Object[] params = new Object[]{ s };
        m = ce.getClass().getDeclaredMethod("setFrom",methodParameters);
        m.setAccessible(true);
        out = (String) m.invoke(ce, params);
        if (isValidEmailAddress(s)&&(s.length()<40)) {
            assertEquals(s,fi.get(ce));
        }
        else{
            assertEquals(prev,fi.get(ce));
        }
        System.out.println(fi.get(ce));
    }

    @ParameterizedTest
    @ValueSource(strings = { "dianakendrick@companyemail.com","diana@.com","hetragedyofhamletprinceofdenmarkasciitextplacedinthepublicdomainbymobylexicaltools1992@gmail.com","keyboard","" })
    void test_1_8(String s) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Method m;
        String out;
        String prev;
        Field fi = ce.getClass().getDeclaredField("toAddress");
        fi.setAccessible(true);
        prev= (String)fi.get(ce);
        Class[] methodParameters = new Class[]{ String.class};
        Object[] params = new Object[]{ s };
        m = ce.getClass().getDeclaredMethod("setTo",methodParameters);
        m.setAccessible(true);
        out = (String) m.invoke(ce, params);
        if (isValidEmailAddress(s)&&(s.length()<40)) {
                assertEquals(s,fi.get(ce));
        }
        else{
            assertEquals(prev,fi.get(ce));
        }
        System.out.println(fi.get(ce));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Pizza got cold","thetragedyofhamletprinceofdenmarkasciitextplacedinthepublicdomainbymobylexicaltools1992sgmlmarkupbyjonbosak19921994xmlversionbyjonbosak19961999",""  })
    void test_1_9(String s) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Method m;
        String out;
        String prev;
        Field fi = ce.getClass().getDeclaredField("subjectLine");
        fi.setAccessible(true);
        prev= (String)fi.get(ce);
        Class[] methodParameters = new Class[]{ String.class};
        Object[] params = new Object[]{ s };
        m = ce.getClass().getDeclaredMethod("setSubject", String.class);
        m.setAccessible(true);
        out = (String) m.invoke(ce, params);
        assertEquals(s,fi.get(ce));
        System.out.println(fi.get(ce));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Pizza got cold",""  })
    void test_1_10(String s) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Method m;
        String out;
        String prev;
        Field fi = ce.getClass().getDeclaredField("emailMessage");
        fi.setAccessible(true);
        prev= (String)fi.get(ce);
        Class[] methodParameters = new Class[]{ String.class};
        Object[] params = new Object[]{ s };
        m = ce.getClass().getDeclaredMethod("setMessage", String.class);
        m.setAccessible(true);
        out = (String) m.invoke(ce, params);
        assertEquals(s,fi.get(ce));
        System.out.println(fi.get(ce));
    }
    @Test
    @DisplayName("test_1_11_1 All values set")
    void test_1_11_1() throws NoSuchFieldException, IllegalAccessException {
        assertTrue(ce.isValid());
        System.out.println(ce.isValid());
    }
    @Test
    @DisplayName("test_1_11_2 Null is pressent")
    void test_1_11_2() throws IllegalAccessException, NoSuchFieldException {
        Field fi = ce.getClass().getDeclaredField("fromAddress");
        fi.setAccessible(true);
        fi.set(ce,null);
        assertFalse(ce.isValid());
        System.out.println(ce.isValid());
    }
    @Test
    @DisplayName("test_1_11_3 Null is pressent")
    void test_1_11_3() throws IllegalAccessException, NoSuchFieldException {
        Field fi = ce.getClass().getDeclaredField("toAddress");
        fi.setAccessible(true);
        fi.set(ce,null);
        assertFalse(ce.isValid());
        System.out.println(ce.isValid());
    }
    @Test
    @DisplayName("test_1_11_4 Null is pressent")
    void test_1_11_4() throws IllegalAccessException, NoSuchFieldException {
        Field fi = ce.getClass().getDeclaredField("subjectLine");
        fi.setAccessible(true);
        fi.set(ce,null);
        assertFalse(ce.isValid());
        System.out.println(ce.isValid());
    }
    @Test
    @DisplayName("test_1_11_5 Null is pressent")
    void test_1_11_5() throws IllegalAccessException, NoSuchFieldException {
        Field fi = ce.getClass().getDeclaredField("emailMessage");
        fi.setAccessible(true);
        fi.set(ce,null);
        assertFalse(ce.isValid());
        System.out.println(ce.isValid());
    }
    @ParameterizedTest
    @ValueSource(strings = { "Pizza got cold",""  })
    void test_1_12(String s) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Field fi = ce.getClass().getDeclaredField("subjectLine");
        fi.setAccessible(true);
        fi.set(ce,s);
        if(s.length()==0){
            assertEquals("[no subject]",ce.isEmptyString());
        }
        else assertEquals(s,ce.isEmptyString());
        System.out.println(ce.isEmptyString());
    }

public boolean isValidEmailAddress(String email) {
    String ePattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
    java.util.regex.Matcher m = p.matcher(email);
    return m.matches();
}

}