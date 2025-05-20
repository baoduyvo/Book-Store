package org.voduybao.bookstorebackend.dao.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.User;
import org.voduybao.bookstorebackend.dao.repositories.RoleRepository;
import org.voduybao.bookstorebackend.dao.repositories.UserRepository;
import org.voduybao.bookstorebackend.tools.contants.AuthProviderEnum;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>> START INIT DATABASE");
        long userCount = this.userRepository.count();

        if (userCount == 0) {
            User user = new User();
            user.setEmail("van.a.nguyen@example.com");
            user.setPhoneNumber("84123456789");
            user.setPassword("$2a$10$I37G59I.XTdzJkcdTzfdv.an1PySI3dyL8k9g40kf5n.gyEy/9hcO");
            user.setAuthProvider(AuthProviderEnum.LOCAL);
            user.setIsStatus(true);
            user.setIsVerified(true);
            user.setRoles(Set.of(
                    roleRepository.getAdminRole(),
                    roleRepository.getCustomerRole()
            ));
            userRepository.save(user);
        }

        System.out.println(">>> END INIT DATABASE");
    }
}