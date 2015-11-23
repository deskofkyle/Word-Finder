 /*
	Kyle Ryan
	CSC 172
	November 14, 2015
*/

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

class Tuple { 
  public int x; 
  public int y; 
  
  public Tuple(int x, int y) { 
    this.x = x; 
    this.y = y; 
  }

} 

class Dictionary {
	public hashtable table;

	 public Dictionary(String dictionaryFile) {
	 	loadTheDictionary(dictionaryFile);
	 }

	 public void loadTheDictionary(String dictionaryFile) {
	 	String nameOfFile = dictionaryFile;	
        String lineString = "";

        table = new hashtable(354997);

		try {
			FileReader fileReader = new FileReader(nameOfFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((lineString = bufferedReader.readLine()) != null) {
				table.insert(lineString);
			}

			bufferedReader.close();         

		} catch (FileNotFoundException e) {
			System.out.println("Unable to open the file");
		}

		catch(IOException e) {
			System.out.println("Unable to open the file");
		}
	 }
}

class TimestepObject {
		public int length;
		public int direction;
		public Tuple position;
		public int width;
		public int height;
		public boolean shouldIterate;

		public TimestepObject(int puzzleWidth, int puzzleHeight) {
			length = 0;
			direction = -1;
			position = new Tuple(0,0);
			width = puzzleWidth;
			height = puzzleHeight;

			if (width != height) {
				System.out.println("Error. The array you gave is not N x N");
				System.exit(0);
			}

			shouldIterate = true;
		}

		public void incrementTimeStep() {

			/*
				[length, direction, (x,y)]
			*/

			/*
				Length is not correct
			*/

			if (direction <= 7) {

				direction++;

				if (direction == 0) {
					length = position.y + 1;
				}

				if (direction == 1) {
					length = position.y + 1;
				}

				if (direction == 2) {
					length = width - position.x - 1;
				}

				if (direction == 3) {
					length = width - position.x - 1;
				}

				if (direction == 4) {
					length = height - position.y - 1;
				}

				if (direction == 5) {				
					length = position.x + 1;
				}
				
				if (direction == 6) {
					length = position.x + 1;
				}

				if (direction == 7) {
					length = position.y + 1;
				}

			} else {
				direction = 0;

				if (position.x < width - 1) {
					position.x++;
				} else {
					position.x = 0;
					
					if (position.y < height - 1) {
						position.y++;
					}
				}

				if (position.x == width - 1 && position.y == height - 1) {
					shouldIterate = false;
					System.out.println("Puzzle completed");
				}
			}

			printTimeStatus();

		}

		public void printTimeStatus(){
			System.out.format("[%d, %d, (%d, %d)]\n", length, direction, position.x, position.y);
		}
	} 

public class project3 {

	private static TimestepObject timestep;
	private static char [][] wordTable;
	private static WordGrid wordGridGUI;

	public static void main (String [] args) {

		int gridSize = 10;

		/*
			Do some code to make sure that the width and height of the inputted text file's char table is equal.
		*/

        String inputFile = args[1];	
        String lineString = "";

        String outputFileName = args[2];

        timestep = new TimestepObject(gridSize, gridSize);
		wordTable = new char [gridSize][gridSize];

		String dictionaryFile = args[0];
		Dictionary dict = new Dictionary(dictionaryFile);
        
		try {
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int count = -1;

			while((lineString = bufferedReader.readLine()) != null) {

				count++;
				
				for (int i = 0; i < lineString.length(); i++) {
					wordTable[count][i] = lineString.charAt(i);
				}
			}

			bufferedReader.close();         

		} catch (FileNotFoundException e) {
			System.out.println("Unable to open the file");
		}

		catch(IOException e) {
			System.out.println("Unable to open the file");
		}

		wordGridGUI = new WordGrid(gridSize, wordTable);

		SolutionList list = new SolutionList();

        while (timestep.shouldIterate) {
        	String currentGuess = "";

        	for (int i = timestep.length; i >= 0; i--) {
	        		if (timestep.direction == 0) {

	        		try {
	         			currentGuess += wordTable[timestep.position.x][timestep.position.y - i];
	      			} catch (ArrayIndexOutOfBoundsException e) {
 	      			} catch (ArithmeticException e) {
	         			 
	         		}

        		}

        		if (timestep.direction == 1) {
	        		try {
	         			currentGuess += wordTable[timestep.position.x + i][timestep.position.y + i];
	      			} catch (ArrayIndexOutOfBoundsException e) {
 	      			} catch (ArithmeticException e) {
	         			 
	         		}
	       		}

        		if (timestep.direction == 2) {
        			try {
	         			currentGuess += wordTable[timestep.position.x + i][timestep.position.y];
	      			} catch (ArrayIndexOutOfBoundsException e) {
 	      			} catch (ArithmeticException e) {
	         			 
	         		}
        		}

        		if (timestep.direction == 3) {
        			try {
	         			currentGuess += wordTable[timestep.position.x + i][timestep.position.y + i];
	      			} catch (ArrayIndexOutOfBoundsException e) {
 	      			} catch (ArithmeticException e) {
	         			 
	         		}
        		}

        		if (timestep.direction == 4) {
        			try {
	         			currentGuess += wordTable[timestep.position.x][timestep.position.y + i];
	      			} catch (ArrayIndexOutOfBoundsException e) {
 	      			} catch (ArithmeticException e) {
 	         		}
        		}

        		if (timestep.direction == 5) {
        			try {
	         			currentGuess += wordTable[timestep.position.x - i][timestep.position.y + i];
	      			} catch (ArrayIndexOutOfBoundsException e) {
 	      			} catch (ArithmeticException e) {
	         			 
	         		}
        		}

        		if (timestep.direction == 6) {
        			try {
	         			currentGuess += wordTable[timestep.position.x - i][timestep.position.y];
	      			} catch (ArrayIndexOutOfBoundsException e) {
 	      			} catch (ArithmeticException e) {
	         			 
	         		}
        		}

        		if (timestep.direction == 7) {
        			try {
	         			currentGuess += wordTable[timestep.position.x - i][timestep.position.y + i];
	      			} catch (ArrayIndexOutOfBoundsException e) {
 	      			} catch (ArithmeticException e) {
	         			 
	         		}
        		}

        	}

        	// check if current guess if in the hashtable

        	if (dict.table.find(currentGuess) == true) {
        		if (currentGuess.length() > 0) {
        			list.insertUniqueObject(currentGuess);
        		}
        	}

            timestep.incrementTimeStep();
 
        }

        list.printSolutions();


	    	try {
				PrintWriter out = new PrintWriter(new FileWriter(outputFileName));
				for (int i = 0; i < list.elements.size(); i++) {
					out.println(list.elements.get(i));
				}

				out.close();
			}    

			catch(IOException e) {
				System.out.println("Unable to open the file");
			}
	}
}

class SolutionList {
	
