package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.UserLoginHistoryDto;

public interface UserLoginHistoryService {
    boolean add(UserLoginHistoryDto userLoginHistoryDto);
}
