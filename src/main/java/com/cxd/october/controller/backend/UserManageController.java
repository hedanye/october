package com.cxd.october.controller.backend;


import com.cxd.october.common.Const;
import com.cxd.october.common.Role;
import com.cxd.october.common.ServerResponse;
import com.cxd.october.pojo.MmallUser;
import com.cxd.october.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService userService;



    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public ServerResponse<MmallUser> login(String username, String password, HttpSession session){

        ServerResponse<MmallUser> response = userService.login(username, password);
        if (response.isSuccess()){
            MmallUser user=response.getData();
            if (user.getRole().intValue()== Role.ROLE_ADMIN.getCode()){
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            }else {
                return ServerResponse.createByError("不是管理员");
            }
        }
          return response;

    }

































}
