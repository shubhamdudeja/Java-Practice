import java.util.Scanner;

public class Main {
    public static void main(String[] argv){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the input Sentence(English): ");
        String str;
        str = scan.nextLine();


        PigLatin translator = new PigLatin(str);
        System.out.println("Translated Sentence(Pig Latin): "+translator.processSentence());


    }
}
