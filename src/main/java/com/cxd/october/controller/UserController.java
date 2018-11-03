package com.cxd.october.controller;


import com.cxd.october.common.Const;
import com.cxd.october.common.ServerResponse;
import com.cxd.october.pojo.MmallUser;
import com.cxd.october.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService userService;




    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public ServerResponse<MmallUser> login(HttpSession session, String username, String password){

        ServerResponse<MmallUser> response = userService.login(username, password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }

        return response;
    }

    @RequestMapping(value = "loginOut.do",method = RequestMethod.GET)
    public ServerResponse loginOut(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value = "register.do",method = RequestMethod.POST)
    public ServerResponse<String> register(MmallUser user){
       return userService.register(user);
    }


    @RequestMapping(value = "checkValid.do",method = RequestMethod.POST)
    public ServerResponse<String> checkValid(String str,String type){
          return userService.checkValid(str,type);

    }



    @RequestMapping(value = "getUserInfo.do",method = RequestMethod.POST)
    public ServerResponse getUserInfo(HttpSession session){
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user!=null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByError("未登录");
    }




    @RequestMapping(value = "getQuestion.do",method = RequestMethod.POST)
    public ServerResponse<String> getQuestion(String username){
            return userService.getQuestion(username);
    }



    @RequestMapping(value = "checkAnswer.do",method = RequestMethod.POST)
    public ServerResponse<String> checkAnswer(String username,String question,String answer){
       return userService.checkAnswer(username,question,answer);
    }



    @RequestMapping(value = "forgetrestPassword.do",method = RequestMethod.POST)
    public ServerResponse<String> forgetrestPassword(String username,String passwordNew,String uuid){
        return userService.restPassword(username,passwordNew,uuid);
    }


    @RequestMapping(value = "restpassword.do",method = RequestMethod.POST)
    public ServerResponse<String> restpassword(HttpSession session,String passwordOld,String passwordNew){
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByError("未登录");
        }
        return userService.restpassword(user,passwordOld,passwordNew);

    }

    @RequestMapping(value = "updateInfo.do",method = RequestMethod.POST)
    public ServerResponse<MmallUser> updateInfo(HttpSession session,MmallUser user){
        MmallUser mmallUser = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (mmallUser==null){
            return ServerResponse.createByError("未登录");
        }
        user.setId(mmallUser.getId());
        user.setUsername(mmallUser.getUsername());

        ServerResponse<MmallUser> serverResponse=userService.updateInfo(user);
        if (serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        }
        return serverResponse;
    }







}
