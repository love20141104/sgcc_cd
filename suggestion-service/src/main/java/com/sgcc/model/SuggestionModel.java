package com.sgcc.model;

import com.example.IDUtil;
import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.*;
import com.sgcc.dto.*;
import com.sgcc.repository.SuggestionReplyRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
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
    private SuggestionSubmitDTO m_suggestionSubmitDTO = null;
    private SuggestionReplyDTO m_suggestionUpdatetDTO = null;
    private String m_userId = null;
    private String m_errMsg = "";
    private SuggestionDao m_dao = null;

    public SuggestionModel( SuggestionSubmitDTO dto  ){
        m_suggestionSubmitDTO = dto;
    }
    public SuggestionModel( SuggestionReplyDTO updateDto  ){
        m_suggestionUpdatetDTO = updateDto;
    }
    public SuggestionModel( SuggestionSubmitDTO dto , List<SuggestionDao> daos){
        m_suggestionSubmitDTO = dto;
        m_suggestionDaos = daos;
    }
    public SuggestionModel( List<SuggestionDao> daos ){
        m_suggestionDaos = daos;
    }
    public SuggestionModel( String userId ){
        m_userId = userId;
    }

    public ReplierAndCheckerDao GetFirstMatch(List<ReplierAndCheckerDao> daos , String location )
    {
        if( daos == null || daos.size() < 1 || Strings.isNullOrEmpty(location) )
            return null;
        for( ReplierAndCheckerDao dao : daos )
        {
            if( location.contains( dao.getMajor_region() ) )
            {
                return dao;
            }
        }
        return null;
    }
    public SuggestionReplyDao GetSuggestionReplyDao( SuggestionReplyContentDTO dto )
    {
        SuggestionReplyDao dao = new SuggestionReplyDao();
        dao.setId( UUID.randomUUID().toString() );
        dao.setSuggestion_id( dto.getSuggestion_id() );
        dao.setReply_content( dto.getReply_content() );
        dao.setReply_date( Utils.GetCurrentTime() );
        return dao;
    }

    public SuggestionCheckDao GetSuggestionCheckDao( SuggestionReplyCheckDTO dto )
    {
        SuggestionCheckDao dao = new SuggestionCheckDao();
        dao.setId( UUID.randomUUID().toString() );
        dao.setSuggestion_id( dto.getSuggestion_id() );
        dao.setCheck_date( Utils.GetCurrentTime() );
        dao.setCheck_state( dto.getCheck_state() );
        return dao;
    }


    public List<SuggestionViewDTO> DAOS2DTOS( List<SuggestionDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;

        List<SuggestionViewDTO> dtos = new ArrayList<>();
        for( SuggestionDao dao : daos ){
            dtos.add(DAO2DTO(dao));
        }
        return dtos;
    }
    public SuggestionViewDTO DAO2DTO( SuggestionDao dao )
    {
        if( dao == null )
            return null;

        SuggestionViewDTO dto = new SuggestionViewDTO();
        BeanUtils.copyProperties(dao,dto);
        dto.setReplyDate(Utils.GetTime(dao.getReplyDate()));
        dto.setSubmitDate(Utils.GetTime(dao.getSubmitDate()));
        return dto;
    }
    public List<SuggestionViewDTO> RedisDAOS2DTOS( List<SuggestionRedisDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;

        List<SuggestionViewDTO> dtos = new ArrayList<>();
        for( SuggestionRedisDao dao : daos ){
            dtos.add(RedisDAO2DTO(dao));
        }
        return dtos;
    }
    public SuggestionViewDTO RedisDAO2DTO( SuggestionRedisDao dao )
    {
        if( dao == null )
            return null;

        SuggestionViewDTO dto = new SuggestionViewDTO();
        BeanUtils.copyProperties(dao,dto);
        return dto;
    }

    public SuggestionRedisDao Dao2RedisDao( SuggestionDao dao )
    {
        if( dao == null )
            return null;
        SuggestionRedisDao rdao = new SuggestionRedisDao();
        rdao.setID(dao.getId());
        rdao.setSubmitDate(dao.getSubmitDate());
        rdao.setSuggestionContact(dao.getSuggestionContact());
        rdao.setSuggestionContent(dao.getSuggestionContent());
        rdao.setSuggestionId(dao.getSuggestionId());
        rdao.setSuggestionTel(dao.getSuggestionTel());
        rdao.setUserId(dao.getUserId());
        rdao.setReplyUserId(dao.getReplyUserId());
        rdao.setReplyDate(dao.getReplyDate());
        rdao.setReplyContent(dao.getReplyContent());

        if( !Strings.isNullOrEmpty(dao.getImg_1()) && Utils.verifyUrl(dao.getImg_1()) )
            rdao.setImg_1(dao.getImg_1());
        else
            rdao.setMediaId_1(dao.getImg_1());

        if( !Strings.isNullOrEmpty(dao.getImg_2()) && Utils.verifyUrl(dao.getImg_2()) )
            rdao.setImg_2(dao.getImg_2());
        else
            rdao.setMediaId_2(dao.getImg_2());

        if( !Strings.isNullOrEmpty(dao.getImg_3()) && Utils.verifyUrl(dao.getImg_3()) )
            rdao.setImg_3(dao.getImg_3());
        else
            rdao.setMediaId_3(dao.getImg_3());

        return rdao;
    }
    public List<SuggestionRedisDao> ListDao2ListRedisDao(List<SuggestionDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<SuggestionRedisDao> list = new ArrayList<>();
        for( SuggestionDao dao:daos)
            list.add(Dao2RedisDao(dao));

        return list;
    }
    public SuggestionRedisDaos ListDao2RedisDaos(List<SuggestionDao> daos )
    {
        SuggestionRedisDaos ret = new SuggestionRedisDaos();
        ret.setSuggestionRedisDaoList(  ListDao2ListRedisDao(daos) );
        return ret;
    }
    public SuggestionDao DTO2DAO( SuggestionSubmitDTO dto )
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

        if( !Strings.isNullOrEmpty( m_suggestionSubmitDTO.getMedia_1() ) )
            dao.setImg_1(dto.getMedia_1());
        else
            dao.setImg_1("");

        if( !Strings.isNullOrEmpty( m_suggestionSubmitDTO.getMedia_2() ) )
            dao.setImg_2(dto.getMedia_2());
        else
            dao.setImg_2("");

        if( !!Strings.isNullOrEmpty( m_suggestionSubmitDTO.getMedia_3() ) )
            dao.setImg_3(dto.getMedia_3());
        else
            dao.setImg_3("");

        return dao;
    }
    public SuggestionDetailDTO RedisDAO2DetailDTO(SuggestionRedisDao dao)
    {
        if( dao == null )
            return null;
        SuggestionDetailDTO dto = new SuggestionDetailDTO();
        dto.setId(dao.getID());
        dto.setSuggestionId( dao.getSuggestionId());
        dto.setUserId( dao.getUserId());
        dto.setSuggestionContent( dao.getSuggestionContent());
        dto.setSuggestionContact( dao.getSuggestionContact());
        dto.setSuggestionTel( dao.getSuggestionTel());
        dto.setSubmitDate( dao.getSubmitDate()==null?null:Utils.GetTime(dao.getSubmitDate()));
        dto.setImg_1( dao.getImg_1());
        dto.setMedia_1( dao.getMediaId_1());
        dto.setImg_2( dao.getImg_2());
        dto.setMedia_2( dao.getMediaId_2());
        dto.setImg_3( dao.getImg_3());
        dto.setMedia_3( dao.getMediaId_3());
        dto.setCheck_state(dao.getCheckState()==null?false:dao.getCheckState());
        dto.setReplyContent(dao.getReplyContent());
        dto.setReplyDate(dao.getReplyDate()==null?null:Utils.GetTime(dao.getReplyDate()));
        return dto;
    }
    public SuggestionDao DTO2DAO(  )
    {
        if( m_suggestionSubmitDTO == null )
            return null;

        SuggestionDao dao = new SuggestionDao();
        dao.setId(UUID.randomUUID().toString());
        dao.setSuggestionId(IDUtil.generate12NumId());
        dao.setSuggestionTel(m_suggestionSubmitDTO.getSuggestionTel());
        dao.setSuggestionContact(m_suggestionSubmitDTO.getSuggestionContact());
        dao.setSuggestionContent(m_suggestionSubmitDTO.getSuggestionContent());
        dao.setUserId(m_suggestionSubmitDTO.getUserId());
        dao.setSubmitDate(new Date());
        dao.setUserLocation(m_suggestionSubmitDTO.getUserLocation());
        dao.setCheckState(false);

        if( !Strings.isNullOrEmpty( m_suggestionSubmitDTO.getMedia_1() ) )
            dao.setImg_1(m_suggestionSubmitDTO.getMedia_1());
        else
            dao.setImg_1("");

        if( !Strings.isNullOrEmpty( m_suggestionSubmitDTO.getMedia_2() ))
            dao.setImg_2(m_suggestionSubmitDTO.getMedia_2());
        else
            dao.setImg_2("");

        if( !Strings.isNullOrEmpty( m_suggestionSubmitDTO.getMedia_3() ))
            dao.setImg_3(m_suggestionSubmitDTO.getMedia_3());
        else
            dao.setImg_3("");

        return dao;
    }
    public SuggestionRedisDao Dao2RedisDao( )
    {
        if( m_dao == null )
            return null;
        SuggestionRedisDao rdao = new SuggestionRedisDao();
        rdao.setID(m_dao.getId());
        rdao.setImg_1(m_dao.getImg_1());
        rdao.setImg_2(m_dao.getImg_2());
        rdao.setImg_3(m_dao.getImg_3());
        rdao.setSubmitDate(m_dao.getSubmitDate());
        rdao.setSuggestionContact(m_dao.getSuggestionContact());
        rdao.setSuggestionContent(m_dao.getSuggestionContent());
        rdao.setSuggestionId(m_dao.getSuggestionId());
        rdao.setSuggestionTel(m_dao.getSuggestionTel());
        rdao.setUserId(m_dao.getUserId());
        rdao.setReplyUserId(m_dao.getReplyUserId());
        rdao.setReplyDate(m_dao.getReplyDate());
        rdao.setReplyContent(m_dao.getReplyContent());
        return rdao;
    }
    public SuggestionDao MapDTO2DAO(SuggestionMappingDTO dto )
    {
        SuggestionDao dao = new SuggestionDao();
        dao.setId(dto.getId());
        dao.setSuggestionId(dto.getSuggestionId());
        dao.setSuggestionContact(dto.getSuggestionContact());
        dao.setSuggestionContent(dto.getSuggestionContent());
        dao.setSuggestionTel(dto.getSuggestionTel());
        dao.setUserId(dto.getUserId());
        dao.setSubmitDate(dto.getSubmitDate());
        dao.setReplyUserId(dto.getReplyUserId());
        dao.setReplyDate(dto.getReplyDate());
        dao.setReplyContent(dto.getReplyContent());
        dao.setImg_1(dto.getImg_1());
        dao.setImg_2(dto.getImg_2());
        dao.setImg_3(dto.getImg_3());
        return dao;
    }
    public SuggestionMappingDTO DAO2MapDTO( SuggestionDao dao )
    {
        SuggestionMappingDTO dto = new SuggestionMappingDTO();
        dto.setId(dao.getId());
        dto.setSuggestionId(dao.getSuggestionId());
        dto.setSuggestionContact(dao.getSuggestionContact());
        dto.setSuggestionContent(dao.getSuggestionContent());
        dto.setSuggestionTel(dao.getSuggestionTel());
        dto.setUserId(dao.getUserId());
        dto.setSubmitDate(dao.getSubmitDate());
        dto.setReplyUserId(dao.getReplyUserId());
        dto.setReplyDate(dao.getReplyDate());
        dto.setReplyContent(dao.getReplyContent());
        dto.setImg_1(dao.getImg_1());
        dto.setImg_2(dao.getImg_2());
        dto.setImg_3(dao.getImg_3());
        return dto;
    }
    public List<SuggestionDao> MapDTOs2DAOs(List<SuggestionMappingDTO> dtos)
    {
        if( dtos == null || dtos.size() < 1 )
            return null;
        List<SuggestionDao> daos = new ArrayList<>();
        for( SuggestionMappingDTO dto : dtos )
        {
            daos.add( MapDTO2DAO(dto) );
        }
        return daos;
    }
    public List< SuggestionMappingDTO> DAOs2MapDTOs(List<SuggestionDao> daos)
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<SuggestionMappingDTO> dtos = new ArrayList<>();
        for( SuggestionDao dao : daos )
        {
            dtos.add( DAO2MapDTO(dao) );
        }
        return dtos;
    }


    public List<SuggestionReplyInfoDTO> getSuggestionReplyByOpenIdTrans(List<SuggestionReplyInfoDao> suggestionReplyInfoDaos) {

        List<SuggestionReplyInfoDTO> dtos = new ArrayList<>();
        suggestionReplyInfoDaos.forEach(dao->{
            SuggestionReplyInfoDTO dto = new SuggestionReplyInfoDTO();
            BeanUtils.copyProperties(dao,dto);
            //1 处理人未回复 2未审批 3 审批未通过 4 审批通过
            if (dao.getReply_content()==null
                    &&dao.getReply_date()==null
                    &&dao.getCheck_date()==null
                    &&dao.getCheck_reject()==null
                    &&dao.getCheck_state()==0)
            {
                dto.setState("1");
            }

            if (dao.getReply_content()!=null
                    &&dao.getReply_date()!=null
                    &&dao.getCheck_date()==null
                    &&dao.getCheck_reject()==null
                    &&dao.getCheck_state()==0){
                dto.setState("2");
            }
            if (dao.getReply_content()!=null
                    &&dao.getReply_date()!=null
                    &&dao.getCheck_date()!=null
                    &&dao.getCheck_reject()!=null
                    &&dao.getCheck_state()==0){
                dto.setState("3");
            }

            if (dao.getReply_content()!=null
                    &&dao.getReply_date()!=null
                    &&dao.getCheck_date()!=null
                    &&dao.getCheck_state()==1){
                dto.setState("4");
            }
            dto.setSubmitDate(dao.getSubmitDate()==null?null:Utils.GetTime(dao.getSubmitDate()));
            dto.setReply_date(dao.getReply_date()==null?null:Utils.GetTime(dao.getReply_date()));
            dto.setCheck_date(dao.getCheck_date()==null?null:Utils.GetTime(dao.getCheck_date()));
            dtos.add(dto);
        });

        return dtos;

    }




    public SuggestionRejectDTO DAO2RejectDTO(SuggestionRejectDao dao)
    {
        SuggestionRejectDTO dto = new SuggestionRejectDTO();
        dto.setId(dao.getId());
        dto.setSuggestionId(dao.getSuggestionId());
        dto.setSuggestionContact(dao.getSuggestionContact());
        dto.setSuggestionContent(dao.getSuggestionContent());
        dto.setSuggestionTel(dao.getSuggestionTel());
        dto.setUserId(dao.getUserId());
        dto.setSubmitDate(dao.getSubmitDate());
        dto.setReplyUserId(dao.getReplyUserId());
        dto.setReplyDate(dao.getReplyDate());
        dto.setReplyContent(dao.getReplyContent());
        dto.setImg_1(dao.getImg_1());
        dto.setImg_2(dao.getImg_2());
        dto.setImg_3(dao.getImg_3());
        dto.setReplyReject(dao.getCheck_reject());
        return dto;
    }

    public List<SuggestionRejectDTO> DAOs2RejectDTOs(List<SuggestionRejectDao> daos)
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<SuggestionRejectDTO> dtos = new ArrayList<>();
        for( SuggestionRejectDao dao : daos )
        {
            dtos.add( DAO2RejectDTO(dao) );
        }
        return dtos;
    }

    public List<SuggestionReplyCheckInfoDTO> suggestionReplyCheckInfoListTrans(List<SuggestionReplyCheckInfoDao> daos) {
        List<SuggestionReplyCheckInfoDTO> suggestionReplyCheckInfoDTOS = new ArrayList<>();
        daos.forEach(dao->{
            SuggestionReplyCheckInfoDTO dto = new SuggestionReplyCheckInfoDTO();
            BeanUtils.copyProperties(dao,dto);
            dto.setReplyDate(dao.getReplyDate()==null?null:Utils.GetTime(dao.getReplyDate()));
            dto.setSubmitDate(dao.getSubmitDate()==null?null:Utils.GetTime(dao.getSubmitDate()));
            dto.setCheckerDate(dao.getCheckerDate()==null?null:Utils.GetTime(dao.getCheckerDate()));
            suggestionReplyCheckInfoDTOS.add(dto);
        });
        return suggestionReplyCheckInfoDTOS;
    }


    public SuggestionDetailDTO dAO2DetailDTO(SuggestionReplyInfoDao dao) {
        if( dao == null )
            return null;
        SuggestionDetailDTO dto = new SuggestionDetailDTO();
        dto.setId(dao.getId());
        dto.setSuggestionId( dao.getSuggestion_id());
        dto.setUserId( dao.getUserId());
        dto.setSuggestionContent( dao.getSuggestionContent());
        dto.setSuggestionContact( dao.getSuggestionContact());
        dto.setSuggestionTel( dao.getSuggestionTel());
        dto.setSubmitDate(dao.getSubmitDate()==null?null:Utils.GetTime(dao.getSubmitDate()));
        dto.setImg_1( dao.getImg_1());
        //dto.setMedia_1( dao.getMediaId_1());
        dto.setImg_2( dao.getImg_2());
        //dto.setMedia_2( dao.getMediaId_2());
        dto.setImg_3( dao.getImg_3());
        //dto.setMedia_3( dao.getMediaId_3());
        dto.setCheck_state(dao.getCheck_state()==1?true:false);
        dto.setReplyContent(dao.getReply_content());
        dto.setReplyDate(dao.getReply_date()==null?null:Utils.GetTime(dao.getReply_date()));
        return dto;
    }
}
