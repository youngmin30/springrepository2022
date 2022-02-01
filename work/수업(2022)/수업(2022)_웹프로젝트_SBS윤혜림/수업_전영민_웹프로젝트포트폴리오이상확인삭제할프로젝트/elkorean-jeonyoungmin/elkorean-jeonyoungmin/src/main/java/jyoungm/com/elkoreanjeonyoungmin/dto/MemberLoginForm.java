package jyoungm.com.elkoreanjeonyoungmin.dto;

//package com.yhr.cleanCM.dto; 이름을 com.jyoungmin.elkorean.dto가 되도록 다시 만들기

import lombok.Data;

@Data
public class MemberLoginForm {

    private String loginId;

    private String loginPw;

}