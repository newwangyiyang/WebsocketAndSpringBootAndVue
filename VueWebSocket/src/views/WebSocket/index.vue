<template>
    <div class="WebSocket">
        {{obj}}
    </div>
</template>

<script>
import SockJS from  'sockjs-client';
import  Stomp from 'stompjs';
export default {
    name: 'WebSocket',
    data() {
      return {
        websock: null,
        obj: ''
      }
    },
    created() {
      this.initWebSocket();
    },
    destroyed() {
      this.websock.close() //离开路由之后断开websocket连接
    },
    methods: {
      initWebSocket(){ //初始化weosocket
        const wsuri = "ws://127.0.0.1:8082/socketServer/321";
        this.websock = new WebSocket(wsuri);
        this.websock.onmessage = this.websocketonmessage;
        this.websock.onopen = this.websocketonopen;
        this.websock.onerror = this.websocketonerror;
        this.websock.onclose = this.websocketclose;
      },
      websocketonopen(){ //连接建立之后执行send方法发送数据
        let actions = {"test":"12345"};
        this.websocketsend(JSON.stringify(actions));
      },
      websocketonerror(){//连接建立失败重连
        this.initWebSocket();
      },
      websocketonmessage(e){ //数据接收
        console.log(e)
        this.obj = JSON.parse(e.data).name;
      },
      websocketsend(Data){//数据发送
        this.websock.send(Data);
      },
      websocketclose(e){  //关闭
        console.log('断开连接',e);
      },
    },
}
</script>

<style scoped lang="less">
.WebSocket {

}
</style>