package com.sgcc.model;

import com.sgcc.dao.ReadingQuantityStatistcsDao;
import com.sgcc.dto.ReadingQuantityStatistcsDTO;
import com.sgcc.dto.ReadingQuantityStatistcsViewDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReadingQuantityModel {
    private List<ReadingQuantityStatistcsDao> readingQuantityStatistcsDaos = new ArrayList<>();
    private List<ReadingQuantityStatistcsDTO> readingQuantityStatistcsDTOS = new ArrayList<>();
    private ReadingQuantityStatistcsViewDTO readingQuantityStatistcsViewDTO;

    public ReadingQuantityModel( List<ReadingQuantityStatistcsDao> readingQuantityStatistcsDaos){
        this.readingQuantityStatistcsDaos = new ArrayList<>(readingQuantityStatistcsDaos);
    }

    public void daos2dtos(){
        this.readingQuantityStatistcsDaos.forEach(dao->{
            readingQuantityStatistcsDTOS.add(
                    new ReadingQuantityStatistcsDTO(
                            dao.getTitle()
                            ,dao.getUrl()
                            ,dao.getReadNum()
                    )
            );
        });
    }

    public void viewDTOTransform(){
        this.readingQuantityStatistcsViewDTO = new ReadingQuantityStatistcsViewDTO(this.readingQuantityStatistcsDTOS);
    }

}
