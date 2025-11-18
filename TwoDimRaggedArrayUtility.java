/*
 * Class: CMSC203 
 * Instructor: Huseyin Aygun
 * Description: This Two dim ragged array utility class works with data from districts to calculate the sum of sales for each row, col, etc, and even read/write to files.
 * Due: 11/17/2025
 * Platform/compiler: JDK version 21.0.7, Eclipse version 2025-03
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Izzat Ismailov
*/

import java.util.Scanner;
import java.io.*;

public class TwoDimRaggedArrayUtility {
	//the java doc specifies a constructor but, it's the no arg constructor and there are no instructions for what goes in it
	//so I will just be using the default constructor
	//the java doc doesn't specify any fields
	
	public static double[][] readFile(File file) throws FileNotFoundException { //function for reading the ragged array from a file and copying all its elements to the ragged array used for this program
		final int MAX_ROW = 10; //the maximum rows and cols the ragged array can have
		final int MAX_COLUMN = 10;
		
		String[][] tempArray = new String[MAX_ROW][MAX_COLUMN]; //used temporarily, only for reading from the file initially (dimensions of the array are maxxed)
		
		Scanner inputFile = new Scanner(file); //for reading from the file
		
		int lastNonNullRow = -1; //the last row to be added to the ragged array (last non null row)
		int[] noSpacesLengths = new int[10]; //lengths of the individual rows (without spaces from the file), max 10 rows
	
		for (int row = 0; row < 10; row++) { //looping through each row
			if (inputFile.hasNextLine()) { //if there is a row/data to be read
				String line = inputFile.nextLine(); //this string variable is assigned a string of the whole row
				String[] noSpaces = line.split(" "); //assigns each element separated by the spaces (in the row) to an array element here
				
				noSpacesLengths[row] = noSpaces.length; //the length of the noSpaces array (number of columns in the row) is assigned to an index corresponding to the row in the noSpacesLengths array
				
				for (int col = 0; col < noSpaces.length; col++) { //looping through the row based on the length calculated
					tempArray[row][col] = noSpaces[col]; //assigning the elements/columns of the row to the temp array
				}
				
				lastNonNullRow = row; //lastNonNullRow is equal to this current row (updates each time until loop stops)
			}
			
			else { //there is no more data to be read (no more rows)
				break; //break out 
			}
		}
		
		double[][] actualArray = new double[lastNonNullRow + 1][]; //now this is the ragged double array to return
		//lastNonNullRow is +1 for how many rows there are so no off by one error/lastNonNullRow starts at -1
		
		for (int row = 0; row < actualArray.length; row++) { //looping through the rows of the actualArray given the length provided via lastNonNullRow	+ 1
			actualArray[row] = new double[noSpacesLengths[row]]; //setting the length/# of cols of each row via the corresponding length stored in noSpacesLengths 
			
			for (int col = 0; col < actualArray[row].length; col++) { //looping through the row now but through each element/col			
				if (tempArray[row][col] != null) { //precaution (if the original string temp array is not null at this index)
					actualArray[row][col] = Double.parseDouble(tempArray[row][col]); //assign the double parsed version of the element in the temp array to the actual array
				}
				
				else { //if no more values in these cols (null detected)
					break; //break out and go to next row
				}
 			}
		}
		
		inputFile.close(); //close 

		return actualArray; //return the ragged array of doubles
	}
	
	public static void writeToFile(double[][] data, File outputFile) throws FileNotFoundException { //function for writing ragged array to a file
		PrintWriter newFile = new PrintWriter(outputFile); //new PrintWriter object for writing
		
		for (int row = 0; row < data.length; row++) { //looping through array
			for (int col = 0; col < data[row].length; col++) {
				
				if (col != data[row].length - 1) { //if we're not at the last column entry, where adding a space after is pointless
					newFile.print(data[row][col] + " "); //print the specified elements separated by a space to the file, on the same line
				}
				
				else { //if we're at the last entry of the row
					newFile.print(data[row][col]); //print the specified element (no space at the end, it's the last entry for the row)
				}
			}
			
			if (row != data.length - 1) { //only add the newline if we're not at the last row (adding the newline would be pointless)
				newFile.println(); //add a new line after the row
			}
		}
		
		newFile.close(); //close print writer object
	}

