import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Sami Jenedi This class reads a student’s name, semester letter grade,
 *         and semester hour attempted from one file; calculates the semester
 *         GPA; and writes the student’s name and semester GPA to another file.
 */

public class GpaCalculation {

  public static void main(String[] args) throws IOException

  {

    Scanner inFile = openFile();

    String[] gradeSummary = processGrades(inFile);

    inFile.close();

    storeGpa(gradeSummary);

    System.out.println("Data processing complete.");

  } // end main

  /**
   * Get student name and grade then calculate the gpa
   * @param inFile
   * @return - a String array containing student name and gpa
   */

  private static String[] processGrades(Scanner inFile) {

    String[] data = new String[2];

    if (inFile.hasNext()) {

      String name = inFile.nextLine();

      data[0] = name;

    }

    int quality_points = 0;

    int total_points = 0;

    double gpa = 0;

    while (inFile.hasNext()) {

      String line = inFile.nextLine();

      String[] fields = line.split(",");

      char grade = fields[0].charAt(0);

      int hours = Integer.parseInt(fields[1]);

      switch (grade) {

      case 'A':

        quality_points += 4 * hours;

        total_points += hours;

        break;

      case 'B':

        quality_points += 3 * hours;

        total_points += hours;

        break;

      case 'C':

        quality_points += 2 * hours;

        total_points += hours;

        break;

      case 'D':

        quality_points += 1 * hours;

        total_points += hours;

        break;

      case 'F':

        quality_points += 0 * hours;

        total_points += hours;

        break;

      }

    }

    gpa = (double) quality_points / total_points;

    data[1] = String.format("%.2f", gpa);

    return data;

  }

  /**
   * method to store the calculated gpa
   * @param gradeSummary 
   * @throws IOException
   */

  static void storeGpa(String[] gradeSummary) throws IOException {

    File outFile = new File(gradeSummary[0]);

    PrintWriter writer = new PrintWriter(outFile);

    writer.append(gradeSummary[0] + "," + gradeSummary[1]);

    writer.close();

  }

  /**
   * method to prompt the user for a file name and check if the file exists,
   * and return a scanner
   * @return - a scanner instance created from the file
   * @throws IOException
   */

  static Scanner openFile() throws IOException {

    Scanner keyboard = new Scanner(System.in);

    System.out.print("Enter grade input file in the form filename.ext: ");

    String fileName = keyboard.nextLine();

    File file = new File(fileName);

    if (!file.exists()) {
      System.out.println("File open error:  " + fileName);
      System.exit(1);
    }

    Scanner fileScanner = new Scanner(file);

    return fileScanner;

  }

}
