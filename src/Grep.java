import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Grep {
    private String command, text;
    private StringBuilder outText;
    boolean inverseFlag;
    private List<String> fileNames=new ArrayList<>(0);
    private List<String> flags=new ArrayList<>(0);

    public Grep(String command){
        this.command = new String(command);
        outText = new StringBuilder("");
        inverseFlag = false;
        extractElements();
        printElements();
    }

    private void printElements(){
        System.out.println("Flags: "+flags);
        System.out.println("Files: "+fileNames);
        System.out.println("Text: "+text);
    }

    private void raiseException(){
        System.out.println("Invalid Command!\n Command should be: \"grep -flag1 -flag2 ... \"text\" file1.ext file2.ext ...\n" +
                "Possible Reasons: file does not exist, wrong flags");
    }

    private void extractElements(){
        StringBuilder tempText = new StringBuilder("");
        String[] elements = command.split(" ");
        if(!elements[0].equals("grep")){
            raiseException();
        }
        for (String el: elements) {
            if(Pattern.matches("^-.$", el)){
                flags.add(el);
                if(el.equals("-v")){
                    inverseFlag = true;
                    System.out.println("!!! Inverse Flag activated");
                }
                continue;
            }
            if(Pattern.matches(".*\\..*$", el)){
                fileNames.add(el);
                continue;
            }
            if(el.equals("grep")){
                continue;
            }
            tempText.append(el).append(" ");
        }
        text = tempText.toString().substring(0, tempText.length()-1).replaceAll("\"", "");
//        System.out.println(fileNames, flags, text);
    }


    private void nfl() throws IOException {
        for(int i = 0;i<fileNames.size();++i){
            File file = new File(fileNames.get(i));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            outText.append("(Case Sensitive) Matching Text found in ").append(fileNames.get(i)).append(" at line: \n");
            int line_no = 0;
            while ((st = br.readLine()) != null) {
                line_no++;
                if (Pattern.matches(".*"+text+".*", st) && !inverseFlag) {
                    outText.append(line_no).append(": ").append(st).append("\n");
                }
                if (!Pattern.matches(".*"+text+".*", st) && inverseFlag) {
                    outText.append(line_no).append(": ").append(st).append("\n");
                }
            }
        }
    }

    private void lfl() throws IOException {
        for(int i = 0;i<fileNames.size();++i){
            File file = new File(fileNames.get(i));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            outText.append("Matching Text found in ").append(fileNames.get(i)).append(" files: ");
            int flag = 0;
            while ((st = br.readLine()) != null){
                if (Pattern.matches(".*"+text.toLowerCase()+".*", st.toLowerCase()) && !inverseFlag){
                    flag = 1;
                    break;
                }
                if (!Pattern.matches(".*"+text.toLowerCase()+".*", st.toLowerCase()) && inverseFlag){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1) {
                outText.append(fileNames.get(i)).append("\n");
            }
        }
    }

    private void ifl() throws IOException {
        for(int i = 0;i<fileNames.size();++i){
            File file = new File(fileNames.get(i));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            outText.append("(Case Insensitive) Matching Text found in ").append(fileNames.get(i)).append(" at line: \n");
            int line_no = 0;
            while ((st = br.readLine()) != null) {
                line_no++;
                if (Pattern.matches(".*"+text.toLowerCase()+".*", st.toLowerCase()) && !inverseFlag) {
                    outText.append(line_no).append(": ").append(st).append("\n");
                }
                if (!Pattern.matches(".*"+text.toLowerCase()+".*", st.toLowerCase()) && inverseFlag) {
                    outText.append(line_no).append(": ").append(st).append("\n");
                }
            }
        }
    }

    private void xfl() throws IOException {
        for(int i = 0;i<fileNames.size();++i){
            File file = new File(fileNames.get(i));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            outText.append("Matching Text found in ").append(fileNames.get(i)).append(" at line: \n");
            int line_no = 0;
            while ((st = br.readLine()) != null) {
                line_no++;
                if (Pattern.matches("^" + text.toLowerCase() + "$", st.toLowerCase()) && !inverseFlag) {
                    outText.append(line_no).append(": ").append(st).append("\n");
                }
                if (!Pattern.matches("^" + text.toLowerCase() + "$", st.toLowerCase()) && inverseFlag) {
                    outText.append(line_no).append(": ").append(st).append("\n");
                }
            }

        }
    }

    public StringBuilder executeQuery() throws IOException {
        for (String flag : flags) {
            switch (flag) {
                case "-n" -> nfl();
                case "-l" -> lfl();
                case "-x" -> xfl();
                case "-i" -> ifl();
                case "-v" -> {
                    break;
                }
                default -> {
                    System.out.println("Invalid Flag");
                    raiseException();
                }
            }
        }
        return outText;
    }

}
