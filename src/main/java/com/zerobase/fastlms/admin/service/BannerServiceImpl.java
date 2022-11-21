package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.CommonParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public List<BannerDto> list(CommonParam commonParam) {
        List<Banner> banners = bannerMapper.selectList(commonParam);
        List<BannerDto> list = new ArrayList<>();

        banners.forEach(banner -> {
            list.add(new BannerDto().builder()
                    .id(banner.getId())
                    .bannerName(banner.getBannerName())
                    .imagePath(banner.getImagePath())
                    .alterText(banner.getAlterText())
                    .linkAddress(banner.getLinkAddress())
                    .target(banner.getTarget())
                    .sortValue(banner.getSortValue())
                    .usingYn(banner.isUsingYn())
                    .regDt(banner.getRegDt())
                    .build());
        });

        return list;
    }

    @Override
    public List<BannerDto> list() {
        List<Banner> banners = bannerRepository.findAll();
        List<BannerDto> list = new ArrayList<>();

        banners.forEach(banner -> {
            list.add(new BannerDto().builder()
                    .id(banner.getId())
                    .bannerName(banner.getBannerName())
                    .imagePath(banner.getImagePath())
                    .alterText(banner.getAlterText())
                    .linkAddress(banner.getLinkAddress())
                    .target(banner.getTarget())
                    .sortValue(banner.getSortValue())
                    .usingYn(banner.isUsingYn())
                    .regDt(banner.getRegDt())
                    .build());
        });

        return list;
    }

    @Override
    public long listTotalCount() {
        return bannerRepository.count();
    }

    @Override
    public BannerDto findByUserId(long id) {
        Banner banner = bannerRepository.findById(id).get();
        return new BannerDto().builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .imagePath(banner.getImagePath())
                .alterText(banner.getAlterText())
                .linkAddress(banner.getLinkAddress())
                .target(banner.getTarget())
                .sortValue(banner.getSortValue())
                .usingYn(banner.isUsingYn())
                .regDt(banner.getRegDt())
                .build();
    }

    @Override
    public boolean add(BannerDto bannerDto) {
        bannerRepository.save(new Banner().builder()
                .bannerName(bannerDto.getBannerName())
                .imagePath(bannerDto.getImagePath())
                .alterText(bannerDto.getAlterText())
                .linkAddress(bannerDto.getLinkAddress())
                .target(bannerDto.getTarget())
                .sortValue(bannerDto.getSortValue())
                .usingYn(bannerDto.isUsingYn())
                .regDt(bannerDto.getRegDt())
                .build());

        return true;
    }

    @Override
    public boolean update(BannerDto bannerDto) {
        Optional<Banner> banner = bannerRepository.findById(bannerDto.getId());

        if (banner.isPresent()) {
            bannerRepository.save(new Banner().builder()
                    .id(bannerDto.getId())
                    .bannerName(bannerDto.getBannerName())
                    .imagePath(bannerDto.getImagePath())
                    .alterText(bannerDto.getAlterText())
                    .linkAddress(bannerDto.getLinkAddress())
                    .target(bannerDto.getTarget())
                    .sortValue(bannerDto.getSortValue())
                    .usingYn(bannerDto.isUsingYn())
                    .build());
        }

        return true;
    }

    @Override
    public boolean delete(BannerDto bannerDto) {
        bannerRepository.deleteById(bannerDto.getId());
        return true;
    }
}
