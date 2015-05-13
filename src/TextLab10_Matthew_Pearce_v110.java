import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TextLab10_Matthew_Pearce_v110 {

	public static void main(String args[]) throws IOException {
		System.out.println("\nTextLab10\n");

		ArrayList<Person> students = new ArrayList<Person>();

		DecimalFormat df = new DecimalFormat("00.000");
		File f1 = new File("students.dat");
		FileWriter f2 = new FileWriter("passing.txt");
		FileWriter f3 = new FileWriter("honors.txt");
		// f2.delete();
		// f3.delete();

		if (f1.exists()) {
			FileReader inFile = new FileReader(f1);
			BufferedReader inStream = new BufferedReader(inFile);
			createArray(inStream, students);

		} else {
			System.out.println(f1.getName() + " does not exist");
		}
		System.out.println();

		displayArray(students);
		System.out.println("\n\nThe number of records is: "
				+ countRecords(students));
		System.out.println("\n\nThe mean gpa is: " + meanGpa(students, df));
		System.out.println("\n\nThe mean age is: " + meanAge(students, df));

		processRankings(students, 2.0, f2);
		processRankings(students, 3.75, f3);

	}

	public static void createArray(BufferedReader file, ArrayList<Person> array)
			throws IOException {

		String name = file.readLine();
		double gpa = 0;
		int age = 0;

		while (name != null) {

			age = Integer.parseInt(file.readLine());
			gpa = Double.parseDouble(file.readLine());

			array.add(new Person(name, age, gpa));
			name = file.readLine();
		}

		file.close();
	}

	public static void displayArray(ArrayList<Person> array) {
		System.out.println(array);
	}

	public static int countRecords(ArrayList<Person> array) {
		return array.size();
	}

	public static String meanGpa(ArrayList<Person> array, DecimalFormat df) {
		double sum = 0;
		for (Person person : array) {
			sum += person.getGpa();
		}

		return df.format(sum / array.size());
	}

	public static String meanAge(ArrayList<Person> array, DecimalFormat df) {
		double sum = 0;
		for (Person person : array) {
			sum += person.getAge();
		}

		return df.format(sum / array.size());
	}

	public static void processRankings(ArrayList<Person> array, double minGpa,
			FileWriter file) throws IOException {

		BufferedWriter passingFile = new BufferedWriter(file);
		for (Person person : array) {
			if (person.getGpa() >= minGpa) {
				passingFile.write("\nName: " + person.getName());
				passingFile.write("\nAge: " + person.getAge());
				passingFile.write("\nGpa: " + person.getGpa() + "\n\n");
			}
		}
		passingFile.close();
		System.out.println("\n\nAll students with a gpa great than or equal to " + minGpa + " have been placed in a seperate text file.");
	}
	
}

class Person {
	public String name;
	public int id;
	public int age;
	public double gpa;

	Person(String n, int a, double g) {
		name = n;
		age = a;
		gpa = g;
	}

	public double getGpa() {
		return gpa;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;

	}

	public String toString() {
		return "\n\nName: " + name + "\nAge: " + age + "\nGpa: " + gpa;

	}
}
