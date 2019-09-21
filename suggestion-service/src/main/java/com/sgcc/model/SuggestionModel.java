package com.sgcc.model;

import com.example.Utils;
import com.sgcc.dao.SuggestionDao;
import com.sgcc.dto.SuggestionViewDTO;
import com.sgcc.dto.SuggetionSubmitDTO;
import com.sgcc.entity.event.SuggestionQueryEntity;
import com.sgcc.entity.query.SuggestionEventEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SuggestionModel {
    private List<SuggestionDao> m_suggestionDaos = null;
    private List<SuggestionViewDTO> m_suggestionViewDTO = null;
    private SuggetionSubmitDTO m_suggetionSubmitDTO = null;
    private String m_userId = null;
    private String m_errMsg = "";
    @Autowired
    private SuggestionQueryEntity suggestionQueryEntity;

    @Autowired
    private SuggestionEventEntity suggestionEventEntity;

    public SuggestionModel( SuggetionSubmitDTO dto ,String userId ){
        m_suggetionSubmitDTO = dto;
        m_userId = userId;
    }

    public SuggestionModel(SuggetionSubmitDTO dto ,List<SuggestionDao> daos){
        m_suggetionSubmitDTO = dto;
        m_suggestionDaos = daos;
    }

    public SuggestionModel( List<SuggestionDao> daos ){
        m_suggestionDaos = daos;
    }

    public List<SuggestionViewDTO> DAOS2DTOS( List<SuggestionDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;

        List<SuggestionViewDTO> dtos = new ArrayList<>();
        for( SuggestionDao dao : daos ){
            SuggestionViewDTO dto = new SuggestionViewDTO();
            dto.setSuggestionId(dao.getSuggestionId());
            dto.setSuggestionContent( dao.getSuggestionContent());
            dto.setImg_1( dao.getImg_1());
            dto.setImg_2( dao.getImg_2());
            dto.setImg_3( dao.getImg_3());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<SuggestionViewDTO> GetAllSuggestions( )
    {
        List<SuggestionDao> daos = m_suggestionDaos;
        if( daos == null || daos.size() < 1 )
            return null;

        return DAOS2DTOS(daos);
    }

    public SuggestionDao DTO2DAO( SuggetionSubmitDTO dto )
    {
        if( dto == null )
            return null;

        SuggestionDao dao = new SuggestionDao();
        dao.setId(UUID.randomUUID().toString());
        dao.setSuggestionId(dao.getId());
        dao.setSuggestionTel(dto.getSuggestionTel());
        dao.setSuggestionContact(dto.getSuggestionContact());
        dao.setSuggestionContent(dto.getSuggestionContent());
        dao.setUserId(dto.getUserId());
        dao.setSubmitDate(new Date());

        if( !dto.getMedia_1().equals("") )
            dao.setImg_1(dto.getMedia_1());
        else
            dao.setImg_1("");

        if( !dto.getMedia_2().equals("") )
            dao.setImg_2(dto.getMedia_2());
        else
            dao.setImg_2("");

        if( !dto.getMedia_3().equals("") )
            dao.setImg_3(dto.getMedia_3());
        else
            dao.setImg_3("");

        return dao;
    }

    public int submit()
    {
        // check inputDto
        SuggestionDao dao = DTO2DAO(m_suggetionSubmitDTO);
        if( dao == null ){
            m_errMsg = "";
            return -1;
        }

        suggestionEventEntity.Save( dao );
        return 0;
    }
}
