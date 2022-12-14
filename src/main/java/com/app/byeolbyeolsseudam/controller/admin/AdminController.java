package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.service.admin.AdminMemberService;
import com.app.byeolbyeolsseudam.service.admin.AdminPickService;
import com.app.byeolbyeolsseudam.service.admin.AdminProductService;
import com.app.byeolbyeolsseudam.service.admin.AdminProgramService;
import com.app.byeolbyeolsseudam.service.main.BannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/admin","/admin"})
public class AdminController {
    private final AdminMemberService adminMemberService;
    private final AdminProgramService adminProgramService;
    private final AdminPickService adminPickService;
    private final AdminProductService adminProductService;
    /* ############################### 공통  ################################### */

    /* 사이드바 */
    @GetMapping("adminsidebar")
    public String adminSideBar(){ return "/app/admin/adminSideBar"; }

    /* ############################### 대시 보드  ############################### */

    /* 대시보드 */
    @GetMapping("")
    public String adminMain(Model model){
        model.addAttribute("members", adminMemberService.showAdminMemberList());
        model.addAttribute("programs", adminProgramService.showList());
        model.addAttribute("pickups", adminPickService.showAdminPickupList());
        model.addAttribute("orders", adminProductService.showAdminOrderList());

        return "/app/admin/adminMain"; }

    /* ############################### 회원 관리  ############################### */

    //AdminMemberController

    /* ############################### 주문 관리 ################################ */

    //AdminProductController

    /* ############################### 프로그램 관리 ############################# */

    //AdminProgramController

    /* ############################### 줍깅 관리 ################################ */

    //AdminJubggingController

    /* ############################### 수거서비스 ############################### */

    //AdminPickupController

    /* ############################### 게시판 관리 ############################## */

    //AdminCommunityController

    /* ############################### 고객 센터 ################################ */

    //AdminNoticeController

    /* ############################### 배너 관리 ############################### */

    //BannerController
}