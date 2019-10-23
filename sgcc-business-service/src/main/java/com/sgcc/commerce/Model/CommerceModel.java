package com.sgcc.commerce.Model;

import com.example.Utils;
import com.sgcc.commerce.dao.CommerceNewDao;
import com.sgcc.commerce.dao.CommerceRenameDao;
import com.sgcc.commerce.dto.CommerceNewDTO;
import com.sgcc.commerce.dto.CommerceNewSubmitDTO;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
import com.sgcc.inhabitant.dto.InhabitantNewDTO;
import com.sgcc.inhabitant.dto.InhabitantSubmitDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommerceModel {



    public CommerceNewDTO CommerceNewDao2DTO(CommerceNewDao dao )
    {
        CommerceNewDTO dto = new CommerceNewDTO();
        BeanUtils.copyProperties( dao,dto);
        return dto;
    }


    public CommerceNewDao CommerceNewDTO2Dao( CommerceNewDTO dto )
    {
        CommerceNewDao dao = new CommerceNewDao();
        BeanUtils.copyProperties( dto,dao );
        return dao;
    }


    public CommerceNewDao CommerceNewSubmitDTO2Dao( CommerceNewSubmitDTO dto)
    {
        CommerceNewDao dao = new CommerceNewDao();
        BeanUtils.copyProperties( dto,dao );
        dao.setId(UUID.randomUUID().toString());
        dao.setSubmit_date(Utils.GetCurTime());
        return dao;
    }


    public List<CommerceNewDTO> CommerceNewDaos2Dtos(List<CommerceNewDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<CommerceNewDTO> dtos = new ArrayList<>();
        for (CommerceNewDao dao:daos )
            dtos.add(CommerceNewDao2DTO(dao));

        return dtos;
    }
}
