package com.cxd.october.service.impl;

import com.cxd.october.common.Const;
import com.cxd.october.common.Role;
import com.cxd.october.common.ServerResponse;
import com.cxd.october.common.TokenCache;
import com.cxd.october.dao.MmallUserMapper;
import com.cxd.october.pojo.MmallUser;
import com.cxd.october.service.IUserService;
import com.cxd.october.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private MmallUserMapper userMapper;


    @Override
    public ServerResponse<MmallUser> login(String username, String password){
        int count = userMapper.selectUsername(username);
        if (count==0){
            return ServerResponse.createByError("用户名不存在");
        }

        String md5Encode = MD5Util.MD5EncodeUtf8(password);


        MmallUser mmallUser = userMapper.selectUsernameAndPassword(username, md5Encode);
        if (mmallUser==null){
            return ServerResponse.createByError("密码错误");
        }


        mmallUser.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",mmallUser);


    }

    @Override
    public ServerResponse<String> register(MmallUser user) {

        ServerResponse<String> stringServerResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!stringServerResponse.isSuccess()){
            return stringServerResponse;
        }

        stringServerResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!stringServerResponse.isSuccess()){
            return stringServerResponse;
        }


        user.setRole(Role.ROLE_ADMIN.getCode());
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

         int count = userMapper.insert(user);
         if (count>0){
             return ServerResponse.createBySuccess("注册成功");
         }
         return ServerResponse.createByError("注册失败");


    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
      if (StringUtils.isNotBlank(type)){
          if (Const.USERNAME.equals(type)){
              int count = userMapper.selectUsername(str);
              if (count>0){
                  return ServerResponse.createByError("用户名已存在");
              }
          }
          if (Const.EMAIL.equals(type)){
              int count = userMapper.selectEmail(str);
              if (count>0){
                  return ServerResponse.createByError("email已注册");
              }
          }

      }else {
          return ServerResponse.createByError("参数错误");
      }
      return ServerResponse.createBySuccess("校验成功");

    }

    @Override
    public ServerResponse<String> getQuestion(String username) {
        ServerResponse<String> serverResponse = this.checkValid(username, Const.USERNAME);
        if (serverResponse.isSuccess()){
            return ServerResponse.createByError("用户名不存在");
        }

        String question = userMapper.getQuestion(username);
        if (StringUtils.isBlank(question)){
            return ServerResponse.createByError("问题为空");
        }
        return ServerResponse.createBySuccess(question);


    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int count=userMapper.checkAnswer(username,question,answer);
        if (count>0){
            String uuid=UUID.randomUUID().toString();
            TokenCache.setKey(Const.TOKEN+username,uuid);
            return ServerResponse.createBySuccess(uuid);
        }
        return ServerResponse.createByError("问题答案错误");

    }

    @Override
    public ServerResponse<String> restPassword(String username, String passwordNew, String uuid) {
        if (StringUtils.isBlank(uuid)){
            return ServerResponse.createByError("参数错误");
        }
        ServerResponse<String> serverResponse = this.checkValid(username, Const.USERNAME);
        if (serverResponse.isSuccess()){
            return ServerResponse.createByError("用户名不存在");
        }

        String key = TokenCache.getKey(Const.TOKEN + username);
        if (StringUtils.isBlank(key)){
            return ServerResponse.createByError("token过期");
        }
        if (StringUtils.equals(key,uuid)){
            String md5password=MD5Util.MD5EncodeUtf8(passwordNew);
           int count= userMapper.restpassword(username,md5password);
            if (count>0){
                return ServerResponse.createBySuccess("修改成功");
            }else {
                return ServerResponse.createByError("修改失败");
            }
        }else {
            return ServerResponse.createByError("token不一致");
        }


    }

    @Override
    public ServerResponse<String> restpassword(MmallUser user,String passwordOld, String passwordNew) {

        int count=userMapper.checkpassword(user.getId(),MD5Util.MD5EncodeUtf8(passwordOld));
        if (count>0){
            user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
            count=userMapper.updateByPrimaryKeySelective(user);
            if (count>0){
                return ServerResponse.createBySuccess("更新成功");
            }else {
                return ServerResponse.createByError("更新失败");
            }
        }
        return ServerResponse.createByError("旧密码错误");







    }

    @Override
    public ServerResponse<MmallUser> updateInfo(MmallUser user) {

        int count=userMapper.checkEmail(user.getEmail(),user.getId());
        if (count>0){
            return ServerResponse.createByError("email已占用");
        }

        MmallUser update=new MmallUser();
        update.setId(user.getId());
        update.setPassword(user.getPassword());
        update.setAnswer(user.getAnswer());
        update.setEmail(user.getEmail());
        update.setQuestion(user.getQuestion());
        update.setPhone(user.getPhone());

       count= userMapper.updateByPrimaryKeySelective(update);
        if (count>0){
            return ServerResponse.createBySuccess("更新成功",update);
        }
        return ServerResponse.createByError("更新失败");









    }


}
