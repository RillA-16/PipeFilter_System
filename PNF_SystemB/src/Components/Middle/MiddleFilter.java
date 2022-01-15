/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import java.util.ArrayList;

import Framework.CommonFilterImpl;
import Utility.Course;
import Utility.CourseList;
import Utility.Line;
import Utility.Student;

public class MiddleFilter extends CommonFilterImpl {

	public MiddleFilter(int outPort, int inPort) {
		super(outPort, inPort);
		// TODO Auto-generated constructor stub
	}

	private Course course;
	private Student student;
	private int checkOKAY = 0;
	private int checkNO = 0;
	private int stuSize = 0;

	@Override
	public boolean specificComputationForFilter() throws IOException {

		boolean CourseFinish = false;
		int idx = 0;
		byte[] buffer = new byte[64];

		int byte_readCourse = 0;
		int byte_readStu = 0;

		String convertString;

		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<Student> Students = new ArrayList<Student>();

		boolean isSatisfied = false;
		boolean isUnSatisfied = false;

		Line line;

		while (true) {
			if (!CourseFinish) {
				while (byte_readCourse != '\n' && byte_readCourse != -1) {
					byte_readCourse = in.get(1).read(); // Course는 port가 2번(Vector.get(1))
					// File 끝날 때 까지
					if (byte_readCourse != -1) {
						buffer[idx++] = (byte) byte_readCourse;
					}

					if (byte_readCourse == '\n' && buffer[0] != 13 || byte_readCourse == -1) {
						line = new Line();
						convertString = line.convert(buffer);
						course = new Course(convertString);
						courses.add(course);

						if (byte_readCourse == -1)
							CourseFinish = true;
					}
				}
			}

			buffer = new byte[64];
			idx = 0;
			byte_readCourse = '\0';

			if (CourseFinish) {
				while (byte_readStu != '\n' && byte_readStu != -1) {

					byte_readStu = in.get(0).read();

					if (byte_readStu != -1) {
						buffer[idx++] = (byte) byte_readStu;
					}

					if (byte_readStu == '\n' && buffer[0] != 13) {

						line = new Line();
						convertString = line.convert(buffer);
						student = new Student(convertString);

						Students.add(student);

						// course id 찾아서 선이수 확인
						String courseId = null;

						ArrayList<String> temp = student.getCompletedCoursesList();
						ArrayList<String> courseInfo;

						int stuCourseIdx = 0;
						int stuCourseSize = temp.size();

						// 1번
						for (String stuCourse : temp) {
							if (stuCourseIdx != stuCourseSize - 1) {
								// 2번
								for (Course course : courses) {
									courseId = course.getCourseId();
									// 3번
									if (courseId.equals(stuCourse)) {
										courseInfo = course.getPreLecture();

										ArrayList<String> modifiedStu = new ArrayList<String>();
										ArrayList<String> modifiedCourse = new ArrayList<String>();

										for (int i = 0; i < temp.size() - 1; i++) {
											modifiedStu.add(temp.get(i));
										}

										for (int j = 0; j < courseInfo.size() - 1; j++) {
											modifiedCourse.add(courseInfo.get(j));
										}

										// 4번
										if (modifiedStu.containsAll(modifiedCourse)) {
											isSatisfied = true;
										} else {
											isUnSatisfied = true;
										}
									}
								}
							}
							stuCourseIdx++;
						}

					}
				}

				if (isSatisfied == true && isUnSatisfied == false) {
					for (int i = 0; i < idx; i++) {
						out.get(0).write((char) buffer[i]);
					}
					isSatisfied = false;
					isUnSatisfied = false;
				} else if (isSatisfied == true && isUnSatisfied == true) {
					for (int i = 0; i < idx; i++) {
						out.get(1).write((char) buffer[i]);
					}
					isSatisfied = false;
					isUnSatisfied = false;
				}
			}

			if (byte_readStu == -1) {
				return true;
			}
			buffer = new byte[64];
			idx = 0;
			byte_readStu = '\0';
		}

	}

	@Override
	public void setFileName(String fileName) {

	}
}