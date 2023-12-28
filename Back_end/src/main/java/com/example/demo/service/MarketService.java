package com.example.demo.service;

import com.example.demo.model.MarketInfo;
import com.example.demo.repository.MarketInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarketService {
    @Autowired
    private MarketInfoRepository marketInfoRepository;

    public List<MarketInfo> getMarketInfo() {
        // 使用自定义查询获取最新的市场信息
        List<MarketInfo> allInfo = marketInfoRepository.findLatestMarketInfo();
        if (allInfo.isEmpty()) {
            return null;
        }
        return allInfo;
    }
}
