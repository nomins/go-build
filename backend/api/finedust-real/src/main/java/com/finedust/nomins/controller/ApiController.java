package com.finedust.nomins.controller;

import com.finedust.nomins.Api.ApiRequestException;
import com.finedust.nomins.Api.FineDustRequest;
import com.finedust.nomins.Api.FineDustResponse;
import com.finedust.nomins.domain.FineDust;
import com.finedust.nomins.domain.FineDustWrapper;
import com.finedust.nomins.service.WritingFineDustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    WritingFineDustService dustService;


    @GetMapping("v1/finedust")
    public ResponseEntity<Void> getFineDust(FineDustRequest param) {
        FineDustResponse dust = null;
        try{
            checkParam(param);
            FineDust fineDust = dustService.getFineDust(param);

            dust = new FineDustResponse();
            dust.setFineDust(new FineDustWrapper(fineDust, param.getType()));
            dust.setCode(200);
        }catch(ApiRequestException are){
            dust = new FineDustResponse();
            dust.setCode(400);
            dust.setErrorReason(are.getMessage());
        }
        return new ResponseEntity(dust, HttpStatus.OK);
    }

    private void checkParam(FineDustRequest param) throws ApiRequestException {
        if(param == null) {
            throw new ApiRequestException("사용자의 요청이 잘못되었습니다.");
        }

        if( StringUtils.isEmpty(param.getType()) )
            throw new ApiRequestException("사용자의 요청이 잘못되었습니다.");

        if(StringUtils.isEmpty(param.getProvince()))
            throw new ApiRequestException("사용자의 요청이 잘못되었습니다.");

        if( !param.getType().equalsIgnoreCase("general") && !param.getType().equalsIgnoreCase("detail") ){
            throw new ApiRequestException("사용자의 요청 [type] 오류");
        }
    }
}
