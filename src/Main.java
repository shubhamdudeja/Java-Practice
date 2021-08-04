import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] argv) throws IOException {
        Scanner scan = new Scanner(System.in);
        String choice;
        String str;
        while(true) {
            System.out.print("\n--Menu--\n1. English to Pig latin Translator\n2. Grep CLI\n3. Exit\nEnter your choice: ");
            choice = scan.nextLine();
            switch (choice) {
                case "1": {
                    System.out.println("Enter the input Sentence(English): ");
                    str = scan.nextLine();
                    PigLatin translator = new PigLatin(str);
                    System.out.println("Translated Sentence(Pig Latin): " + translator.processSentence());
                    break;
                }
                case "2": {
                    System.out.println("Grep CLI\nEnter command: ");
                    str = scan.nextLine();
                    Grep grep = new Grep(str);
                    System.out.println(grep.executeQuery());
                    break;
                }
                default:{
                    System.out.println("Invalid choice/Exiting");
                    choice = "3";
                }
            }
            if(choice.equals("3")){
                break;
            }
        }
    }
}