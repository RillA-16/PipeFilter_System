package Utility;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student {

	private String studentId;
	private String firstname;
	private String lastname;
	private String department;
	private ArrayList<String> completedCoursesList;

	public Student(String studentString) {
		StringTokenizer stringTokenizer = new StringTokenizer(studentString);

		this.studentId = stringTokenizer.nextToken();
		this.firstname = stringTokenizer.nextToken();
		this.lastname = stringTokenizer.nextToken();
		this.department = stringTokenizer.nextToken();

		this.completedCoursesList = new ArrayList<String>();

		while (stringTokenizer.hasMoreTokens()) {
			this.completedCoursesList.add(stringTokenizer.nextToken());
		}
	}

	public ArrayList<String> getCompletedCoursesList() {
		return this.completedCoursesList;
	}
}
