package co.hafid.speltutorial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author abelb
 */

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok().body("App is running just fine ... Like British Wine");
    }

}
