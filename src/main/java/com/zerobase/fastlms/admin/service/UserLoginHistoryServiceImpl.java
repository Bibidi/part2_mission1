package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.UserLoginHistoryDto;
import com.zerobase.fastlms.admin.entity.UserLoginHistory;
import com.zerobase.fastlms.admin.repository.UserLoginHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoginHistoryServiceImpl implements UserLoginHistoryService {

    private final UserLoginHistoryRepository userLoginHistoryRepository;

    @Override
    public boolean add(UserLoginHistoryDto userLoginHistoryDto) {
        userLoginHistoryRepository.save(new UserLoginHistory().builder()
                .userId(userLoginHistoryDto.getUserId())
                .loginDt(userLoginHistoryDto.getLoginDt())
                .clientIP(userLoginHistoryDto.getClientIP())
                .userAgent(userLoginHistoryDto.getUserAgent())
                .build());
        return true;
    }
}
