package com.sgcc.model;

import com.sgcc.dto.BusinessCategoryDto;
import com.sgcc.dto.BusinessCategoryHotDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BusinessHotModel {
    private List<String> hotIds = new ArrayList<>();
    private List<BusinessCategoryDto> businessCategoryDtos = new ArrayList<>();
    private List<BusinessCategoryHotDto> businessCategoryHotDtos = new ArrayList<>();

    public BusinessHotModel( List<String> hotIds,List<BusinessCategoryDto> businessCategoryDtos){
        this.hotIds = new ArrayList<>(hotIds);
        this.businessCategoryDtos = new ArrayList<>(businessCategoryDtos);
    }

    public void dto2hotDto(){
        this.businessCategoryDtos.forEach(dto->{
            if(this.hotIds.contains(dto.getId())){
                this.businessCategoryHotDtos.add(
                        new BusinessCategoryHotDto(dto.getId(),dto.getCategoryName(),dto.getNote(),true)
                );
            }else {
                this.businessCategoryHotDtos.add(
                        new BusinessCategoryHotDto(dto.getId(),dto.getCategoryName(),dto.getNote())
                );
            }
        });
    }
}
