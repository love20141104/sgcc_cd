package com.sgcc.Entity.Event;
import com.sgcc.dao.BannerDao;
import com.sgcc.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BannerEventEntity {
    @Autowired
    private BannerRepository bannerRepository;
    public void UpdateBanner( BannerDao dao )
    {
        bannerRepository.update(dao);
    }
    public void SaveBanner( BannerDao dao )
    {
        bannerRepository.save(dao);
    }
    public void DeleteBanners( List<String> Ids )
    {
        bannerRepository.deletes(Ids);
    }
}
