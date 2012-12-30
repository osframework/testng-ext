package org.osframework.testng;

import static org.osframework.testng.Assert.*;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class AssertTest {

	@Test(dataProvider = "dates")
	public void testAssertSameDayDate(Date d1, Date d2) {
		assertSameDay(d1, d2);
	}

	@Test(dataProvider = "dates")
	public void testAssertSameDayCalendar(Calendar c1, Calendar c2) {
		assertSameDay(c1, c2);
	}

	@DataProvider
	public Object[][] dates(Method method) {
		Calendar c1, c2, c3, c4;
		
		c1 = Calendar.getInstance();
		c1.set(Calendar.HOUR_OF_DAY, 23);
		c1.set(Calendar.MINUTE, 59);
		c1.set(Calendar.SECOND, 59);
		c1.set(Calendar.MILLISECOND, 999);
		
		c2 = (Calendar)c1.clone();
		c2.set(Calendar.HOUR_OF_DAY, 20);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);
		
		c3 = Calendar.getInstance();
		c3.add(Calendar.DAY_OF_MONTH, -1);
		c4 = (Calendar)c3.clone();
		
		Object[] set1 = null, set2 = null;
		if ("testAssertSameDayDate".equals(method.getName())) {
			set1 = new Object[] { c1.getTime(), c2.getTime() };
			set2 = new Object[] { c3.getTime(), c4.getTime() };
		} else if ("testAssertSameDayCalendar".equals(method.getName())) {
			set1 = new Object[] { c1, c2 };
			set2 = new Object[] { c3, c4 };
		}
		
		return new Object[][] {
			set1, set2,
		};
	}

}
