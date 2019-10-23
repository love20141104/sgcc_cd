package com.sgcc.commerce.Entity.Query;

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
public class CommerceChangeTaxInfoQueryEntity {
    @Autowired
    private CommerceChangeTaxInfoRepository commerceChangeTaxInfoRepository;
    public List<CommerceChangeTaxInfoDao> GetAll()
    {
        return commerceChangeTaxInfoRepository.findAll();
    }
    public CommerceChangeTaxInfoDao GetById(String id )
    {
        return commerceChangeTaxInfoRepository.findById(id);
    }
}
