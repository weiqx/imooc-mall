package com.imooc.mall.controller;

import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.filter.UserFilter;
import com.imooc.mall.model.vo.CartVO;
import com.imooc.mall.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 描述：  购物车Controller
 */
@RestController   //  rest response  ResponseBody
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/list")
    @ApiOperation("购物车列表")
    public ApiRestResponse list() {
        //内部获取用户ID，防止横向越权
        List<CartVO> cartList = cartService.list(UserFilter.currentUser.getId());
        return ApiRestResponse.success(cartList);
    }

    @PostMapping("/add")
    @ApiOperation("添加商品到购物车")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVO> cartVOList = cartService.add(UserFilter.currentUser.getId(), productId, count);// 用户id session -- 过滤器
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/update")
    @ApiOperation("更新购物车")
    public ApiRestResponse update(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVO> cartVOList = cartService.update(UserFilter.currentUser.getId(), productId, count);// 用户id session -- 过滤器
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/delete")
    @ApiOperation("删除保护购物车")
    public ApiRestResponse delete(@RequestParam Integer productId) {
        //不能传入userId，cartId ，否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService.delete(UserFilter.currentUser.getId(), productId);// 用户id session -- 过滤器
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/select")
    @ApiOperation("选择/不选择购物车的某商品")
    public ApiRestResponse select(@RequestParam Integer productId, @RequestParam Integer selected) {
        //不能传入userId，cartId ，否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService.selectOrNot(UserFilter.currentUser.getId(), productId , selected);// 用户id session -- 过滤器
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/selectAll")
    @ApiOperation("全选择/全不选择购物车的某商品")
    public ApiRestResponse selectAll(@RequestParam Integer selected) {
        //不能传入userId，cartId ，否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService.selectAllOrNot(UserFilter.currentUser.getId(), selected);// 用户id session -- 过滤器
        return ApiRestResponse.success(cartVOList);
    }

}
