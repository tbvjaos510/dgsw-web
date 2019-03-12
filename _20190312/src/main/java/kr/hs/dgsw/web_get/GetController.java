package kr.hs.dgsw.web_get;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GetController {

    @GetMapping("/greeting")
    public String getHello(
            @RequestParam(required = false, defaultValue = "User") String to,
            @RequestParam(required = false, defaultValue = "Hello") String say
    ) {
        return say + " " + to;
    }

    @GetMapping(
            value = {"/greeting1/{say}/{to}",
                    "/greeting1/{say}",
                    "/greeting1//{to}",
            "/greeting1"})
    public String sayHello1(@PathVariable Optional<String > say, @PathVariable Optional<String> to) {
        String tmp = say.isPresent() ? to.get() : "NONAME";
        String tmp2 = to.isPresent() ? to.get() : "NONAME";
        return tmp + " " + tmp2;
    }
}
