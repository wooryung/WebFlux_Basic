package com.wrsungwebflux.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wrsungwebflux.consts.ResCode;
import com.wrsungwebflux.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserListRespDto {
    private List<UserVo> userList;
    private int code = ResCode.SUCCESS.value();
    private String message;
}
