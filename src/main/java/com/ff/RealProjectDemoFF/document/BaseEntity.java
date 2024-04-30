package com.ff.RealProjectDemoFF.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ff.RealProjectDemoFF.util.CommonUtil;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {
    String createdBy;
    String modifiedBy;
    String createdByUname;
    String modifiedByUname;
    String createdOn;
    String modifiedOn;

    public void createEntity(String userId, String userName){
        this.createdOn = CommonUtil.getCurrentDate();
        this.createdBy=userId;
        this.createdByUname = userName;
        this.modifiedBy=userId;
        this.modifiedByUname=userName;
        this.modifiedOn=CommonUtil.getCurrentDate();
    }

    public void updateEntity (String userId, String userName){
        this.modifiedOn=CommonUtil.getCurrentDate();
        this.modifiedBy=userId;
        this.modifiedByUname=userName;
    }
}
