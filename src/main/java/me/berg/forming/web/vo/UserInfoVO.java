package me.berg.forming.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {

    /**
     * 类型
     */
    private String type;

    /**
     * 内容
     */
    private String content;
}

