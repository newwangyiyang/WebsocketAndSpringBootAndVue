package cn.yiyang.system.websocket.controller;

import cn.yiyang.system.websocket.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName WebSocketController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/6 11:11
 * @Version 1.0
 **/

/**
 * websocket
 * 消息推送(个人和广播)
 */
@Controller
public class WebSocketController {

    @Autowired
    private SocketService socketServer;

    /**
     *
     * 客户端页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {

        return "index";
    }

//    /**
//     *
//     * 服务端页面
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/admin")
//    public String admin(Model model) {
//        int num = socketServer.getOnlineNum();
//        List<String> list = socketServer.getOnlineUsers();
//
//        model.addAttribute("num",num);
//        model.addAttribute("users",list);
//        return "admin";
//    }

    /**
     * 个人信息推送
     * @return
     */
    @RequestMapping("/sendmsg")
    @ResponseBody
    public String sendmsg(String msg, String username){
        //第一个参数 :msg 发送的信息内容
        //第二个参数为用户长连接传的用户人数
        String [] persons = username.split(",");
        SocketService.SendMany(msg,persons);
        return "success";
    }

    /**
     * 推送给所有在线用户
     * @return
     */
    @RequestMapping("/sendAll")
    @ResponseBody
    public String sendAll(String msg){
        SocketService.sendAll(msg);
        return "success";
    }
}
