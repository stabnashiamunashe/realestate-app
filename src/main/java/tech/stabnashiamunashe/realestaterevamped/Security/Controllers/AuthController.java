package tech.stabnashiamunashe.realestaterevamped.Security.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.stabnashiamunashe.realestaterevamped.Security.Service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import tech.stabnashiamunashe.realestaterevamped.Services.PropertyOwnerService;
import tech.stabnashiamunashe.realestaterevamped.Services.TenantService;

import java.util.Arrays;


@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

//    @PostMapping("/register/email/owner")
//    public ResponseEntity<?> registerOwnerByEmail(@RequestBody PendingPropertyOwner pendingPropertyOwner) throws MessagingException {
//        return propertyOwnerServices.registerOwnerByEmail(pendingPropertyOwner);
//    }
//
//    @PostMapping("/register/sms/owner")
//    public ResponseEntity<?> registerOwnerByPhoneNumber(@RequestBody PendingPropertyOwner pendingPropertyOwner) throws Exception {
//        return propertyOwnerServices.registerOwnerByPhoneNumber(pendingPropertyOwner);
//    }
//
//    @PostMapping("/verify/tenant")
//    public ResponseEntity<?> verifyTenant(@RequestParam String email,
//                                          @RequestParam int verificationCode) {
//
//        return tenantsServices.verifyTenant(email,verificationCode);
//
//    }
//    @PostMapping("/verify/owner")
//    public ResponseEntity<?> verifyOwner(@RequestParam String email,
//                                         @RequestParam int verificationCode) {
//
//        return propertyOwnerServices.verifyOwner(email,verificationCode);
//    }
//
//    @PostMapping("/register/email/tenant")
//    public ResponseEntity<?> createTenantByEmail(@RequestBody PendingTenant pendingTenant) throws MessagingException {
//        return tenantsServices.registerTenantByEmail(pendingTenant);
//    }
//
//    @PostMapping("/register/sms/tenant")
//    public ResponseEntity<?> createTenantBySMS(@RequestBody PendingTenant pendingTenant) throws Exception {
//        return tenantsServices.registerTenantByPhoneNumber(pendingTenant);
//    }

    @PostMapping("/login")
    public String token(@RequestBody LoginRequest loginRequest){

        try {

            System.out.println(loginRequest.email() + " " + loginRequest.password());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
            System.out.println(authentication.getAuthorities());
            return "token : " + tokenService.generateToken(authentication) + "\n role : " + authentication.getAuthorities();

        }
        catch (Exception e){
            // log the error
            log.info(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
            return "User Not Found";
        }
    }
}
