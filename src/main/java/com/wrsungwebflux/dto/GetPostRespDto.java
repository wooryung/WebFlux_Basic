package com.wrsungwebflux.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wrsungwebflux.consts.ResCode;
import com.wrsungwebflux.vo.PostVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetPostRespDto {
    private PostVo post;
    private int code = ResCode.SUCCESS.value();
    private String message;
}
