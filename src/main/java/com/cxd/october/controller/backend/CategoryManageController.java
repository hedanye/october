package com.cxd.october.controller.backend;


import com.cxd.october.common.Const;
import com.cxd.october.common.ResponseCode;
import com.cxd.october.common.Role;
import com.cxd.october.common.ServerResponse;
import com.cxd.october.pojo.MmallUser;
import com.cxd.october.service.ICategoryService;
import com.cxd.october.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manage/category")
public class CategoryManageController {


    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "add.do",method = RequestMethod.POST)
    public ServerResponse add(HttpSession session,String categoryName,@RequestParam(value = "parentId",defaultValue ="0") Integer parentId){
       MmallUser user= (MmallUser) session.getAttribute(Const.CURRENT_USER);
       if (user==null){
           return ServerResponse.createByErrorCode(ResponseCode.NEED_LOGIN.getCode(),"未登录");
       }
       if (userService.checkAdmin(user).isSuccess()){
           return categoryService.add(categoryName,parentId);

       }else {
           return ServerResponse.createByError("请登录管理员");

       }

    }


    @RequestMapping(value = "update.do",method = RequestMethod.POST)
    public ServerResponse update(HttpSession session,String categoryName, Integer categoryId){
        MmallUser user= (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCode(ResponseCode.NEED_LOGIN.getCode(),"未登录");
        }
        if (userService.checkAdmin(user).isSuccess()){
            return categoryService.update(categoryName,categoryId);
        }else {
            return ServerResponse.createByError("请登录管理员");

        }

    }


    @RequestMapping(value = "getChildrenParallel.do",method = RequestMethod.POST)
    public ServerResponse getChildrenParallel(HttpSession session, @RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        MmallUser user= (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCode(ResponseCode.NEED_LOGIN.getCode(),"未登录");
        }
        if (userService.checkAdmin(user).isSuccess()){
            return categoryService.getChildrenParallel(categoryId);

        }else {
            return ServerResponse.createByError("请登录管理员");

        }

    }



    @RequestMapping(value = "getChildren.do",method = RequestMethod.POST)
    public ServerResponse getChildren(HttpSession session, @RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        MmallUser user= (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCode(ResponseCode.NEED_LOGIN.getCode(),"未登录");
        }
        if (userService.checkAdmin(user).isSuccess()){
            return categoryService.getChildren(categoryId);

        }else {
            return ServerResponse.createByError("请登录管理员");

        }

    }













}
