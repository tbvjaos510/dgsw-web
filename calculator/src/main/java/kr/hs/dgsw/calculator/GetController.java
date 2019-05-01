package kr.hs.dgsw.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {

    @Autowired
    CalculatorService cs;

    @GetMapping("/calculate")
    public String Calculate(@RequestParam String num1, @RequestParam String num2, @RequestParam String oper) {
        return cs.calculate(num1, num2, oper);
        // return num1 + num2 + oper;
    }
}
