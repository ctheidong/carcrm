package com.ruoyi.web.controller.system;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.redis.CacheTime;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.module.utils.JwtUtil;
import com.ruoyi.system.module.utils.PassToken;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
@Api(tags = "用户管理相关接口")
public class SysLoginController extends BaseController
{
    private final  static JwtUtil jwt = new JwtUtil();
    private final Map<String,Object> map = new HashMap<>();
    private Cache<String, Deque<Serializable>> cache;
//    @Autowired
//    RedisClient redisClient;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private ISysRoleService roleService;
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }
    @SuppressWarnings("all")
    @PostMapping("/userlogin")
    @PassToken
    @ApiOperation(value = "小程序登录接口",httpMethod = "post")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "userName",value = "用户名", required = true),
          @ApiImplicitParam(name = "password",value = "密码", required = true)
    })
    @ResponseBody
    public AjaxResult userlogin(@RequestBody  SysUser user)
    {
        String username = user.getUserName();
        String password = user.getPassword();
        //通过登录名查询出用户对应的盐值
        SysUser user1 = userService.selectUserByLoginName(username);
        if(!StringUtils.isNotNull(user1)){
            return  AjaxResult.error("用户不存在!");
        }
        //通过登录名查询出该用户的登录密码,验证从数据库查询出来的值与当前值保持一致
        //根据用户名 密码 和用户对应的盐值 得到加密后的密码
        String password1 = passwordService.encryptPassword(username, password,user1.getSalt());
        //设置加密后的密码
        user.setPassword(password1);
        // 然后再通过登录名称和加密后的密码查询数据库对应的用户信息  是否存在
        SysUser userResult = userService.selectUserByLoginNameAndPwd(user);
        if(!StringUtils.isNotNull(user1)){
            return AjaxResult.error("用户不存在!");
        }
        user.setPassword(userResult.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        long time = 3600 * 1000 * 2;
        Subject subject = SecurityUtils.getSubject();
        try
        {
//            subject.login(token);
            //生成token
            String jwtToken = jwt.createJWT(CacheTime.TIME_OUT, user);
            //存入redis
//            redisClient.set("token" + username,jwtToken,CacheTime.TIME_OUT);
//            //查询当前用户对应的角色列表
//            List<SysRole> sysRoles = roleService.getRolesByUserId(user1.getUserId());
//            List<Integer> roleIdList = new LinkedList<>();
//            for(SysRole sysRole:sysRoles) {
//                roleIdList.add(Integer.parseInt(String.valueOf(sysRole.getRoleId())));
//            }
            //查询出角色对应的菜单列表
//            List<String> stringList = roleService.roleList(roleIdList);
//            map.put("loginName",userResult.getLoginName());
            map.put("token",jwtToken);//token
            map.put("userId",userResult.getUserId());//用户userId
            map.put("userName",userResult.getUserName());//用户名称
//            map.put("auth",stringList);//菜单列表
//            map.put("roleId",sysRoles);//角色列表
            return AjaxResult.success(map);
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }catch (Exception e){
            e.printStackTrace();
            return error("请求异常!");
        }

    }
    @ApiOperation("小程序退出接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName",value = "用户名", required = true)
    })
    @PostMapping("/quit")
    @ResponseBody
    @PassToken
    public AjaxResult quit(@RequestBody SysUser user){
//        SysUser user1 = ShiroUtils.getSysUser();
        if (StringUtils.isNotNull(user))
        {
            String loginName = user.getLoginName();
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success")));
        }
        return  AjaxResult.success();
    }
    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}
