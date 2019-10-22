package com.bo7.f19.e3;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Test;

import com.b07.f19.e3.Course;
import com.b07.f19.e3.CourseDay;
import com.b07.f19.e3.SingleLectureCourse;
import com.b07.f19.e3.StudentTimeTable;
import com.b07.f19.e3.TimeTable;

public class TestSingleLectureCourse{
	
  //
  // TESTING the basic setup of the two constructors
  //
  
  @Test
  public void testSingleLectureCourse_name() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);

    Field courseName = SingleLectureCourse.class.getDeclaredField("name");
    courseName.setAccessible(true);
    String courseNameValue = (String) courseName.get(course);
    
    assertEquals("Math", courseNameValue);
  }
  
  @Test
  public void testSingleLectureCourse_code() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);

    Field code = SingleLectureCourse.class.getDeclaredField("code");
    code.setAccessible(true);
    String codeValue = (String) code.get(course);
    
    assertEquals("abcd12", codeValue);
  }

  @Test
  public void testSingleLectureCourse_CourseDay() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);

    Field courseDay = SingleLectureCourse.class.getDeclaredField("courseDay");
    courseDay.setAccessible(true);
    CourseDay courseDayValue = (CourseDay) courseDay.get(course);
    
    assertEquals(CourseDay.FR , courseDayValue);
  }
  
  @Test
  public void testSingleLectureCourse_CourseTime() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);

    Field courseTime = SingleLectureCourse.class.getDeclaredField("courseTime");
    courseTime.setAccessible(true);
    int courseTimeValue = (int) courseTime.get(course);
    
    assertEquals(10, courseTimeValue);
  }
  
  @Test
  public void testSingleLectureCourse_LectureLength() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);

    Field lectureLength = SingleLectureCourse.class.getDeclaredField("lectureLength");
    lectureLength.setAccessible(true);
    int lectureLengthValue = (int) lectureLength.get(course);
    
    assertEquals(2, lectureLengthValue);
  }
  
  @Test
  public void testSingleLectureCourse_ExcludedCourses() throws Exception {
    
    SingleLectureCourse science = new SingleLectureCourse("Science", "abcd13", CourseDay.MO, 13, 1);
    ArrayList<Course> excludedCourses = new ArrayList<Course>();
    excludedCourses.add(science);
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", excludedCourses, CourseDay.FR, 10, 2);

    Field excludedCourse = SingleLectureCourse.class.getDeclaredField("excludedCourses");
    excludedCourse.setAccessible(true);
    ArrayList<Course> excludedCourseValue = (ArrayList<Course>) excludedCourse.get(course);
 
    SingleLectureCourse scienceTest = new SingleLectureCourse("Science", "abcd13", CourseDay.MO, 13, 1);
    ArrayList<Course> excludedCoursesTest = new ArrayList<Course>();
    excludedCoursesTest.add(scienceTest);
    
    assertEquals(excludedCoursesTest, excludedCourseValue);
  }
  
  //
  // TESTING the base cases for each Setter method
  //
  
  @Test
  public void testSetCourseCode() throws Exception{
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    
    course.setCourseCode("MATB52");
    
    Field code = SingleLectureCourse.class.getDeclaredField("code");
    code.setAccessible(true);
    String codeValue = (String) code.get(course);
    
    assertEquals("MATB52", codeValue);
    
  }
  
  @Test
  public void testSetCourseName() throws Exception{
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    
    course.setCourseName("Probability");
    
    Field name = SingleLectureCourse.class.getDeclaredField("name");
    name.setAccessible(true);
    String nameValue = (String) name.get(course);
    
    assertEquals("Probability", nameValue);
    
  }
  
  @Test
  public void testSetCourseStartTime() throws Exception{
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    
    course.setCourseStartTime(14);
    
    Field time = SingleLectureCourse.class.getDeclaredField("courseTime");
    time.setAccessible(true);
    int timeValue = (int) time.get(course);
    
    assertEquals(14, timeValue);
    
  }
  
  @Test
  public void testSetLectureLength() throws Exception{
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 2);
    
    course.setLectureLength(3);
    
    Field length = SingleLectureCourse.class.getDeclaredField("lectureLength");
    length.setAccessible(true);
    int lengthValue = (int) length.get(course);
    
    assertEquals(3, lengthValue);
   
  }
  
  // TESTING the boundry cases within the two constructors
  
  @Test
  public void testSingleLectureCourse_CourseTimeMin() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 8, 2);

    Field courseTime = SingleLectureCourse.class.getDeclaredField("courseTime");
    courseTime.setAccessible(true);
    int courseTimeValue = (int) courseTime.get(course);
    
    assertEquals(8, courseTimeValue);
  }
  
  @Test
  public void testSingleLectureCourse_CourseTimeMax() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 20, 2);

    Field courseTime = SingleLectureCourse.class.getDeclaredField("courseTime");
    courseTime.setAccessible(true);
    int courseTimeValue = (int) courseTime.get(course);
    
    assertEquals(20, courseTimeValue);
  }
  
  @Test
  public void testSingleLectureCourse_LectureLengthMin() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 1);

    Field lectureLength = SingleLectureCourse.class.getDeclaredField("lectureLength");
    lectureLength.setAccessible(true);
    int lectureLengthValue = (int) lectureLength.get(course);
    
    assertEquals(1, lectureLengthValue);
  }
  
  @Test
  public void testSingleLectureCourse_LectureLengthMax() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 3);

    Field lectureLength = SingleLectureCourse.class.getDeclaredField("lectureLength");
    lectureLength.setAccessible(true);
    int lectureLengthValue = (int) lectureLength.get(course);
    
    assertEquals(3, lectureLengthValue);
    
  }
  
  // TESTING Exceptions
  // testing unexpected values for the functions with integer input
  
  @Test(expected = Exception.class)
  public void testSingleLectureCourse_CourseTimeNegative() throws Exception {   
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, -10, 2);
  }
  
  @Test(expected = Exception.class)
  public void testSingleLectureCourse_CourseTimeZero() throws Exception {
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 0, 2);
  }
  
  @Test(expected = Exception.class)
  public void testSingleLectureCourse_CourseTimeAboveMax() throws Exception {
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 18, 2);
  }
  
  @Test(expected = Exception.class)
  public void testSingleLectureCourse_LectureLengthNegative() throws Exception {
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, -2);
  }

  
  @Test(expected = Exception.class)
  public void testSingleLectureCourse_LectureLengthZero() throws Exception {
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 0);
  }
  
  @Test(expected = Exception.class)
  public void testSingleLectureCourse_LectureLengthAboveMax() throws Exception {
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", CourseDay.FR, 10, 17);
  }
  
  // TESTING Null Cases
  
  @Test
  public void testSingleLectureCourse_AllNull() throws Exception {
  
    SingleLectureCourse course = new SingleLectureCourse(null, null, null, 0, 0);

    Field code = SingleLectureCourse.class.getDeclaredField("code");
    code.setAccessible(true);
    String codeValue = (String) code.get(course);
    
    assertEquals(null, codeValue);
    
  }
  
  
  @Test
  public void testSingleLectureCourse_nameNull() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse(null, "abcd12", CourseDay.FR, 10, 2);

    Field courseName = SingleLectureCourse.class.getDeclaredField("name");
    courseName.setAccessible(true);
    String courseNameValue = (String) courseName.get(course);
    
    assertEquals(null, courseNameValue);
    
  }
  
  @Test
  public void testSingleLectureCourse_codeNull() throws Exception {
    
    SingleLectureCourse course = new SingleLectureCourse("Math", null, CourseDay.FR, 10, 2);

    Field code = SingleLectureCourse.class.getDeclaredField("code");
    code.setAccessible(true);
    String codeValue = (String) code.get(course);
    
    assertEquals(null, codeValue);
    
  }

  @Test(expected = Exception.class)
  public void testSingleLectureCourse_CourseDayNull() throws Exception {
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", null, 10, 2);
  }
 
  @Test
  public void testSingleLectureCourse_ExcludedCoursesNull() throws Exception {

    ArrayList<Course> excludedCourses = new ArrayList<Course>();
    
    SingleLectureCourse course = new SingleLectureCourse("Math", "abcd12", excludedCourses, CourseDay.FR, 10, 2);

    Field excludedCourse = SingleLectureCourse.class.getDeclaredField("excludedCourses");
    excludedCourse.setAccessible(true);
    ArrayList<Course> excludedCourseValue = (ArrayList<Course>) excludedCourse.get(course);

    ArrayList<Course> excludedCoursesTest = new ArrayList<Course>(); 
    
    assertEquals(excludedCoursesTest, excludedCourseValue);
    
  }
  
  
  

  
  
  
  
	
}
