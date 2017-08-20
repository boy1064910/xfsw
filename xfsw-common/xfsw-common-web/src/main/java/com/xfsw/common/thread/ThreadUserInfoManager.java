package com.xfsw.common.thread;

import com.xfsw.session.model.UserSessionModel;

public class ThreadUserInfoManager {

	private static ThreadLocal<UserSessionModel> threadUserInfoLocal = new ThreadLocal<UserSessionModel>();
	
	public static void setUserInfo(UserSessionModel user){
		threadUserInfoLocal.set(user);
	}
	
	public static UserSessionModel getUserInfo(){
		return threadUserInfoLocal.get();
	}
	
	public static String getAccount(){
		return threadUserInfoLocal.get().getAccount();
	}
	
	public static Integer getUserId(){
		return threadUserInfoLocal.get().getId();
	}
	
	public static Integer getRoleId(){
		return threadUserInfoLocal.get().getRoleId();
	}
	
	public static void removeRequest(){
		threadUserInfoLocal.remove();
	}
}
