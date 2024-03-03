package com.mandacarubroker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/index")
    public String goHome(){
        return "index";
    }

    @GetMapping("/profile")
    public String goProfile(){
        return "assets/profile";
    }


    @GetMapping("/elements")
    public String elements(){
        return "/elements/index";
    }


    @GetMapping("/security")
    public String security(){
        return "/security/index";
    }

}
