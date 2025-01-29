import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}

class Course {
    private String courseId;
    private String courseName;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Course Name: " + courseName;
    }
}

class Grade {
    private String studentId;
    private String courseId;
    private double grade;

    public Grade(String studentId, String courseId, double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Course ID: " + courseId + ", Grade: " + grade;
    }
}

public class StudentGradeManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Grade> grades = new ArrayList<>();

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        students.add(new Student(id, name));
        System.out.println("Student added successfully!");
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        courses.add(new Course(courseId, courseName));
        System.out.println("Course added successfully!");
    }

    private static void assignGrade(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter Grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        grades.add(new Grade(studentId, courseId, grade));
        System.out.println("Grade assigned successfully!");
    }

    private static void calculateGPA(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        double totalGrades = 0;
        int courseCount = 0;

        for (Grade grade : grades) {
            if (grade.getStudentId().equals(studentId)) {
                totalGrades += grade.getGrade();
                courseCount++;
            }
        }

        if (courseCount > 0) {
            double gpa = totalGrades / courseCount;
            System.out.println("GPA for Student ID " + studentId + ": " + gpa);
        } else {
            System.out.println("No grades found for Student ID: " + studentId);
        }
    }

    private static void displayData() {
        System.out.println("\nStudents:");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("\nCourses:");
        for (Course course : courses) {
            System.out.println(course);
        }

        System.out.println("\nGrades:");
        for (Grade grade : grades) {
            System.out.println(grade);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Grade Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Display All Data");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    addCourse(scanner);
                    break;
                case 3:
                    assignGrade(scanner);
                    break;
                case 4:
                    calculateGPA(scanner);
                    break;
                case 5:
                    displayData();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}