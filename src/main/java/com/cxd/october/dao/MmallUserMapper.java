package com.cxd.october.dao;

import com.cxd.october.pojo.MmallUser;
import org.apache.ibatis.annotations.Param;

public interface MmallUserMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(MmallUser record);

    int insertSelective(MmallUser record);

    int selectUsername(String username);

    int selectEmail(String email);


    MmallUser selectUsernameAndPassword(@Param(value = "username") String username, @Param(value = "password") String password);


    String getQuestion(String username);

    int checkAnswer(@Param(value = "username") String username,@Param(value = "question") String question,@Param(value = "answer") String answer);

    int restpassword(@Param(value = "username")String usernanme,@Param(value = "passwordNew")String passwordNew);


    int checkpassword(@Param(value = "userId")Integer userId,@Param(value = "password")String password);


    int checkEmail(@Param(value = "email") String email,@Param(value = "userId") Integer userId);




    MmallUser selectByPrimaryKey(Integer id);



    int updateByPrimaryKeySelective(MmallUser record);

    int updateByPrimaryKey(MmallUser record);
}