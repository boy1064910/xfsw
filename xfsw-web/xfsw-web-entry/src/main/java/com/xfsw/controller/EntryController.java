package com.xfsw.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.xfsw.account.model.UserModel;
import com.xfsw.account.service.RoleAuthorityService;
import com.xfsw.account.service.UserService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.enums.RequestClient;
import com.xfsw.common.util.CookieUtil;
import com.xfsw.common.util.HttpServletRequestUtil;
import com.xfsw.common.util.RandomUtil;
import com.xfsw.common.util.StringUtil;
import com.xfsw.session.consts.SessionConstant;
import com.xfsw.session.model.UserSessionModel;
import com.xfsw.session.service.UserSessionService;

@RestController
@RequestMapping("/entry")
public class EntryController {

//	private static Logger logger = LoggerFactory.getLogger(EntryController.class);
	
	@Value("${wx.mini.appid}")
	private String wxMiniAppid;
	
	@Value("${wx.mini.secret}")
	private String wxMiniSecret;
	
	@Resource(name = "roleAuthorityService")
	RoleAuthorityService roleAuthorityService;
	
	@Resource(name = "userService")
	UserService userService;

	@Resource(name = "userSessionService")
	UserSessionService userSessionService;
//	
//	@Resource(name="messageCodeService")
//	private MessageCodeService messageCodeService;
//	
//	@Resource(name = "studentService")
//	StudentService studentService;
//	
//	@Resource(name = "guardianService")
//	GuardianService guardianService;
//	
//	@Resource(name = "teacherService")
//	TeacherService teacherService;
//	
//	@Resource(name="systemErrorLogService")
//	SystemErrorLogService systemErrorLogService;
//	
//	@PostMapping("/unionIdLogin")
//	public ResponseModel unionIdLogin(String code,String encryptedData,String iv,HttpServletRequest request) {
//		StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session?");
//		url.append("appid=").append(wxMiniAppid);
//		url.append("&secret=").append(wxMiniSecret);
//		url.append("&js_code=").append(code);
//		url.append("&grant_type=authorization_code");
//		
//		String responseBody = HttpRequestUtil.post(url.toString());
//		WxSessionKeyModel model = (WxSessionKeyModel) JsonUtil.json2Entity(responseBody, WxSessionKeyModel.class);
//		if(!StringUtil.isEmpty(model.getErrcode())){
//			logger.error("微信登录获取session_key失败"+JsonUtil.entity2Json(model));
//			return new ResponseModel(ErrorConstant.ACCOUNT_WX_LOGIN_FAIL,"微信登录失败,请联系客服");
//		}
//		
//		// 被加密的数据
//        byte[] dataByte = Base64.decode(encryptedData);
//        // 加密秘钥
//        byte[] keyByte = Base64.decode(model.getSession_key());
//        // 偏移量
//        byte[] ivByte = Base64.decode(iv);
//        
//        WxUserInfo userInfo = null;
//        try {
//        	// 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
//            int base = 16;
//            if (keyByte.length % base != 0) {
//                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
//                byte[] temp = new byte[groups * base];
//                Arrays.fill(temp, (byte) 0);
//                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
//                keyByte = temp;
//            }
//            // 初始化
//            Security.addProvider(new BouncyCastleProvider());
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
//            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
//            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
//            parameters.init(new IvParameterSpec(ivByte));
//            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
//            byte[] resultByte = cipher.doFinal(dataByte);
//            if (null != resultByte && resultByte.length > 0) {
//                String result = new String(resultByte, "UTF-8");
//                userInfo = (WxUserInfo) JsonUtil.json2Entity(result, WxUserInfo.class);
//            }
//        } catch (NoSuchAlgorithmException | InvalidParameterSpecException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
//            logger.error("微信登录密钥解析失败", e);
//            return new ResponseModel(ErrorConstant.ACCOUNT_WX_LOGIN_FAIL,"微信登录失败,请联系客服");
//        } 
//        if(userInfo==null){
//        	logger.error("微信登录获取用户信息失败");
//        	return new ResponseModel(ErrorConstant.ACCOUNT_WX_LOGIN_FAIL,"微信登录失败,请联系客服");
//        }
//        
//        // 微信unionId登录
//        String ip = HttpServletRequestUtil.getIpAddr(request);
//        User user = userService.wxLogin(userInfo.getUnionId(),ip);
//        if(user==null){
//        	ResponseModel resultModel = new ResponseModel();
//        	resultModel.setCode(ErrorConstant.ACCOUNT_NOT_BIND);//尚未绑定手机号
//        	Map<String,Object> result = new HashMap<String,Object>();
//        	result.put("unionId", userInfo.getUnionId());
//        	result.put("openId", userInfo.getOpenId());
//        	resultModel.setData(result);
//        	return resultModel;
//        }
//        // 记录登录session信息
// 		String sessionIdValue = System.nanoTime() + RandomUtil.getCharAndNumr(8);
// 		userSessionService.addUserSession(sessionIdValue, new UserSessionModel(user.getId(), user.getAccount(), user.getHead(), user.getNickName(), user.getEmail()));
//		return new ResponseModel(new UserModel(sessionIdValue,user.getAccount(),user.getHead(),user.getNickName()));
//	}
//	
//	@RequestMapping(value = "/bindAccount")
//	@ResponseBody
//	public HttpResultModel bindAccount(
//			String openId,String unionId,String account,String code,String nickName,String head,HttpServletRequest request,
//			int identity,//0：学生，1：家长，2：老师
//			String studentName,
//			String guardianName,String[] studentNames,String[] studentAccounts,
//			Teacher teacher){
//		if(messageCodeService.verifyPhoneCode(account, MessageCodeBusinessTypeEnum.BIND_MINI_ACCOUNT.getBusinessType(), code)){
//			String ip = HttpRequestUtil.getIpAddr(request);
//			User user = userService.wxBindAccount(account, unionId, openId, nickName, head, ip);
//			
//			//微信登录成功后，处理相关登录session逻辑
//			String sessionIdValue = System.nanoTime() + RandomUtil.getCharAndNumr(8);
//	 		userSessionService.addUserSession(sessionIdValue, user);
//			
//			Date date = new Date();
//			switch(identity){
//				case 0:{
//					//新增或者修改学生数据
//					Student student = new Student();
//					student.setUserId(user.getId());
//					student.setName(studentName);
//					student.setPhone(account);
//					student.setLastUpdater(account);
//					student.setLastUpdateTime(date);
//					studentService.insertUpdateStudent(student);
//					break;
//				}
//				case 1:{
//					//初始化家长数据
//					Guardian guardian = new Guardian();
//					guardian.setId(user.getId());
//					guardian.setName(guardianName);
//					guardian.setLastUpdater(account);
//					guardian.setLastUpdateTime(date);
//					//新增或者修改学生数据
//					List<Student> studentList = new ArrayList<Student>();
//					for(int i=0;i<studentNames.length;i++){
//						Student student = new Student();
//						student.setName(studentNames[i]);
//						student.setPhone(studentAccounts[i]);
//						student.setGuardianId(guardian.getId());
//						student.setLastUpdater(account);
//						student.setLastUpdateTime(date);
//						studentList.add(student);
//					}
//					guardianService.insertGuardian(guardian,studentList);
//					break;
//				}
//				case 2:{
//					teacher.setId(user.getId());
//					TeacherStatusEnum status = TeacherStatusEnum.TYPE_APPROVING;
//					teacher.setStatus(status.getStatus());
//					teacher.setStatusName(status.getStatusName());
//					teacher.setLastUpdater(account);
//					teacher.setLastUpdateTime(date);
//					teacherService.insertTeacher(teacher);
//					break;
//				}
//				default:{
//					String info = "请求路径：/mini/entry/bindAccount.shtml,身份信息标记："+identity;
//					systemErrorLogService.insertBackErrorLog(info, account);
//				}
//			}
//			return new HttpResultModel(new UserModel(user,sessionIdValue,openId,unionId));
//		}
//		else{
//			return new HttpResultModel(ErrorConstant.ERROR_BUSINESS_KNOWN,"验证码校验失败，请重试！");
//		}
//	}
//
//	/**
//	 * 微信小程序账号密码登录
//	 * @param account
//	 * @param pwd
//	 * @param request
//	 * @return
//	 * @author liuxifan
//	 */
//	@RequestMapping("/login")
//	@ResponseBody
//	public HttpResultModel login(String account, String pwd,HttpServletRequest request) {
//		String ip = HttpRequestUtil.getIpAddr(request);
//		User user = userService.login(account, pwd, ip);
//
//		// 记录登录session信息
//		String sessionIdValue = System.nanoTime() + RandomUtil.getCharAndNumr(8);
//		userSessionService.addUserSession(sessionIdValue, user);
//		return new HttpResultModel(new UserModel(user,sessionIdValue,user.getMiniOpenId(),user.getUnionId()));
//	}
	
	
	@PostMapping("/login")
	public ResponseModel login(String account, String pwd, HttpServletRequest request, HttpServletResponse response) {
		UserSessionModel userSessionModel = null;
		
		//获取请求来源客户端
		RequestClient requestClient = RequestClient.valuesOf(request.getHeader("X-REQUESTED-CLIENT"));
		//session id信息
		String sessionId = null;
		switch(requestClient) {
			//默认为浏览器，从cookie中获取sessionId相关信息
			case Default:{
				sessionId = CookieUtil.getCookie(SessionConstant.XFSW_SESSION_ID, request);
				break;
			}
			//微信小程序
			case WxMiniProgram:{
				sessionId = request.getHeader(SessionConstant.XFSW_SESSION_ID);
				break;
			}
		}
		
		if (!StringUtils.isEmpty(sessionId)) {// 如果cookie-dpsessionid不为空，并且cookie-dpsessionid值存在redis中
			userSessionModel = userSessionService.getUserSession(sessionId);
			if(userSessionModel!=null){ 
				switch(requestClient) {
					//默认为浏览器，从cookie中获取sessionId相关信息
					case Default:{
						//刷新cookie过期时间
						CookieUtil.refreshCookie(request, response, SessionConstant.XFSW_SESSION_ID, SessionConstant.XFSW_SESSION_EXPIRE, HttpServletRequestUtil.getDomain(request), "/");
						break;
					}
					default:{
						break;
					}
				}
				new ResponseModel(userSessionModel);
			}
		}
		// session 不存在，验证用户名密码
		String ip = HttpServletRequestUtil.getIpAddr(request);
		UserModel userModel = userService.login(account, pwd, ip);

		// 记录登录session信息
		String sessionIdValue = System.nanoTime() + RandomUtil.getCharAndNumr(8);
		userSessionModel = new UserSessionModel(userModel);
		userSessionService.addUserSession(sessionIdValue, userSessionModel);
		switch(requestClient) {
			//默认为浏览器，从cookie中获取sessionId相关信息
			case Default:{
				//设置cookie
				CookieUtil.setCookie(response, SessionConstant.XFSW_SESSION_ID, sessionIdValue, SessionConstant.XFSW_SESSION_EXPIRE, HttpServletRequestUtil.getDomain(request), "/");
				break;
			}
			default:{
				break;
			}
		}
		return new ResponseModel(userSessionModel);
	}

	@PostMapping("/logout")
	public ResponseModel logout(HttpServletRequest request,HttpServletResponse response,String path) {
		// 获取cookie-dpsessionid的值
		String sessionidValue = CookieUtil.getCookie(SessionConstant.XFSW_SESSION_ID, request);
		if (!StringUtil.isEmpty(sessionidValue)) {
			// 调用session-redis服务，删除用户的session信息
			userSessionService.deleteUserSession(sessionidValue);
		}
		// 清除cookie信息
		CookieUtil.delCookie(SessionConstant.XFSW_SESSION_ID, request, response, HttpServletRequestUtil.getDomain(request), path);
		return new ResponseModel();
	}
}
