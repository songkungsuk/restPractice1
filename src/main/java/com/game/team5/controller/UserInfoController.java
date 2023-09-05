package com.game.team5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.team5.service.UserInfoService;
import com.game.team5.util.JWTToken;
import com.game.team5.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class UserInfoController {

	@Autowired
	private UserInfoService uiService;

	@Autowired
	private JWTToken jwtToken;

	@GetMapping("/user-infos")
	public List<UserInfoVO> getUserInfos(UserInfoVO user, HttpServletRequest req) {
		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("token=>{}", token);
		String uiId = jwtToken.getUserIdFromToken(token);
		log.info("login id =>{}", uiId);
		return uiService.selectUserInfos(user);
	}

	@GetMapping("/user-infos/{uiNum}")
	public UserInfoVO getUserinfo(@PathVariable int uiNum) {
		return uiService.selectUserInfo(uiNum);
	}

	@PostMapping("/login")
	public UserInfoVO login(@RequestBody UserInfoVO user) {
		return uiService.login(user);
	}

	@PostMapping("/user-infos")
	public int insertUserInfo(@RequestBody UserInfoVO user) {
		return uiService.insertUserInfo(user);
	}

	@PutMapping("/user-infos")
	public int updateUserInfo(@RequestBody UserInfoVO user) {
		return uiService.updateUserInfo(user);
	}

	@DeleteMapping("/user-infos/{uiNum}")
	public int deleteUserInfo(@PathVariable int uiNum) {
		return uiService.deleteUserinfo(uiNum);
	}

}
