import java.util.*;

public class RomanToArabNumbers {
    static private int convertedNumber = 0;
    char [] romanArray = {'I','V','X','L','C','D','M'};

    char[] invalidDuplicates ={'V','L','D'};
    private String position = "";

    Map<Character, Integer> RomanValues = new LinkedHashMap<>();
    public int convertToInt(String romanNumber){
        romanNumber = romanNumber.toUpperCase();
        try{
            switch(romanNumber){
                case "I":convertedNumber = 1; break;
                case "V":convertedNumber = 5; break;
                case "X":convertedNumber = 10; break;
                case "L":convertedNumber = 50; break;
                case "C":convertedNumber = 100; break;
                case "D":convertedNumber = 500; break;
                case "M":convertedNumber = 1000; break;
                default: System.out.println("Invalid input...");
                    throw new Exception();
            }
        }
        catch (Exception e) {

            System.err.println("Please enter a valid Roman number");
            e.getMessage();
            System.exit(0);
        }

        return convertedNumber;
    }

    public int convertToInt2Chars(String romanNumber) throws Exception{
        romanNumber = romanNumber.toUpperCase();

        if(!trueIfVLDGreaterThanRest(romanNumber)){
            System.out.println("Invalid input VLD greater than rest");
            System.exit(0);
        }

        if(!trueIfNotDuplicateVLD(romanNumber)){
            System.out.println("Invalid input VLD");
            System.exit(0);
        }
        if(!trueIfLessThanFour(romanNumber)){
            System.out.println("Invalid input more than three");
            System.exit(0);
        }

        for(int i=0; i<romanNumber.length()-1; i++){
            int left = convertToInt(Character.toString(romanNumber.charAt(i)));
            int right = convertToInt(Character.toString(romanNumber.charAt(i+1)));

            if(left >= right){
                convertedNumber = left + right;

            }
            else if(left < right){
                convertedNumber = right - left;
            }
        }
        return convertedNumber;
    }
    public boolean trueIfNotDuplicateVLD(String romanValue){

        char[] romanValueArray = romanValue.toCharArray();
        int counter = 0;
        for (int i=0;i<invalidDuplicates.length; i++){
            for (int j=0; j<romanValue.length(); j++){
                if (romanValueArray[j] == invalidDuplicates[i]){
                    counter++;
                    if (counter == 2){
                        return false;
                    }
                }
            }counter = 0;
        } return true;
    }
    public boolean trueIfLessThanFour(String romanValue){
        char[] romanValueArray = romanValue.toCharArray();
        int counter = 0;
        for (int i=0;i<romanArray.length; i++){
            for (int j=0; j<romanValue.length(); j++){
                if (romanValueArray[j] == romanArray[i]){
                    counter++;
                    if (counter > 3){
                        return false;
                    }
                }
            }counter = 0;
        } return true;
    }
    public boolean trueIfVLDGreaterThanRest(String romanValue){
        char[] romanValueArray = romanValue.toCharArray();
        RomanValues.put('I',1);
        RomanValues.put('V',5);
        RomanValues.put('X',10);
        RomanValues.put('L',50);
        RomanValues.put('C',100);
        RomanValues.put('D',500);
        RomanValues.put('M',1000);
        for (int i=0; i<romanValue.length(); i++){
            for(int j=0; j<invalidDuplicates.length;j++)
                if (romanValueArray[i]==invalidDuplicates[j]){
                    for (int k = i+1; k<romanValue.length(); k++){
                        if (RomanValues.get(romanValueArray[i])<= RomanValues.get(romanValueArray[k])){
                            return false;
                        }
                    }
                }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        RomanToArabNumbers m = new RomanToArabNumbers();

        Scanner sc = new Scanner(System.in);

        System.out.print("Input a valid Roman numeral:");

        String inputtedRomanNumeral = sc.nextLine();
        System.out.println(inputtedRomanNumeral + " = " + m.convertToInt2Chars(inputtedRomanNumeral));

    }
}