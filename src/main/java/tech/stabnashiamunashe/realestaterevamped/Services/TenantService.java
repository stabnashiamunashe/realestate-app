package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.Repos.TenantRepository;
import tech.stabnashiamunashe.realestaterevamped.Tenant;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }


    public Tenant createTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
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

    public void deleteTenant(String id) {
        tenantRepository.deleteById(id);
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

}
