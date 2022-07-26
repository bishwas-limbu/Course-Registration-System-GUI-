/**
 * AcademicCourse class is the Sub class inherited from Super class.
 * @author (Bishwas Limbu)
 * @College ID (NP01NT4S210077)
 * Report File Name : 20049443 BISHWAS LIMBU
 */
public class AcademicCourse extends Course {
    /*declares   private instance variables*/
    private String lecturerName;
    private String level;
    private String credit;
    private String startingDate;
    private String completionDate;
    private int numberOfAssessments;
    private boolean isRegistered;
    
    /*creates AcademicCourse constructor with 6 parameters*/
    public AcademicCourse(String courseID, String courseName, int duration, String level, String credit, int numberOfAssessments) {
        super(courseID, courseName, duration);//calls constructor with 3 parameters from super class Course using super key
        this.level = level;
        this.credit = credit;
        this.numberOfAssessments = numberOfAssessments;
        this.lecturerName = ""; // assigns empty string value to instance variable
        this.startingDate = "";
        this.completionDate = "";
        this.isRegistered = false;
    }
     

    /*accessor method for given atrributes.*/
    /*allows user to access private instance variable.*/
    /*returns value of private instance variables*/
    public String getLecturerName() {
            return this.lecturerName;
    }

    public String getLevel() {  
        return this.level;
    }

    public String getCredit() {    
        return this.credit;
    }

    public String getStartingDate() {    
        return this.startingDate;
    }

    public String getCompletionDate() {
           return this.completionDate;
    }

    public int getNumberOfAssessments() {    
        return this.numberOfAssessments;
    }

    public boolean getIsRegistered() {    
        return this.isRegistered;
    }

    /*mutator method for attributes.*/
    /*allows user to set new values to private instance variable.*/
    public void setLecturerName(String lecturerName) {    
        this.lecturerName = lecturerName;
    }

    public void setNumberOfAssessments(int numberOfAssessments) {   
        this.numberOfAssessments = numberOfAssessments;
    }
    
    /*registers particular academic course if not register*/
    /*displays lecture name,start and completion date of course,information on course already register*/
    /*accepts 4 string values for its parameters*/
    public void register(String courseLeader, String lecturerName, String startingDate, String completionDate) {   
        if (this.isRegistered == true) {   
            System.out.println("The Course is already registered.");
            System.out.println("The name of lecturer for this course is " + lecturerName +".");
            System.out.println("This Course will be commencing from " + startingDate +".");
            System.out.println("This Course will be completed by " + completionDate +".");
        }
            else {  
                super.setCourseLeader(courseLeader);
                this.lecturerName = lecturerName;
                this.startingDate = startingDate;
                this.completionDate = completionDate;
                this.isRegistered = true;
        }
    }
    
    /*displays the details of the course*/
    /*displays the lecturer name,credit,start,completion and number of assessments if registered*/
    public void display(){   
        super.display();  //calls display method  from super class Course using super key    
            if(this.isRegistered == true){
                System.out.println("The name of lecturer for this course is " + this.lecturerName +".");
                System.out.println("The Level for this course is " + this.level +".");
                System.out.println("This total credit hours for this course is " + this.credit +".");
                System.out.println("This Course will be commencing from " + this.startingDate +".");
                System.out.println("This Course will be completed by " + this.completionDate +".");
                System.out.println("The total number of assessments for this course is " + this.numberOfAssessments +".");
            }
    }
}
