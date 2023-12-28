package com.example.demo.repository;

import com.example.demo.model.MarketInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarketInfoRepository extends JpaRepository<MarketInfo, Long> {
    // 添加一个方法来获取最新的市场信息，按市场ID降序排序
    @Query("SELECT m FROM MarketInfo m ORDER BY m.marketId DESC")
    List<MarketInfo> findLatestMarketInfo();
}