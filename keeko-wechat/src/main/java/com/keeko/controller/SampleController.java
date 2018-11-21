package com.keeko.controller;

import com.keeko.entity.ArticleItem;
import com.keeko.entity.InMsgEntity;
import com.keeko.entity.OutMsgEntity;
import com.keeko.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class SampleController {

    public static final String tooken = "keeko";

    /**
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	    随机数
     * echostr	    随机字符串
     */
    @RequestMapping(value = "/weChat", method = RequestMethod.GET)
    @ResponseBody
    public String home(String signature, String timestamp, String nonce, String echostr) {
        //定义数组存放tooken, timestamp, nonce
        String[] arr ={tooken, timestamp, nonce};
        //对数组进行排序
        Arrays.sort(arr);
        //生成字符串
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        //shal加密算法,
        String temp = SecurityUtil.getSha1(sb.toString());
        if(temp.equals(signature)){
            System.out.println("接入成功");
            //返回 echostr 表示配置成功
            return echostr;
        }
        System.out.println("接入失败");
        return null;
    }

    /**
     * 微信消息处理
     */
    @RequestMapping(value = "/weChat", method = RequestMethod.POST, produces = "application/xml")
    @ResponseBody
    public Object handleMessage(@RequestBody InMsgEntity msg) {
        //创建消息响应对象
        OutMsgEntity out = new OutMsgEntity();
        //把原来的发送方设置为接收方
        out.setToUserName(msg.getFromUserName());
        //把原来的接收方设置为发送方
        out.setFromUserName(msg.getToUserName());
        //获取接收的消息类型
        String msgType = msg.getMsgType();
        //设置消息创建时间
        out.setCreateTime(System.currentTimeMillis());
        //公众号回复的内容
        String outContent = null;
        //根据类型设置不同的消息数据
        if("text".equals(msgType)){
            //用户发送的内容
            String inContent = msg.getContent();
            //关键字判断
            if(inContent.contains("开始")){
                outContent = "开始咯\n" +
                        "结束咯";
            }else if(inContent.contains("地址")){
                outContent = "北京：北京昌平区沙河镇万家灯火装饰城2楼8077号\n" +
                        "广州：广州市天河区棠下涌东路大地工业区D栋六楼\n" +
                        "上海：上海市青浦区华新镇华隆路1777号E通世界商务园华新园A座4楼402";
            }else{
                //用户发什么就回复什么
                outContent = inContent;
            }
            //设置消息的响应类型
            out.setMsgType("text");
            out.setContent(outContent);
        }else if("image".equals(msgType)){
            out.setMsgType(msgType);
            out.setMediaId(new String[]{msg.getMediaId()});
        }else if("event".equals(msgType)){
            //判断关注事件
            if("subscribe".equals(msg.getEvent())){
                //out.setContent("欢迎关注![愉快]"); //设置消息的响应类型 //out.setMsgType("text");
                //回复图文消息
                out.setMsgType("news");
                //设置图文个数
                out.setArticleCount(1);
                //设置图文明细列表
                ArticleItem item = new ArticleItem();
                item.setTitle("写标题");
                item.setPicUrl("http://yuyan_dev.v.keeko.ai/static/data/courseware/1531194654421.jpg"); //图片地址
                item.setDescription("图文消息描述...想写什么写什么");
                item.setUrl("http://localhost:8080/"); //跳转到哪里
                out.setItems(new ArticleItem[]{item});
            }else if ("clisk".equals(msg.getEvent())){
                //获取菜单的key值
                String eventKey = msg.getEventKey();
                //判断按钮的key值
                if("classinfo".equals(eventKey)){
                    outContent = "来吧小野兽\n" +
                            "来丫小野兽";
                }else if("address".equals(eventKey)){
                    outContent = "北京：北京昌平区沙河镇万家灯火装饰城2楼8077号\n" +
                            "广州：广州市天河区棠下涌东路大地工业区D栋六楼\n" +
                            "上海：上海市青浦区华新镇华隆路1777号E通世界商务园华新园A座4楼402";
                }
                //设置消息的响应类型
                out.setMsgType("text");
                out.setContent(outContent);

            }
        }
        return out;
    }

}
























