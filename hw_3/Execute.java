package hw_3;
// import Scanner class to using Scanner class
import java.util.Scanner;
// import Console class to using Console class
import java.io.Console;

// Execute class, it's where main function declared
public class Execute {
	// main function
	public static void main(String[] args) {
		// my name and school number 
		System.out.println("Name : Son Jae Sung");
		System.out.println("School Numbers : 201824501");
		// declare CSECourseManagement object SquidCSE
		CSECourseManagement SquidCSE = new CSECourseManagement();
		// Declare primordial account, it is inevitable for this program
		// primordial account's name is admin and it's employmentID is 0
		// As Homework's request, it's ID/PW is fixed, it's declared on Staff.java code
		Staff admin = new Staff("admin");
		// int type instance state for poining current state, it is used as main switch condition parameter
		int state = 0;
		// int type instance login_idx for pointing current logined user,
		// it is used by student user to point who logged in
		int login_idx = -1;
		// boolean type instance using for imply someone has logined in or not
		boolean using = false;
		// infinity loop that implements program
		while(true) {
			// if nobody logined, below codes are excuted
			if(using == false) {
				// Scanner class instance input to get input values
				Scanner input = new Scanner(System.in);
				// Consol class instance pwd for using console class
				Console pwd = System.console();
				System.out.println("=========================================================");
				System.out.println("Welcome to the CSE Department!!");
				System.out.println("Login : CSEStaff of Student ID");
				// get ID
				String ID = input.nextLine();
				System.out.print("Passwd : ");
				// char[] type PW_c for store inputed password
				// get PW by using readPassword() method, it is not echoed by terminal
				char[] PW_c = pwd.readPassword();
				// Store PW as String class value, initiated as same value with PW_c
				String PW = String.valueOf(PW_c);
				// find out ID is for Staff or student
				if(ID.equals("Staff")) {
					// log in as Staff
					state = admin.login(ID, PW);
					// set using instance to true
					using = true;
				}
				// in case it's not Staff ID(Staff)
				else {
					// check every student objects and find matching ID
					for(int i = 0; i < SquidCSE.student.size(); i++) {
						if(SquidCSE.student.get(i).get_ID().equals(ID)) {
							// in case find mathching ID, set using instance to true, and set login_idx to i
							state = SquidCSE.student.get(i).login(ID, PW);
							using = true;
							login_idx = i;
							// and then break the loop(stop finding)
							break;
						}
					}
					// in case there isn't matching ID in CSECourseManagement class's ArrayList
					if(using == false) {
						System.out.println("There isn't existing ID : " + ID);
						// switch case 0, print Login again
						state = 0;
					}
				}
			}
			// switch condition, choose state by parameter state
			// case 0 is for login fail case
			// case 1 is for admin
			// case 2 is for student
			// default for default situation
			switch(state) {
			case 0:
				System.out.println("=========================================================");
				System.out.println("console : Login again");
				System.out.println("=========================================================");
				using = false;
				break;
			case 1: // execute when admin account loged in
				System.out.println("=========================================================");
				System.out.println("console : admin user authenticated, press action");
				System.out.println("1. Set course");
				System.out.println("2. list interested course information");
				System.out.println("3. list all course setted");
				System.out.println("4. enroll new student");
				System.out.println("5. list all student members enrolled this Management program");
				System.out.println("6. enroll new staff");
				System.out.println("7. list all staff members with employment number");
				System.out.println("8. log out");
				System.out.println("9. exit program");
				System.out.print("console : select action : ");
				Scanner input = new Scanner(System.in);
				int actionA = input.nextInt(); // get actionA(admin action) for choose actions
				// switch condition, choose state by parameter actionA
				// case 1 for setting course
				// case 2 for list interested course(access by courseID)
				// case 3 for list all course setted
				// case 4 for enroll new student
				// case 5 for enroll new staff
				// case 6 for list current staff members and their employment ID
				// case 7 for log out
				// case 8 for exit program
				// default for default situation
				switch(actionA) {
				case 1:
					System.out.println("=========================================================");
					System.out.println("console : type course name that you want to add");
					// use admin object set_course method for setting course
					admin.set_course();
					System.out.println("=========================================================");
					break;
				case 2:
					System.out.println("=========================================================");
					System.out.println("console : type courseID that you want to list informations");
					// use admin object list method for list course
					admin.list();
					System.out.println("=========================================================");
					break;
				case 3:
					System.out.println("=========================================================");
					System.out.println("console : Display current setted courses");
					System.out.println("console : These courses might not opened if there isn't enough students for open course");
					for(int i = 0; i < SquidCSE.course.size(); i++) {
						System.out.println("course name(courseID) : " + SquidCSE.course.get(i).get_course_name() + "(" +
								SquidCSE.course.get(i).course_ID + ")");
					}
					System.out.println("=========================================================");
					break;
				case 4:
					System.out.println("=========================================================");
					// use admin object enroll_student method for enroll student
					admin.enroll_sutdent();
					System.out.println("=========================================================");
					break;
				case 5:
					System.out.println("=========================================================");
					System.out.println("console : There are " + SquidCSE.student.size() +
							" members of enrolled students");
					System.out.println("< Student List >");
					for(int i = 0 ; i < SquidCSE.student.size(); i++) {
						System.out.println("school number : " + 
					SquidCSE.student.get(i).school_number + " \n name : " + SquidCSE.student.get(i).name + "\n");
					}
					System.out.println("=========================================================");
					break;
					
				case 6:
					System.out.println("=========================================================");
					// ID and PW for new staff member
					String s1 = "Staff";
					String s2 = "Squid";
					admin.addStaffID(s1, s2);
					System.out.println("=========================================================");
					break;
				case 7:
					// list staff list enrolled at CSECourseManagement Program
					System.out.println("=========================================================");
					System.out.println("console : There are " + SquidCSE.staff.size() +
							" members of enrolled staff");
					System.out.println("< Staff List >");
					for(int i = 0 ; i < SquidCSE.staff.size(); i++) {
						System.out.println("employment number : " + 
					SquidCSE.staff.get(i).employment_identification_number + " \n name : " + SquidCSE.staff.get(i).name + "\n");
					}
					System.out.println("=========================================================");
					break;
				case 8:
					System.out.println("=========================================================");
					System.out.println("console : log out"); // log out method
					// use admin object logout method for log out
					admin.logout();
					using = false;
					System.out.println("=========================================================");
					break;
				case 9:
					System.out.println("=========================================================");
					System.out.println("console : exit program...");
					// use System.exit(0) to end program
					System.exit(0);
				default:
					// other case - Wrong input
					System.out.println("=========================================================");
					System.out.println("console : Wrong input, please type again");
					System.out.println("=========================================================");
					break;
				}
				break;
			case 2: //execute when student account loged in
				System.out.println("=========================================================");
				System.out.println("console : Student user < "+SquidCSE.student.get(login_idx).name +
						" > authenticated, press action");
				System.out.println("1. add course");
				System.out.println("2. drop course");
				System.out.println("3. list registered course");
				System.out.println("4. list current setted course");
				System.out.println("5. inquire student nubmer");
				System.out.println("6. log out");
				System.out.println("7. exit program");
				System.out.print("console : select action : ");
				Scanner inputS = new Scanner(System.in);
				int actionS = inputS.nextInt(); // get actionS(student action) for choose actions
				// switch condition, choose state by parameter actionS
				// case 1 for add course
				// case 2 for drop course
				// case 3 for list course
				// case 4 for list current setted course by admin
				// case 5 for inquire school_number of logined student
				// case 6 for log out
				// case 7 for exit program
				// default for default situation
				switch(actionS) {
				case 1:
					System.out.println("=========================================================");
					System.out.println("console : type courseID that you want to add");
					// use student object add_course method, access the matching student by login_idx variable
					SquidCSE.student.get(login_idx).add_course();
					System.out.println("=========================================================");
					break;
				case 2:
					System.out.println("=========================================================");
					System.out.println("console : type courseID that you want to drop");
					// use student object drop_course method, access the matching student by login_idx variable
					SquidCSE.student.get(login_idx).drop_course();
					System.out.println("=========================================================");
					break;
				case 3:
					System.out.println("=========================================================");
					// use student object list method, access the matching student by login_idx variable
					System.out.println("console : display enrolled courses");
					SquidCSE.student.get(login_idx).list(); // display enrolled lists
					System.out.println("=========================================================");
					break;
				case 4:
					System.out.println("=========================================================");
					System.out.println("console : Display current setted courses");
					System.out.println("console : These courses might not opened if there isn't enough students for open course");
					for(int i = 0; i < SquidCSE.course.size(); i++) {
						System.out.println("course name(courseID) : " + SquidCSE.course.get(i).get_course_name() + "(" +
								SquidCSE.course.get(i).course_ID + ")");
					}
					System.out.println("=========================================================");
					break;
				case 5:
					System.out.println("=========================================================");
					// at here.. I didn't use the automatically generated getter... but i just use my own
					System.out.println("console : " + 
						SquidCSE.student.get(login_idx).get_student_name() + "'s school number is "
						+ SquidCSE.student.get(login_idx).school_number + "!\nDon't forget it!");
					System.out.println("=========================================================");
					break;
				case 6:
					System.out.println("=========================================================");
					System.out.println("console : log out");
					// use student object logout method, access the matching student by login_idx variable
					SquidCSE.student.get(login_idx).logout();
					// set variable using to imply nobody currently logined now
					using = false;
					System.out.println("=========================================================");
					break;
				case 7:
					System.out.println("=========================================================");
					System.out.println("console : exit program...");
					// use System.exit(0) to end program
					System.exit(0);
				default:
					// other case - Wrong input
					System.out.println("=========================================================");
					System.out.println("console : Wrong input, please type again");
					System.out.println("=========================================================");
					break;
				}
				break;
			default:
				// default cases(maybe not happened at normal situation)
				System.out.println("=========================================================");
				break;
			}
		}
	}
}
