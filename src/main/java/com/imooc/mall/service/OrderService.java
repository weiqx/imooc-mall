package com.imooc.mall.service;

import com.imooc.mall.model.request.CreateOrderReq;
import com.imooc.mall.model.vo.OrderVO;

/**
 * 描述：  订单service
 */
public interface OrderService {
    String create(CreateOrderReq createOrderReq);

    OrderVO detail(String orderNo);
}
