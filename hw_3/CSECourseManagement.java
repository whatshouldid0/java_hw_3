package hw_3;
// import ArrayList class for using ArrayList
import java.util.ArrayList;
import java.util.Scanner;
// CSECourseManagement class
public class CSECourseManagement {
	// CSECourseManagement constructor
	CSECourseManagement(){}
	// method which generate staff account
	// Staff account's ID and PW is fixed, so I just only check the input values,
	// those are matching or not. if input value is match for fixed values,
	// register admin's name and then issue employment numbers
	void addStaffID(String s1, String s2) {
		// if condition for checking input values matches with fixed values
		if(s1 == "Staff" && s2 == "Squid") {
			System.out.print("consle : add new person as Staff, enter staff name : ");
			// declare scanner class input instance for initiate name of staff
			Scanner input = new Scanner(System.in);
			// declare String class instance name to store name
			String name = input.next();
			// add new Staff class object to Arraylist by using add method
			staff.add(new Staff(name));
		}
		// when admin ID/PW is different with designated form, approval denied
		else
			// if input values aren't match with fixed values, print as below.
			System.out.println("console : approval denied, wrong ID/PW");
	}
	// method which generate student account
	// this method is used by Staff object, I know it's ok in case of declared on staff class,
	// but i wrote this code here to increase readability with addStaff method
	void addStudentID(String name, String ID, String PW) {
		// add new Student class object to Arraylist by using add method
		student.add(new Student(name, ID, PW));
		// announce account has been made
		System.out.println("console : " + name + "'s account enrolled!");
	}
	
	// fields that consisting CSEManagement
	// Belows have to be declared static because below members have to used by all classes
	// which inherit CSECourseManagement class
	// course information container
	protected static ArrayList<Course> course = new ArrayList<Course>(3);
	// student information container
	protected static ArrayList<Student> student = new ArrayList<Student>(15);
	// staff information container
	protected static ArrayList<Staff> staff = new ArrayList<Staff>();
}
