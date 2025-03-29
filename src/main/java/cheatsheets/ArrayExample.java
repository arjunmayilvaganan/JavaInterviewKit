package cheatsheets;

import java.util.Arrays;

public class ArrayExample {
    public static void main(String[] args) {
        // Array creation
        int[] numbers = new int[5];  // Initialize with zeros
        String[] fruits = {"apple", "banana", "orange"};  // Initialize with values

        // Filling array
        Arrays.fill(numbers, 1);  // Fill with 1s
        System.out.println("Filled array: " + Arrays.toString(numbers));

        // Copying arrays
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        int[] partialCopy = Arrays.copyOfRange(numbers, 1, 3);
        System.out.println("Full copy: " + Arrays.toString(numbersCopy));
        System.out.println("Partial copy: " + Arrays.toString(partialCopy));

        // Sorting
        int[] unsorted = {5, 2, 8, 1, 9};
        Arrays.sort(unsorted);  // In-place sort
        System.out.println("Sorted array: " + Arrays.toString(unsorted));

        // Binary search (array must be sorted)
        int index = Arrays.binarySearch(unsorted, 8);
        System.out.println("Index of 8: " + index);

        // 2D array creation and access
        int[][] matrix = new int[3][3];
        // Fill matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = i * 3 + j;
            }
        }

        // Print 2D array
        System.out.println("\n2D Array:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        // Compare arrays
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        System.out.println("Arrays equal? " + Arrays.equals(array1, array2));

        // Array as List
        String[] stringArray = {"one", "two", "three"};
        var asList = Arrays.asList(stringArray);  // Fixed-size List view
        System.out.println("As List: " + asList);

        // Common pitfall prevention: array initialization
        // Preferred way to declare array size
        int length = 5;
        int[] safeArray = new int[length];  // Clear size declaration

        // Safe way to initialize 2D array with different column lengths
        int[][] irregular = new int[3][];
        irregular[0] = new int[2];
        irregular[1] = new int[3];
        irregular[2] = new int[4];
    }
}