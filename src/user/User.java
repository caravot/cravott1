package user;

/**
 * Created by carrie on 12/6/16.
 */
import java.io.Serializable;
import java.util.Arrays;

// save user registration
public class User implements Serializable {
    // user variables
    private String name;
    private String email;
    private String empStatus;
    private String[] courses;
    private String[] fees;
    private int totalCost;

    // default user initialization
    public User() {
        name = "";
        email = "";
        empStatus = "";
        courses = new String[0];
        fees = new String[0];
        totalCost = 0;
    }

    // set user variable functions
    public void setName(String n) {
        name = n;
    }

    public void setEmail(String e) {
        email = e;
    }

    public void setEmpStatus(String es) {
        empStatus = es;
    }

    public void setCourses(String[] c) {
        courses = c;

        // update total cost
        setTotalCost();
    }

    public void setFees(String[] f) {
        fees = f;

        // update total cost
        setTotalCost();
    }

    public void setTotalCost(int tc) { totalCost = tc; }

    // get user variable functions
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public String[] getCourses() {
        return courses;
    }

    public String[] getFees() {
        return fees;
    }

    public int getTotalCost() { return totalCost; }

    public String getCoursesToList() {
        return (Arrays.toString(courses)).replace("[", "").replace("]", "").replace(", ", ",");
    }

    public String getFeesToList() {
        return (Arrays.toString(fees)).replace("[", "").replace("]", "").replace(", ", ",");
    }

    // determine if a fee is listed in fees selected by user
    public boolean findFee(String f) {
        for (String fee : fees) {
            if (fee.equals(f)) {
                return true;
            }
        }

        return false;
    }

    // determine if a fee is listed in courses selected by user
    public boolean findCourse(String c) {
        for (String course : courses) {
            if (course.equals(c)) {
                return true;
            }
        }

        return false;
    }

    public void setTotalCost() {
        int courseCost = 0;
        int total = 0;

        if (empStatus.equals("jhu-employee")) {
            courseCost = 850;
        } else if (empStatus.equals("jhu-student")) {
            courseCost = 1000;
        } else if (empStatus == "other") {
            courseCost = 1350;
        }

        for (int i = 0; i < courses.length; i++) {
            total += courseCost;
        }

        for (int i = 0; i < fees.length; i++) {
            if (fees[i].equals("hotel")) {
                total += 185;
            } else if (fees[i].equals("parking")) {
                total += 10;
            }
        }

        totalCost = total;
    }
}
