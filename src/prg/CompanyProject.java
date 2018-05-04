import java.util.ArrayList;
import java.util.Scanner;

public class CompanyProject {
    private int PID;
    private String PTitle;
    private ArrayList<String> ProjectContacts;
    private int ProjectPhase;
    private ArrayList[] ProjectEmails = new ArrayList[6];
    //2.1
    public CompanyProject() {
        CompanyEmailSystem.GlobalProjectCounter++;
        PID = CompanyEmailSystem.GlobalProjectCounter;
        PTitle = "New Project";
        ProjectContacts = new ArrayList<String>();
        ProjectPhase = 0;
        ProjectEmails[ProjectPhase] = new ArrayList<CompanyEmail>();
    }
    //2.2
    public CompanyProject(String pTitle) {
        CompanyEmailSystem.GlobalProjectCounter++;
        PID = CompanyEmailSystem.GlobalProjectCounter;
        PTitle = pTitle;
        ProjectContacts = new ArrayList<String>();
        ProjectPhase = 0;
        ProjectEmails[ProjectPhase] = new ArrayList<CompanyEmail>();
    }
    //2.3
    public int getPID() {
        return PID;
    }
    //2.4
    public String getPTitle() {
        return PTitle;
    }
    //2.5
    public void setPTitle(String pTitle) {
        if (pTitle.length() >= 10 ) {
            PTitle = pTitle;
        }
    }
    //2.6
    public boolean isContact(String emailAddress) {
        return ProjectContacts.contains(emailAddress);
    }
    //2.7
    public void addContact(String emailAddress) {
        String ePattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(emailAddress);
        if (m.matches()&& (emailAddress.length()<40)) {
            if(ProjectContacts.contains(emailAddress)) {
                return;
            }
            else {
                ProjectContacts.add(emailAddress);
            }
        }

    }
    //2.8
    public void addEmail(CompanyEmail newEmail) {
        if (newEmail.isValid()) {
            ProjectEmails[ProjectPhase].add(newEmail);
            if (ProjectContacts.contains(newEmail.fromAddress())) {
                //do nothing
            } else {
                ProjectContacts.add(newEmail.fromAddress());
            }
        }
    }
    //2.9
    public ArrayList<CompanyEmail> getEmailsForPhase() {
        return ProjectEmails[ProjectPhase];
    }
    //2.10
    public ArrayList<CompanyEmail> getEmailsForPhase(int thePhase) {
        return ProjectEmails[thePhase];
    }
    //2.11
    public boolean nextPhase() {
        ProjectPhase++;
        if (ProjectPhase > CompanyEmailSystem.ProjectPhases.length) {
            return false;
        } else {
            return true;
        }
    }
    //2.12
    public String getPhaseByName() {
        return CompanyEmailSystem.ProjectPhases[ProjectPhase];
    }
    //2.13
    public int getPhaseByID() {
        return ProjectPhase;
    }
    //2.14
    public ArrayList<String> getProjectContacts() {
        return ProjectContacts;
    }
    //2.15

    public String toString() {
        return PTitle + " [" + getPhaseByName() + "]";
    }

}