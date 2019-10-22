package com.bo7.f19.e3;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.ArrayList;

import com.b07.f19.e3.Course;
import com.b07.f19.e3.CourseDay;
import com.b07.f19.e3.CourseOverlapException;
import com.b07.f19.e3.SingleLectureCourse;
import com.b07.f19.e3.StudentTimeTable;
import com.b07.f19.e3.TimeTable;

public class TestStudentTimeTable {

  // Test the base cases of each method

  @Test
  public void testStudentTimeTable() throws Exception{

    TimeTable table = new StudentTimeTable(1005129157);

    Field studentNumber = StudentTimeTable.class.getDeclaredField("studentNumber");
    studentNumber.setAccessible(true);
    int studentNumberValue = (int) studentNumber.get(table);

    assertEquals(1005129157, studentNumberValue);
  }

  @Test
  public void testAddCourse() throws Exception{

    TimeTable table = new StudentTimeTable(1005129157);
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    table.setMaxCourses(2);
    table.addCourse(course);

    Field currentCourses = StudentTimeTable.class.getDeclaredField("currentCourses");
    currentCourses.setAccessible(true);
    ArrayList<Course> currentCoursesValue = (ArrayList<Course>) currentCourses.get(table);

    SingleLectureCourse courseTest = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    ArrayList<Course> listTest = new ArrayList<Course>();
    listTest.add(courseTest);
    
    assertTrue(listTest.containsAll(currentCoursesValue) && currentCoursesValue.containsAll(listTest));
  }
  
  @Test
  public void testRemoveCourse() throws Exception{

    TimeTable table = new StudentTimeTable(1005129157);
    
    table.setMaxCourses(5);
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    table.addCourse(course);
    
    SingleLectureCourse course2 = new SingleLectureCourse("Science", "SCIe12", CourseDay.WE, 17, 1);
    table.addCourse(course2);
    
    table.removeCourse(course);
    
    Field currentCourses = StudentTimeTable.class.getDeclaredField("currentCourses");
    currentCourses.setAccessible(true);
    ArrayList<Course> currentCoursesValue = (ArrayList<Course>) currentCourses.get(table);

    ArrayList<Course> listTest = new ArrayList<Course>();
    listTest.add(course2);
    
    assertEquals(listTest, currentCoursesValue);
  }

  @Test
  public void testGetMaxCourses() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    TimeTable table = new StudentTimeTable(1005129157);

    table.setMaxCourses(6);

    int value = table.getMaxCourse();

    assertEquals(6 , value);
  }

  @Test
  public void testSetMaxCourses() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

    TimeTable table = new StudentTimeTable(1005129157);

    table.setMaxCourses(6);

    Field maxCourses = StudentTimeTable.class.getDeclaredField("maxCourses");
    maxCourses.setAccessible(true);
    int maxCoursesValue = (int) maxCourses.get(table);

    assertEquals(6 , maxCoursesValue);
  }

  @Test
  public void testPrintTimeTable() throws CourseOverlapException {

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    TimeTable table = new StudentTimeTable(1005129157);

    table.setMaxCourses(6);

    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.MO, 10, 2);
    SingleLectureCourse course2 = new SingleLectureCourse("Science", "abcd12", CourseDay.FR, 10, 2);

    table.addCourse(course);
    table.addCourse(course2);

    table.printTimeTable();

    //String result = "Timetable for [student = 1005129157] MONDAY:\n CODE: abcd12\n Name: Math MO 10 - 12 TUESDAY: WEDNESDAY: THURSDAY: "
    // + "FRIDAY: CODE: abcd12 Name: Science FR 10 - 12";


    String result = "Timetable for student: 1005129157\r\n" + 
        "MONDAY: \r\n" + 
        "CODE: abcd12\r\n" + 
        "Name: Math\r\n" + 
        "MO 10 - 12\r\n" + 
        "\r\n" + 
        "TUESDAY: \r\n" + 
        "\r\n" + 
        "WEDNESDAY: \r\n" + 
        "\r\n" + 
        "THURSDAY: \r\n" + 
        "\r\n" + 
        "FRIDAY: \r\n" + 
        "CODE: abcd12\r\n" + 
        "Name: Science\n" + 
        "FR 10 - 12";

    assertEquals(result , outContent.toString());

  }

  // Test the boundary cases of each method
  
  @Test
  public void testSetMaxCourses_Zero() throws Exception{

    TimeTable table = new StudentTimeTable(1005129157);

    table.setMaxCourses(0);

    Field maxCourses = StudentTimeTable.class.getDeclaredField("maxCourses");
    maxCourses.setAccessible(true);
    int maxCoursesValue = (int) maxCourses.get(table);

    assertEquals(0 , maxCoursesValue);
  }

  // Test the Exception cases of each method
  
  @Test(expected = CourseOverlapException.class)
  public void testAddCourse_Overlap() throws Exception {

    TimeTable table = new StudentTimeTable(1005129157);
    table.setMaxCourses(4);

    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    table.addCourse(course);

    SingleLectureCourse courseTest = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 11, 2);
    table.addCourse(courseTest);
  }

  @Test(expected = CourseOverlapException.class)
  public void testAddCourse_MoreThanMax() throws Exception {

    TimeTable table = new StudentTimeTable(1005129157);
    table.setMaxCourses(1);

    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    table.addCourse(course);

    SingleLectureCourse courseTest = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 11, 2);
    table.addCourse(courseTest);
  }
  
  @Test(expected = Exception.class)
  public void testSetMaxCourses_Negative() throws Exception{
    TimeTable table = new StudentTimeTable(1005129157);

    table.setMaxCourses(-6);
  }
  
  @Test(expected = Exception.class)
  public void testAddCourse_SameCourseTwice() throws Exception{

    TimeTable table = new StudentTimeTable(1005129157);
    table.setMaxCourses(4);
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    table.addCourse(course);
    
    SingleLectureCourse course2 = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    table.addCourse(course2);
    
  }
  
  @Test(expected = Exception.class)
  public void testStudentTimeTable_Negative() throws Exception{
    TimeTable table = new StudentTimeTable(-1005129157);
  }
  
  // Test the Null cases of each method

  @Test(expected = NullPointerException.class)
  public void testStudentTimeTable_Null() throws Exception{
    StudentTimeTable table = new StudentTimeTable((Integer) null);
  }

  @Test(expected = NullPointerException.class)
  public void testAddCourse_Null() throws CourseOverlapException {
    TimeTable table = new StudentTimeTable(123456);
    table.addCourse(null);
  }
  
  @Test(expected = Exception.class)
  public void testRemoveCourse_Null() throws Exception{

    TimeTable table = new StudentTimeTable(1005129157);
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    table.addCourse(course);
    
    table.removeCourse(null);
    
  }
  
}
