/*
 * Class: CMSC203 
 * Instructor: Huseyin Aygun
 * Description: This class tests the holiday bonus class
 * Due: 11/17/2025
 * Platform/compiler: JDK version 21.0.7, Eclipse version 2025-03
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Izzat Ismailov
*/

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HolidayBonusTestStudent {
	private double[][] dataSet4 = {{-2.5, -5.3, 6.1},{-4.4, 8.2},{2.3, -7.5},{-4.2, 7.3, -5.9, 2.6}}; //testing set

	@Test
	public void testCalculateHolidayBonus() {
		//testing how much bonus each store gets
		assertEquals(5000.0, HolidayBonus.calculateHolidayBonus(dataSet4)[0]);
		assertEquals(5000.0, HolidayBonus.calculateHolidayBonus(dataSet4)[1]);
		assertEquals(5000.0, HolidayBonus.calculateHolidayBonus(dataSet4)[2]);
		assertEquals(7000.0, HolidayBonus.calculateHolidayBonus(dataSet4)[3]);
	}
	
	@Test
	public void testCalculateTotalHolidayBonus() {
		//total bonus
		assertEquals(22000.0, HolidayBonus.calculateTotalHolidayBonus(dataSet4));		
	}
}