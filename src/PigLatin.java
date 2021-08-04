import java.util.regex.Pattern;

public class PigLatin {

    private StringBuilder inpSentence, outSentence;

    public PigLatin(String inpStr){
        this.inpSentence = new StringBuilder(preprocess(inpStr));
        outSentence = new StringBuilder("");
    }

    private String preprocess(String str){
        return str.replaceAll("[^a-zA-Z0-9\\ ]", "");
    }

    private Boolean vowelBeg(String str){
        if(Pattern.matches("([aeiou]|xr|yt).*", str)){
            return true;
        }
        return false;
    }


    private StringBuilder processWord(StringBuilder inpStr){
//        System.out.println("Original Word -> "+inpStr);
        StringBuilder outStr = new StringBuilder("");
        if(vowelBeg(inpStr.toString())){
            outStr = inpStr;
        }
        else {
            if(Pattern.matches("^.qu", inpStr)){
                outStr.append(inpStr.subSequence(4, inpStr.length())).append(inpStr.subSequence(0, 4));
            }
            else {
                int i;
                for (i = 0; i < inpStr.length(); ++i) {
                    if (vowelBeg(inpStr.subSequence(i, inpStr.length()).toString())) {
                        break;
                    }
                }
                outStr.append(inpStr.subSequence(i, inpStr.length())).append(inpStr.subSequence(0, i));
            }
        }
        outStr = outStr.append("ay");
        return outStr;
    }
    public StringBuilder processSentence(){
        for (String inpStr: inpSentence.toString().split(" "))
        {
            outSentence.append(processWord(new StringBuilder(inpStr))+" ");
        }
        return outSentence;
    }

}
