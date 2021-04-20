package gg.jominsubyungsin.admin.controller;

import gg.jominsubyungsin.admin.response.UserListResponse;
import gg.jominsubyungsin.admin.service.AdminService;
import gg.jominsubyungsin.domain.dto.user.UserDto;
import gg.jominsubyungsin.domain.entity.UserEntity;
import gg.jominsubyungsin.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/admin")
@Controller
public class AdminController {
    private final AdminService adminService;

    /**
     * 유저 무한 스크롤
     * @param pageable
     * @return
     */
    @GetMapping("/userlist")
    public Response showUserList(Pageable pageable){
        UserListResponse response = new UserListResponse();

        List<UserEntity> userList;
        Page<UserEntity> userEntityPage;
        List<UserEntity> allUserList;

        System.out.println(pageable);

        try {
//            userEntityPage = adminService.getUserList(pageable);
//            userList = userEntityPage.getContent();
            allUserList = adminService.getUserList();
        } catch (Exception e){
            throw e;
        }

        response.setMessage("페이지의 유저 보내기 성공");
        response.setHttpStatus(HttpStatus.OK);
        response.setUserEntity(allUserList);
        response.setTotalPages(0);

        return response;
    }

    /**
     * 유저 삭제
     */
    @DeleteMapping("/deleteuser")
    public Response deleteUser(@RequestParam Long id){
        UserListResponse response = new UserListResponse();
        List<UserEntity> allUserList;

        System.out.println("delete user id = " + id);

        try {
            adminService.dropUser(id);
            allUserList = adminService.getUserList();
        } catch (Exception e){
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "유저 저장 실패");
        }

        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("유저 삭제 성공");
        response.setUserEntity(allUserList);
        response.setTotalPages(0);

        return response;
    }

    /**
     * 유저 권한 추가
     * @param user
     * @return
     */
    @PutMapping("/permission/add")
    public Response addPermission(@RequestBody UserDto user){
        UserListResponse response = new UserListResponse();


        try {
            adminService.addPerUser(user);
        } catch (Exception e){
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "역할 수정 실패");
        }

        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("권한 주기 성공");
        response.setUserEntity(findGeneralUserListAsResponse());
        response.setTotalPages(0);

        return response;
    }

    /**
     * 유저 권한 삭제
     * @param user
     * @return
     */
    @PutMapping("/permission/delete")
    public Response delPermission(@RequestBody UserDto user){
        UserListResponse response = new UserListResponse();

        try {
            adminService.delPerUser(user);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "권한 삭제 실패");
        }

        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("권한 삭제 성공");
        response.setUserEntity(findAdminUserListAsResponse());
        response.setTotalPages(0);

        return response;
    }

    private List<UserEntity> findAllUserListAsResponse(){
        List<UserEntity> allUserList;

        try {
            allUserList = adminService.getUserList();
        } catch (Exception e){
            throw e;
        }

        return allUserList;
    }

    private List<UserEntity> findAdminUserListAsResponse(){
        List<UserEntity> adminUserList;

        try {
            adminUserList = adminService.getAdminUserList();
        } catch (Exception e) {
            throw e;
        }

        return adminUserList;
    }

    private List<UserEntity> findGeneralUserListAsResponse(){
        List<UserEntity> generalUserList;

        try {
            generalUserList = adminService.getGeneralUserList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return generalUserList;
    }
}
