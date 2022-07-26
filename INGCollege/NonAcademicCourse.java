
/**
 * NonAcademicCourse class is the Sub class inherited from Super class.
 * @author (Bishwas Limbu)
 * @College ID (NP01NT4S210077)
 * Report File Name : 20049443 BISHWAS LIMBU
 */
public class NonAcademicCourse extends Course
{ 
    /*declares  private instance variables*/
    private String instructorName;
    private String startDate;
    private String completionDate;
    private String examDate;
    private String preRequisite;
    private boolean isRegistered;
    private boolean isRemoved;
    
    /*creates NonAcademicCourse constructor with 4 parameters*/
    public NonAcademicCourse(String courseID,String courseName, int duration, String preRequisite){ 
        super(courseID, courseName, duration);//calls constructor with 3 parameters from super class Course using super key
        this.preRequisite = preRequisite;
        this.startDate = "";
        this.completionDate = "";// assigning empty string value to instance variable
        this.examDate = "";
        this.isRegistered = false;
        this.isRemoved = false;
    }

    /*accessor method for given atrributes.*/
    /*allows user to access private instance variable.*/
    /*returns value of private instance variables*/
    public String getInstructorName() {
        return this.instructorName; 
    }

    public String getStartDate() {    
        return this.startDate;
    }

    public String getCompletionDate() {    
        return this.completionDate;
    }

    public String getExamDate() {   
        return this.examDate;
    }

    public String getPreRequisite() {   
        return this.preRequisite;
    }

    public boolean getIsRegistered() {   
        return this.isRegistered;
    }

    public boolean getIsRemoved() {    
        return this.isRemoved;
    }

    /*mutator method for attributes.*/
    /*allows user to set new values to private instance variable.*/
    public void setInstructorName(String instructorName){    
        if(this.isRegistered == false){       //checks condition if course registered.
            this.instructorName = instructorName;
        }
            else {
                System.out.println("Course is already Registered.Instructor cannot be changed.");
        }
    }
    
    /*registers particular nonacademic course if not register*/
    /*displays information on course already registered if registered*/
    /*accepts 5 string values for its parameters*/
    public void register(String courseLeader, String instructorName, String startDate, String completionDate, String examDate){    
        if(this.isRegistered == false){
            super.setCourseLeader(courseLeader);
            setInstructorName(instructorName);
            this.startDate = startDate;
            this.completionDate = completionDate;
            this.examDate = examDate;
            this.isRegistered = true;     
        } 
            else{   
                System.out.println("This course has been  already registered.");
        }
    }
    
    /*removes a particular course details by assiging  empty string value to instance variables*/
    /*displays information that course is removed if condition is ture*/
    public void remove() {
        if (this.isRemoved == true) { //checks condition if isremoved value is true
             System.out.println("The Course you looking for is removed already.");    
        } 
            else {
                super.setCourseLeader("");
                this.instructorName = "";
                this.startDate = "";
                this.completionDate = "";
                this.examDate = "";
                this.isRegistered = false;
                this.isRemoved = true;
        }
    }
    
    /*displays the details of the course*/
    /*displays the instruction name,start,completion and exam date if registered*/
    public void display(){
        super.display(); //calls display method from super class Course using super key
        if(this.isRegistered == true){    
            System.out.println("The name of Instructor for this course is " + this.instructorName +".");
            System.out.println("This Course will be commencing from " + this.startDate +".");
            System.out.println("This Course will be completed by " + this.completionDate +".");
            System.out.println("The Examination date for this course will start form " + this.examDate +".");
        }
    }
}
