package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.CommonParam;
import com.zerobase.fastlms.admin.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController {

    private final BannerService bannerService;
    final static String altertext = "이미지 파일";

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, HttpServletRequest request, CommonParam commonParam) {
        List<BannerDto> list = bannerService.list(commonParam);

        PageUtil pageUtil = new PageUtil(bannerService.listTotalCount(), commonParam.getPageSize(), commonParam.getPageIndex(), "");

        model.addAttribute("pager", pageUtil.pager());
        model.addAttribute("list", list);


        return "admin/banner/list";
    }

    @GetMapping(value = {"/admin/banner/add.do", "admin/banner/update.do"})
    public String add(Model model, HttpServletRequest request) {

        boolean editMode = request.getRequestURI().contains("/update.do");
        BannerDto bannerDto = new BannerDto();

        if (editMode) {
            long id = Integer.parseInt(request.getParameter("id"));
            bannerDto = bannerService.findByUserId(id);
        }

        model.addAttribute("banner", bannerDto);
        model.addAttribute("editMode", editMode);
        return "admin/banner/add";
    }

    @PostMapping("/admin/banner/add.do")
    public String addSubmit(HttpServletRequest request, MultipartFile file) {
        BannerDto bannerDto = new BannerDto().builder()
                .bannerName(request.getParameter("bannerName"))
                .alterText("이미지 파일")
                .linkAddress(request.getParameter("linkAddress"))
                .target(request.getParameter("target"))
                .sortValue(Integer.parseInt(request.getParameter("sortValue")))
                .usingYn(Boolean.parseBoolean(request.getParameter("usingYn")))
                .regDt(LocalDateTime.now())
                .build();

        if (file != null) {
            String localPath = "/Users/byungdeukkim/Documents/fastlms3/files";
            String datePath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("/yyyy/MM/dd"));
            String name = UUID.randomUUID().toString().replace("-", "");
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            try {
                File dir = new File(localPath + datePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File newFile = new File(localPath + datePath + "/" + name + "." + ext);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
                bannerDto.setImagePath("/files" + datePath + "/" + name + "." + ext);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }

        // imagePath 처리
        bannerService.add(bannerDto);

        return "redirect:/admin/banner/list.do";
    }

    @PostMapping("/admin/banner/delete.do")
    public void del(HttpServletRequest request) {
        bannerService.delete(new BannerDto().builder()
                .id(Integer.parseInt(request.getParameter("id")))
                .build());
    }

    @PostMapping("/admin/banner/update.do")
    public String update(HttpServletRequest request, MultipartFile file) {
        BannerDto bannerDto = new BannerDto().builder()
                .bannerName(request.getParameter("bannerName"))
                .alterText("이미지 파일")
                .linkAddress(request.getParameter("linkAddress"))
                .target(request.getParameter("target"))
                .sortValue(Integer.parseInt(request.getParameter("sortValue")))
                .usingYn(Boolean.parseBoolean(request.getParameter("usingYn")))
                .regDt(LocalDateTime.now())
                .build();

        if (file != null) {
            String localPath = "/Users/byungdeukkim/Documents/fastlms3/files";
            String datePath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("/yyyy/MM/dd"));
            String name = UUID.randomUUID().toString().replace("-", "");
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            try {
                File dir = new File(localPath + datePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File newFile = new File(localPath + datePath + "/" + name + "." + ext);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
                bannerDto.setImagePath("/files" + datePath + "/" + name + "." + ext);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }

        bannerService.update(bannerDto);

        return "redirect:/admin/banner/list.do";
    }
}
