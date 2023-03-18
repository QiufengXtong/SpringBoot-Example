package cn.xtong.example.user.enmus;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysUserSexEnum {

    MAN(0,"男"),
    GIRL(1,"女");

    @EnumValue
    private int code;
    @JsonValue
    private String desc;
}
