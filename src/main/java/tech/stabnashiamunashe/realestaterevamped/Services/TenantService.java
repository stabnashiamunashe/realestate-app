package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.Emails.EmailService;
import tech.stabnashiamunashe.realestaterevamped.Repos.TenantRepository;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.UserRoles;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.UserStatus;
import tech.stabnashiamunashe.realestaterevamped.Models.Tenant;
import tech.stabnashiamunashe.realestaterevamped.Models.VerificationData;
import tech.stabnashiamunashe.realestaterevamped.Models.VerificationMedium;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;

    private final VerificationServices verificationService;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    public TenantService(TenantRepository tenantRepository, VerificationServices verificationService, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.tenantRepository = tenantRepository;
        this.verificationService = verificationService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }


    public Tenant createTenant(Tenant tenant, VerificationMedium verificationMedium) throws Exception {
        if (emailService.isValidEmail(tenant.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (tenantRepository.existsByEmail(tenant.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        tenant.setUserStatus(UserStatus.PENDING);
        tenant.setUserRoles(List.of(UserRoles.TENANT));
        tenant.setPassword(passwordEncoder.encode(tenant.getPassword()));
        var savedTenant = tenantRepository.save(tenant);

        switch (verificationMedium) {
            case EMAIL -> verificationService.sendVerificationCode(verificationMedium, tenant.getEmail());
            case PHONE_NUMBER -> verificationService.sendVerificationCode(verificationMedium, tenant.getPhoneNumber());
        }

        return savedTenant;

    }

    public Optional<Tenant> getTenantById(String id) {
        return tenantRepository.findById(id);
    }

    public Tenant updateTenant(Tenant tenant) {
        Tenant existingTenant = tenantRepository.findById(tenant.getId()).orElse(null);
        assert existingTenant != null;
        existingTenant.setFirstName(tenant.getFirstName());
        return tenantRepository.save(existingTenant);
    }


    public boolean updateTenantStatus(String tenantId, UserStatus userStatus) {
        try {
            Tenant tenant = tenantRepository.findById(tenantId).orElse(null);
            assert tenant != null;
            tenant.setUserStatus(userStatus);
            tenantRepository.save(tenant);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteTenant(String id) {
        tenantRepository.deleteById(id);
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findByUserStatus(UserStatus.ACTIVE);
    }


    public Boolean verifyTenant(String identifier, VerificationMedium verificationMedium, String verificationCode) {
        return switch (verificationMedium) {
            case EMAIL -> processVerification(identifier, verificationCode, tenantRepository::findByEmail);
            case PHONE_NUMBER -> processVerification(identifier, verificationCode, tenantRepository::findByPhoneNumber);
            default -> false;
        };
    }


    private boolean processVerification(String identifier, String verificationCode, Function<String, Optional<Tenant>> findByFunction) {
        Optional<Tenant> tenant = findByFunction.apply(identifier);

        if (tenant.isPresent()) {
            Tenant existingTenant = tenant.get();
            VerificationData verificationData = verificationService.getVerificationDataByUserId(existingTenant.getId());

            if (verificationData.getVerificationCode().equals(verificationCode)) {
                verificationService.deleteVerificationData(existingTenant.getId());
                return true;
            }
        }

        return false;
    }
}
