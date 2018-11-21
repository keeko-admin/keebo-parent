package com.keeko.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ArticleItem {

    private String Title; //图文消息标题
    private String Description; //图文消息描述
    private String PicUrl; //图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
    private String Url; //点击图文消息跳转链接

}
