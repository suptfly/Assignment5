/*
 * Class: CMSC203 
 * Instructor: Huseyin Aygun
 * Description: This holiday bonus class calculates holiday bonus's for stores based on their sales in their categories.
 * Due: 11/17/2025
 * Platform/compiler: JDK version 21.0.7, Eclipse version 2025-03
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Izzat Ismailov
*/

public class HolidayBonus {
	//not mentioned in java doc but these fields represent the bonus amounts
	private static final int HIGHEST_BONUS = 5000;
	private static final int LOWEST_BONUS = 1000;
	private static final int REGULAR_BONUS = 2000;
	
	public static double[] calculateHolidayBonus(double[][] data) { //calculates/returns the bonuses of each store
		double[] bonuses = new double[data.length]; //array of bonuses of each store with length of how many rows (stores) in the data array
		
		for (int row = 0; row < data.length; row++) { //looping through the data array
			for (int col = 0; col < data[row].length; col++) {
				
				if (data[row][col] > 0) { //not negative, and sold something, then they are eligible for a bonus
					if (TwoDimRaggedArrayUtility.getHighestInColumn(data, col) == data[row][col]) { //if the highest value in the category belongs to this store
						bonuses[row] += HIGHEST_BONUS; //add the highest bonus to this store's bonus
					}
					
					else if (TwoDimRaggedArrayUtility.getLowestInColumn(data, col) == data[row][col]) { //if the lowest value in the category belongs to this store
						bonuses[row] += LOWEST_BONUS; //add the lowest bonus to this store's bonus
					}
					
					else { //normal bonus (not highest or lowest value)
						bonuses[row] += REGULAR_BONUS; //add the normal bonus to this store's bonus
					}
				}
			}
		}
		
		return bonuses; //return the bonuses array
	}
	
	public static double calculateTotalHolidayBonus(double[][] data) { //calculates the total of all the bonuses
		double totalBonus = 0; //total bonus variable
		//same structure as the method above, however, each bonus is added to totalBonus
		
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				
				if (data[row][col] > 0) { //not negative, and sold something
					if (TwoDimRaggedArrayUtility.getHighestInColumn(data, col) == data[row][col]) {
						totalBonus  += HIGHEST_BONUS;
					}
					
					else if (TwoDimRaggedArrayUtility.getLowestInColumn(data, col) == data[row][col]) {
						totalBonus += LOWEST_BONUS;
					}
					
					else {
						totalBonus += REGULAR_BONUS;
					}
				}
			}
		}
		
		return totalBonus; //return the sum of all bonuses
	}
}