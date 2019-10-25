package com.sgwl.filemanager.exception;

import com.sgwl.filemanager.enums.ExceptionEnum;
import lombok.Data;

@Data
public class FileManagerException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public FileManagerException(ExceptionEnum exceptionEnum) {
          this.exceptionEnum =exceptionEnum;
    }


}
