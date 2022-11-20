package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.dto.UserLoginHistoryDto;
import com.zerobase.fastlms.admin.entity.UserLoginHistory;
import com.zerobase.fastlms.admin.mapper.UserLoginHistoryMapper;
import com.zerobase.fastlms.admin.repository.UserLoginHistoryRepository;
import com.zerobase.fastlms.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserLoginHistoryServiceImpl implements UserLoginHistoryService {

    private final UserLoginHistoryRepository userLoginHistoryRepository;
    private final UserLoginHistoryMapper userLoginHistoryMapper;

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

    @Override
    public List<UserLoginHistoryDto> findAllByUserId(String userId) {
        List<UserLoginHistoryDto> list = new ArrayList<>();
        userLoginHistoryRepository.findAllByUserIdOrderByLoginDtDesc(userId).forEach(history -> list.add(
                new UserLoginHistoryDto().builder()
                        .userId(history.getUserId())
                        .clientIP(history.getClientIP())
                        .userAgent(history.getUserAgent())
                        .loginDt(history.getLoginDt())
                        .build()
        ));
        return list;
    }

    @Override
    public LocalDateTime findLastLoginDate(MemberDto memberDto) {
        return userLoginHistoryMapper.selectLastLogin(new Member().builder()
                .userId(memberDto.getUserId())
                .build());
    }

    @Override
    public List<UserLoginHistoryDto> findAllByUserId(MemberDto memberDto) {
        List<UserLoginHistory> userLoginHistories = userLoginHistoryMapper.selectAllByUserId(new Member().builder()
                .userId(memberDto.getUserId())
                .build());

        return userLoginHistories.stream().map(entity -> new UserLoginHistoryDto().builder()
                        .userId(entity.getUserId())
                        .loginDt(entity.getLoginDt())
                        .clientIP(entity.getClientIP())
                        .userAgent(entity.getUserAgent())
                        .build())
                .collect(Collectors.toList());
    }
}
