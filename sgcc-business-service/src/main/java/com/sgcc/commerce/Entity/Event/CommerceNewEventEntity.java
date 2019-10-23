package com.sgcc.commerce.Entity.Event;
import com.sgcc.commerce.dao.CommerceNewDao;
import com.sgcc.commerce.repository.CommerceNewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CommerceNewEventEntity {
    @Autowired
    private CommerceNewRepository commerceNewRepository;
    public void UpdateCommerceNew( CommerceNewDao dao )
    {
        commerceNewRepository.update(dao );
    }
    public void SaveCommerceNew( CommerceNewDao dao )
    {
        commerceNewRepository.save( dao );
    }
    public void Deletes( List<String> ids )
    {
        commerceNewRepository.deletes( ids );
    }
}
