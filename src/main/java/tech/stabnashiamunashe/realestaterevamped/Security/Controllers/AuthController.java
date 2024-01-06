package tech.stabnashiamunashe.realestaterevamped.Security.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.User;
import tech.stabnashiamunashe.realestaterevamped.Security.Service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import tech.stabnashiamunashe.realestaterevamped.Security.Service.UserService;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final UserService userServices;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, UserService userServices) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userServices = userServices;
    }

    @GetMapping("/test")
    public ResponseEntity<User> test(@RequestParam String email){
        Optional<User> userOptional = userServices.getUserByEmail(email);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/test2")
    public ResponseEntity<List<User>> test2(){
        return userServices.getAllUsers();
   }

    @PostMapping("/login")
    public ResponseEntity<String> token(@RequestBody LoginRequest loginRequest){

        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
            return ResponseEntity.ok("token : " + tokenService.generateToken(authentication) + "\n role : " + authentication.getAuthorities());

        }
        catch (Exception e){
            // log the error
            log.info(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Invalid Credentials");
        }
    }
}
