package com.finedust.nomins.controller;

import com.finedust.nomins.api.ApiRequestException;
import com.finedust.nomins.api.FineDustRequest;
import com.finedust.nomins.api.FineDustResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

    @GetMapping("v1/findusts")
    public ResponseEntity<Void> getFineDust(FineDustRequest param) {
        FineDustResponse dust = null;
        try{
            System.out.println(param);
            param = null;
            checkParam(param);


            dust = new FineDustResponse();
        }catch(ApiRequestException are){
            dust = new FineDustResponse();
            dust.setCode(400);
            dust.setReason(are.getMessage());
        }
        return new ResponseEntity(dust, HttpStatus.OK);
    }

    private void checkParam(FineDustRequest param) throws ApiRequestException{
        if(param == null) {
            throw new ApiRequestException("사용자의 요청이 잘못되었습니다.");
        }

        if( StringUtils.isEmpty(param.getType()) )
            throw new ApiRequestException("사용자의 요청이 잘못되었습니다.");

        if( StringUtils.isEmpty(param.getLevel()))
            throw new ApiRequestException("사용자의 요청이 잘못되었습니다.");

        if(StringUtils.isEmpty( param.getStartDate() ))
            throw new ApiRequestException("사용자의 요청이 잘못되었습니다.");

        if(StringUtils.isEmpty(param.getEndDate()))
            throw new ApiRequestException("사용자의 요청이 잘못되었습니다.");

        if(StringUtils.isEmpty(param.getProvince()))
            throw new ApiRequestException("사용자의 요청이 잘못되었습니다.");




        if( !param.getType().equalsIgnoreCase("general") || !param.getType().equalsIgnoreCase("detail") ){
            throw new ApiRequestException("사용자의 요청 [type] 오류");
        }

        if(!param.getLevel().equalsIgnoreCase("min") || !param.getLevel().equalsIgnoreCase("max") ){
            throw new ApiRequestException("사용자의 요청 [level] 오류");
        }

        //TODO Date UTC parse, 범위 유효성 체크

    }
}
