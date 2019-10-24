package com.sgcc.model;

import com.sgcc.dao.BannerDao;
import com.sgcc.dto.BannerDTO;
import com.sgcc.dto.BannerSubmitDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class BannerModel {
    public BannerDTO BannerDao2DTO(BannerDao dao)
    {
        if( dao == null )
            return null;
        BannerDTO dto = new BannerDTO();
        BeanUtils.copyProperties( dao,dto);
        return dto;
    }
    public BannerDao BannerDTO2Dao(BannerDTO dto)
    {
        if( dto == null )
            return null;
        BannerDao dao = new BannerDao();
        BeanUtils.copyProperties( dto,dao);
        return dao;
    }
    public BannerDao BannerSubmitDTO2Dao(BannerSubmitDTO dto)
    {
        if( dto == null )
            return null;
        BannerDao dao = new BannerDao();
        BeanUtils.copyProperties( dto,dao);
        dao.setId(UUID.randomUUID().toString());
        return dao;
    }
    public List<BannerDTO> BannerDaos2DTOs(List<BannerDao> daos)
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<BannerDTO> dtos = new ArrayList<>();
        for( BannerDao dao :daos)
            dtos.add( BannerDao2DTO(dao));
        return dtos;
    }
}
