import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BigNumArithmetic {

    /**removes any leading zeros from a string*/
    private static String removeLeadingZeros(String input) {
        String copy = input;
        int string_length = input.length();
        //we never remove the last digit even if it is zero
        if (string_length>1){
            //iterate from right to left except the last digit
            for (int j = 0; j < string_length - 1; j++){
                //if the digit is zero
                if (input.charAt(j)=='0'){
                    //remove the leading zero from our copy
                    copy = input.substring(j+1);
                }
                else{break;}//else there are no more zeros in front so break out
            }
        }
        return copy;
    }

    /**takes a line of input and returns an array of operand and operator strings, while removing any leading zeros*/
    public String[] clean(String line){
        //create an array from the line
        String[] array = line.trim().split("\\s+");
        if(array!=null){
            //for each string in the array
            for(int i=0;i< array.length;i++){
                //get the string
                String string=array[i];
                //remove any leading zeros
                string= removeLeadingZeros(string);
                //assign it back to the array
                array[i]=string;
            }
        }
        return array;
    }

    /**take an array containing operands and operators, do the correct operations with them
     * @param operation an array of operands and operators in Reverse Polish Notation.
     * @return output the result of a valid operation, else a blank
     */
    public String operationToStack(String[] operation){
        //make a Stack of Link Lists corresponding to our operation array
        LStack operation_stack = new LStack();
        int length=operation.length;
        //if there are less than 2 element no operation can be done, we need at least 2 operands and an operator
        if(length>=2) {
            for (int i = 0; i < length; i++) {
                //if an element is not an operator
                if (!operation[i].equals("+") && !operation[i].equals("-") && !operation[i].equals("*") && !operation[i].equals("/")) {
                    //push it to stack
                    operation_stack.push(operation[i]);
                } else {//pop the last 2 elements as operands
                    String operand1 = (String) operation_stack.pop();
                    String operand2 = (String) operation_stack.pop();
                    //if the pop was successful
                    if (operand1 != null && operand2 != null) {
                        //then perform the operation
                        String result = null;
                        if (operation[i].equals("+")) {
                            result = addition(operand1, operand2);
                        } else if (operation[i].equals("-")) {
                            result = subtraction(operand1, operand2);
                        }
                        else if (operation[i].equals("*")){
                            result = multiplication(operand1,operand2);
                        }
                        //push result to the stack
                        operation_stack.push(result);
                    }
                }
            }
        }
        //when all operations are finished there should be one String on the stack, which is the result
        String output = (String) operation_stack.pop();
        //if there is another object on the stack: the operation was invalid (not enough operators), output a blank
        if(operation_stack.pop()!=null||output==null){return "";}
        else{return output;}
    }


     /**takes a string and returns a link list of reverse order*/
    public LList string_to_Reverse_LL(String operand){
        LList operand_list = new LList();
        //for each character in the String right to left
        for (int i=operand.length()-1;i>=0;i--){
            //append the digit to the list
            Integer value = Integer.parseInt(String.valueOf(operand.charAt(i)));
            operand_list.append(value);
        }
        //return the link list
        return operand_list;
    }



    /**returns a String with characters in reverse order of the link list given*/
    public String Reverse_LL_to_string(LList input){
        String output="";
        for(int i=0;i<input.length();i++){
            String digit = input.getValue().toString();
            //add the digit to the front of the string
            output=digit+output;
            //move to next digit
            input.next();
        }
        return output;
    }
    /**add a specific number of zeros to the end of a link list
     * @param count number of zeros to extend*/
    public void add_zeros(LList list, int count){
        Integer zero=0;
        for(int i=0;i<count;i++){
            list.append(zero);
        }
    }

    /**returns the sum of two String operands
     * it assumes both are positive*/
    public String addition(String operand1,String operand2){
        LList sum_list = new LList();
        int carry = 0;
        //convert operands 1 and 2 to link lists, in with the least significant digit first
        LList operand1_list = string_to_Reverse_LL(operand1);
        LList operand2_list = string_to_Reverse_LL(operand2);
        int length_difference;
        int operand1_size = operand1_list.length();
        int operand2_size = operand2_list.length();
       //extend either list to be equal in length
        if(operand1_size>operand2_size){
            length_difference=operand1_size-operand2_size;
            add_zeros(operand2_list,length_difference);
        }
        else if(operand2_size>operand1_size) {
            length_difference = operand2_size - operand1_size;
            add_zeros(operand1_list, length_difference);
        }
        operand1_list.moveToStart();
        operand2_list.moveToStart();
        //iterate over both lists at the same time
        for(int i=0;i<operand1_list.length();i++) {
            //add both digits and the carry together
            Integer value1 =(Integer) operand1_list.getValue();
            Integer value2=(Integer) operand2_list.getValue();
            int sum = value1+value2+carry;
            //if the sum is greater than 9
            if(sum>9){
                //carry the 1 and keep the remainder
                carry=1;
                sum=sum-10;
            }else{carry=0;}
            sum_list.append(sum);
            operand1_list.next();
            operand2_list.next();
        }
        //if there's still a carry at the end add it to the list
        if(carry==1){
            sum_list.append(carry);
        }
        //return the String of sum_list in (greatest to least) order
        return Reverse_LL_to_string(sum_list);
    }

    /**returns the difference between to String operands*/
    public String subtraction(String operand1,String operand2) {
        LList result_list = new LList();
        String greater = "";
        String lesser = "";
        int length_difference = operand1.length() - operand2.length();
        //if the difference in length is 0 we must compare digits to find which string is greater
        if (length_difference == 0) {
            //iterate over both until we find one that's greater
            for (int i = 0; i < operand1.length(); i++) {
                int digit1 = Integer.parseInt(String.valueOf(operand1.charAt(i)));
                int digit2 = Integer.parseInt(String.valueOf(operand2.charAt(i)));
                if (digit1 == digit2) {}
                else if (digit1 > digit2){greater = operand1; lesser = operand2; break;}
                else{greater = operand2; lesser = operand1; break;}
            }
            //if greater hasn't been assigned to an operand: they are equal
            if (greater.equals("")) {
                //subtracting equal numbers results in 0
                return "0";
            }
        }
        //when lengths are different we assign the greater
        else if (length_difference > 0) {greater = operand1; lesser = operand2;}
        else {greater = operand2; lesser = operand1;}
        LList greater_list = string_to_Reverse_LL(greater);
        LList lesser_list = string_to_Reverse_LL(lesser);
        //extend the length of lesser to equal greater
        length_difference = Math.abs(length_difference);
        add_zeros(lesser_list,length_difference);
        greater_list.moveToStart();
        lesser_list.moveToStart();
        int carry = 0;
        //iterate over both lists together
        for (int i = 0; i < greater_list.length(); i++){
            Integer digit1 = (Integer) greater_list.getValue();
            Integer digit2 = (Integer) lesser_list.getValue();
            //subtract the carry from the greater_list digit
            digit1 -= carry;
            //if the digit in greater_list is less than the digit in lesser_list we need to carry a 1 over
            if (digit1 < digit2) {
                digit1 += 10;
                carry = 1;
            }
            else{carry=0;}
            Integer digit_difference = digit1 - digit2;
            result_list.append(digit_difference);
            greater_list.next();
            lesser_list.next();
        }
        String output = Reverse_LL_to_string(result_list);
        output = removeLeadingZeros(output);
    return output;
    }


    /**returns the product of two String operands*/
    public String multiplication(String operand1,String operand2){
        String greater = operand1;
        String lesser = operand2;
        int length_difference = operand1.length() - operand2.length();
        //if the difference in length is negative operand2 is greater
        if (length_difference < 0) {greater = operand2; lesser = operand1;}
        LList greater_list = string_to_Reverse_LL(greater);
        LList lesser_list = string_to_Reverse_LL(lesser);
        //extend the length of lesser_list to equal greater_list
        length_difference = Math.abs(length_difference);
        add_zeros(lesser_list,length_difference);
        lesser_list.moveToStart();
        //use a stack to hold the products of multiplying each digit
        LStack addition_stack = new LStack();
        String round_output = "";
        //iterate over the lesser list from least to greatest
        for (int i=0;i<lesser_list.length();i++) {
            int carry=0;
            int digit_product = 0;
            //reset the output and add the correct number of zeros for each round of multiplication
            round_output="";
            for(int z=0;z<i;z++){round_output=round_output+'0';}
            greater_list.moveToStart();
            Integer digitL = (Integer) lesser_list.getValue();
            //iterate over the greater list from least to greatest
            for (int j=0; j<greater_list.length();j++) {
                Integer digitG=(Integer) greater_list.getValue();
                digit_product=(digitL*digitG)+carry;
                //if the product of both digits is over 9 get the carry and keep the remainder
                if(digit_product>9){
                    carry=digit_product/10;
                    digit_product=digit_product%10;
                }
                else{carry=0;}
                round_output = digit_product+round_output;
                greater_list.next();
            }
            //if there is a carry left over at the end of the round of multiplication we add it to the front
            if (carry!=0){round_output = carry+round_output;}
            addition_stack.push(round_output);
            lesser_list.next();
        }
        //add all the round_outputs together to get our total product
        String total_product = (String) addition_stack.pop();
        while (!addition_stack.isEmpty()){
            total_product = addition(total_product,(String)addition_stack.pop());
        }
        total_product = removeLeadingZeros(total_product);
        return total_product;
    }

    /**reads the given command-file and does its operations,then prints the results to the console
     * @param filename the name of the command-file to read*/
    public void read_File(String filename){
        try {
            FileInputStream file = new FileInputStream(filename);
            Scanner in = new Scanner(file);
            while(in.hasNextLine()) {
                String line = in.nextLine();
                if(!line.isEmpty()){
                    String[] operation_array = clean(line);//get an array of operands and operators from our clean function
                    String result = operationToStack(operation_array);//then execute them in a stack
                    String operation_string="";
                    //print the operation and its result to the console
                    for(int i=0;i<operation_array.length;i++){
                        operation_string += operation_array[i]+" ";
                    }
                    System.out.println(operation_string + "= " + result);

                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("input file could not be opened!");
        }
    }
    /**gets the command-file argument from user to execute*/
    public static void main(String[] args) {
        //if there's an argument for a file, get it and do its operations
        if(args.length>0){
            String inputFile = args[0];
            BigNumArithmetic mainObject = new BigNumArithmetic();
            mainObject.read_File(inputFile);
        }
        else{
            System.out.println("no input file given");
        }
    }
}