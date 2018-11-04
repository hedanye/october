package com.cxd.october.service;


import com.cxd.october.common.ServerResponse;
import com.cxd.october.pojo.MmallUser;


public interface IUserService {


    ServerResponse<MmallUser> login(String username, String password);


    ServerResponse<String> register(MmallUser user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse<String> getQuestion(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> restPassword(String username,String passwordNew,String uuid);

    ServerResponse<String> restpassword(MmallUser user,String passwordOld, String passwordNew);

    ServerResponse<MmallUser> updateInfo( MmallUser user);

    ServerResponse checkAdmin(MmallUser user);




}
