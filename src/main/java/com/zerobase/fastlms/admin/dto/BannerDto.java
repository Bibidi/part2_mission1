package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerDto extends CommonParam {
    long id;
    String bannerName;
    String imagePath;
    String alterText;
    String linkAddress;
    String target;
    int sortValue;
    boolean usingYn;
    LocalDateTime regDt;
}
