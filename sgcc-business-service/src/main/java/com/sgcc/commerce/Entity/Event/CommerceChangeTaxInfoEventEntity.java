package com.sgcc.commerce.Entity.Event;

import com.sgcc.commerce.dao.CommerceChangeTaxInfoDao;
import com.sgcc.commerce.repository.CommerceChangeTaxInfoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class CommerceChangeTaxInfoEventEntity {
    @Autowired
    private CommerceChangeTaxInfoRepository commerceChangeTaxInfoRepository;
    public void UpdateCommerceChangeTaxInfo( CommerceChangeTaxInfoDao dao )
    {
        commerceChangeTaxInfoRepository.save(dao );
    }
    public void SaveCommerceChangeTaxInfo( CommerceChangeTaxInfoDao dao )
    {
        commerceChangeTaxInfoRepository.update( dao );
    }
    public void Deletes( List<String> ids )
    {
        commerceChangeTaxInfoRepository.deletes( ids );
    }
}
