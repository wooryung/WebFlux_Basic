package com.wrsungwebflux.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wrsungwebflux.consts.ResCode;
import com.wrsungwebflux.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserRespDto {
    private UserVo user;
    private int code = ResCode.SUCCESS.value();
    private String message;
}
