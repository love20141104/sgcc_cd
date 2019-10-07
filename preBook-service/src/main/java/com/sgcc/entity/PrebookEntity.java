package com.sgcc.entity;

import com.sgcc.dao.PreBookDao;
import com.sgcc.repository.PreBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrebookEntity {

    @Autowired
    private PreBooksRepository preBooksRepository;

    /**
     * 添加在线预约信息
     *
     * @param preBookDaoList
     */
    public void savePrebooks(List<PreBookDao> preBookDaoList) {
        preBooksRepository.addPreBook(preBookDaoList);
    }


}
