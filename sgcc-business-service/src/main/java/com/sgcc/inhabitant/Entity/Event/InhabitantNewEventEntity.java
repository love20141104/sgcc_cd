package com.sgcc.inhabitant.Entity.Event;

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
public class InhabitantNewEventEntity  {
    @Autowired
    private InhabitantNewRepository inhabitantNewRepository;
    public void UpdateInhabitantNew( InhabitantNewDao dao )
    {
        inhabitantNewRepository.save(dao );
    }
    public void SaveInhabitantNew( InhabitantNewDao dao )
    {
        inhabitantNewRepository.update( dao );
    }
    public void Deletes( List<String> ids )
    {
        inhabitantNewRepository.deletes( ids );
    }
}
