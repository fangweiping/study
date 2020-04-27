package com.sgwl.filemanager.vo;

import com.sgwl.filemanager.enums.ExceptionEnum;
import lombok.Data;

@Data
public class ExceptionResult {

   private int status;
   private String msg;
   private long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getValue();
        this.msg=em.getMsg();
        this.timestamp=System.currentTimeMillis();
    }

}
