package com.imooc.mall.controller;

import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.model.request.CreateOrderReq;
import com.imooc.mall.model.vo.OrderVO;
import com.imooc.mall.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：  订单Controller
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping("order/create")
    public ApiRestResponse create(@RequestBody CreateOrderReq createOrderReq) {
        String orderNo = orderService.create(createOrderReq);
        return ApiRestResponse.success(orderNo);
    }

    @GetMapping("order/detail")
    @ApiOperation("前台订单详情")
    public ApiRestResponse detail(@RequestParam String orderNo) {
        OrderVO orderVO = orderService.detail(orderNo);
        return ApiRestResponse.success(orderVO);
    }

    @GetMapping("order/list")
    @ApiOperation("前台订单列表")
    public ApiRestResponse list(@RequestParam Integer pageNum) {

    }
}
