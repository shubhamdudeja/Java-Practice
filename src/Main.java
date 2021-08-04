import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] argv) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the input Sentence(English): ");
        String str;
        str = scan.nextLine();


        PigLatin translator = new PigLatin(str);
        System.out.println("Translated Sentence(Pig Latin): "+translator.processSentence());

        System.out.println("Grep\nEnter command: ");
        str = scan.nextLine();

        Grep grep = new Grep(str);
        System.out.println(grep.executeQuery());

    }
}