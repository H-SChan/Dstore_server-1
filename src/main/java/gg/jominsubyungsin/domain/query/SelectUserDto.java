package gg.jominsubyungsin.domain.query;

import gg.jominsubyungsin.domain.dto.user.UserDto;
import gg.jominsubyungsin.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserDto {
  private Long id;
  private String name;
  private String introduce;
  private String profileImage;

  public SelectUserDto(UserEntity userEntity){
    this.id = userEntity.getId();
    this.name = userEntity.getName();
    this.introduce = userEntity.getIntroduce();
    this.profileImage = userEntity.getProfileImage();
  }
}
