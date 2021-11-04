package hw_3;
// import Scanner class to use Scanner
import java.util.Scanner;
// Staff class inherit CSECourseManagement class 
public class Staff extends CSECourseManagement {
	// constructor of class Staff
	Staff(String name){
		// initiate field data
		this.name = name;
		this.employment_identification_number = current_identification_number++;
	}
	// fields of class Staff
	String employment_login_ID; // employment login ID
	String login_PW; // employment login password
	boolean log; // authentication of user
	long employment_identification_number; // staff's employment identification number
	// current sequentially issued identification number pointer
	// declare long type instance current_identification_number by static
	// to point next (will issued) employment_identification_number
	// It initiated by 0, because i decided to issue 0~9999 for employment_identification_number
	static long current_identification_number = 0;
	String name; // employment name
	
	
	// methods of class Staff
	// course setting method
	void set_course() {
		if(this.log == true) { // works only login with admin staff ID/PW
			// change isStaff value to true so that only admin could add course
			// declare boolean type instance is_in to imply enroll ended successfully
			// Scanner class instance input to get input values
			Scanner input = new Scanner(System.in);
			// String class instance title to get input values
			String title = input.nextLine();
			// enrolled course number is over 3, alarm user that there are too much courses
			if(super.course.size() == 3) 
				System.out.println("console : Too many courses!");
			// if there is space for enroll new course and is_in variable is false execute as belows
			else {
				// make new course with input value(title) and add it to CSECourseManagement's course arraylist
				// use temporary instant tmp for temporarily store new Course object
				Course tmp = new Course(title);
				super.course.add(tmp);
				// inform that course has been successfully enrolled
				System.out.println("console : course " + title + "-" + tmp.course_ID + " has been enrolled");
				}
		}
		// execute when trying to use this method when nobody logined
		// Actually, it's not going to happen in real use, but this code is for debugging
		else {
			System.out.println("console : You should log in first!");
		}
	}
	// method that enroll student to CSECourseManagement System
	void enroll_sutdent() {
		// boolean type make variable to imply enroll student ended successfully
		boolean make = true;
		Scanner input = new Scanner(System.in);
		System.out.println("console : proceeding enroll student");
		System.out.print("input student name : ");
		// String class instance name, ID, PW to declare and initiate new student object
		String name = input.nextLine();
		System.out.print("\ninput student ID (5 letters) : ");
		String ID = input.nextLine();
		// check the length of input value
		if (ID.length() > 5) {
			System.out.println("console : ID length is too long, maximum length is 5 letters");
			make = false;
		}
		System.out.print("\ninput student PW (5 letters) : ");
		String PW = input.nextLine();
		// check the length of input value
		if (PW.length() > 5) {
			System.out.println("console : PW length is too long, maximum length is 5 letters");
			make = false;
		}
		// search CSECourseManagement's arraylist student to find out input ID value is already using or not
		for(int i = 0; i < super.student.size(); i++) {
			// if the input ID value is already using, set make variable to false, and inform ID has been taken
			if(ID.equals(super.student.get(i).student_login_ID)) {
				System.out.println("console : already existing ID, try another ID");
				make = false;
			}
		}
		// if there isn't matching ID matching with other existing ID execute as below
		if(make == true)
			// make new student object and initiate with input name,
			// ID, PW using CSECourseManagement's addStudent method
			// add it to CSECourseManagement class's student arraylist 
			super.addStudentID(name, ID, PW);
	}
	// login method to log in with admin authentication
	// every Staff account use same ID and PW, so those value are fixed in code
	int login(String ID, String PW) {
		// parameter ID is equals String "Staff", ID value is matched
		if(ID.equals("Staff")){
			// and then check the PW value, comparing parameter PW and String "Squid"
			if(PW.equals("Squid")){
				// if ID and PW are both matched with fixed values, set log value to ture
				// so that imply current Staff account logined successfully
				log = true;
				// for this method's use at Execute.java(main function),
				// it returns 1 when logined has been successfully ended
				// it is going to initiate state value of the Execute.java's switch condition
				return 1;
			}
			// in case PW is not matching with fixed values
			// alarm to user that PW is wrong
			else {
				System.out.println("console : Wrong password");
				log = false;
				// it returns 0 when login failed
				return 0;
			}
		}
		// When it comes to ID is not matching with fixed values
		else {
			// in case ID is not matching with fixed values
			// alarm to user that ID is wrong
			System.out.println("console : Wrong ID");
			log = false;
			// it returns 0 when login failed
			return 0;
		}
	}
	// course list method
	void list() {
		// declare int type variable idx and initiate it by -1;
		// it's value will used to point the position of arraylist at CSECourseManagemen
		int idx = -1;
		// Scanner class instance input to get input values
		Scanner input = new Scanner(System.in);
		// String class instance course_name to store input values
		long course_ID = input.nextInt();
		// search CSECourseManagement's course arraylist to find out matching course with input value
		for(int i = 0; i < super.course.size(); i++) {
			// if input value is matching with course name in arraylist, 
			// set idx variable value to iterator i value 
			if(course_ID == (super.course.get(i).course_ID)) idx = i;
		}
		// warning messages if students are not enough for open course
		if(super.course.get(idx).number_of_sutdents < 2) {
			System.out.println("console : not enough students for open course now... "
					+ "\nconsole : it could be deleted if students aren't register until opening date of semaster");
		}
		// if idx value is -1, it means that there isn't matching course name at arraylist of CSECourseManagement
		// so the value isn't -1 execute belows
		if(idx != -1) {
			// number of students who register the course, access right arraylist using variable idx
			System.out.println("console : number of students who register the course : " + 
					super.course.get(idx).NoS());
			// student's information(name and school number)
			System.out.println("< " + super.course.get(idx).get_course_name() +
					"'s attending students info >");
			// display informations by using handmade getter..
			super.course.get(idx).get_stu_info();
		}
		// in case of idx value is -1, there isn't existing course name equals with input value
		// so alarm user with messages as below
		else {
			System.out.println("console : " + course_ID + " is not existing course ID");
		}
	}
	// logout method
	void logout() {
		log = false;
	}
}
