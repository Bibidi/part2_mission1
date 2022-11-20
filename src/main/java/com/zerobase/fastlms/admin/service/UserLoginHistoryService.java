package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.dto.UserLoginHistoryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface UserLoginHistoryService {
    boolean add(UserLoginHistoryDto userLoginHistoryDto);

    List<UserLoginHistoryDto> findAllByUserId(String userId);

    LocalDateTime findLastLoginDate(MemberDto memberDto);

    List<UserLoginHistoryDto> findAllByUserId(MemberDto memberDto);
}
