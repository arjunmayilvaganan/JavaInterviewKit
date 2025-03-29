package cheatsheets;

public class StringBuilderExample {
    public static void main(String[] args) {
        // Creating StringBuilder
        StringBuilder sb = new StringBuilder();
        System.out.println("Empty StringBuilder capacity: " + sb.capacity());
        
        // append() - Adding strings
        sb.append("Hello");
        sb.append(' ');
        sb.append("World");
        System.out.println("After append: " + sb);
        
        // insert() - Insert at specific position
        sb.insert(5, " beautiful");
        System.out.println("After insert: " + sb);
        
        // replace() - Replace range of characters
        sb.replace(6, 15, "amazing");
        System.out.println("After replace: " + sb);
        
        // delete() and deleteCharAt()
        sb.delete(5, 12); // delete range
        System.out.println("After delete: " + sb);
        sb.deleteCharAt(5); // delete single char
        System.out.println("After deleteCharAt: " + sb);
        
        // reverse()
        sb.reverse();
        System.out.println("After reverse: " + sb);
        sb.reverse(); // reverse back
        
        // substring()
        String sub = sb.substring(0, 5);
        System.out.println("Substring(0,5): " + sub);
        
        // length() and capacity()
        System.out.println("Length: " + sb.length());
        System.out.println("Capacity: " + sb.capacity());
        
        // setLength() - Truncate or expand
        sb.setLength(4);
        System.out.println("After setLength(4): " + sb);
        
        // charAt() and setCharAt()
        char ch = sb.charAt(0);
        System.out.println("charAt(0): " + ch);
        sb.setCharAt(0, 'h');
        System.out.println("After setCharAt(0,'h'): " + sb);
        
        // indexOf()
        sb.append(" hello");
        System.out.println("indexOf('e'): " + sb.indexOf("e"));
        System.out.println("lastIndexOf('l'): " + sb.lastIndexOf("l"));
        
        // toString()
        String result = sb.toString();
        System.out.println("Final toString(): " + result);
    }
}
