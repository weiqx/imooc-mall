package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.model.request.CreateOrderReq;
import com.imooc.mall.model.vo.OrderVO;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：  订单service
 */
public interface OrderService {
    String create(CreateOrderReq createOrderReq);

    OrderVO detail(String orderNo);

    PageInfo listForCustomer(@RequestParam Integer pageNum, @RequestParam Integer pageSize);

    void cancel(String orderNo);

    String qrcode(String orderNo);

    PageInfo listForAdmin(Integer pageNum, @RequestParam Integer pageSize);

    void pay(String orderNo);

    void deliver(String orderNo);

    void finish(String orderNo);
}
