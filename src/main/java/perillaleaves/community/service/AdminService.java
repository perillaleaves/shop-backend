package perillaleaves.community.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.community.config.EncryptUtils;
import perillaleaves.community.domain.Admin;
import perillaleaves.community.domain.Rank;
import perillaleaves.community.repository.AdminRepository;
import perillaleaves.community.request.AdminDTO;

import java.time.LocalDateTime;

@Service
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin save(AdminDTO adminDTO) {
        Admin admin = mapper(adminDTO);

        admin.setPassword(EncryptUtils.sha256(adminDTO.getPassword()));

        admin.setPosition(Rank.KING);

        LocalDateTime date = LocalDateTime.now();
        admin.setCreatedAt(date);
        admin.setUpdatedAt(date);

        return adminRepository.save(admin);
    }

    private static Admin mapper(AdminDTO adminDTO) {
        return new Admin(adminDTO.getLogin_id(),
                adminDTO.getPassword(),
                adminDTO.getName(),
                adminDTO.getPosition(),
                adminDTO.getCreatedAt(),
                adminDTO.getUpdatedAt());
    }


}
