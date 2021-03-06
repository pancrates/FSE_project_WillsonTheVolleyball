import java.util.*;

public class CompanyEmailSystem {

    public static int GlobalProjectCounter;
    public static String[] ProjectPhases = new String[]{"Feasibility","Design","Implementation","Testing","Deployment","Completed"};

    private static ArrayList<CompanyProject> AllProjects;
    private static int currentProjShowing;
    //3.1
    public static void main(String[] args) {

        ///////
        //Startup
        //////
        GlobalProjectCounter = 0;
        AllProjects = new ArrayList<CompanyProject>();
        currentProjShowing=-1;
        //////////////
        //example test data
        //////////////
        //The titles should be at least 10 characters long, but we decided not to change them and consider them as test data.
        CompanyProject cp1 = new CompanyProject("Proj1");
        CompanyProject cp2 = new CompanyProject("Proj2");
        CompanyProject cp3 = new CompanyProject("Proj3");

        for (int x=0;x <10; x++) {
            CompanyEmail ce = new CompanyEmail("me"+x+"@me.com", "you"+x+"@you.com", "this is a test subject for email"+x, "this is a test message for email "+x);

            switch(x%3) {
                case 0:
                    cp1.addEmail(ce);
                    break;
                case 1:
                    cp2.addEmail(ce);
                    break;
                case 2:
                    cp3.addEmail(ce);
                    break;
            }
        }

        AllProjects.add(cp1);
        AllProjects.add(cp2);
        AllProjects.add(cp3);

        /// END OF TEST DATA ///

        System.out.println("What do you want to do?\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String s = in.next();
            try{
                if(currentProjShowing == -1) {
                    if (s.equals("P")) {
                        ListProjects();
                    } else if (s.equals("A")) {
                        AddProject(in);
                    } else if (s.equals("X")) {
                        System.out.println("Goodbye!");
                        break;
                    }else if(!(s.matches("[0-9]+"))){
                        System.out.println("Command not recognised");
                    }else if (Integer.parseInt(s) != -1 ) {
                        currentProjShowing = Integer.parseInt(s)-1;
                    } else {
                        System.out.println("Command not recognised");
                    }
                } else {
                    if (s.equals("A")) {
                        AddEmail(in);
                    }else if (s.equals("L")) {
                        ListEmails(0);
                    } else if (s.equals("F")) {
                        ListPhases();
                    } else if (s.equals("C")) {
                        ListContacts();
                    } else if (s.equals("N")) {
                        ChangeProjectPhase();
                    } else if (s.equals("X")) {
                        currentProjShowing = -1;
                    }else if(!(s.matches("[0-9]+"))){
                        System.out.println("Command not recognised");
                    }else if (Integer.parseInt(s) != -1 ) {
                        ListEmails(Integer.parseInt(s));
                    } else {
                        System.out.println("Command not recognised");
                    }
                }
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.toString() + "\n");
            }
            if(currentProjShowing == -1) {
                System.out.println("What do you want to do?\n P = List [P]rojects, [num] = Open Project [num], A = [A]dd Project, X = E[x]it Software");
            } else {
                System.out.println("What do you want to do?\n L = [L]ist Emails, A = [A]dd Email, F = List Phase [F]olders, N = Move to [N]ext Phase, [num] = List Emails in Phase [num], C = List [C]ontacts, X =  E[x]it Project");
            }
        }
        in.close();

    }
    //3.2
    private static void ListProjects(){
        for (int x = 0; x < AllProjects.size(); x++) {
            CompanyProject cp = AllProjects.get(x);
            int emailCount = cp.getEmailsForPhase().size();
            System.out.println((x+1) + ") " + cp.toString() + " - " + emailCount + " emails");
        }
    }
    //3.3
    private static void AddProject(Scanner in) {
        System.out.println("What is the title of the project?");
        in.nextLine(); // to remove read-in bug
        String title = in.nextLine();
        CompanyProject cp = new CompanyProject();
        AllProjects.add(cp);
        cp.setPTitle(title);
        if(title.length()==0) System.out.println("[Project added]");
        else if(title.length()<10) System.out.println("[The title is less than 10 characters long, default title was set.]");
        else System.out.println("[Project added]");
    }
    //3.4
    private static void ListEmails(int phaseToShow) {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        ArrayList<CompanyEmail> projectPhaseEmails = null;
        if (phaseToShow==0) {
            projectPhaseEmails = cp.getEmailsForPhase();
        } else if (phaseToShow-1 <= cp.getPhaseByID()) {
            projectPhaseEmails = cp.getEmailsForPhase(phaseToShow-1);
        } else {
            System.out.println("Error: Unknown Phase");
        }
        if (projectPhaseEmails != null) {
            System.out.println(cp.toString());
            System.out.println("\n   From                Subject");
            System.out.println("--------------------------------");
            for (int x = 0; x < projectPhaseEmails.size(); x++) {
                CompanyEmail ce = projectPhaseEmails.get(projectPhaseEmails.size()-x-1);
                System.out.println((x+1) + ") " + ce.fromAddress() + " - " + ce.subjectLine());
                if (x==10) {
                    System.out.println("...");
                    break;
                }
            }
        }
    }
    //3.5
    private static void ListPhases() {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        for (int x=0; x <= cp.getPhaseByID(); x++ ) {
            System.out.println((x+1)+") "+ProjectPhases[x]+" - "+cp.getEmailsForPhase(x).size()+" Emails");
        }
    }
    //3.6
    private static void ListContacts() {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        ArrayList<String> projectContacts = cp.getProjectContacts();
        for (int x=0; x < projectContacts.size(); x++ ) {
            System.out.println((x+1)+") "+projectContacts.get(x));
        }
    }
    //3.7
    private static void AddEmail(Scanner in) {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        CompanyEmail ce = new CompanyEmail();
        System.out.println("Which email address is it from?");
        in.nextLine(); //to remove read-in bug
        String fromAddress = in.nextLine();
        ce.setFrom(fromAddress);
        System.out.println("Which email address is it to?");
        String toAddress = in.nextLine();
        ce.setTo(toAddress);
        System.out.println("What is the Subject?");
        String subjectLine = in.nextLine();
        ce.setSubject(subjectLine);
        System.out.println("What is the Message?");
        String emailBody = in.nextLine();
        ce.setMessage(emailBody);
        if(ce.isValid()){
            System.out.println("[Email added to " + cp.toString() + "]");
            cp.addEmail(ce);
        }
        else {
            System.out.println("[Email is not valid, not added to the current Project]");
            return;
        }
    }
    //3.8
    private static void ChangeProjectPhase() {
        CompanyProject cp = AllProjects.get(currentProjShowing);
        if (cp.nextPhase()) {
            System.out.println("[Phase changed: " + cp.toString());
        } else {
            System.out.println("Project already in last phase.");
        }
    }

}
