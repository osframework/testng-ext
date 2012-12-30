/*
 * File: Assert.java
 * 
 * Copyright 2012 OSFramework Project.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.osframework.testng;

import static org.testng.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;


/**
 * Assertion tool class. Presents assertion methods for use in addition to
 * methods provided by {@linkplain org.testng.Assert}.
 *
 * @author <a href="mailto:dave@osframework.org">Dave Joyce</a>
 */
public class Assert {

	protected Assert() {}

	/**
	 * Asserts that two dates fall on same day. If they do not, an
	 * AssertionError, with the given message, is thrown.
	 * 
	 * @param actual the actual value
	 * @param expected the expected value
	 * @param message the assertion error message
	 */
	public static void assertSameDay(Calendar actual, Calendar expected, String message) {
		Calendar actualMidnight = atMidnight(actual);
		Calendar expectedMidnight = atMidnight(expected);
		assertEquals(actualMidnight.get(Calendar.YEAR), expectedMidnight.get(Calendar.YEAR), ("<YEAR> " + message));
		assertEquals(actualMidnight.get(Calendar.MONTH), expectedMidnight.get(Calendar.MONTH), ("<MONTH> " + message));
		assertEquals(actualMidnight.get(Calendar.DAY_OF_MONTH), expectedMidnight.get(Calendar.DAY_OF_MONTH), ("<DAY_OF_MONTH> " + message));
	}

	/**
	 * Asserts that two dates fall on same day. If they do not, an
	 * AssertionError is thrown.
	 * 
	 * @param actual the actual value
	 * @param expected the expected value
	 */
	public static void assertSameDay(Calendar actual, Calendar expected) {
		assertSameDay(actual, expected, null);
	}

	/**
	 * Asserts that two dates fall on same day. If they do not, an
	 * AssertionError, with the given message, is thrown.
	 * 
	 * @param actual the actual value
	 * @param expected the expected value
	 * @param message the assertion error message
	 */
	public static void assertSameDay(Date actual, Date expected, String message) {
		Calendar actualCal = Calendar.getInstance(), expectedCal = Calendar.getInstance();
		actualCal.setTime(actual);
		expectedCal.setTime(expected);
		assertSameDay(actualCal, expectedCal, message);
	}

	/**
	 * Asserts that two dates fall on same day. If they do not, an
	 * AssertionError is thrown.
	 * 
	 * @param actual the actual value
	 * @param expected the expected value
	 */
	public static void assertSameDay(Date actual, Date expected) {
		assertSameDay(actual, expected, null);
	}

	private static Calendar atMidnight(final Object raw) {
		long rawMillis;
		if (raw instanceof Date) {
			rawMillis = ((Date)raw).getTime();
		} else if (raw instanceof Calendar) {
			rawMillis = ((Calendar)raw).getTimeInMillis();
		} else {
			throw new IllegalArgumentException("Argument class is not assignable to Date or Calendar");
		}
		Calendar midnight = Calendar.getInstance();
		midnight.setTimeInMillis(rawMillis);
		midnight.set(Calendar.HOUR_OF_DAY, 0);
		midnight.set(Calendar.HOUR, 0);
		midnight.set(Calendar.MINUTE, 0);
		midnight.set(Calendar.SECOND, 0);
		midnight.set(Calendar.MILLISECOND, 0);
		return midnight;
	}

}
