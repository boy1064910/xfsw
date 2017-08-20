package com.xfsw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xfsw.common.classes.ResponseModel;

@RestController
@RequestMapping("/order")
public class OrderController {

//	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@GetMapping("/test")
	public ResponseModel test(){
		return new ResponseModel();
	}
}
