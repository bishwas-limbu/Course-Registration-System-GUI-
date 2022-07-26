/**
 * Course class is Super class.
 * @author (Bishwas Limbu)
 * @College ID (NP01NT4S210077)
 * Report File Name : 20049443 BISHWAS LIMBU
 */
public class Course
{
    /*declares  private instance variables*/
    private String courseID;
    private String courseName;
    private String courseLeader;
    private int duration;

    /*creates Course constucter with three parameter*/
    public Course(String courseID,String courseName,int duration){   
        this.courseID = courseID;
        this.courseName =  courseName;
        this.duration = duration;
        this.courseLeader=""; //assigns emptystring to instance variable
    }

    /*accessor methods for given atrributes.*/
    /*allows user to access private instance variable.*/
    /*returns value of private instance variable*/
    
    public String getCourseID(){   
        return this.courseID;
    }

    public String getCourseName(){        
        return this.courseName;
    }

    public String getCourseLeader(){    
        return this.courseLeader;
    }

    public int getDuration(){   
        return this.duration;
    }

    /*mutator method for attributes.*/
    /*allows user to set new values to private instance variable.*/
    public void setCourseLeader(String courseLeader){   
        this.courseLeader= courseLeader;
    }

    /*displays course details and also courseLeader name if course leader is not empty*/
    public void display(){
        System.out.println("Course Details:-");
        System.out.println("Course ID is "+ this.courseID +".");
        System.out.println("Course Title is "+ this.courseName +".");
        System.out.println("The Duration of Course is "+ this.duration+" Year.");
            if (!this.courseLeader.equals("")){
                System.out.println("Course Leader name is "+ this.courseLeader +".");
            }           
    }
}



