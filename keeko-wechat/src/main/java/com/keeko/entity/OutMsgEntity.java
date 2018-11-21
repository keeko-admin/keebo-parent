package com.keeko.entity;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class OutMsgEntity {
    //这里用大写不用映射
    private String ToUserName;    //开发者微信号
    //@XmlElement(name="FromUserName") 若是小写规范打开该注解
    private String FromUserName;    //发送方帐号（一个OpenID）
    private Long CreateTime;    //消息创建时间 （整型）
    private String MsgType;    //text
    private Long MsgId;    //消息id，64位整型

    private String Content;    //文本消息内容

    private String PicUrl;    //图片链接（由系统生成）
    @XmlElementWrapper(name="Image")
    private String[] MediaId;    //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。

    private Integer ArticleCount; //图文消息个数，限制为8条以内
    //图文列表明细
    @XmlElementWrapper(name="Articles")
    private ArticleItem[] items;
}
