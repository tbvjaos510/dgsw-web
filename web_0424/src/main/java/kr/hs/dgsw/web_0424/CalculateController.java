package kr.hs.dgsw.web_0424;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateController {

    @GetMapping("/calculate")
    public String calculate(@RequestParam String oper, @RequestParam Long num1, @RequestParam Long num2) {
        switch (oper) {
            case "plus":
                return num1 + num2 + "";
            case "minus":
                return num1 - num2 + "";
            case "multiply":
                return num1 * num2 + "";
            case "divide":
                return num1 / num2 + "";

        }
        return "";
    }
}
