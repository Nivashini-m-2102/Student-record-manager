import java.util.*;
import java.io.Serializable;
import java.lang.String;
import java.io.*;
class Student implements Serializable
{
  private int rollNo;
  private String name;
  private int year;
  private String department;
  private String email;
  public Student(int rollNo,String name,int year,String department,String email)
  {
    this.rollNo = rollNo;
    this.name = name;
    this.year = year;
    this.department = department;
    this.email = email;
  }
  public int getRollNo()
  {
    return rollNo;
  }
  public String getName()
  {
    return name;
  }
  public int getYear()
  {
    return year;
  }
  public String getDepartment()
  {
    return department;
  }
  public String getEmail()
  {
    return email;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public void setYear(int year)
  {
    this.year = year;
  }
  public void setDepartment(String department)
  {
    this.department = department;
  }
  public void setEmail(String email)
  {
    this.email = email;
  }
  public String toString()
    {
        return "Roll No: " + rollNo + ", Name: " + name + ",Year:" + year + ", Department:" + department + ",Email:" + email;
    }
}
class StudentManager
{
    private List<Student> students;
    private final String FILE_NAME = "student.dat";
    public StudentManager()
    {
      students = loadFromFile();
    }

    public void addStudent(Student s)
    {
        for(Student st:students)
        {
            if(st.getRollNo() == s.getRollNo())
            {
                System.out.println("* student with this RollNo already exits *");
                return;
            }
        }
        students.add(s);
        saveToFile();
        System.out.println(" Student added !!");
    }
    public void viewAllStudents()
    {
        if(students.isEmpty())
        {
            System.out.println("* No students Found *");
        }
        else
        {
            for(Student s:students)
            {
                System.out.println(s);
            }
        }
    }
    public Student searchStudent(int rollNo)
    {
      for(Student s :students)
      {
        if(s.getRollNo() == rollNo)
        {
          return s;
        }
      }
      return null;
    }
    public void updateStudent(int rollNo,String name,int year,String department,String email)
    {
      Student stu = searchStudent(rollNo);
      if(stu!=null)
      {
        stu.setName(name);
        stu.setYear(year);
        stu.setDepartment(department);
        stu.setEmail(email);
        System.out.println("Student upadted sucessfully !!");
      }
      else
      {
        System.out.println("* Student not found *");
      }

    }
    public void deleteStudent(int rollNo)
    {
      Student s = searchStudent(rollNo);
      if(s!=null)
      {
          students.remove(s);
          saveToFile();
          System.out.println("Student deleted !!");
      }
      else
      {
        System.out.println("* Student not found *");
      }
    }
    public void sortStudent(int option)
    {
      if(students.isEmpty())
      {
        System.out.println("No Students Record Found");
      }
      switch(option)
      {
        case 1:
              students.sort(Comparator.comparingInt(Student::getRollNo));
              break;
        case 2:
             students.sort(Comparator.comparing(Student::getName));
             break;
        case 3:
             students.sort(Comparator.comparingInt(Student::getYear));
             break;
        default:
            System.out.println("Invalid sort Option");
            return;
          }
          System.out.println("Students sorted sucessfully");
          viewAllStudents();
    }
    private void saveToFile() 
    {
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
        oos.writeObject(students);
    } catch (IOException e) {
        System.out.println("Error saving file.");
    }
}

    private List<Student> loadFromFile()
    {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME)))
      {
        return (List<Student>) ois.readObject();
      } 
      catch (Exception e)
      {
        return new ArrayList<>();
      }
    }
}
public class Main
{
  public static boolean login(Scanner sc)
  {
    final String USERNAME = "Nivashini";
    final String PASSWORD = "Niva@2006";
    int attempts=0;
    while(attempts<2)
    {
      System.out.println("----Login----");
    System.out.println("Enter the USERNAME: ");
    String inputName = sc.nextLine();
    System.out.println("Enter the USERPASSWORD: ");
    String inputPassword = sc.nextLine();
    if(inputName.equals(USERNAME) && inputPassword.equals(PASSWORD))
    {
      System.out.println("Login Sucessfully !!\n");
      return true;
    }
    else
    {
      attempts++;
      System.out.println("Invalid username or password. Attempts left : "+ (2-attempts)+"\n");
    }
  }
  return false;

    }
    
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        if(!login(sc))
        {
          System.out.println("Access denied.Existing....");
          return;
        }
        StudentManager manager = new StudentManager();
        while(true)
        {
            System.out.println("---Student Management System---");
            System.out.println("1.Add student");
            System.out.println("2.View all students");
            System.out.println("3.search student");
            System.out.println("4.update student");
            System.out.println("5.delete student");
            System.out.println("6.Sort Students");
            System.out.println("7.exit");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                     System.out.print("Enter the RollNo:");
                     int rollNo = sc.nextInt(); sc.nextLine();
                     System.out.print("Enter the name:");
                     String name = sc.nextLine();
                     System.out.print("Enter the Year:");
                     int year = sc.nextInt();sc.nextLine();
                     System.out.print("Enter the Department:");
                     String dep = sc.nextLine();
                     System.out.println("Enter the Email:");
                     String email = sc.nextLine();
                     manager.addStudent(new Student(rollNo,name,year,dep,email));
                     break;
                case 2:
                    manager.viewAllStudents();
                    break;
                case 3:
                    System.out.print("Enter the rollNo to search: ");
                    int searchNo = sc.nextInt();
                    Student found = manager.searchStudent(searchNo);
                    if(found != null)
                    {
                        System.out.println("Found !!");
                    }
                    else
                    {
                        System.out.println("* Student not found *");
                    }
                    break;
                case 4:
                    System.out.print("Enter the rollNo to update:");
                    int updateNo = sc.nextInt();sc.nextLine();
                    System.out.print("Enter the name to update:");
                    String updateName = sc.nextLine();
                    System.out.print("Enter the year to update:");
                    int updateYear = sc.nextInt();sc.nextLine();
                    System.out.print("Enter the department to update:");
                    String updateDepartment = sc.nextLine();
                    System.out.print("Enter the email to update:");
                    String updateEmail = sc.nextLine();
                    manager.updateStudent(updateNo,updateName,updateYear,updateDepartment,updateEmail);
                    break;
                case 5:
                    System.out.print("Enter the RollNo to delete:");
                    int deleteNo = sc.nextInt();
                    manager.deleteStudent(deleteNo);
                    break;
                case 6:
                    System.out.println("1.Rollno");
                    System.out.println("2.Name");
                    System.out.println("3.Year");
                    int sortOption =sc.nextInt();sc.nextLine();
                    manager.sortStudent(sortOption);
                    break;
                case 7:
                    System.out.println("Existing...goodbye");
                    return;
                default:
                    System.out.println("Invalid choice");
            }

        }
    }
}