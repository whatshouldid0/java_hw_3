package hw_3;
//import ArrayList class for using ArrayList
import java.util.ArrayList;
// Course class inherit CSECourseManagement class 
public class Course extends CSECourseManagement{
	// Course class constructor
	Course(String title){
		// initiating Course data fields
		this.course_title = title;
		// automatically issue course_ID value by using static long variable current_course_ID pointer
		this.course_ID = Course.current_course_ID_pointer++;
	}
	// declare String class instance course_title for store course title
	String course_title;
	// declare long type instance course_ID for store course ID number
	// it's for distinguish courses that have same course names but different course in real
	long course_ID;
	// declare long type instance current_course_ID_pointer by static to point next (will issued) courseID number
	// It initiated by 20000, because i decided to issue 20000~29999 for courseID
	static long current_course_ID_pointer = 20000;
	// declare int type instance number_of_students to store current students of this course
	// of course, it is initiated by 0
	int number_of_sutdents = 0;
	// student information who attends this course, maximum student is 5
	// declared by arraylist type.
	ArrayList<Student> attending_students = new ArrayList<Student>(5);
	
	
	// add_student and sub_student methods are designed for used by Student object
	// so those code are fit for only Student objects
	
	// add student method
	// It takes String type instance ID as parameter
	void add_student(String ID) {
		// if course is full, inform to user and set variable pos to false
		if (this.number_of_sutdents == 5) {
			System.out.println("console : Course is full, you can't register this course right now");
		}
		// if there is possible space for new student, execute as follows
		else {
			// increase class data instance number_of_students
			this.number_of_sutdents++;
			// for loop for find the student who added this course 
			// and add the student to this course's student list(attending_students)
			for(int i = 0; i < super.student.size(); i++) {
				// find all student objects and if the ID is matching with
				// the student who added this course with current iterator,
				// add that student to course's student list(attending_students)
				if(super.student.get(i).student_login_ID.equals(ID)) {
					attending_students.add(super.student.get(i));
				}
			}
			// inform user that adding action has been successfully ended,
			// it will used by student.java file
			System.out.println("console : course name ( " + this.course_title +  "(" + 
					this.course_ID + ") ) added to your list");
		}
	}
	// method that removes student from this course
	// It takes String type instance ID as a parameter
	void sub_student(String ID) {
		// decrease class data instance number_of_students
		this.number_of_sutdents--;
		// for loop for find the student who removed this course
		// and remove the student to this course's student list(attending_students)
		for(int i = 0; i < super.student.size(); i++) {
			// find all student objects and if the ID is matching with
			// the student who removed this course with current iterator,
			// remove that student to course's student list(attending_students)
			if(super.student.get(i).student_login_ID.equals(ID)) {
				attending_students.remove(super.student.get(i));
			}
		}
	}
	// returns number of students of this course,
	// I realized setter and getter are automatically generated at Java language after finished coding
	// but It too late and too annoying to fix all code... so I just using this method...
	int NoS() {
		return this.number_of_sutdents;
	}
	// print right student's information method
	// this method is designed for used by Staff object
	void get_stu_info() {
		for(int i = 0; i < attending_students.size(); i++) {
		System.out.println(attending_students.get(i).get_student_name() + " (" +
				attending_students.get(i).get_school_number() + ")");
		}
	}
	// returns course title of this course
	String get_course_name() {
		return this.course_title;
	}
}
