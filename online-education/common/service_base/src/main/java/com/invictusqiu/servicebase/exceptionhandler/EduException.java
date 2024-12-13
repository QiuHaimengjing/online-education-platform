package com.invictusqiu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 自定义异常处理类
 * @className: EduException.java
 * @author: qqdas
 * @createTime: 2023/10/2 10:47
 */
@Data
@AllArgsConstructor //生成有参构造方法
@NoArgsConstructor  //生成无参构造方法
public class EduException extends RuntimeException {
    private Integer code; //状态码
    private String msg; //异常信息
}
