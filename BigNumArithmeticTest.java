//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
public class BigNumArithmeticTest {
    /*BigNumArithmetic object to test with*/
    BigNumArithmetic objectOfTests = new BigNumArithmetic();
    /**testing addition function*/
    @Test public void additionTest_2_2_4(){assertEquals("4",objectOfTests.addition("2","2"));} //single digits normal case
    @Test public void additionTest_0_8_8(){assertEquals("8",objectOfTests.addition("0","8"));} //zero case
    @Test public void additionTest_0_0_0(){assertEquals("0",objectOfTests.addition("0","0"));} //both zeros case
    @Test public void additionTest_10000_2_10002(){assertEquals("4",objectOfTests.addition("2","2"));} //adding operands of different lengths case

    /**testing subtraction function*/
    @Test public void subtractionTest_100_50_50(){assertEquals("3080",objectOfTests.subtraction("4310","1230"));}//1 digit difference in length case
    @Test public void subtractionTest_0_0_0(){assertEquals("0",objectOfTests.subtraction("0","0"));}//zeros case
    @Test public void subtractionTest_0_50_50(){assertEquals("50",objectOfTests.subtraction("0","50"));}//case that result should always be positive
    @Test public void subtractionTest_100_100_0(){assertEquals("0",objectOfTests.subtraction("100","100"));}//equal numbers should result in zero case
    @Test public void subtractionTest_1000000_5_999995(){assertEquals("999995",objectOfTests.subtraction("1000000","5"));} //large difference in length case

    /**testing multiplication function*/
    @Test public void multiplicationTest_11_5_55(){assertEquals("55",objectOfTests.multiplication("11","5"));}//normal case
    @Test public void multiplicationTest_45_0_0(){assertEquals("0",objectOfTests.multiplication("45","0"));}//zero case
    @Test public void multiplicationTest_225_34_7650(){assertEquals("7650",objectOfTests.multiplication("225","34"));}//varying length case
    @Test public void multiplicationTest_0_0_0(){assertEquals("0",objectOfTests.multiplication("0","0"));}//zeros case
    @Test public void multiplicationTest_120_23_2760(){assertEquals("2760",objectOfTests.multiplication("120","23"));}//case for varying lengths with least sig digit zero
    @Test public void multiplicationTest_5_5_25(){assertEquals("25",objectOfTests.multiplication("5","5"));} //duplicate digit case
    @Test public void multiplicationTest_15_500_7500(){assertEquals("7500",objectOfTests.multiplication("15","500"));}//case for varying lengths with multiple zeros

    /**testing with a command file*/
    //this should print the expected output to the console
    @Test public void readFileTest_AdditionInput(){objectOfTests.read_File("CommandFile.txt");}
}
