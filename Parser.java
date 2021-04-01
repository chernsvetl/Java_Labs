import java.lang.*;
        import java.io.*;
        import java.util.*;


public class Parser {
    public static void main (String[] args) throws FileNotFoundException {
        WordCounter counter = new WordCounter();
        counter.read("input.txt");
        counter.write("output.txt");
    }
}
