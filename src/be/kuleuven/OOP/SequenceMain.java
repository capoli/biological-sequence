package be.kuleuven.OOP;

import java.io.File;

import be.kuleuven.OOP.exceptions.*;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.FileNotFoundException;
import java.util.*;

//TODO: Please check the completeness of the main method
public class SequenceMain {
    /**
     * Main method of the class sequence
     *
     * @param args
     */
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            String choice;
            while (true) {
                System.out.println();
                System.out.println();
                System.out.println("Which part of this project will you like to see? (press key)");
                System.out.println("1. Part one");
                System.out.println("2. Part two");
                System.out.println("3. Part three");
                System.out.println("Any other key to quit.");
                choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        part1();
                        break;
                    case "2":
                        part2();
                        break;
                    case "3":
                        part3();
                        break;
                    default:
                        return;
                }
            }
        }
    }


    private static void part1() {
        /** Create a new sequence with the given DNA sequence. **/
        BiologicalSequence sequence;
        try {
            sequence = SequenceFileParser.getDnaSequence("dna");
            /** Retrieve the sequence id. **/
            System.out.println(String.format("BiologicalSequence id: %s\n\n", sequence.getId()));

            /** Retrieve the organism from which the sequence originates **/
            System.out.println(String.format("Organism: %s\n\n", sequence.getOrganism()));

            /** Retrieve the actual Dna sequence from the sequence **/
            System.out.println("Dnasequence: " + sequence.getNucleotideSequence());
            System.out.println();
            /** Retrieve the alphabet from the sequence **/
            System.out.println("Alphabet: " + sequence.getAlphabet());

            /** Give the different symbols occurring in the sequence. **/
            sequence.getNucleotides().forEach(System.out::println);
            System.out.println();

            /** Count the number of symbols in the sequence. **/
            System.out.println(String.format("The number of symbols in the sequence are %d.\n", sequence.getNucleotideSequenceLength()));

            /** Count the amount of times a given nucleic acid occurs in the sequence. **/
            for (Map.Entry<Character, Integer> entry : sequence.getNumberForEachNucleotide()) {
                System.out.println(String.format("Symbol %s - amount: %d", entry.getKey(), entry.getValue()));
            }
            System.out.println();

            /** Alternative representation of the amount of times a given nucleic acid occurs in the sequence. **/
            System.out.println(String.format("Nucleic acid A occures %d times.\n", sequence.getNumberOfNucleotide('A')));
            System.out.println(String.format("Nucleic acid C occures %d times.\n", sequence.getNumberOfNucleotide('C')));
            System.out.println(String.format("Nucleic acid T occures %d times.\n", sequence.getNumberOfNucleotide('T')));
            System.out.println(String.format("Nucleic acid G occures %d times.\n", sequence.getNumberOfNucleotide('G')));

            /** Return the complement of the sequence **/
            System.out.println(String.format("Complementary string:\n%s", BiologicalSequence.complementaryNucleotideSequenceOf(sequence).getNucleotideSequence()));

            /** Return the mutated sequence **/


        } catch (IllegalNucleotideSequenceException | IllegalParseException | IllegalIdException | IllegalAlphabetException | IllegalBiologicalSequenceException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void part2() {
        try {
            SequenceRepository repository = new SequenceRepository();
            SequenceRepository repository2 = new SequenceRepository();

            BiologicalSequence biologicalSequence1 = new BiologicalSequence("DNAGLAPROTEIN", "human", "ACGTN", new DnaAlphabet());
            BiologicalSequence biologicalSequence2 = new BiologicalSequence("RNAGLAPROTEIN", "human", "ACGUN", new RnaAlphabet());

            repository.addBiologicalSequence(biologicalSequence1);
            repository.addBiologicalSequence(biologicalSequence2);
            repository.addBiologicalSequence(new BiologicalSequence("GLOBIN", "CHICKEN", "ATGN", new DnaAlphabet()));
            repository.addBiologicalSequence(new BiologicalSequence("EPOPROTEIN", "MOUSE", "ACGTN", new DnaAlphabet()));
            repository2.addBiologicalSequence(new BiologicalSequence("EPOPROTEIN", "MOUSE", "ACGTN", new DnaAlphabet()));


            System.out.println();
            System.out.println("Repository 2 status before terminating: " + repository2.isTerminated());
            repository2.terminate();
            System.out.println("Repository 2 status after terminating: " + repository2.isTerminated());

            QueryResult resultdna = repository.getAllIds(DnaAlphabet.class);

            System.out.println();
            System.out.println("QueryResult with all DnaAlphabet ids: ");
            for (String id : resultdna.getQueryResult()) {
                System.out.println("The following id' from DNA sequences: " + id);
            }

            QueryResult resultSubSequences = repository.getIdsForSubsequence("ACG", DnaAlphabet.class);

            System.out.println();
            System.out.println("QueryResult of all dna biological sequences containing subsequence: ACG, Ids:");
            for (String id : resultSubSequences.getQueryResult()) {
                System.out.println(id);
            }

            QueryResult resultOrganism = repository.getIdsForOrganism("CHICKEN", DnaAlphabet.class);
            System.out.println();
            System.out.println("QueryResult of all dna biological sequences with organism CHICKEN, Ids:");
            for (String id : resultOrganism.getQueryResult()) {
                System.out.println(id);
            }

            QueryResult resultrna = repository.getAllIds(RnaAlphabet.class);

            System.out.println();
            System.out.println("QueryResult with all RnaAlphabet ids: ");
            for (String id : resultrna.getQueryResult()) {
                System.out.println("The following id' from RNA sequences: " + id);
            }


            System.out.println("The current size of the repository is: " + repository.getNbBiologicalSequences());

            repository.removeBiologicalSequence(biologicalSequence1);

            System.out.println("The current size of the repository is after removing a biological sequence: " + repository.getNbBiologicalSequences());

        } catch (IllegalBiologicalSequenceException | IllegalNucleotideSequenceException | IllegalAlphabetException | IllegalIdException | QueryResultException e) {
            e.printStackTrace();
        }
    }

    private static void part3() {
        DnaAlphabet dna = new DnaAlphabet();

        System.out.println("Dna alphabet contains:");
        for (Symbol symbol : dna.getSymbols()) {
            System.out.println("Actual symbol: " + symbol.getActual() + ", complementary symbol: " + symbol.getComplementary());
        }

        RnaAlphabet rna = new RnaAlphabet();
        System.out.println("Rna alphabet contains:");
        for (Symbol symbol : rna.getSymbols()) {
            System.out.println("Actual symbol: " + symbol.getActual() + ", complementary symbol: " + symbol.getComplementary());
        }
    }
}