package com.game.team5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.team5.mapper.UserInfoMapper;
import com.game.team5.vo.UserInfoVO;

@Service
public class UserInfoService {
	
	@Autowired
	UserInfoMapper uiMapper;
	
	public List<UserInfoVO> selectUserInfos(UserInfoVO user){
		return uiMapper.selectUserInfos(user);
	}
	
	public UserInfoVO selectUserInfo(int uiNum) {
		return uiMapper.selectUserInfo(uiNum);
	}
	
	public int insertUserInfo(UserInfoVO user) {
		return uiMapper.insertUserInfo(user);
	}
	
	public int updateUserInfo(UserInfoVO user) {
		return uiMapper.updateUserInfo(user);
	}
	
	public int deleteUserinfo(int uiNum) {
		return uiMapper.deleteUserInfo(uiNum);
	}
}
