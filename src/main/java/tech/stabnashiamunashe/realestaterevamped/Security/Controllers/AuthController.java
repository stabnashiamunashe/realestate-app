package tech.stabnashiamunashe.realestaterevamped.Security.Controllers;

import org.springframework.web.bind.annotation.*;
import tech.stabnashiamunashe.realestaterevamped.Security.Service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import tech.stabnashiamunashe.realestaterevamped.Services.PropertyOwnerService;
import tech.stabnashiamunashe.realestaterevamped.Services.TenantService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final TenantService tenantsServices;

    private final PropertyOwnerService propertyOwnerServices;


    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, TenantService tenantsServices, PropertyOwnerService propertyOwnerServices) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.tenantsServices = tenantsServices;
        this.propertyOwnerServices = propertyOwnerServices;
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

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
            System.out.println(authentication.getAuthorities());
            return "token : " + tokenService.generateToken(authentication) + "\n role : " + authentication.getAuthorities();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "User Not Found";
        }
    }
}
