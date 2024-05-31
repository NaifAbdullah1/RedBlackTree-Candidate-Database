package rbt;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * This class contains JUnit tests as it is mainly used for testing the
 * RedBlackTree class
 *
 * @author Naif Abdullah
 */
public class TestRedBlackTree {

    //The @Test annotation allows JUnit to recognize its following method as a test method
    @Test
    public void testInsertCandidates() {
        RedBlackTree<Candidate> candidateRBT = new RedBlackTree<>();

        // Taking in student info from the csv file 
        String candidatesCSVPath = "./src/main/resources/candidate-info.csv";
        List<Candidate> candidates = readCandidatesFromCSV(candidatesCSVPath);

        for (Candidate candidate : candidates) {
            candidateRBT.insert(candidate);
        }

        // Create candidate objects to see if they already exist
        Candidate candidate1 = new Candidate(0, "Kiana Lor", "China", "Suzhou", 31.31, 120.62, 'F', 22, 3.5, 3.7, 3.1, 1, 4, 4, 4);
        Candidate candidate2 = new Candidate(1, "Joshua Lonaker", "United States of America", "Santa Clarita", 34.39, -118.54, 'M', 22, 2.9, 3.2, 3.6, 5, 5, 4, 5);
        Candidate candidate3 = new Candidate(2, "Dakota Blanco", "United States of America", "Oakland", 37.8, -122.27, 'F', 22, 3.9, 3.8, 3.2, 5, 3, 3, 4);
        Candidate candidate4 = new Candidate(3, "Natasha Yarusso", "United States of America", "Castro Valley", 37.69, -122.09, 'F', 20, 3.3, 2.8, 3.2, 5, 5, 2, 4);

        // Assert that the tree contains the inserted candidates
        assertTrue(candidateRBT.contains(candidate1));
        assertTrue(candidateRBT.contains(candidate2));
        assertTrue(candidateRBT.contains(candidate3));
        assertTrue(candidateRBT.contains(candidate4));
    }

    public static List<Candidate> readCandidatesFromCSV(String filePath) {
        List<Candidate> candidates = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Read the header line and discard it
            reader.readNext();
            String[] csvLine;
            while ((csvLine = reader.readNext()) != null) {
                Candidate candidate = createCandidateFromCSV(csvLine);
                candidates.add(candidate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    private static Candidate createCandidateFromCSV(String[] csvLine) {
        return new Candidate(
                Integer.parseInt(csvLine[0]),
                csvLine[1],
                csvLine[2],
                csvLine[3],
                Double.parseDouble(csvLine[4]),
                Double.parseDouble(csvLine[5]),
                csvLine[6].charAt(0),
                Integer.parseInt(csvLine[7]),
                Double.parseDouble(csvLine[8]),
                Double.parseDouble(csvLine[9]),
                Double.parseDouble(csvLine[10]),
                Double.parseDouble(csvLine[11]),
                Integer.parseInt(csvLine[12]),
                Integer.parseInt(csvLine[13]),
                Integer.parseInt(csvLine[14])
        );
    }
}
