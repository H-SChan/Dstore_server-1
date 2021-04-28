package gg.jominsubyungsin.admin.domain.dto.query;

import gg.jominsubyungsin.domain.entity.UserEntity;
import gg.jominsubyungsin.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserForAdminDto {
    private Long id;
    private String name;
    private String email;
    private boolean mailAccess;
    private String profileImage;
    private Role role;

    public SelectUserForAdminDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
//        this.mailAccess = userEntity.get
    }
}
