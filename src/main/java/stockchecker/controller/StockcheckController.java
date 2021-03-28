package stockchecker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockcheckController {

    @RequestMapping("/")
    public String index() {
        return "Controller is working";
    }
}