	public ArrayList<String> elements;

	public SolutionList() {
		elements = new ArrayList<String>();
	}

	public void insertUniqueObject(String element) {

		if(!elements.contains(element)) {
    		elements.add(element);

    		System.out.println("Found word:" + element);

    	}
	}

	public void printSolutions() {
		for (int i = 0; i < elements.size(); i++) {
			for (String s : elements)
    			System.out.println(s);
		}

	}
}

class hashtable { // class for the hash table

	private int sizeOfArray; // method for the actual size of the items in the array
	private String [] hashArray; // array for the hash table
	private String [] newHashArray; // temporary array used in re-hashing. 
	int maxCap;

	public hashtable(int initialSize) { // contructor method
		hashArray = new String[initialSize]; // inits the hash table array
		maxCap = initialSize;
	}

	public int hashFunc(String item, int size) { // "Good" hash function from Chapter 5 pages 174
		int hashVal = 0;

		for (int i = 0; i < item.length(); i++) {
			hashVal = 37 * hashVal + item.charAt(i);	
		}

		hashVal %= maxCap;

		if (hashVal < 0) {
			hashVal += maxCap;
		}

		return hashVal;
	}

	public void insert(String item) { // inserts a string into the hash table
		if (sizeOfArray > returnLoadFactor()) { 
			String [] newHashArray = new String[hashArray.length * 2];

			maxCap = maxCap * 2;

			for (int i = 0; i  < hashArray.length; i++) {
				if (hashArray[i] != null) {
					newHashArray[hashFunc(hashArray[i], maxCap)] = hashArray[i];
				}
			}

			hashArray = newHashArray;
		}

		/*
			Insertion part of the method
		*/

		int hashedValue = hashFunc(item, maxCap);

		if (hashArray[hashedValue] == null) {
			hashArray[hashedValue] = item;
			sizeOfArray++;
		} else {
			probe(hashedValue, item);
		}
	}

	public void probe(int index, String item) {

		for (int i = index; i < maxCap; i++) {
			if (hashArray[i] == null) {
				hashArray[i] = item;
				sizeOfArray++;
				return;
			}
		}

		probe(0, item);
	}

	public boolean find (String word) {
		int hashedValue = hashFunc(word, maxCap);
		return find(hashedValue, word);
	}

	public boolean find(int index, String word) {

		for (int i = index; i < maxCap; i++) {
			if (hashArray[i] == null) {
				return false;
			} 

			if (hashArray[i].equals(word)) {
				return true;
			}
		}

		return find(0, word);
	}

	public void print() { // print method
		System.out.println("Here's the hash table:");

		for (int i = 0; i < sizeOfArray; i++) {
			if (hashArray[i] != null) {
				System.out.println(hashArray[i] + " ");
			} else {
				System.out.println("Nothing in the hash table");
			}
		}

		System.out.println("\n");
	}

	public int getCapacity() { // find capacity of the array
		return hashArray.length;
	}

	public int numberOfUniqueItems() { // returns the amount of unique items
		return sizeOfArray;
	}

	public int returnLoadFactor() { // returns a number that tells when the array is at 50% capacity. 
		return (int) (hashArray.length + 0.0) / 2;
	}
}

class WordGrid {
	public WordGrid(int size, char [][] wordTable) {
        JFrame frame = new JFrame ();
        frame.setLayout (new BorderLayout ());
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    	frame.setPreferredSize(new Dimension(500, 500));

        JPanel northPanel = new JPanel (new GridLayout (2, 1));

        JPanel namePanel = new JPanel (new FlowLayout (FlowLayout.CENTER));      
        northPanel.add (new JLabel ("Kyle Ryan's Word Solver"));

        northPanel.add (namePanel);

        JPanel mainPanel = new JPanel (new FlowLayout (FlowLayout.CENTER));
        northPanel.add (mainPanel);

        JPanel middlePanel = new JPanel (new GridLayout (size, size));

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                JLabel jlabel = new JLabel(String.valueOf(wordTable[i][j]), SwingConstants.CENTER);
                jlabel.setForeground(Color.black);
    			middlePanel.add(jlabel);
            }
        }

        frame.add (northPanel, BorderLayout.NORTH);
        frame.add (middlePanel, BorderLayout.CENTER);

        frame.pack ();
        frame.setVisible (true);
    }

    public void updateUI() {
    	System.out.println("updateUI");
    }

}