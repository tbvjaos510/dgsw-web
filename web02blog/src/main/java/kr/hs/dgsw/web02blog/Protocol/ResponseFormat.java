package kr.hs.dgsw.web02blog.Protocol;

import lombok.Data;

@Data
public class ResponseFormat {
    private int code;
    private String desc;
    private Object data;

    public ResponseFormat(ResponseType rt, Object data, Object option) {
        this.code = rt.code();
        if (rt != ResponseType.FAIL && (option instanceof Long || option instanceof String))
            this.desc = String.format(rt.desc(), option);
        else
            this.desc = rt.desc();
        this.data = data;
    }

    public ResponseFormat(ResponseType rt, Object data) {
        this(rt, data, null);
    }
}
