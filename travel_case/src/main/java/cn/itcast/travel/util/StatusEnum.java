package cn.itcast.travel.util;

/**
 * @author ziyin
 @create 2019-06-2019/6/8-18:49
 */
public enum StatusEnum {

    Y("Y"), N("N");

    String status;

    private StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
