package cn.xtong.example.user.enmus;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysUserStateEnum {
    NORMAL(0,"正常"),
    FREEZE(1,"冻结");

    @EnumValue

    private final int code;
    @JsonValue
    private final String desc;
}
