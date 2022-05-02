package se.iths.complexjavaproject.service;

import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.entity.Role;
import se.iths.complexjavaproject.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

}
