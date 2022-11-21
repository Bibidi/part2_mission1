package com.zerobase.fastlms.admin.mapper;

import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.model.CommonParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    List<Banner> selectList(CommonParam commonParam);
}
