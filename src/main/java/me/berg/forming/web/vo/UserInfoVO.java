package me.berg.forming.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoVO {

    /**
     * 类型
     */
    private Integer type;

    /**
     * 内容
     */
    private String content;
}

