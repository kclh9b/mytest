package study.mytest.dto.common;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto<T> {

    public int resultCode;
    public String resultMsg;
    public T resultData;

    public ResponseDto() {
    }

    public ResponseDto(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ResponseDto(int resultCode, String resultMsg, T resultData) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }
}
