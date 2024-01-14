package tech.stabnashiamunashe.realestaterevamped.Security.Configss;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import tech.stabnashiamunashe.realestaterevamped.Security.Service.LoginAttemptService;

import java.io.IOException;

//@Component
//public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//
//    private final MessageSource messages;
//
//    private final LocaleResolver localeResolver;
//
//    private final HttpServletRequest request;
//
//    private final LoginAttemptService loginAttemptService;
//
//    public CustomAuthenticationFailureHandler(MessageSource messages, LocaleResolver localeResolver, HttpServletRequest request, LoginAttemptService loginAttemptService) {
//        this.messages = messages;
//        this.localeResolver = localeResolver;
//        this.request = request;
//        this.loginAttemptService = loginAttemptService;
//    }
//
////    @Override
////    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
////        if (loginAttemptService.isBlocked()) {
////            errorMessage = messages.getMessage("auth.message.blocked", null, locale);
////        }
////        String errorMessage = messages.getMessage("message.badCredentials", null, locale);
////        if (exception.getMessage() != null && exception.getMessage().equalsIgnoreCase("blocked")) {
////            errorMessage = messages.getMessage("auth.message.blocked", null, locale);
////        }
////
////        // Your remaining code for handling authentication failure
////    }
//}