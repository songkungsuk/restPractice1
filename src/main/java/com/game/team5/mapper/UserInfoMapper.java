package com.game.team5.mapper;

import java.util.List;

import com.game.team5.vo.UserInfoVO;

public interface UserInfoMapper {
	List<UserInfoVO> selectUserInfos(UserInfoVO user);
	UserInfoVO selectUserInfo(int uiNum);
	UserInfoVO selectUserInfoByIdPwd(UserInfoVO user);
	int insertUserInfo(UserInfoVO user);
	int updateUserInfo(UserInfoVO user);
	int deleteUserInfo(int uiNum);
}
