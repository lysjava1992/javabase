package com.test.securtiy.component.sms;

import com.test.securtiy.component.ajax.AjaxLoginAuthenticationToken;
import com.test.securtiy.model.CustomUser;
import com.test.securtiy.service.UserService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;

public class SmsLoginAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;
    private SmsCodeMemory smsCodeMemory=SmsCodeMemory.getInstance();

    public void setUserDetailsService(UserService userDetailsService) {
        this.userService = userDetailsService;
    }

    /**
     * 认证过程
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phone= (String) authentication.getPrincipal();
        String  code= (String) authentication.getCredentials();
        CustomUser user=userService.findByPhone(phone);
        SmsCode smsCode= smsCodeMemory.getCode(phone);

        if (user == null) {
            throw new UsernameNotFoundException("无效手机号");
        } else if (smsCode==null) {
            throw new UsernameNotFoundException("请先获取验证码");
        } else if (!user.isEnabled()) {
            throw new DisabledException("用户已被禁用");
        } else if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        } else if (!user.isAccountNonLocked()) {
            throw new LockedException("账号已被锁定");
        } else if (!user.isCredentialsNonExpired()||smsCode.isExpired()) {
            throw new LockedException("凭证已过期");
        }else if(!code.equals(smsCode.getCode())){
            throw new BadCredentialsException("验证码错误");
        }
        smsCodeMemory.remove(phone);
        SmsLoginAuthenticationToken result = new SmsLoginAuthenticationToken(user,
                authentication.getCredentials(), user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return  result;
    }

    /**
     * 判断是否支持
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsLoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
