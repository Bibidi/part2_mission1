package com.zerobase.fastlms.admin.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserLoginHistoryDto {
    private String userId;
    private LocalDateTime loginDt;
    private String clientIP;
    private String userAgent;
}
