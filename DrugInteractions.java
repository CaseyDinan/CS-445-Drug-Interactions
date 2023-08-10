import java.util.*;
import java.io.*;

public class DrugInteractions
{
	public static void main( String[] args ) throws Exception
	{
		BufferedReader foodDrug2CategoryFile = new BufferedReader( new FileReader( "foodDrug2Category.txt" ) );
		TreeMap<String,TreeSet<String>> foodDrug2Category = new TreeMap<String,TreeSet<String>>();
		TreeSet<String> allCategories = new TreeSet<String>();
		while (foodDrug2CategoryFile.ready()) {
			String[] tokens = foodDrug2CategoryFile.readLine().split(",");
			String category = tokens[0];
			allCategories.add(category);
			TreeSet<String> fd = new TreeSet<String>();
			for (int i = 1; i < tokens.length; i++) {
				fd.add(tokens[i]);
			}
			foodDrug2Category.put(category, fd);
		}
		for (String categories : allCategories) {
			System.out.print(categories + " ");
			TreeSet<String> fd = foodDrug2Category.get(categories);
			for (String foods : fd) {
				System.out.print(foods + " ");
			}
			System.out.println();
		}
		System.out.println();
		BufferedReader patient2FoodDrugFile = new BufferedReader( new FileReader( "patient2FoodDrug.txt") );
		TreeMap<String,TreeSet<String>> patient2FoodDrug = new TreeMap<String,TreeSet<String>>();
		TreeSet<String> allPatients = new TreeSet<String>();
		while (patient2FoodDrugFile.ready()) {
			String[] tokens = patient2FoodDrugFile.readLine().split(",");
			String patient = tokens[0];
			allPatients.add(patient);
			TreeSet<String> fd = new TreeSet<String>();
			for (int i = 1; i < tokens.length; i++) {
				fd.add(tokens[i]);
			}
			patient2FoodDrug.put(patient, fd);
		}
		for (String patients : allPatients) {
			System.out.print(patients + " ");
			TreeSet<String> fd = patient2FoodDrug.get(patients);
			for (String foods : fd) {
				System.out.print(foods + " ");
			}
			System.out.println();
		}
		System.out.println();
		TreeMap<String,TreeSet<String>> patientCategories = new TreeMap<String,TreeSet<String>>();
		for (String patients : allPatients) {
			TreeSet<String> patientDrugs = patient2FoodDrug.get(patients);
			TreeSet<String> catgor = new TreeSet<String>();
			for (String drugs : patientDrugs) {
			for (String categories : allCategories) {
				TreeSet<String> cat = foodDrug2Category.get(categories);
				if (cat.contains(drugs)) {
					catgor.add(categories);
				}
			}
			patientCategories.put(patients, catgor);
			}
		}
		BufferedReader dontMixFile = new BufferedReader( new FileReader( "dontMix.txt" ) );
		//System.out.println(patients);
		/*for (String mix : dontMix) {
			System.out.println(mix);
		} */
		while (dontMixFile.ready()) {
			String[] tokens = dontMixFile.readLine().split(",");
			for(String patients : allPatients) {
			TreeSet<String> dontMix = patientCategories.get(patients);
			if (dontMix.contains(tokens[0]) && dontMix.contains(tokens[1])) {
				System.out.println(patients);
			}	
			}
		}
	} // END MAIN

} // END CLASS