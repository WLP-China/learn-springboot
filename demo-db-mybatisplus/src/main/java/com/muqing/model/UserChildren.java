package com.muqing.model;

import com.muqing.entity.Children;
import com.muqing.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Create by iFun on 2020/08/30
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserChildren extends User {

    private List<Children> children;
}
