package com.fwp.demo.reptile.polayoutu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

    private String code;
    private String message;
    private String status;
    private List<ResEntity> data ;

}
