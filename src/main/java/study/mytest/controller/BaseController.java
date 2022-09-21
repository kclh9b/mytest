package study.mytest.controller;

import study.mytest.dto.common.ResponseDto;

public class BaseController {

    public ResponseDto getSuccessResponseDto(String resultMsg) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResultCode(1);
        responseDto.setResultMsg(resultMsg);
        return responseDto;
    }

    public <T> ResponseDto getSuccessResponseDto(T data) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResultCode(1);
        responseDto.setResultData(data);
        return responseDto;
    }

    public <T> ResponseDto getSuccessResponseDto(String resultMsg, T data) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResultCode(1);
        responseDto.setResultMsg(resultMsg);
        responseDto.setResultData(data);
        return responseDto;
    }
}
