package com.zerobase.fastlms.admin.repository;

import com.zerobase.fastlms.admin.entity.UserLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLoginHistoryRepository extends JpaRepository<UserLoginHistory, Long> {
    List<UserLoginHistory> findAllByUserIdOrderByLoginDtDesc(String userId);
}
