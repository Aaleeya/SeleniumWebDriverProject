package test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class TestUtils {
	// Provide test data CSV file path.
		static String CSV_PATH = "./generatedData.csv";
		WebDriver driver;
		private static CSVReader csvReader;
		static String[] csvCell;


		public static ArrayList<Object[]> getDataReadCSV() throws IOException, CsvException {
			// Create an object of CSVReader
			System.out.println("Get data has been called");
			ArrayList<Object[]> data = new ArrayList<Object[]>();
			try {
				//CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
				csvReader = new CSVReaderBuilder(new FileReader(CSV_PATH)).withSkipLines(1).build();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while ((csvCell = csvReader.readNext()) != null) {
					String EntryType = csvCell[0]; 
					String FirstName = csvCell[1];
					String LastName = csvCell[2];
					String AddressLine1 = csvCell[3];
					String City = csvCell[4]; 
					String Province = csvCell[5];
					String Country = csvCell[6];
					String PostalCode = csvCell[7];
					String Phone1Type = csvCell[8];
					String Phone1Number = csvCell[9];
					Object ob[] = {EntryType, FirstName, LastName, AddressLine1, City, Province, Country, PostalCode, Phone1Type, Phone1Number};
					data.add(ob);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*finally {
			      try {
			    	  csvReader.close();
			      } catch (IOException e) {
			        e.printStackTrace();
			      }
			    }*/

			return data;
		}
}
