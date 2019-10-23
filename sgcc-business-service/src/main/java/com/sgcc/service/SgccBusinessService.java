package com.sgcc.service;

import com.sgcc.commerce.Entity.Event.CommerceChangeTaxInfoEventEntity;
import com.sgcc.commerce.Entity.Event.CommerceNewEventEntity;
import com.sgcc.commerce.Entity.Query.CommerceChangeTaxInfoQueryEntity;
import com.sgcc.commerce.Entity.Query.CommerceNewQueryEntity;
import com.sgcc.commerce.Model.CommerceModel;
import com.sgcc.commerce.dao.CommerceChangeTaxInfoDao;
import com.sgcc.commerce.dao.CommerceNewDao;
import com.sgcc.commerce.dto.*;
import com.sgcc.inhabitant.Entity.Event.InhabitantNewEventEntity;
import com.sgcc.inhabitant.Entity.Query.InhabitantNewQueryEntity;
import com.sgcc.inhabitant.Model.InhabitantModel;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
import com.sgcc.inhabitant.dto.InhabitantNewDTO;
import com.sgcc.inhabitant.dto.InhabitantSubmitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SgccBusinessService {
    @Autowired
    private CommerceNewQueryEntity commerceNewQueryEntity;
    @Autowired
    private CommerceNewEventEntity commerceNewEventEntity;
    @Autowired
    private InhabitantNewEventEntity inhabitantNewEventEntity;
    @Autowired
    private InhabitantNewQueryEntity inhabitantNewQueryEntity;
    @Autowired
    private CommerceChangeTaxInfoEventEntity commerceChangeTaxInfoEventEntity;
    @Autowired
    private CommerceChangeTaxInfoQueryEntity commerceChangeTaxInfoQueryEntity;
//CommerceChangeTaxInfoEventEntity
    // -------------------------个体工商业新装--------------------------------------
    // 微信端用
    public void SubmitCommerceNew( CommerceNewSubmitDTO dto )
    {
        CommerceModel model = new CommerceModel();
        CommerceNewDao dao = model.CommerceNewSubmitDTO2Dao(dto);
        if( dao == null )
            return;
        commerceNewEventEntity.SaveCommerceNew(dao);
    }

    public void UpdateCommerceNew( CommerceNewDTO dto )
    {
        CommerceModel model = new CommerceModel();
        CommerceNewDao dao = model.CommerceNewDTO2Dao(dto);
        if( dao == null )
            return;
        commerceNewEventEntity.UpdateCommerceNew(dao);
    }

    public List<CommerceNewDTO> GetAllCommerceNewRecords()
    {
        List<CommerceNewDao> daos = commerceNewQueryEntity.GetAll();
        if( daos ==null || daos.size() < 1 )
            return null;
        CommerceModel model = new CommerceModel();
        return model.CommerceNewDaos2Dtos(daos);
    }

    public CommerceNewDTO GetCommerceNewRecord(String id)
    {
        CommerceNewDao dao = commerceNewQueryEntity.GetById(id);
        if( dao ==null )
            return null;
        CommerceModel model = new CommerceModel();
        return model.CommerceNewDao2DTO(dao);
    }

    public void DeleteCommerceNewRecors( DeleteDTO dto )
    {
        if( dto == null || dto.getIds() == null || dto.getIds().size() < 1)
            return;
        commerceNewEventEntity.Deletes(dto.getIds());
    }

    // -------------------------居民新装--------------------------------------
    // 微信端用
    public void SubmitInhabitantNew( InhabitantSubmitDTO dto )
    {
        InhabitantModel model = new InhabitantModel();
        InhabitantNewDao dao = model.InhabitantSubmitDTO2Dao(dto);
        if( dao == null )
            return;
        inhabitantNewEventEntity.SaveInhabitantNew(dao);
    }

    public void UpdateInhabitantNew( InhabitantNewDTO dto )
    {
        InhabitantModel model = new InhabitantModel();
        InhabitantNewDao dao = model.InhabitantNewDTO2Dao(dto);
        if( dao == null )
            return;
        inhabitantNewEventEntity.UpdateInhabitantNew(dao);
    }

    public List<InhabitantNewDTO> GetAllInhabitantNewRecords()
    {
        List<InhabitantNewDao> daos = inhabitantNewQueryEntity.GetAll();
        if( daos ==null || daos.size() < 1 )
            return null;
        InhabitantModel model = new InhabitantModel();
        return model.InhabitantNewDaos2Dtos(daos);
    }

    public InhabitantNewDTO GetInhabitantNewRecord(String id)
    {
        InhabitantNewDao dao = inhabitantNewQueryEntity.GetById(id);
        if( dao ==null )
            return null;
        InhabitantModel model = new InhabitantModel();
        return model.InhabitantNewDao2DTO(dao);
    }

    public void DeleteInhabitantNewRecors( DeleteDTO dto )
    {
        if( dto == null || dto.getIds() == null || dto.getIds().size() < 1)
            return;
        inhabitantNewEventEntity.Deletes(dto.getIds());
    }
    // -------------------------税票变更--------------------------------------
    // 微信端用
    public void SubmitCommerceChangeTaxInfo( CommerceChangeTaxInfoSubmitDTO dto )
    {
        CommerceModel model = new CommerceModel();
        CommerceChangeTaxInfoDao dao = model.CommerceChangeTaxInfoSubmitDTO2Dao(dto);
        if( dao == null )
            return;
        commerceChangeTaxInfoEventEntity.SaveCommerceChangeTaxInfo(dao);
    }

    public void UpdateCommerceChangeTaxInfo( CommerceChangeTaxInfoDTO dto )
    {
        CommerceModel model = new CommerceModel();
        CommerceChangeTaxInfoDao dao = model.CommerceChangeTaxInfoDTO2Dao(dto);
        if( dao == null )
            return;
        commerceChangeTaxInfoEventEntity.UpdateCommerceChangeTaxInfo(dao);
    }

    public List<CommerceChangeTaxInfoDTO> GetAllCommerceChangeTaxInfoRecords()
    {
        List<CommerceChangeTaxInfoDao> daos = commerceChangeTaxInfoQueryEntity.GetAll();
        if( daos ==null || daos.size() < 1 )
            return null;
        CommerceModel model = new CommerceModel();
        return model.CommerceChangeTaxInfoDaos2Dtos(daos);
    }

    public CommerceChangeTaxInfoDTO GetCommerceChangeTaxInfoRecord(String id)
    {
        CommerceChangeTaxInfoDao dao = commerceChangeTaxInfoQueryEntity.GetById(id);
        if( dao ==null )
            return null;
        CommerceModel model = new CommerceModel();
        return model.CommerceChangeTaxInfoDao2DTO(dao);
    }

    public void DeleteCommerceChangeTaxInfoRecors( DeleteDTO dto )
    {
        if( dto == null || dto.getIds() == null || dto.getIds().size() < 1)
            return;
        commerceChangeTaxInfoEventEntity.Deletes(dto.getIds());
    }
}
