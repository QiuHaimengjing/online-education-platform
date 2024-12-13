<template>
  <div>

    <!-- 阿里云视频播放器样式 -->
    <link rel="stylesheet" href="https://g.alicdn.com/de/prismplayer/2.8.1/skins/default/aliplayer-min.css">

    <!-- 定义播放器dom -->
    <div id="J_prismPlayer" class="prism-player" />
  </div>
</template>
<script charset="utf-8" type="text/javascript" src="https://g.alicdn.com/de/prismplayer/2.8.1/aliplayer-min.js"/>
<script>
import vod from '@/api/vod'
export default {
  layout: 'video', // 使用video布局
  asyncData({ params, error }) {
    return vod.getPlayAuth(params.vid)
      .then(response => {
        return {
          playAuth: response.data.playAuth,
          vid: params.vid
        }
      })
  },
  mounted() {
    new Aliplayer({
      id: 'J_prismPlayer',
      vid: this.vid, // 视频id
      playauth: this.playAuth, // 播放凭证
      // encryptType: '1', // 如果播放加密视频，则需设置encryptType=1，非加密视频无需设置此项
      width: '100%',
      height: '500px'
    }, function(player) {
      console.log('播放器创建成功')
    })
  }
}
</script>
