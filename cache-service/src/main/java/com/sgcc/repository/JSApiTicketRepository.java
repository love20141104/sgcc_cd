package com.sgcc.repository;

import com.sgcc.dao.JSApiTicketDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JSApiTicketRepository extends CrudRepository<JSApiTicketDao,String> {
}
