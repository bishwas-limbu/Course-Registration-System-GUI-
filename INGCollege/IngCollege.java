/**
 * IngCollege class for GUI .
 * @author (Bishwas Limbu)
 * @Group N3
 * @College ID (NP01NT4S210077)
 * Report File Name : 20049443 BISHWAS LIMBU
 */
//importing packages from java libaries
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class IngCollege implements ActionListener,MouseListener 
{   
    //declearing instances for JFrame JPanel,JLabel,JMenu,JMenuBar,JMenuItem,JTextField,Font,ImageIcon
    private JFrame mainFrame;
    private JPanel panelCourse;
    private JMenuBar mB;
    private JMenu help,course;
    private JMenuItem academic,nonAcademic,exit,about;
    private JLabel lbl_academic,lbl_nonAcademic,lbl_courseID,lbl_courseName,lbl_duration,lbl_lecturerName,lbl_level,lbl_credit,lbl_startDate,lbl_completionDate,lbl_numberOfAssessment,lbl_courseLeader;
    private JTextField txt_courseID,txt_courseName,txt_duration,txt_lecturerName,txt_level,txt_credit,txt_numberOfAssessment,txt_courseLeader;
    private JLabel lbl_instructorName,lbl_examDate,lbl_preRequisite,lbl_logo,lbl_footer;
    private JTextField txt_instructorName,txt_preRequisite,txt_examDate,txt_startDate,txt_completionDate;
    private JButton btnAcademic,btnNonAcademic,btnDisplay,btnRegister,btnAdd,btnClear,btnRemove;
    private Font flbl1,ff,ftxt,fMnu,fBtn;
    private ImageIcon titleIcon;
    //Creating reference variable of AcademicCourse and NonAcademicCourse
    public static AcademicCourse acd;
    public static NonAcademicCourse nacd;
    //creating reference object of ArrayList of Course class type 
    public ArrayList<Course>academicList = new ArrayList<Course>();
    public ArrayList<Course>nonAcademicList = new ArrayList<Course>();

    public IngCollege(){   
        mainFrame = new JFrame();//initializing JFrame 
        
        //initializing and setting panel's layout,position,size,background color,focusable
        panelCourse = new JPanel();
        panelCourse.setLayout(null);
        panelCourse.setBackground(new Color(255,255,255));
        panelCourse.setFocusable(true);//sets foucs of mouse to panel when frame is loaded
        panelCourse.setSize(1650,1080);
        
        //defining Font attibutes as parameter in Font class constructor for  textfield,label,button
        flbl1 = new Font("Bookman Old Style",Font.BOLD,40);
        ff = new Font("Arial",Font.BOLD,18);
        ftxt = new Font("Calibri (Body)",Font.PLAIN,14);
        fMnu = new Font("Calibri (Body)",Font.PLAIN,14);
        fBtn = new Font("Arial Rounded MT Bold",Font.BOLD,20);
        
        //creating JMenuBar
        mB = new JMenuBar();
        
        //creating and adding JMenu to JMenuBar
        course = new JMenu("Course");
        help = new JMenu("Help");
        
       //creating adding JMenuItem to JMenu and implementing Action listener to JMenuItems
        academic = new JMenuItem("Academic Course");
        nonAcademic = new JMenuItem("Non Academic Course");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("about");
        //adding action listener to JMenuItems
        academic.addActionListener(this);
        nonAcademic.addActionListener(this);
        exit.addActionListener(this);
        about.addActionListener(this);
       
        
        //initializing and setting title,fonts,size, color, visiblity,position,functionality of buttons//
        btnAcademic = new JButton("For   Academic   Course");
        btnAcademic.setBounds(780,100,500,36);
        btnAcademic.setBorder(null);
        btnAcademic.setFont(fBtn);
        btnAcademic.setBackground(new Color(76,187,23));
        btnAcademic.setForeground(Color.WHITE);
        btnAcademic.setVisible(false);
        //adding MouseListener and ActionListener interface
        btnAcademic.addMouseListener(this);
        
        btnAcademic.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                academicComponent();//calling method for academic component
            }
        });


        btnNonAcademic = new JButton(" For Non Academic Course ");
        btnNonAcademic.setBounds(780,100,500,36);
        btnNonAcademic.setBorder(null);
        btnNonAcademic.setFont(fBtn);
        btnNonAcademic.setBackground(new Color(76,187,23));
        btnNonAcademic.setForeground(Color.WHITE);
        //adding MouseListener and ActionListener interface
        btnNonAcademic.addMouseListener(this);
        
        btnNonAcademic.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              nonAcademicComponent();//calling method for non academic component
            }
        });
        
        btnDisplay = new JButton("Display");
        btnDisplay.setBounds(780,575,210,35);
        btnDisplay.setBorder(null);
        btnDisplay.setBackground(new Color(76,187,23));
        btnDisplay.setForeground(Color.WHITE);
        btnDisplay.setFont(fBtn);
        //adding MouseListener and ActionListener interface
        btnDisplay.addMouseListener(this);
        
        btnDisplay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(txt_courseID.getText().equals("Enter The Course ID.........")){
                    //displays message dialog box when courseId text field equals "Enter The Course ID........."
                    JOptionPane.showMessageDialog(null,"Please, \nFill  Course ID text field present....","Alert",JOptionPane.WARNING_MESSAGE); 
                }
                else{ 
                    JFrame frame = new JFrame();//initializing JFrame for display purpose
                     //chceking visibility boolean value for lable and button for using same button for academic  course
                    if(lbl_academic.isVisible() == true || btnNonAcademic.isVisible() == true){
                        //defining column of JTable with string value for column title
                        Object col[]= {"Course ID","Course Name","Course Leader","Lecturer Name","Duration",
                            "Level","Total Credit","Total Assessment","Start Date","Completion Date"};
                        //intializing constructor of DefaultTableModel with two parameter i.e. column and rows count values.    
                        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
                        JTable table = new JTable(tableModel);//initializing JTable with tabelModle instance object as parameter for creating table
                        Font font = new Font("Verdana", Font.PLAIN, 12);
                        table.setFont(font);//setting font 
                        table.setRowHeight(20);//setting height of row 
                        JScrollPane scrollPane = new JScrollPane(table);//initializing JScrollPane in table
                           
                        for(int i = 0; i < academicList.size(); i++){
                            //downcasting from Course to AcademicCourse type for accessing child class method 
                            acd = (AcademicCourse)academicList.get(i);
                            //creating data for row section of Table and accessing data from academiclist arraylist and academicCourse                           
                            Object data[]= {academicList.get(i).getCourseID(),
                                            academicList.get(i).getCourseName(),
                                            academicList.get(i).getCourseLeader(),
                                            acd.getLecturerName(),
                                            academicList.get(i).getDuration(),
                                            acd.getLevel(),
                                            acd.getCredit(),
                                            acd.getNumberOfAssessments(),
                                            acd.getStartingDate(),
                                            acd.getCompletionDate()
                                            };
                            tableModel.addRow(data);//adding data list into row section of table model
                            } 
                        frame.getContentPane().add(scrollPane); //adding scrollpane to frame
                        JTableHeader tableHeader = table.getTableHeader();//intializing JTabelHeader to access table header
                        tableHeader.setBackground(new Color(76,187,23));//setting background color
                        tableHeader.setForeground(Color.white);//setting foreground color
                        Font headerFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 14);//untializing and define font attributes
                        tableHeader.setFont(headerFont);//setting font of table header
                    }
                    else { //chceking visibility boolean value for lable and button for using same button for  non academic course
                        if(lbl_nonAcademic.isVisible() == true || btnAcademic.isVisible() == true){
                            //defining column of JTable with string value for column title
                            Object col[] = {"Course ID","Course Name","Course Leader","Instructor Name","Duration",
                                    "preRequisite","Exam Date","Start Date","Completion Date"};
                            //intializing constructor of DefaultTableModel with two parameter i.e. column and rows count values.            
                            DefaultTableModel tableModel = new DefaultTableModel(col, 0);
                            JTable table = new JTable(tableModel);//initializing JTable with tabelModle instance object as parameter for creating table
                            Font font = new Font("Verdana", Font.PLAIN, 12);
                            table.setFont(font);//setting font 
                            table.setRowHeight(20);
                            JScrollPane scrollPane = new JScrollPane(table);//initializing JScrollPane in table
                                   
                            for(int i = 0; i < nonAcademicList.size(); i++){
                                //downcasting from Course to NonAcademicCourse type for accessing child class method    
                                nacd = (NonAcademicCourse)nonAcademicList.get(i);
                                //creating data for row section of Table and accessing data from nonacademiclist arraylist and NonacademicCourse                                  
                                Object data[] = {nonAcademicList.get(i).getCourseID(),
                                                nonAcademicList.get(i).getCourseName(),
                                                nonAcademicList.get(i).getCourseLeader(),
                                                nacd.getInstructorName(),
                                                nonAcademicList.get(i).getDuration(),
                                                nacd.getPreRequisite(),
                                                nacd.getExamDate(),
                                                nacd.getStartDate(),
                                                nacd.getCompletionDate()
                                                };
                                tableModel.addRow(data);//adding data list into row section of table model
                            } 
                            frame.getContentPane().add(scrollPane); //adding scrollpane to frame
                            JTableHeader tableHeader = table.getTableHeader();//intializing JTabelHeader to access table header
                            tableHeader.setBackground(new Color(76,187,23));//setting background color
                            tableHeader.setForeground(Color.white);//setting foreground color
                            Font headerFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 14);//untializing and define font attributes
                            tableHeader.setFont(headerFont);//setting font of table header
                        }
                    }   
                    //setting icon image,title,location,size,visible,colseoperation of frame
                    titleIcon = new ImageIcon("icon.jpg");//initializing reference object of ImageIcon class with image location as parameter
                    frame.setIconImage(titleIcon.getImage());
                    frame.setTitle("ING College");
                    frame.setLocation(40,50);
                    frame.setSize(1300,600);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
                   
                 }  
            }
        });
        
        btnRegister = new JButton("Register");
        btnRegister.setBounds(120,575,210,35);
        btnRegister.setBackground(new Color(76,187,23));
        btnRegister.setBorder(null);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(fBtn);
        //adding MouseListener and ActionListener interface
        btnRegister.addMouseListener(this);
        
        btnRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 //chceking visibility boolean value for lable and button for using same button for academic  course
                if(lbl_academic.isVisible() == true || btnNonAcademic.isVisible() == true){
                    if(txt_courseID.getText().equals("Enter The Course ID.........")||txt_courseLeader.getText().equals("Enter The Name for Course Leader..")||txt_lecturerName.getText().equals("Enter The Name for Lecturer..")
                    ||txt_startDate.getText().equals("dd-mm-yy")||txt_completionDate.getText().equals("dd-mm-yy")){
                        //displays message dialog if condition is met
                        JOptionPane.showMessageDialog(null,"Please, \nFill all the required text field present....","Alert",JOptionPane.WARNING_MESSAGE); 
                    }
                    else{
                        //declaring variable and assigning text field value to same variable
                        String courseID = txt_courseID.getText();
                        String startDate = txt_startDate.getText();
                        String completionDate =  txt_completionDate.getText();
                        String courseLeader = txt_courseLeader.getText();
                        String lecturerName = txt_lecturerName.getText();
    
                        for(int i = 0; i < academicList.size(); i++){
                            //checks if user entered course id is already in arraylist
                            if(academicList.get(i).getCourseID().equals(courseID)){
                                AcademicCourse ac = (AcademicCourse)academicList.get(i);//downcasting from Course to AcademicCourse type for accessing child class method
                                if(acd.getIsRegistered() == false){
                                    acd.register(courseLeader,lecturerName,startDate,completionDate);//passing value to parameter of register method 
                                    JOptionPane.showMessageDialog(null," The Academic course is successfully registered. ","Alert",JOptionPane.INFORMATION_MESSAGE);
                                    return;   
                                    
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"The Academic course is already registered","Alert",JOptionPane.INFORMATION_MESSAGE);
                                    return;
                                }
                                
                            }
                            
                        }
                        JOptionPane.showMessageDialog(null,"The course ID doesnot exist","Alert",JOptionPane.ERROR_MESSAGE);
                        return;    
                    }                    
                }
                else{
                     //chceking visibility boolean value for lable and button for using same button for non academic course
                    if(lbl_nonAcademic.isVisible() == true || btnAcademic.isVisible() == true){
                        if(txt_courseID.getText().equals("Enter The Course ID.........")||txt_courseLeader.getText().equals("Enter The Name for Course Leader..")||txt_instructorName.getText().equals("Enter The Instructor Name...")
                    ||txt_startDate.getText().equals("dd-mm-yy")||txt_completionDate.getText().equals("dd-mm-yy")||txt_examDate.getText().equals("dd-mm-yy")){
                        //displays message dialog if condition is met
                        JOptionPane.showMessageDialog(null,"Please, \nFill all the required text field present....","Alert",JOptionPane.WARNING_MESSAGE); 
                    }
                    else{
                        //declaring variable and assigning text field value to same variable
                        String courseID = txt_courseID.getText();
                        String startDate = txt_startDate.getText();
                        String examDate = txt_examDate.getText();
                        String completionDate =  txt_completionDate.getText();
                        String courseLeader = txt_courseLeader.getText();
                        String instructorName = txt_instructorName.getText();
    
                        for(int i = 0; i < nonAcademicList.size(); i++){
                            //checks if user entered course id is already in arraylist
                            if(nonAcademicList.get(i).getCourseID().equals(courseID)){
                                nacd = (NonAcademicCourse)nonAcademicList.get(i);//downcasting from Course to NonAcademicCourse type for accessing child class method
                                if(nacd.getIsRegistered() == false){
                                    nacd.register(courseLeader,instructorName,startDate,completionDate,examDate);//passing value to parameter of register method 
                                    JOptionPane.showMessageDialog(null," The Non Academic course is successfully registered. ","Alert",JOptionPane.INFORMATION_MESSAGE);
                                  return;
                                    
                                }
                                else{
                                  JOptionPane.showMessageDialog(null,"The Non Academic course is already registered","Alert",JOptionPane.INFORMATION_MESSAGE);
                                  return;
                                }
                                
                            }
                        }
                        JOptionPane.showMessageDialog(null,"The course ID doesnot exist","Alert",JOptionPane.ERROR_MESSAGE);
                        return;
                       }            
                    }
                }
            }
        });
        
        
        btnAdd = new JButton("Add");
        btnAdd.setBounds(120,355,210,35);
        btnAdd.setBorder(null);
        btnAdd.setBackground(new Color(76,187,23));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(fBtn);
        //adding MouseListener and ActionListener interface
        btnAdd.addMouseListener(this);
        btnAdd.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 //checking visibility boolean value for lable and button for using same button for academic course
                if(lbl_academic.isVisible() == true || btnNonAcademic.isVisible() == true){
                      if(txt_courseID.getText().equals("Enter The Course ID.........")||txt_courseName.getText().equals("Enter The Course Name...")||txt_duration.getText().equals("Enter the Duration For Course..")
                       ||txt_numberOfAssessment.getText().equals("Enter Total Number of  Assessment..")||txt_credit.getText().equals("Enter The Total credit For Course....")||txt_level.getText().equals("Enter The Course Level....")){
                           //displays message dialog if condition is met 
                           JOptionPane.showMessageDialog(null,"Please, \nFill all the required text field present....","Alert",JOptionPane.WARNING_MESSAGE);  
                        }  
                      else{
                          //declaring variable and assigning text field value to same variable
                         String courseID = txt_courseID.getText();
                         String courseName =  txt_courseName.getText();
                         String credit = txt_credit.getText();
                         String level = txt_level.getText();
                         int duration = 0;
                         int numberOfAssessments = 0;
                         //using try and catch block NumberFormatException error handling
                         try{duration = Integer.parseInt(txt_duration.getText()); }//chaning string datatype to integer datatype
                         catch(NumberFormatException d){
                             JOptionPane.showMessageDialog(null,"Please, \n give valid user input...","Invalid duration datatype",JOptionPane.ERROR_MESSAGE); 
                             return;
                         }
                         try{numberOfAssessments = Integer.parseInt(txt_numberOfAssessment.getText()); }//chaning string datatype to integer datatype
                         catch(NumberFormatException n){
                             JOptionPane.showMessageDialog(null,"Please, \n give valid user input...","Invalid numberOfAssessments datatype",JOptionPane.ERROR_MESSAGE);
                             return;
                         }
                         //iterating academicList
                         for(Course c : academicList){
                             //checks if user entered course id is already in arraylist
                             if(c.getCourseID().equals(courseID)){
                                  //displays message dialog if conditon is met
                                  JOptionPane.showMessageDialog(null," Entered Course ID already exist. ","Alert",JOptionPane.INFORMATION_MESSAGE);
                                  return;
                             }
                         }
                         acd = new AcademicCourse(courseID,courseName,duration,level,credit,numberOfAssessments);//passing value to parameter of AcademicCourse constructor
                         academicList.add(acd);//adding AcademicCourse object in arraylist
                         JOptionPane.showMessageDialog(null,"Academic Course is successfully added.","Alert",JOptionPane.INFORMATION_MESSAGE);
    
                        }    
                }
                else{ //checking visibility boolean value for lable and button for using same button for  non academic course
                    if(lbl_nonAcademic.isVisible() == true || btnAcademic.isVisible() == true){
                        if(txt_courseID.getText().equals("Enter The Course ID.........")||txt_courseName.getText().equals("Enter The Course Name...")||txt_duration.getText().equals("Enter the Duration For Course..")
                   ||txt_preRequisite.getText().equals("Enter The  PreRequisite For Course..")){
                       //displays message dialog if condition is met  
                       JOptionPane.showMessageDialog(null,"Please, \nFill all the required text field present....","Alert",JOptionPane.WARNING_MESSAGE);  
                    }  
                    else{//declaring variable and assigning text field value to same variable
                         String courseID = txt_courseID.getText();
                         String courseName =  txt_courseName.getText();
                         String preRequisite = txt_preRequisite.getText();
                         int duration = 0;
                          //using try and catch block NumberFormatException error handling
                         try{duration = Integer.parseInt(txt_duration.getText()); }//chaning string datatype to integer datatype
                         catch(NumberFormatException d){
                             JOptionPane.showMessageDialog(null,"Please, \n give valid user input...","Invalid duration datatype",JOptionPane.ERROR_MESSAGE); 
                             return;
                         }
                          //iterating nonacademicList
                         for(Course c : nonAcademicList){
                             //checks if user entered course id is already in arraylist
                             if(c.getCourseID().equals(courseID)){
                                 //displays message dialog if conditon is met
                                  JOptionPane.showMessageDialog(null," Entered Course ID already exist. ","Alert",JOptionPane.INFORMATION_MESSAGE);
                                  return;
                             }
                         }
                         nacd = new NonAcademicCourse(courseID,courseName,duration,preRequisite);//passing value to parameter of NonAcademicCourse constructor  
                         nonAcademicList.add(nacd);//adding NonAcademicCourse object in arraylist
                         JOptionPane.showMessageDialog(null,"Non Academic Course is successfully added.","Alert",JOptionPane.INFORMATION_MESSAGE);
                             
                       }
                        
                    }
                }
             }
        });
        
        
        btnClear = new JButton("Clear");
        btnClear.setBounds(1000,575,210,35);
        btnClear.setBorder(null);
        btnClear.setBackground(new Color(255,51,51));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(fBtn);
         //adding MouseListener and ActionListener interface
        btnClear.addMouseListener(this);
        btnClear.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                   int response = JOptionPane.showConfirmDialog(null," Are you sure, do you want to clear all text field? ", "Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    //clears all textields if condition is met4
                   if(response == JOptionPane.YES_OPTION){
                        //setting some text in the text Field which act as place holder
                        txt_courseID.setText("Enter The Course ID........."); 
                        txt_courseName.setText("Enter The Course Name...");
                        txt_duration.setText("Enter the Duration For Course..");
                        txt_lecturerName.setText("Enter The Name for Lecturer..");
                        txt_level.setText("Enter The Course Level....");
                        txt_credit.setText("Enter The Total credit For Course....");
                        txt_numberOfAssessment.setText("Enter Total Number of  Assessment..");
                        txt_courseLeader.setText("Enter The Name for Course Leader..");
                        txt_startDate.setText("dd-mm-yy");
                        txt_completionDate.setText("dd-mm-yy");
                        txt_preRequisite.setText("Enter The  PreRequisite For Course..");
                        txt_instructorName.setText("Enter The Instructor Name...");
                        txt_examDate.setText("dd-mm-yy");
                        
                        //setting color to text in the text Field
                        txt_courseID.setForeground(Color.GRAY);
                        txt_courseName.setForeground(Color.GRAY);
                        txt_duration.setForeground(Color.GRAY);
                        txt_courseLeader.setForeground(Color.GRAY);
                        txt_startDate.setForeground(Color.GRAY);
                        txt_completionDate.setForeground(Color.GRAY);
                        txt_numberOfAssessment.setForeground(Color.GRAY);
                        txt_level.setForeground(Color.GRAY);
                        txt_credit.setForeground(Color.GRAY);
                        txt_lecturerName.setForeground(Color.GRAY);
                        txt_instructorName.setForeground(Color.GRAY);
                        txt_examDate.setForeground(Color.GRAY);
                        txt_preRequisite.setForeground(Color.GRAY);
               
                        JOptionPane.showMessageDialog(null,"All Text Field are succcessfully Cleared.","Alert",JOptionPane.INFORMATION_MESSAGE);
                    }
             }
        });
        
        btnRemove = new JButton("Remove");
        btnRemove.setBounds(340,355,210,35);
        btnRemove.setBorder(null);
        btnRemove.setBackground(new Color(255,51,51));
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(fBtn);
        btnRemove.setVisible(false);
         //adding MouseListener and ActionListener interface
        btnRemove.addMouseListener(this);
        
        btnRemove.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                    
                        if(txt_courseID.getText().equals("Enter The Course ID.........")){
                        //displays message dialog if condition is met 4
                       JOptionPane.showMessageDialog(null,"Please, \nFill Course ID text field present....","Alert",JOptionPane.WARNING_MESSAGE);  
                    }  
                    else{
                        int response = JOptionPane.showConfirmDialog(null," Are you sure, do you want to remove this non academic course? ", "Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if(response == JOptionPane.YES_OPTION){ 
                            //declaring variable and assigning text field value to same variable
                             String courseID = txt_courseID.getText();
                             //iterating nonacademicList
                             for(int i = 0; i < nonAcademicList.size(); i++){
                                 //checks if user entered course id is already in arraylist
                                 if(nonAcademicList.get(i).getCourseID().equals(courseID)){
                                    nacd = (NonAcademicCourse)nonAcademicList.get(i);//downcasting from Course to NonAcademicCourse type for accessing child class method
                                     if(nacd.getIsRemoved() == false){
                                         nacd.remove();//calling remove method of NonAcademicCourse
                                         nonAcademicList.remove(i);//removing object in i index
                                         JOptionPane.showMessageDialog(null,"Non Academic Course is succcessfully removed.","Alert",JOptionPane.INFORMATION_MESSAGE);
                                         return;
                                     }
                                     
                                 } 
                                 
                             }
                             JOptionPane.showMessageDialog(null,"The course ID doesnt exist.","Alert",JOptionPane.INFORMATION_MESSAGE);
                             return;
    
                        }
                    
                                            
                    }
             }
        });

        //initializing and setting title,fonts,size, color, visiblity,position of JLabel//
        lbl_academic = new JLabel("ACADEMIC COURSE");
        lbl_academic.setBounds(450,30,500,30);
        lbl_academic.setFont(flbl1);
        
        
        lbl_nonAcademic = new JLabel("NON ACADEMIC COURSE");
        lbl_nonAcademic.setBounds(430,30,600,30);
        lbl_nonAcademic.setFont(flbl1);
        lbl_nonAcademic.setVisible(false);
        
        lbl_logo = new JLabel((new ImageIcon("icon1.png")));//inserting image in label and adding it to panel
        lbl_logo .setBounds(20,0,150,100);

        lbl_footer = new JLabel("Copyright © 2021 || Design by Bishwas Limbu");
        lbl_footer .setBounds(520,600,700,100);
        Font fFooter = new Font("Arial",Font.PLAIN,14);
        lbl_footer.setForeground(new Color(65, 105, 225));
        lbl_footer .setFont(fFooter);
        
        lbl_courseID = new JLabel("Course ID : ");
        lbl_courseID.setBounds(120,185,200,20);
        lbl_courseID.setFont(ff);
        
        lbl_courseName = new JLabel("Course Name : ");
        lbl_courseName.setBounds(120,235,300,20);
        lbl_courseName.setFont(ff);
        
        lbl_duration = new JLabel("Duration : ");
        lbl_duration.setBounds(780,185,100,20);
        lbl_duration.setFont(ff);
        
        
        lbl_lecturerName = new JLabel("Lecturer Name : ");
        lbl_lecturerName.setBounds(120,500,200,20);
        lbl_lecturerName.setFont(ff);
        
        
        lbl_level = new JLabel("Level : ");
        lbl_level.setBounds(780,235,90,20);
        lbl_level.setFont(ff);
        
        lbl_credit = new JLabel("Credit : ");
        lbl_credit.setBounds(780,285,90,20);
        lbl_credit.setFont(ff);
        
        lbl_startDate = new JLabel("Start Date : ");
        lbl_startDate.setBounds(780,450,150,20);
        lbl_startDate.setFont(ff);
        
        lbl_completionDate = new JLabel("Completion Date : ");
        lbl_completionDate.setBounds(780,500,200,20);
        lbl_completionDate.setFont(ff);
        
        lbl_numberOfAssessment = new JLabel("Total Assessment : ");
        lbl_numberOfAssessment.setBounds(120,285,200,20);
        lbl_numberOfAssessment.setFont(ff);
        
        lbl_instructorName = new JLabel("Instructor Name : ");
        lbl_instructorName.setBounds(120,500,200,20);
        lbl_instructorName.setFont(ff);
        lbl_instructorName.setVisible(false);
        
        lbl_examDate = new JLabel("Exam Date : ");
        lbl_examDate.setBounds(780,400,120,20);
        lbl_examDate.setFont(ff);
        lbl_examDate.setVisible(false);
               
        
        lbl_preRequisite = new JLabel("PreRequisite : ");
        lbl_preRequisite.setBounds(780,235,130,20);
        lbl_preRequisite.setFont(ff);
        lbl_preRequisite.setVisible(false);
        
        lbl_courseLeader = new JLabel("Course Leader : ");
        lbl_courseLeader.setBounds(120,450,200,20);
        lbl_courseLeader.setFont(ff);
        
        
        //setting title,fonts,size, color, visiblity,position of JTextField//
        txt_courseID = new JTextField(20);
        txt_courseID.setText("Enter The Course ID.........");
        txt_courseID.setBounds(300,180,250,30);
        txt_courseID.setFont(ftxt);
        txt_courseID.setForeground(Color.GRAY);
        //adding FoucusListener to textField
        txt_courseID.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_courseID.getText().equals("Enter The Course ID.........")){
                    txt_courseID.setText(""); //clears or make empty text field if condition is ture  
                }
                txt_courseID.setForeground(Color.BLACK); //changes color of text in text field   
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_courseID.getText().equals("") || txt_courseID.getText().equals("Enter The Course ID........."))
                {    
                    txt_courseID.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_courseID.setText("Enter The Course ID.........");//set text field with text            
                }
            }       
        });
        
        txt_courseName = new JTextField(20);
        txt_courseName.setBounds(300,230,250,30);
        txt_courseName.setFont(ftxt);
        txt_courseName.setText("Enter The Course Name...");
        txt_courseName.setForeground(Color.GRAY);
        //adding FoucusListener to textField 
        txt_courseName.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_courseName.getText().equals("Enter The Course Name...")){
                    txt_courseName.setText(""); //clears or make empty text field if condition is ture  
                }
                txt_courseName.setForeground(Color.BLACK); //changes color of text in text field   
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_courseName.getText().equals("") || txt_courseName.getText().equals("Enter The Course Name..."))
                {    
                    txt_courseName.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_courseName.setText("Enter The Course Name...");//set text field with text     
                }
            }       
        });
        
        txt_duration = new JTextField(20);
        txt_duration.setBounds(950,180,250,30);
        txt_duration.setFont(ftxt);
        txt_duration.setText("Enter the Duration For Course..");
        txt_duration.setForeground(Color.GRAY);
        //adding FoucusListener to textField 
        txt_duration.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_duration.getText().equals("Enter the Duration For Course..")){
                    txt_duration.setText("");//clears or make empty text field if condition is ture   
                }
                txt_duration.setForeground(Color.BLACK); //changes color of text in text field  
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_duration.getText().equals("") || txt_duration.getText().equals("Enter the Duration For Course.."))
                {    
                    txt_duration.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_duration.setText("Enter the Duration For Course..");//set text field with text                 
                }
            }       
        });
        
        txt_lecturerName = new JTextField(20);
        txt_lecturerName.setBounds(300,495,250,30);
        txt_lecturerName.setFont(ftxt);
        txt_lecturerName.setText("Enter The Name for Lecturer..");
        txt_lecturerName.setForeground(Color.GRAY);
        //adding FoucusListener to textField
        txt_lecturerName.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_lecturerName.getText().equals("Enter The Name for Lecturer..")){
                    txt_lecturerName.setText("");//clears or make empty text field if condition is ture   
                }
                txt_lecturerName.setForeground(Color.BLACK);//changes color of text in text field    
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_lecturerName.getText().equals("") || txt_lecturerName.getText().equals("Enter the Total Duration For Course.."))
                {    
                    txt_lecturerName.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_lecturerName.setText("Enter The Name for Lecturer..");//set text field with text
                }
            }       
        });
        
        txt_instructorName = new JTextField(20);
        txt_instructorName.setBounds(300,495,250,30);
        txt_instructorName.setFont(ftxt);
        txt_instructorName.setText("Enter The Instructor Name...");
        txt_instructorName.setVisible(false);
        txt_instructorName.setForeground(Color.GRAY);
        //adding FoucusListener to textField
        txt_instructorName.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_instructorName.getText().equals("Enter The Instructor Name...")){
                    txt_instructorName.setText("");//clears or make empty text field if condition is ture   
                }
                txt_instructorName.setForeground(Color.BLACK); //changes color of text in text field   
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_instructorName.getText().equals("") || txt_instructorName.getText().equals("Enter The Instructor Name..."))
                {    
                    txt_instructorName.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_instructorName.setText("Enter The Instructor Name...");//set text field with text   
                }
            }       
        });
        
        txt_level = new JTextField(20);
        txt_level.setBounds(950,235,250,30);
        txt_level.setFont(ftxt);
        txt_level.setText("Enter The Course Level....");
        txt_level.setForeground(Color.GRAY);
        //adding FoucusListener to textField 
        txt_level.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_level.getText().equals("Enter The Course Level....")){
                    txt_level.setText("");//clears or make empty text field if condition is ture   
                }
                txt_level.setForeground(Color.BLACK);//changes color of text in text field   
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_level.getText().equals("") || txt_level.getText().equals("Enter The Course Level...."))
                {    
                    txt_level.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_level.setText("Enter The Course Level....");//set text field with text
                }
            }       
        });
        
        txt_credit = new JTextField(20);
        txt_credit.setBounds(950,285,250,30);
        txt_credit.setFont(ftxt);
        txt_credit.setText("Enter The Total credit For Course....");
        txt_credit.setForeground(Color.GRAY);
        //adding FoucusListener to textField 
        txt_credit.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                       
                if(txt_credit.getText().equals("Enter The Total credit For Course....")){
                    txt_credit.setText("");//clears or make empty text field if condition is ture   
                }
                txt_credit.setForeground(Color.BLACK); //changes color of text in text field   
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_credit.getText().equals("") || txt_credit.getText().equals("Enter The Total credit For Course...."))
                {    
                    txt_credit.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_credit.setText("Enter The Total credit For Course....");//set text field with text   
                }
            }       
        });
        
        txt_numberOfAssessment = new JTextField(20);
        txt_numberOfAssessment.setBounds(300,280,250,30);
        txt_numberOfAssessment.setFont(ftxt);
        txt_numberOfAssessment.setText("Enter Total Number of  Assessment..");
        txt_numberOfAssessment.setForeground(Color.GRAY);
        //adding FoucusListener to textField 
        txt_numberOfAssessment.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_numberOfAssessment.getText().equals("Enter Total Number of  Assessment..")){
                    txt_numberOfAssessment.setText("");//clears or make empty text field if condition is ture   
                }
                txt_numberOfAssessment.setForeground(Color.BLACK);//changes color of text in text field    
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_numberOfAssessment.getText().equals("") || txt_numberOfAssessment.getText().equals("Enter Total Number of  Assessment.."))
                {    
                    txt_numberOfAssessment.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_numberOfAssessment.setText("Enter Total Number of  Assessment..");//set text field with text 
                }
            }       
        });
        
        txt_preRequisite = new JTextField(20);
        txt_preRequisite.setBounds(950,235,250,30);
        txt_preRequisite.setFont(ftxt);
        txt_preRequisite.setText("Enter The  PreRequisite For Course..");
        txt_preRequisite.setVisible(false);
        txt_preRequisite.setForeground(Color.GRAY);
        //adding FoucusListener to textField 
        txt_preRequisite.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_preRequisite.getText().equals("Enter The  PreRequisite For Course..")){
                    txt_preRequisite.setText(""); //clears or make empty text field if condition is ture  
                }
                txt_preRequisite.setForeground(Color.BLACK); //changes color of text in text field   
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_preRequisite.getText().equals("") || txt_preRequisite.getText().equals("Enter The  PreRequisite For Course.."))
                {    
                    txt_preRequisite.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_preRequisite.setText("Enter The  PreRequisite For Course..");//set text field with text
                     
                }
            }       
        });
        
        txt_courseLeader = new JTextField(20);
        txt_courseLeader.setBounds(300,445,250,30);
        txt_courseLeader.setFont(ftxt);
        txt_courseLeader.setText("Enter The Name for Course Leader..");
        txt_courseLeader.setForeground(Color.GRAY);
        //adding FoucusListener to textField 
        txt_courseLeader.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent evt) {                                        
                //performs action when mouse foucus in gained by textfield
                if(txt_courseLeader.getText().equals("Enter The Name for Course Leader..")){
                    txt_courseLeader.setText("");//clears or make empty text field if condition is ture   
                }
                txt_courseLeader.setForeground(Color.BLACK);//changes color of text in text field   
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_courseLeader.getText().equals("") || txt_courseLeader.getText().equals("Enter The Name for Course Leader.."))
                {    
                    txt_courseLeader.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_courseLeader.setText("Enter The Name for Course Leader..");//set text field with text
                     
                }
            }       
        });
        
        txt_examDate = new JTextField(20);
        txt_examDate.setBounds(950,395,250,30);
        txt_examDate.setFont(ftxt);
        txt_examDate.setText("dd-mm-yy");
        txt_examDate.setVisible(false);
        txt_examDate.setForeground(Color.GRAY);
        //adding FoucusListener to textField 
        txt_examDate.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_examDate.getText().equals("dd-mm-yy")){
                    txt_examDate.setText("");//clears or make empty text field if condition is ture   
                }
                txt_examDate.setForeground(Color.BLACK);//changes color of text in text field    
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_examDate.getText().equals("") || txt_examDate.getText().equals("dd-mm-yy"))
                {    
                    txt_examDate.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_examDate.setText("dd-mm-yy");//set text field with text     
                }
            }       
        });
        
        txt_startDate = new JTextField(20);
        txt_startDate.setBounds(950,445,250,30);
        txt_startDate.setFont(ftxt);
        txt_startDate.setText("dd-mm-yy");
        txt_startDate.setForeground(Color.GRAY);
        //adding FoucusListener to textField 
        txt_startDate.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_startDate.getText().equals("dd-mm-yy")){
                   txt_startDate.setText("");//clears or make empty text field if condition is ture   
                }
                txt_startDate.setForeground(Color.BLACK);//changes color of text in text field    
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
    
                if(txt_startDate.getText().equals("") || txt_startDate.getText().equals("dd-mm-yy"))
                {    
                    txt_startDate.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_startDate.setText("dd-mm-yy");//set text field with text
                     
                }
            }       
        });
        
        txt_completionDate = new JTextField(20);
        txt_completionDate.setBounds(950,495,250,30);
        txt_completionDate.setFont(ftxt);
        txt_completionDate.setText("dd-mm-yy");
        txt_completionDate.setForeground(Color.GRAY);
        //adding FoucusListener to textField 
        txt_completionDate.addFocusListener(new FocusListener(){
            //performs action when mouse foucus in gained by textfield
            public void focusGained(FocusEvent evt) {                                        
                if(txt_completionDate.getText().equals("dd-mm-yy")){
                   txt_completionDate.setText("");//clears or make empty text field if condition is ture
                }
                txt_completionDate.setForeground(Color.BLACK); //changes color of text in text field 
            }                                       
            //performs action when mouse foucus in lost by textfield
            public void focusLost(FocusEvent evt) {                                      
                if(txt_completionDate.getText().equals("") || txt_completionDate.getText().equals("dd-mm-yy"))
                {    
                    txt_completionDate.setForeground(Color.GRAY);//changes color of text in text field 
                    txt_completionDate.setText("dd-mm-yy"); //set text field with text
                     
                }
            }       
        });
        
        //Adding JMenuItem academic,nonAcademic and exit to JMenu course
        course.add(academic);
        course.addSeparator();//seprates academic and nonacademic JMenuItem
        course.add(nonAcademic);
        course.addSeparator();//seprates nonacademic and exit  JMenuItem
        course.add(exit);
        
        //Adding JMenuItem about to JMenu help
        help.add(about);
        
        //Adding JMenu  to JMenuBar
        mB.add(course);
        mB.add(help);
       
        //adding Labels and TextFields to panel
        panelCourse.add(lbl_academic);
        panelCourse.add(lbl_nonAcademic);
        panelCourse.add(lbl_courseID);
        panelCourse.add(txt_courseID);
        panelCourse.add(lbl_duration);
        panelCourse.add(txt_duration);
        panelCourse.add(lbl_courseName);
        panelCourse.add(txt_courseName);
        panelCourse.add(lbl_lecturerName);
        panelCourse.add(txt_lecturerName);
        panelCourse.add(lbl_instructorName);
        panelCourse.add(txt_instructorName);
        panelCourse.add(lbl_courseLeader);
        panelCourse.add(txt_courseLeader);
        panelCourse.add(lbl_level);
        panelCourse.add(txt_level);
        panelCourse.add(lbl_credit);
        panelCourse.add(txt_credit);
        panelCourse.add(lbl_numberOfAssessment);
        panelCourse.add(txt_numberOfAssessment);
        panelCourse.add(lbl_startDate);
        panelCourse.add(lbl_preRequisite);
        panelCourse.add(txt_preRequisite);
        panelCourse.add(lbl_startDate);
        panelCourse.add(lbl_completionDate);
        panelCourse.add(lbl_examDate);
        panelCourse.add(txt_examDate);
        panelCourse.add(txt_startDate);
        panelCourse.add(txt_completionDate);
        panelCourse.add(lbl_logo );
        panelCourse.add(lbl_footer );
        //adding buttons to panel
        panelCourse.add(btnAcademic);
        panelCourse.add(btnNonAcademic);
        panelCourse.add(btnRegister);
        panelCourse.add(btnDisplay);
        panelCourse.add(btnClear);
        panelCourse.add(btnRemove);
        panelCourse.add(btnAdd);
         
       
        mainFrame.add(panelCourse);//adding Jpanel  to JFrame
        
        mainFrame.setJMenuBar(mB);//adding JMenuBar to JFrame
        
        //setting height,width,location,visiblity,title,close operation of JFrame
        titleIcon = new ImageIcon("icon.jpg");//initializing reference object of ImageIcon class with image location as parameter
        mainFrame.setIconImage(titleIcon.getImage());// adding image to title of JFrame       
        mainFrame.setSize(1650,1080);
        mainFrame.setLocationRelativeTo(null);//centers the Freame location 
        mainFrame.setTitle("ING College");
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
        
    }
    
    //overriging method of ActionListener interface for JMenuItems 
    public void actionPerformed(ActionEvent e){        
        if(e.getSource() == academic){
            academicComponent();//calling method for academic component
       }
       else if(e.getSource() == nonAcademic){
            nonAcademicComponent();//calling method for nonacademic component
       }
       else if(e.getSource() == exit){
              //here "null"  value help to position dialouge box in center of JFrame
              int response = JOptionPane.showConfirmDialog(null," Are you sure, do you want to exit? ", "Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
               if(response == JOptionPane.YES_OPTION){ 
               System.exit(0);//exits the program normally when exit JMenuItem is clicked
              }
       } 
       else{
           if(e.getSource() == about){
                about();//calling about method when about JMenuItem is clicked
           }
       }
    } 
    
    //changes color of button when mouse curser is placed on button
    public void mouseEntered(MouseEvent e) {  
        if(e.getSource() == btnAcademic){
           btnAcademic.setBackground(new Color(34,139,34));
         }
          
         else if(e.getSource() == btnNonAcademic){
           btnNonAcademic.setBackground(new Color(34,139,34));
         }
         else if(e.getSource() == btnDisplay){
           btnDisplay.setBackground(new Color(34,139,34));
         }
         else if(e.getSource() == btnRegister){
           
           btnRegister.setBackground(new Color(34,139,34));
         }
          else if(e.getSource() == btnAdd){
           
           btnAdd.setBackground(new Color(34,139,34));
         }
          else if(e.getSource() == btnRemove){
           
           btnRemove.setBackground(new Color(192, 0, 0));
          
         }
         else{
             if(e.getSource() == btnClear){
           
           btnClear.setBackground(new Color(192, 0, 0));
          
         }
        }      
    } 
    
    //changes color of button when mouse curser is moved away from button
    public void mouseExited(MouseEvent e) {  
        if(e.getSource() == btnAcademic){
           btnAcademic.setBackground(new Color(76,187,23));
         }
          
         else if(e.getSource() == btnNonAcademic){
           btnNonAcademic.setBackground(new Color(76,187,23));
         }
        else if(e.getSource() == btnDisplay){
           btnDisplay.setBackground(new Color(76,187,23));
         }
         else if(e.getSource() == btnRegister){
           
           btnRegister.setBackground(new Color(76,187,23));
         }
          else if(e.getSource() == btnAdd){
           
           btnAdd.setBackground(new Color(76,187,23));
         }
          else if(e.getSource() == btnRemove){
           
           btnRemove.setBackground(new Color(255,51,51));
          
         }
         else{
             if(e.getSource() == btnClear){
           
           btnClear.setBackground(new Color(255,51,51));
          
         }
        }
    } 
    
    public void mouseClicked(MouseEvent e) { } 
    
    public void mousePressed(MouseEvent e) { }  
    
    public void mouseReleased(MouseEvent e) { }
    
    //for JMenuItem about frame 
    public void about(){
        //declaring instances of JFrame,ImageIcon,JLabel,JPanel,JButton,Font
        JFrame jfme;
        ImageIcon titleIcon;
        JPanel panelInfo;
        JLabel lblTitle,lblVersion,lblCopyRight,lblContact,lblEmail,lblMobNum ,lblImage;
        JButton btnOk;
        Font fTitle,font,fCright,fBtn;
        //initializing JFrame constructor to its object
        jfme = new JFrame();
        // initializing and setting color,layout,location and size of panel
        panelInfo = new JPanel();
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBounds(200,0,400,350);
        panelInfo.setLayout(null);
        
        //defining Font attibutes as parameter in Font class constructor for label,button
        fTitle = new Font("Arial",Font.BOLD,21);
        font = new Font("Arial",Font.BOLD,17);
        fCright = new Font("Arial",Font.PLAIN,14);
        fBtn = new Font("Arial Rounded MT Bold",Font.BOLD,14);
        
        //setting title,location,size,font,color of label
        lblImage = new JLabel(new ImageIcon("icon1.png"));//inserting image in label and adding it directly to frame
        lblImage.setBounds(0,0,200,300);
        
        lblTitle = new JLabel("ING College Registration Software");
        lblTitle.setBounds(20,20,500,25);
        lblTitle.setFont(fTitle);
        
        lblVersion = new JLabel("Version 0.0.1");
        lblVersion.setBounds(20,70,500,25);
        lblVersion.setFont(font);
        
        lblCopyRight = new JLabel("Copyright © 2021 Design by Bishwas Limbu");//65, 105, 225
        lblCopyRight.setForeground(new Color(65, 105, 225));
        lblCopyRight.setBounds(20,200,500,25);
        lblCopyRight.setFont(fCright);
        
        lblContact = new JLabel("Contact Details :");
        lblContact.setBounds(20,110,500,25);
        lblContact.setFont(font);
        
        lblEmail = new JLabel("bisparov@gmail.com");
        lblEmail.setBounds(20,140,500,25);
        lblEmail.setFont(fCright);
        
        lblMobNum  = new JLabel("+977-980-8348-036");
        lblMobNum .setBounds(20,160,500,25);
        lblMobNum .setFont(fCright);
        
        //setting title,location,size,font,color of button
        btnOk = new JButton("OK");
        btnOk.setBounds(20,250,90,27);
        btnOk.setBorder(null);
        btnOk.setBackground(new Color(255,51,51));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(fBtn);
    
        //adding action listener event function to button 
        btnOk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jfme.dispose();//calling dispose method for closing a particular frame 
            }
        });
        
        //adding mouse listener event function to button 
        btnOk.addMouseListener(new MouseListener(){
            //list of method for MouseListener interface which is must needed to override
            public void mouseEntered(MouseEvent e) {  
                if(e.getSource() == btnOk){
                   btnOk.setBackground(new Color(192, 0, 0));//changes color of button when mouse curser is placed on button
                 }      
            }  
            
            public void mouseExited(MouseEvent e) {  
                if(e.getSource() == btnOk){
                   btnOk.setBackground(new Color(255,51,51));//changes color of button when mouse curser is moved away from button
                 }
            }  
            
            public void mouseClicked(MouseEvent e) 
            { }
            
            public void mousePressed(MouseEvent e) 
            { }  
            
            public void mouseReleased(MouseEvent e) 
            { }       
        });
        
        //adding label and button to panel
        panelInfo.add(lblTitle);
        panelInfo.add(lblVersion);
        panelInfo.add(lblCopyRight);
        panelInfo.add(lblContact);
        panelInfo.add(lblEmail);
        panelInfo.add(lblMobNum );
        panelInfo.add(btnOk);
        
        //adding panel and label in JFrame
        jfme.add(lblImage);
        jfme.add(panelInfo);
        
        //setting JFrame size,location,layout,visibility,title,image icon
        titleIcon = new ImageIcon("icon.jpg");//initializing reference object of ImageIcon class with image location as parameter
        jfme.setIconImage(titleIcon.getImage());// adding image to title of JFrame       
        jfme.setSize(600,350);
        jfme.getContentPane().setBackground(Color.WHITE);
        jfme.setLocationRelativeTo(null);//centers the Frame location 
        jfme.setTitle("About ING College");
        jfme.setLayout(null);
        jfme.setResizable(false);
        jfme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        jfme.setVisible(true);
    }
    
    public void academicComponent(){
            //changes the compontnet visibility,color,and set text when academic JMenuItem is clicked
            panelCourse.setFocusable(true);//sets foucs of mouse to panel when frame is loaded
            // setting visibility False of textFields,labels adn button for Academic
            lbl_nonAcademic.setVisible(false);
            btnAcademic.setVisible(false);
            btnRemove.setVisible(false);
            lbl_examDate.setVisible(false);
            txt_examDate.setVisible(false);
            lbl_instructorName.setVisible(false);
            txt_instructorName.setVisible(false);
            lbl_preRequisite.setVisible(false);
            txt_preRequisite.setVisible(false);
               
            // setting visibility True of textFields,labels adn button for Academic
            lbl_academic.setVisible(true);
            btnNonAcademic.setVisible(true);
            lbl_credit.setVisible(true);
            txt_credit.setVisible(true); 
            lbl_level.setVisible(true);
            txt_level.setVisible(true);
            lbl_numberOfAssessment.setVisible(true);
            txt_numberOfAssessment.setVisible(true);
            lbl_lecturerName.setVisible(true);
            txt_lecturerName.setVisible(true); 
            //setting text in placeholer in Textfields
            txt_courseID.setText("Enter The Course ID........."); 
            txt_courseName.setText("Enter The Course Name...");
            txt_duration.setText("Enter the Duration For Course..");
            txt_lecturerName.setText("Enter The Name for Lecturer..");
            txt_level.setText("Enter The Course Level....");
            txt_credit.setText("Enter The Total credit For Course....");
            txt_numberOfAssessment.setText("Enter Total Number of  Assessment..");
            txt_courseLeader.setText("Enter The Name for Course Leader..");
            txt_startDate.setText("dd-mm-yy");
            txt_completionDate.setText("dd-mm-yy");  
            //setting color to text      
            txt_courseID.setForeground(Color.GRAY);
            txt_courseName.setForeground(Color.GRAY);
            txt_duration.setForeground(Color.GRAY);
            txt_courseLeader.setForeground(Color.GRAY);
            txt_startDate.setForeground(Color.GRAY);
            txt_completionDate.setForeground(Color.GRAY);
            txt_numberOfAssessment.setForeground(Color.GRAY);
            txt_level.setForeground(Color.GRAY);
            txt_credit.setForeground(Color.GRAY);
            txt_lecturerName.setForeground(Color.GRAY);
    }
    
    public void nonAcademicComponent(){
        //changes the compontnet visibility,color,and set text when nonAcademic JMenuItem is clicked
         panelCourse.setFocusable(true);//sets foucs of mouse to panel when frame is loaded
        // setting visibility False of textFields,labels adn button for Non Academic
         btnNonAcademic.setVisible(false);
         lbl_academic.setVisible(false);
         lbl_level.setVisible(false);
         txt_level.setVisible(false);
         lbl_numberOfAssessment.setVisible(false);
         txt_numberOfAssessment.setVisible(false);
         lbl_credit.setVisible(false);
         txt_credit.setVisible(false);
         lbl_lecturerName.setVisible(false);
         txt_lecturerName.setVisible(false);   
         //setting visibility True of textFields,labels adn button for Non Academic
         lbl_examDate.setVisible(true);
         txt_examDate.setVisible(true);
         lbl_nonAcademic.setVisible(true);
         btnRemove.setVisible(true);
         btnAcademic.setVisible(true);
         lbl_preRequisite.setVisible(true);
         txt_preRequisite.setVisible(true);
         lbl_instructorName.setVisible(true);
         txt_instructorName.setVisible(true);   
         //setting text in placeholer in Textfields
         txt_courseID.setText("Enter The Course ID........."); 
         txt_courseName.setText("Enter The Course Name...");
         txt_duration.setText("Enter the Duration For Course..");
         txt_preRequisite.setText("Enter The  PreRequisite For Course..");
         txt_instructorName.setText("Enter The Instructor Name...");
         txt_courseLeader.setText("Enter The Name for Course Leader..");
         txt_examDate.setText("dd-mm-yy");
         txt_startDate.setText("dd-mm-yy");
         txt_completionDate.setText("dd-mm-yy");  
        //setting color to text  
         txt_courseID.setForeground(Color.GRAY);
         txt_instructorName.setForeground(Color.GRAY);
         txt_examDate.setForeground(Color.GRAY);
         txt_preRequisite.setForeground(Color.GRAY);
         txt_courseName.setForeground(Color.GRAY);
         txt_duration.setForeground(Color.GRAY);
         txt_courseLeader.setForeground(Color.GRAY);
         txt_startDate.setForeground(Color.GRAY);
         txt_completionDate.setForeground(Color.GRAY);
    }
    public static void main(String[]args){
        new IngCollege();//calling contructor of IngCollege class
    }   
} 

    
   
