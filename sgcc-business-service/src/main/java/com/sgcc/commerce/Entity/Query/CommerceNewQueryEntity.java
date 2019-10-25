package com.sgcc.commerce.Entity.Query;
import com.sgcc.commerce.dao.CommerceNewDao;
import com.sgcc.commerce.repository.CommerceNewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CommerceNewQueryEntity {
    @Autowired
    private CommerceNewRepository commerceNewRepository;
    public List<CommerceNewDao> GetAll()
    {
        return commerceNewRepository.findAll();
    }
    public CommerceNewDao GetById(String id )
    {
        return commerceNewRepository.findById(id);
    }


    public List<CommerceNewDao> findByOpenId(String openId )
    {
        return commerceNewRepository.findByOpenId(openId);
    }

}
