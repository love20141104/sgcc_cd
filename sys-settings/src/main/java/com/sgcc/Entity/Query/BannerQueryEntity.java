package com.sgcc.Entity.Query;

import com.sgcc.dao.BannerDao;
import com.sgcc.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BannerQueryEntity {
    @Autowired
    private BannerRepository bannerRepository;
    public List<BannerDao> GetAll()
    {
        return bannerRepository.findAll();
    }
    public BannerDao GetById(String id )
    {
        return bannerRepository.findById(id);
    }
}
