package sec.project.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;
    
    private Map<String, String> accountDetails;
    


    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public String getDone() {
        return "done";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "searchForm";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String submitForm(Model model, @RequestParam String name) {

        Signup su = signupRepository.findByName(name);
        model.addAttribute("Su", su);

        return "srchrslt";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdmin() {
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String deleteAllAdmin(@RequestParam String address) {

        if ("yes".equals(address)) {
            signupRepository.deleteAll();
        }

        return "admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String tryLogin(@RequestParam String username, String password) {

        if(username.equals("admin") && password.equals("admin")){
            return "admin";
        }
        

        return "login";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        signupRepository.save(new Signup(name, address));

        return "done";
    }

}
