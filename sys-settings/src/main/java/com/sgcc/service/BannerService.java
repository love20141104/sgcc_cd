package com.sgcc.service;

import com.sgcc.Entity.Event.BannerEventEntity;
import com.sgcc.Entity.Query.BannerQueryEntity;
import com.sgcc.dao.BannerDao;
import com.sgcc.dto.BannerDTO;
import com.sgcc.dto.BannerSubmitDTO;
import com.sgcc.dto.DeleteDTO;
import com.sgcc.model.BannerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {
    @Autowired
    private BannerQueryEntity bannerQueryEntity;
    @Autowired
    private BannerEventEntity bannerEventEntity;

    public void SubmitBanner( BannerSubmitDTO dto )
    {
        BannerModel model = new BannerModel();
        BannerDao dao = model.BannerSubmitDTO2Dao(dto);
        if( dao == null )
            return;
        bannerEventEntity.SaveBanner(dao);
    }

    public void UpdateBanner( BannerDTO dto )
    {
        BannerModel model = new BannerModel();
        BannerDao dao = model.BannerDTO2Dao(dto);
        if( dao == null )
            return;
        bannerEventEntity.UpdateBanner(dao);
    }

    public List<BannerDTO> GetAllBanners()
    {
        List<BannerDao> daos = bannerQueryEntity.GetAll();
        if( daos ==null || daos.size() < 1 )
            return null;
        BannerModel model = new BannerModel();
        return model.BannerDaos2DTOs(daos);
    }

    public BannerDTO GetBanner(String id)
    {
        BannerDao dao = bannerQueryEntity.GetById(id);
        if( dao ==null )
            return null;
        BannerModel model = new BannerModel();
        return model.BannerDao2DTO(dao);
    }

    public void DeleteBanners( DeleteDTO dto )
    {
        if( dto == null || dto.getIds() == null || dto.getIds().size() < 1)
            return;
        bannerEventEntity.DeleteBanners(dto.getIds());
    }
}
