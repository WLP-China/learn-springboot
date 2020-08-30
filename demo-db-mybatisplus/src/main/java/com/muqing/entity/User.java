package com.muqing.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * Create by iFun on 2020/08/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableLogic //逻辑删除
    private Integer deleted;
    @Version
    private Integer version;
    @TableField(value = "credit_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
