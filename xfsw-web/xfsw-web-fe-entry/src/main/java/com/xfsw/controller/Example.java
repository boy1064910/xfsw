/**
 * 
 */
package com.xfsw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@RestController
@RequestMapping("/ee")
public class Example {

	@GetMapping(value="/aa")
	@ResponseBody
    String home() {  
        return "Hello World!";  
    }  
}
