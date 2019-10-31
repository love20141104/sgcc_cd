package com.sgcc.model;

import com.example.Utils;
import com.sgcc.commerce.dao.CommerceNewDao;
import com.sgcc.dto.OrderDTO;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderModel {

    private OrderDTO orderDTO;

    private List<OrderDTO> orderDTOS = new ArrayList<>();

    private List<InhabitantNewDao> inhabitantNewDaos = new ArrayList<>();

    private List<CommerceNewDao> commerceNewDaos = new ArrayList<>();


    public OrderModel(List<InhabitantNewDao> inhabitantNewDaos, List<CommerceNewDao> commerceNewDaos) {
        this.inhabitantNewDaos = inhabitantNewDaos;
        this.commerceNewDaos = commerceNewDaos;
    }


    public void queryOrderByOpenIdTransform(){
        String progress = "已提交";
        this.inhabitantNewDaos.forEach(dao->{
            this.orderDTOS.add(new OrderDTO(
                    dao.getId().replace("-",""),
                    Utils.GetTime(dao.getSubmit_date()),
                    dao.getUser_open_id(),
                    dao.getNew_install_name(),
                    dao.getNew_install_address(),
                    progress,
                    "居民"
            ));
        });

        this.commerceNewDaos.forEach(dao->{
            this.orderDTOS.add(new OrderDTO(
                    dao.getId().replace("-",""),
                    Utils.GetTime(dao.getSubmit_date()),
                    dao.getUser_open_id(),
                    dao.getNew_install_name(),
                    dao.getNew_install_address(),
                    progress,
                    "个体工商业"
            ));
        });

    }














}
