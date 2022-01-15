package Utility;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course {
	private String courseId;
	private String firstName;
	private String lecture;

	private ArrayList<String> preLecture;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public Course(String courseString) {
		StringTokenizer stringTokenizer = new StringTokenizer(courseString);

		this.courseId = stringTokenizer.nextToken();
		this.firstName = stringTokenizer.nextToken();
		this.lecture = stringTokenizer.nextToken();

		this.preLecture = new ArrayList<String>();

		while (stringTokenizer.hasMoreTokens()) {
			this.preLecture.add(stringTokenizer.nextToken());
		}
	}
}
