package base.references;

/**
 * Created by @v.matviichenko
 */
public enum HttpStatusCodes {

    SUCCESS_200(200),
    SUCCESS_201(201),
    CLIENT_ERROR_400(400);

    HttpStatusCodes(Integer code) {
        this.code = code;
    }

    private final Integer code;
    public Integer getCode() {
        return code;
    }
}
