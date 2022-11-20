package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.admin.dto.UserLoginHistoryDto;
import com.zerobase.fastlms.admin.entity.UserLoginHistory;
import com.zerobase.fastlms.admin.repository.UserLoginHistoryRepository;
import com.zerobase.fastlms.admin.service.UserLoginHistoryService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Enumeration;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserLoginHistoryService userLoginHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        userLoginHistoryService.add(new UserLoginHistoryDto().builder()
                        .userId(user.getUsername())
                        .clientIP(RequestUtils.getClientIP(request))
                        .userAgent(RequestUtils.getUserAgent(request))
                        .loginDt(LocalDateTime.now()).build());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
