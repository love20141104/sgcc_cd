package com.sgcc.inhabitant.Model;

import com.example.Utils;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
import com.sgcc.inhabitant.dto.InhabitantNewDTO;
import com.sgcc.inhabitant.dto.InhabitantSubmitDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InhabitantModel {

    public InhabitantNewDTO InhabitantNewDao2DTO(InhabitantNewDao dao )
    {
        InhabitantNewDTO dto = new InhabitantNewDTO();
        BeanUtils.copyProperties( dao,dto);
        return dto;
    }
    public InhabitantNewDao InhabitantNewDTO2Dao( InhabitantNewDTO dto )
    {
        InhabitantNewDao dao = new InhabitantNewDao();
        BeanUtils.copyProperties( dto,dao );
        return dao;
    }
    public InhabitantNewDao InhabitantSubmitDTO2Dao( InhabitantSubmitDTO dto )
    {
        InhabitantNewDao dao = new InhabitantNewDao();
        BeanUtils.copyProperties( dto,dao );
        dao.setId(UUID.randomUUID().toString());
        dao.setSubmit_date(Utils.GetCurTime());
        return dao;
    }
    public List<InhabitantNewDTO> InhabitantNewDaos2Dtos(List<InhabitantNewDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<InhabitantNewDTO> dtos = new ArrayList<>();
        for (InhabitantNewDao dao:daos )
            dtos.add(InhabitantNewDao2DTO(dao));

        return dtos;
    }
}
