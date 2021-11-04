package hw_3;
// import ArrayList class to use ArrayList
import java.util.ArrayList;
// import Scanner class to use Scanner
import java.util.Scanner;
// Student class inherit CSECourseManagement class 
public class Student extends CSECourseManagement {
	// constructor for class Student
	Student(String name, String ID, String PW){
		// initiate field data with parameter name, ID, PW
		this.name = name;
		this.student_login_ID = ID;
		this.login_PW = PW;
		// student's school number is automatically issued by constructor
		this.school_number = current_school_number++;
	}
	// fields for class Student
	String student_login_ID;
	String login_PW;
	String name;
	// to configure this student has enrolled or not,
	// name might be same but it could be different person
	// so i issued school_number to distinguish students
	// it's automatically issued by constructor
	long school_number; 
	boolean log = false; // authentication of user
	// current sequentially issued school number pointer
	// declare long type instance current_school_number by static
	// to point next (will issued) school_number
	// It initiated by 0, because i decided to issue 10000~19999 for school_number
	static long current_school_number = 10000;
	// course information that this student attends on, maximum course number is 4 
	ArrayList<Course> attending_courses = new ArrayList<Course>(4);
	
	// belows are methods for class Student
	// method returns school number
	// this method and get_student_name method dosen't need to be declared
	// but i realized the getter automatically generated in java after finish coding...
	// so is just used my handmade methods..
	long get_school_number() {
		return this.school_number;
	}
	// method returns student name
	String get_student_name() {
		return this.name;
	}
	// course add method
	void add_course() {
		// declare int type variable temporary counter 
		// to imply course adding method ended successfully or not
		// it could be boolean type but i realized this fact after end coding too...
		int tmpcnt = 0;
		// Scanner class instance input to get input values
		Scanner input = new Scanner(System.in);
		// String class instance course_name to store input values
		long course_ID = input.nextInt();
		// search attending_courses arraylist to find out if input value(course) is already enrolled or not
		for(int i = 0; i < attending_courses.size(); i ++) {
			// if the course name is already in attending_course arraylist's course object,
			// alarm that to user and set tmpcnt to 2
			if(course_ID == (attending_courses.get(i).course_ID)) {
				System.out.println("console : It's already in your list!");
				tmpcnt = 2;
				// stop searcing
				break;
			}
		}
		// search CSECourseManagement's course arraylist and find out
		// input courseID is in arraylist's course or not
		// if there is matching course with input value,
		// add course to arraylist attending_courses to store information that
		// student have added this course
		for(int i = 0; i < super.course.size() && tmpcnt != 2; i++) {
			if(super.course.get(i).course_ID == (course_ID)) {
				// add course to arraylist attending_course 
				attending_courses.add(super.course.get(i));
				// when the student add course, this add_course method 
				// automatically add this student to the matching course's student list same time
				// so there is not any codes at course object doing adding students or sub students
				super.course.get(i).add_student(this.student_login_ID);
				tmpcnt++;
				// stop searching
				break;
			}
		}
		// if variable tmpcnt is 0, it means there isn't matching course name with input value
		// so inform user that there isn't course matching to input value
		if(tmpcnt == 0)
			System.out.println("console : " + course_ID + " is not existing course ID");
	}
	// course drop method
	// same principle with add_course method
	// it also subtract sutdent's information automatically from course object
	// when drop course has been successfully activated
	void drop_course() {
		int tmpcnt = 0;
		Scanner input = new Scanner(System.in);
		long course_ID = input.nextInt();
		for(int i = 0; i < super.course.size(); i++) {
			if(super.course.get(i).course_ID == (course_ID)) {
				attending_courses.remove(super.course.get(i));
				super.course.get(i).sub_student(this.student_login_ID);
				System.out.println("console : course name ( " + course_ID +"(" +
						super.course.get(i).course_ID + ") ) deleted from your list");
				tmpcnt++;
				break;
			}
		}
		if(tmpcnt == 0)
			System.out.println("console : " + course_ID + " is not existing course ID");
	}
	// ID getting method for login
	String get_ID() {
		return this.student_login_ID;
	}
	// PW getting method for login
	String get_PW() {
		return this.login_PW;
	}
	// list method
	// display the course titles and ID that this student enrolled
	void list() {
		if(log == true) {
			System.out.println("console : " + 
					this.name + " attends " + attending_courses.size() + "courses");
			System.out.println("< enrolled Course List >");
			for(int i = 0 ; i < attending_courses.size(); i++) {
				System.out.println(attending_courses.get(i).course_title + "-" + attending_courses.get(i).course_ID);
			}
		}
		else
			System.out.println("console : login first!");
	}
	// login method
	// parameter ID and PW is used for compare the enrolled ID&PW values
	int login(String ID, String PW) {
		// search student objects in CSECourseManagement's arraylist and compare it to existing values
		for(int i = 0; i < super.student.size(); i++) {
			// if the current iterator pointing ID which has same value with ID parameter
			// check the PW value
			if(super.student.get(i).get_ID().equals(ID)) {
				if(super.student.get(i).get_PW().equals(PW)) {
					// if the PW value is same value with PW parameter, variable log is setted to true
					log = true;
					// and then return 2 to set Execute.java's switch condition's parameter
					return 2;
				}
			}
		}
		// If ID/PW is wrong, alarm to user that login failed
		System.out.println("console : Wrong ID/PW");
		// and then return 0 to set Execute.java's switch condition's parameter
		return 0;

	}
	// logout method
	void logout() {
		log = false;
	}
}
