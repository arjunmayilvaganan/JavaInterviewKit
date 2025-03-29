package cheatsheets;

public class StringExample {
    public static void main(String[] args) {
        // String creation and basic operations
        String str = "Hello, World!";
        System.out.println("Original: " + str);

        // Length and character access
        System.out.println("Length: " + str.length());
        System.out.println("Char at 0: " + str.charAt(0));

        // Substring - Note: endIndex is exclusive
        String sub = str.substring(0, 5);  // "Hello"
        System.out.println("Substring(0,5): " + sub);

        // Finding content
        int indexOfWorld = str.indexOf("World");  // 7
        int lastIndex = str.lastIndexOf('o');    // 7
        System.out.println("Index of 'World': " + indexOfWorld);
        System.out.println("Last index of 'o': " + lastIndex);

        // Case conversion
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Lowercase: " + str.toLowerCase());

        // Trimming whitespace
        String spacedStr = "  trim me  ";
        System.out.println("Trimmed: '" + spacedStr.trim() + "'");

        // String comparison
        String str1 = "hello";
        String str2 = "HELLO";
        System.out.println("Equals (case sensitive): " + 
            str1.equals(str2));  // false
        System.out.println("Equals (ignore case): " + 
            str1.equalsIgnoreCase(str2));  // true

        // Checking content
        System.out.println("Starts with 'He': " + str.startsWith("He"));
        System.out.println("Ends with '!': " + str.endsWith("!"));
        System.out.println("Contains 'World': " + str.contains("World"));

        // String building (when many modifications needed)
        StringBuilder sb = new StringBuilder();
        sb.append("Hello")
          .append(" ")
          .append("World");
        System.out.println("StringBuilder result: " + sb.toString());

        // Splitting and joining
        String csvLine = "apple,banana,orange";
        String[] fruits = csvLine.split(",");
        System.out.println("Split results:");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }

        // String.join (Java 8+)
        String joined = String.join("-", fruits);
        System.out.println("Joined with '-': " + joined);

        // Replace
        String replaced = str.replace('o', '0');
        System.out.println("Replaced 'o' with '0': " + replaced);
    }
}