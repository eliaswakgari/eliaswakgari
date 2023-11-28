
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
class Student {
    private String fullName;
    private String studentID;
    private String address;
    private String courses;
    private String department;
    public Float GPA;

    public Student(String fullName, String studentID, String address, String courses,String department) {
        this.fullName = fullName;
        this.studentID = studentID;
        this.address = address;
        this.courses = courses;
        this.department=department;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getAddress() {
        return address;
    }

    public String getCourse() {
        return courses;
    }
    public String getDepartment () {
        return department;
    }
}

class Database {

    private static final int MAX_STUDENTS = 80;
    private static Student[] students = new Student[MAX_STUDENTS];
    private static int numStudents = 0;

    Database() throws SQLException {
    }

    public static void addStudent(Student student) {
        if (numStudents < MAX_STUDENTS) {
            students[numStudents] = student;
            numStudents++;
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Maximum number of students reached.");
        }
    }

    public static void viewAllStudents() {
        if (numStudents > 0) {
            for (int i = 0; i < numStudents; i++) {
                Student student = students[i];
                System.out.println("Full Name: " + student.getFullName());
                System.out.println("Student ID: " + student.getStudentID());
                System.out.println("Address: " + student.getAddress());
                System.out.println("Course: " + student.getCourse());
                System.out.println("department: " + student.getDepartment());
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No students found.");
        }
    }
}

public class StudentRegistration {


    private static String courses;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrationsystem", "root", "");
            Statement stmt = conn.createStatement();
            String sql="SELECT * FROM students_data";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String fullName = rs.getString("fullName");
                System.out.println(fullName);
                String Id = rs.getString("Id");
                String department = rs.getString("department");
                String email = rs.getString("email");
                String address = rs.getString("address");
                
                Student student = new Student(fullName, Id, department, address, courses);
            }
            stmt.executeUpdate(sql);
            rs.close();
            stmt.close();
            conn.close();
            }catch (Exception e) {
            System.out.println(e.getMessage());
        }
            Scanner scanner = new Scanner(System.in);
            boolean isRunning = true;

            while (isRunning) {
                System.out.println("Please choose an option:");
                System.out.println("1. Add a new student");
                System.out.println("2. View all students");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();

                switch (choice) {


                    case 1:
                        System.out.println("Enter your GPA");
                        Float GPA=scanner.nextFloat();
                        if (GPA>=1.7 && GPA<=4.0) {
                            System.out.println("Enter full name:");
                            scanner.nextLine();
                            String fullName = scanner.nextLine();

                            System.out.println("Enter student ID:");
                            String studentID = scanner.nextLine();
                            System.out.println("Enter  department:");
                            String department = scanner.nextLine();
                            System.out.println("Enter address:");
                            String address = scanner.nextLine();
                            System.out.println("Enter courses:");
                            String course = scanner.nextLine();
                            Student student = new Student(fullName, studentID, address, course, department);
                            Database.addStudent(student);
                        }

                        else {
                        System.out.println("unable to register");
                    }
                        break;
                        case 2:
                        Database.viewAllStudents();
                        break;

                    case 3:
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");

                }
            }
        }
    }



