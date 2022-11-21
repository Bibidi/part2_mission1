package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.CommonParam;

import java.util.List;

public interface BannerService {
    List<BannerDto> list(CommonParam commonParam);
    List<BannerDto> list();
    long listTotalCount();
    BannerDto findByUserId(long id);
    boolean add(BannerDto bannerDto);
    boolean update(BannerDto bannerDto);
    boolean delete(BannerDto bannerDto);
}
