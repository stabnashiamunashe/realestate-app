package tech.stabnashiamunashe.realestaterevamped.Security.Configss;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import tech.stabnashiamunashe.realestaterevamped.Security.Service.LoginAttemptService;

//@Component
//public class AuthenticationFailureListener implements
//        ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
//
//    private final HttpServletRequest request;
//
//    private final LoginAttemptService loginAttemptService;
//
//    public AuthenticationFailureListener(LoginAttemptService loginAttemptService, HttpServletRequest request) {
//        this.loginAttemptService = loginAttemptService;
//        this.request = request;
//    }
//
//    @Override
//    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
//        final String xfHeader = request.getHeader("X-Forwarded-For");
//        if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(request.getRemoteAddr())) {
//            loginAttemptService.loginFailed(request.getRemoteAddr());
//        } else {
//            loginAttemptService.loginFailed(xfHeader.split(",")[0]);
//        }
//    }
//}