	public static double getTotal(double[][] data) { //function to get total of all elements
		double total = 0; //accumulator
		
		for (int row = 0; row < data.length; row++) { //looping through the ragged array
			for (int col = 0; col < data[row].length; col++) {
				total += data[row][col]; //adding the elements
			}
		}
		
		return total; //return total
	}
	
	public static double getAverage(double[][] data) { //function to get average of elements
		double total = 0; //accumulator
		int numElements = 0; //number of elements in the ragged array
		
		for (int row = 0; row < data.length; row++) { //looping through the ragged array
			for (int col = 0; col < data[row].length; col++) {
				total += data[row][col]; //adding
				numElements++; //increasing num elements by one each time
			}
		}
		
		return (total / numElements); //return the average of all elements
	}
	
	public static double getRowTotal(double[][] data, int row) { //function to get sum of a row
		double total = 0; //accumulator
		
		for (int col = 0; col < data[row].length; col++) { //looping through the given row
			total += data[row][col]; //adding all the elements in the row together
		}
		
		return total; //returning row sum
	}
	
	public static double getColumnTotal(double[][] data, int col) { //function to get sum of a column
		double total = 0; //accumulator
		
		for (int row = 0; row < data.length; row++) { //looping through the given column
			if (data[row].length > col) { //*important, this checks if there exists a value at the specific row index for the given column
				//I am checking if the row's length is > than the column index, assuring a value is there in that row for the given column
				total += data[row][col]; //adding the elements that exist in the given column
			}
		}
		
		return total; //returning column sum
	}
	
	public static double getHighestInRow(double[][] data, int row) { //function to get the highest number in a given row
		double highest = data[row][0]; //setting highest to the first column of the given row
		
		for (int col = 1; col < data[row].length; col++) { //looping through the row, starting with the second column
			if (data[row][col] > highest) { //if this column's value is greater than the highest variable
				highest = data[row][col]; //reassign highest
			}
		}
		
		return highest; //return highest in row
	}
	
	public static int getHighestInRowIndex(double[][] data, int row) { //function to get the highest number's index in a given row
		double highest = data[row][0]; //setting highest to the first column of the given row
		int highestIndex = 0; //setting highest index to the first column of the row
		 
		for (int col = 1; col < data[row].length; col++) { //looping through the row, starting with second col
			if (data[row][col] > highest) { //checking
				highest = data[row][col]; //reassign highest
				highestIndex = col; //reassign highest index 
			}
		}
		
		return highestIndex; //return index of the greatest number in the row
	}
	
	public static double getLowestInRow(double[][] data, int row) { //function to get lowest element in the row
		double lowest = data[row][0]; //seting lowest to the first col of the row
		
		for (int col = 1; col < data[row].length; col++) { //looping through row 
			if (data[row][col] < lowest) { //if this value is less than lowest
				lowest = data[row][col]; //reassign lowest
			}
		}
		
		return lowest; //return lowest value in row
	}
	
	public static int getLowestInRowIndex(double[][] data, int row) { //function to get index of lowest element in the row
		double lowest = data[row][0]; //setting lowest
		int lowestIndex = 0; //lowest index = first col
		
		for (int col = 1; col < data[row].length; col++) { //looping through row
			if (data[row][col] < lowest) { //checking
				lowest = data[row][col]; //reassign lowest
				lowestIndex = col; //reassign lowest index
			}
		}
		
		return lowestIndex; //return lowest index
	}
	
