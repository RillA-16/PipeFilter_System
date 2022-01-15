/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Middle.MiddleFilter;
import Components.Sink.SatisfiedSinkFilter;
import Components.Sink.UnSatisfiedSinkFilter;
import Components.Source.CourseSourceFilter;
import Components.Source.StudentSourceFilter;

public class LifeCycleManager {
	public static void main(String[] args) {

		try {
			// portNum °¹¼ö À¸·Î °´Ã¼ »ý¼º
			CommonFilter studentSourceFilter = new StudentSourceFilter(1, 1);
			CommonFilter satisfiedSinkFilter = new SatisfiedSinkFilter(1, 1);

			CommonFilter courseSourceFilter = new CourseSourceFilter(1, 1);
			CommonFilter unSatisfiedSinkFilter = new UnSatisfiedSinkFilter(1, 1);

			CommonFilter middleFilter = new MiddleFilter(2, 2);

			// fileName setting
			studentSourceFilter.setFileName("Students.txt");
			courseSourceFilter.setFileName("Courses.txt");
			satisfiedSinkFilter.setFileName("Output1.txt");
			unSatisfiedSinkFilter.setFileName("Output2.txt");

			// student -> middle portNum = 0;
			studentSourceFilter.connectOutputTo(0, middleFilter, 0);
			// course -> middle portNum = 1;
			courseSourceFilter.connectOutputTo(0, middleFilter, 1);

			middleFilter.connectOutputTo(0, satisfiedSinkFilter, 0);
			middleFilter.connectOutputTo(1, unSatisfiedSinkFilter, 0);

			Thread studentSourceThread = new Thread(studentSourceFilter);
			Thread satisfiedSinkThread = new Thread(satisfiedSinkFilter);
			Thread courseSourceThread = new Thread(courseSourceFilter);
			Thread unSatisfiedSinkThread = new Thread(unSatisfiedSinkFilter);
			Thread middleThread = new Thread(middleFilter);

			studentSourceThread.start();
			satisfiedSinkThread.start();
			middleThread.start();
			courseSourceThread.start();
			unSatisfiedSinkThread.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
