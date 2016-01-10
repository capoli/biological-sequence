package be.kuleuven.OOP;

import java.io.File;

import be.kuleuven.OOP.exceptions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class SequenceFileParser {
    /**
     * Method to read a biological sequence provided in FASTA format
     *
     * @param fileName
     * @return BiologicalSequence
     * @throws IllegalParseException The given sequence is not a valid sequence
     */
    private static BiologicalSequence getBiologicalSequence(Alphabet alphabet, String fileName) throws IllegalParseException, IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException {
        File file = new File(String.format("resources/%s.txt", fileName));

        StringBuilder dnaString = new StringBuilder();
        String organism = null;
        StringBuilder sequenceId = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith(">")) {
                    line = line.substring(1);
                    String[] descriptionArray = line.split("\\|");
                    for (String des1 : descriptionArray) {
                        System.out.println(des1);
                    }

                    if (descriptionArray.length == 0)
                        throw new IllegalParseException("There is no sequence id present");
                    organism = descriptionArray[descriptionArray.length - 1];
                    sequenceId = new StringBuilder();

                    for (int i = 0; i < descriptionArray.length - 1; i++) {
                        sequenceId.append(String.format("%s%s",
                                descriptionArray[i],
                                (i < descriptionArray.length - 2 ? "-" : "")
                        ));
                    }
                } else {
                    dnaString.append(line);
                }
            }
            return new BiologicalSequence(sequenceId.toString(), organism, dnaString.toString(), alphabet);
        } catch (FileNotFoundException e) {
            throw new IllegalParseException("File was not found");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalParseException("Array out of bound");
        }
    }

    public static BiologicalSequence getDnaSequence(String filename) throws IllegalParseException, IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException {
        return getBiologicalSequence(new DnaAlphabet(), filename);
    }

    public static BiologicalSequence getRnaSequence(String filename) throws IllegalParseException, IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException {
        return getBiologicalSequence(new RnaAlphabet(), filename);
    }
}
