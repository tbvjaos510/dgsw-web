package kr.hs.dgsw.calculator;

import org.springframework.stereotype.Service;
import sun.security.ssl.Debug;

@Service
public class CalculatorServiceImpl implements  CalculatorService {
    @Override
    public String calculate(String num1, String num2, String Operator) {
        int inum1 = Integer.parseInt(num1);
        int inum2 = Integer.parseInt(num2);
        switch(Operator) {
            case "+":
                return Integer.toString(inum1 + inum2);
            case "-":
                return Integer.toString(inum1 - inum2);
            case "*":
                return Integer.toString(inum1 * inum2);
            case "/":
                return Integer.toString(inum1 / inum2);
            case "%":
                return Integer.toString(inum1 % inum2);
        }
        return "";
    }
}