	public static double getHighestInColumn(double[][] data, int col) { //function to get greatest element in a column
		double highest = 0; //assigning 0 to highest variable initially
		
		for (int row = 0; row < data.length; row++) { //looping through col
			if (data[row].length > col) { //if the row has a value at the column
				highest = data[row][col]; //first occurence of the value is assigned to highest
				break; //break out of the loop
			}
			
		}
		
		for (int row = 0; row < data.length; row++) { //looping through col
			if (data[row].length > col) { //if the row has a value at the col
				if (data[row][col] > highest) { //if the value at that row is greater than highest
					highest = data[row][col]; //reassign highest
				}
			}
		}
		
		return highest; //return highest in col
	}
	
	public static int getHighestInColumnIndex(double[][] data, int col) { //function to get index of greatest element in a col
		double highest = 0; //assigning 0 to highest variable
		int highestIndex = 0; //highest index 0 initially
		
		for (int row = 0; row < data.length; row++) { //looping through col
			if (data[row].length > col) { //if the row has value at the col
				highest = data[row][col]; //first occurrence of the value assigned
				highestIndex = row;
				break; //break out 
			}
			
		}
		
		for (int row = 0; row < data.length; row++) { //looping through col
			if (data[row].length > col) { //if value exists
				if (data[row][col] > highest) { //if value greater than highest
					highest = data[row][col]; //reassign highest
					highestIndex = row; //reassign highest index
				}
			}
		}
		
		return highestIndex; //return highestIndex
	}
	
	public static double getLowestInColumn(double[][] data, int col) { //function to get lowest element of the col
		double lowest = 0; //lowest is 0 initially
		
		for (int row = 0; row < data.length; row++) { //looping through col
			if (data[row].length > col) { //if value exists at that row for the column
				lowest = data[row][col]; //first occurrence of the value assigned to lowest
				break; //break out
			}
			
		}
		
		for (int row = 0; row < data.length; row++) { //looping through col
			if (data[row].length > col) { //if value exists
				if (data[row][col] < lowest) { //if value is less than lowest
					lowest = data[row][col]; //reassign lowest
				}
			}
		}
		
		return lowest; //return lowest in col
	}
	
	public static int getLowestInColumnIndex(double[][] data, int col) { //function to get lowest element's index of the col
		double lowest = 0; //lowest is 0 initially
		int lowestIndex = 0; //lowest index is 0 (first row)
		
		for (int row = 0; row < data.length; row++) { //looping through col
			if (data[row].length > col) { //if value exists for the col at the row
				lowest = data[row][col]; //first occurrence of the existing value assigned to lowest
				lowestIndex = row;
				break; //break out
			}
		}
		
		for (int row = 0; row < data.length; row++) { //looping through col
			if (data[row].length > col) { //if value exists
				if (data[row][col] < lowest) { //if value is less than lowest
					lowest = data[row][col]; //reassign lowest
					lowestIndex = row; //reassign lowest index
				}
			}
		}
		
		return lowestIndex; //return lowest index
	}
	
	public static double getHighestInArray(double[][] data) { //function to get highest element in the array
		double highest = data[0][0]; //highest is initially first row and first column
		
		for (int row = 0; row < data.length; row++) { //looping through array
			for (int col = 0; col < data[row].length; col++) {
				if (data[row][col] > highest) { //if element is greater than highest
					highest = data[row][col]; //reassign highest
				}
			}
		}
		
		return highest; //return highest element/value in the array 
	}
	

	public static double getLowestInArray(double[][] data) { //function to get lowest element in the array 
		double lowest = data[0][0]; //lowest is initially first row and first col
	
		for (int row = 0; row < data.length; row++) { //looping through array
			for (int col = 0; col < data[row].length; col++) {
				if (data[row][col] < lowest) { //if element is less than lowest
					lowest = data[row][col]; //reassign lowest
				}
			}
		}
		
		return lowest; //return lowest value in the array
	}
}