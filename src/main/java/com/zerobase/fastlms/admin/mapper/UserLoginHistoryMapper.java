package com.zerobase.fastlms.admin.mapper;

import com.zerobase.fastlms.admin.entity.UserLoginHistory;
import com.zerobase.fastlms.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserLoginHistoryMapper {
    LocalDateTime selectLastLogin(Member member);

    List<UserLoginHistory> selectAllByUserId(Member member);
}
