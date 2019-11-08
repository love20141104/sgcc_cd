package com.sgcc.entity.query;

import com.sgcc.dao.BusinessRecordDao;
import com.sgcc.dto.PaymentAmountChartDTO;
import com.sgcc.dto.PaymentTimesChartDTO;
import com.sgcc.dto.PaymentTimesDTO;
import com.sgcc.dto.TotalFeesAvgChartDTO;
import com.sgcc.repository.BusinessRecordStatistcsRepository;
import com.sgcc.repository.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChartQueryEntity {

    @Autowired
    private ChartRepository chartRepository;
    @Autowired
    private BusinessRecordStatistcsRepository businessRecordStatistcsRepository;

    public TotalFeesAvgChartDTO findTotalFeesAvgChart() {
        return chartRepository.findTotalFeesAvgChart();
    }

    public PaymentTimesDTO findAllPaymentTimesChart() {
        return chartRepository.findAllPaymentTimesChart();
    }

    public List<PaymentTimesDTO> findPaymentTimesChart() {
        return chartRepository.findPaymentTimesChart();
    }

    public List<PaymentAmountChartDTO> findPaymentAmountChart() {
        return chartRepository.findPaymentAmountChart();
    }

    /**
     * 获取所有业务的办理次数
     * @return
     */
    public List<BusinessRecordDao> getBusinessRecord() {
        List<BusinessRecordDao> businessRecordDaos = businessRecordStatistcsRepository.getBusinessRecord();
        return businessRecordDaos;
    }


}
