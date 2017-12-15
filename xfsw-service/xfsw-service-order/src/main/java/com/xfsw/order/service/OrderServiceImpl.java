/**
 * 
 */
package com.xfsw.order.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Resource(name="orderCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public void generateOrder() {
		// TODO Auto-generated method stub

	}

}
