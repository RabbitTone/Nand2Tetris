public class UtilityMethods{
    /**Converts an int to a 15 bit binary string.
     * @param number The number to convert
     * @return A string containing the binary representation
     * */
    public static String toBinary(int number){
        String s = "";
        for(int i = 0; i < 15; i++){
            s = (number & 1) + s;
            number >>= 1;     //Right binary shift
        }
        return s;
    }

    /**Returns true if the given string contains an integer
     * @param symbol The string
     * @return true if symbol is an integer, false otherwise. 
     */
    public static boolean isNumeric(String symbol){
        try{
            Integer.parseInt(symbol);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}