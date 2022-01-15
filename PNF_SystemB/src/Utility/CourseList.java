package Utility;

import java.util.ArrayList;

public class CourseList {
	private ArrayList<Course> vCourse;

	private String courseId;
	private String FirstName;
	private String lecture;

	private ArrayList<String> preLecture;

	public CourseList() {
		this.vCourse = new ArrayList<Course>();
		
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public ArrayList<String> getPreLecture() {
		return preLecture;
	}

	public void setPreLecture(ArrayList<String> preLecture) {
		this.preLecture = preLecture;
	}

}
