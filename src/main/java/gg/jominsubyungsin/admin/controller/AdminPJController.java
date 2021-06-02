package gg.jominsubyungsin.admin.controller;

import gg.jominsubyungsin.admin.domain.dto.project.response.ProjectListResponse;
import gg.jominsubyungsin.admin.service.project.AdminPJService;
import gg.jominsubyungsin.domain.entity.ProjectEntity;
import gg.jominsubyungsin.domain.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/admin/pj")
@Controller
public class AdminPJController {
    private final AdminPJService adminPJService;

//    @GetMapping("/list/all")
//    public Response showProjectList(){
//        ProjectListResponse response = new ProjectListResponse();
//
//        List<ProjectEntity> allPjList;
//
//        System.out.println("프로젝트 전체 보기");
//
//        try {
//            allPjList = adminPJService.getProjectAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//
//        response.setHttpStatus(HttpStatus.OK);
//        response.setMessage("프로젝트 전체보기 성공");
//        response.setProjectList(allPjList);
//
//        return response;
//    }

    @GetMapping("")
    public Response ProjectList(Pageable pageable) {
        Response result;

        result = getProjectList(pageable);

        return result;
    }

//    @GetMapping("/list")
//    public Response showProjectListAsUserId(@RequestParam Long id){
//        ProjectListResponse response = new ProjectListResponse();
//
//        List<ProjectEntity> PjList;
//
//        System.out.println(id + "의 프로젝트 보기");
//
//        try {
//            PjList = adminPJService.getProjectById(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//
//        response.setHttpStatus(HttpStatus.OK);
//        response.setMessage(id + "의 프로젝트 보기");
//        response.setProjectList(PjList);
//
//        return response;
//    }

//    @DeleteMapping("/delete")
//    public Response deleteProject(@RequestParam Long projectId){
//        ProjectListResponse response = new ProjectListResponse();
//
//        List<ProjectEntity> PjList;
//
//        try {
//            PjList = adminPJService.dropProject(projectId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//
//        response.setHttpStatus(HttpStatus.OK);
//        response.setMessage("프로젝트 삭제 완료");
//        response.setProjectList(PjList);
//
//        return response;
//    }

    private Response getProjectList(Pageable pageable) {
        Response result;

        try {
            result = adminPJService.getProjects(pageable);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }

        return result;
    }
}
