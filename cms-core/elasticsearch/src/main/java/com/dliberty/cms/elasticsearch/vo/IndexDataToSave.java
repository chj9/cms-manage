package com.dliberty.cms.elasticsearch.vo;

/**
 * @description:
 * @author: LY
 * @create: 2020-04-20 16:30
 **/
public class IndexDataToSave {

    private String id;

    private Object payload;

    public IndexDataToSave(String id, Object payload) {
        this.id = id;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
