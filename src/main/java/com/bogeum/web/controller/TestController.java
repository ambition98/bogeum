package com.bogeum.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/")
	public String index() {
		log.info("Enter index()");
		List<AccountEntity> accountList = accountService.getAllAccount();
		for(AccountEntity a : accountList) {
			log.info(a.toString());
		}
		return "/test";
	}
}
