package com.hand.report.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_user")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;//编号
    @Column(name = "username")
    private String username;//用户名
    @Column(name = "password")
    private String password;//密码
}
