package com.example.demo.controller;

import com.example.demo.ApiResponse;
import com.example.demo.model.MarketInfo;
import com.example.demo.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market")
public class MarketController {
    @Autowired
    private MarketService marketService;

    @GetMapping("/info")
    public ResponseEntity<List<MarketInfo>> getMarketInfo() {
        List<MarketInfo> marketInfo = marketService.getMarketInfo();
        return ResponseEntity.ok(marketInfo);
    }

}
