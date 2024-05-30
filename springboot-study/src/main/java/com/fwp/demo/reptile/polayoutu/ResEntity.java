package com.fwp.demo.reptile.polayoutu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资源实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEntity implements Serializable {

    private  int id;

    private String avatar;

    private int collection_id;

    private  int comment_count;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime created;

    private String full_res;//资源地址

}
