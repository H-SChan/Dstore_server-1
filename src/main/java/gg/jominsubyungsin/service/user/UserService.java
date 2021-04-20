package gg.jominsubyungsin.service.user;

import gg.jominsubyungsin.domain.dto.user.UserDto;
import gg.jominsubyungsin.domain.dto.user.UserUpdateDto;

import gg.jominsubyungsin.domain.entity.UserEntity;


import gg.jominsubyungsin.domain.dto.query.SelectUserDto;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

public interface UserService {
  void userCreate(UserDto userDto);
  UserEntity login(UserDto userDto);
  void userUpdate(UserUpdateDto userDto) throws HttpServerErrorException;
  void userDelete(UserDto userDto);
  void userUpdateIntroduce(UserDto userDto);
  boolean userMailAccess(String email);
  SelectUserDto finduser(Long id);
  UserEntity findUserId(Long id);
  UserEntity findUser(String email);
  List<SelectUserDto> findUserLikeName(String name, String email);
}
