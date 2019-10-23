package com.sgcc.inhabitant.Entity.Query;

import com.sgcc.inhabitant.Repository.InhabitantNewRepository;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class InhabitantNewQueryEntity {
    @Autowired
    private InhabitantNewRepository inhabitantNewRepository;
    public List<InhabitantNewDao> GetAll()
    {
        return inhabitantNewRepository.findAll();
    }
    public InhabitantNewDao GetById(String id )
    {
        return inhabitantNewRepository.findById(id);
    }
}
