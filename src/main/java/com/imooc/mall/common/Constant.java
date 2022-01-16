package com.imooc.mall.common;

import com.google.common.collect.Sets;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 描述：常量值
 */
@Component
public class Constant {

    public static final String SALT = "8svbsvjkweDF,.03[";
    public static final String IMOOC_MALL_USER = "imooc_mall_user";


    public static String FILE_UPLOAD_DIR;  //解耦 只需要在配置文件填写就可  staic null pointer ex

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface ProductListOrderBy { // ?
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price desc", "price asc");
    }

    public interface SaleStatus {
        int NOT_SALE=0;//商品下架状态
        int SALE=1;//商品上架状态
    }

    public interface Cart {
        int UN_CHECKED=0;//商品下架状态
        int CHECKED=1;//商品上架状态
    }

    public enum OrderStatusEnum {
        CANCELED(0, "用户已取消"),
        NOT_PAID(10, "未付款"),
        PAID(20, "已付款"),
        DELIVERED(30, "已发货"),
        FINISHED(40, "交易完成")
        ;
        private String value;
        private int Code;

        OrderStatusEnum( int code, String value) {
            this.Code = code;
            this.value = value;
        }

        public static OrderStatusEnum codeOf(int code) {
            for (OrderStatusEnum value : values()) {
                if (value.getCode() == code) {
                    return value;
                }
            }
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_ENUM);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return Code;
        }

        public void setCode(int code) {
            Code = code;
        }
    }

}
