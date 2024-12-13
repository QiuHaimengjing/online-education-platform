<template>
  <!-- 滚动到顶部按钮 -->
  <button class="scroll-to-top" @click="scrollToTop()"><i class="el-icon-caret-top"/></button>
</template>

<script>
import { initializeScrollButton } from './backTopBtn'
export default {
  mounted() { // 页面渲染之后执行
    initializeScrollButton()
  },
  methods: {
    // 用于滚动到页面顶部
    scrollToTop() {
      window.scrollTo({ top: 0, behavior: 'smooth' })
    }
  }
}
</script>

<style scoped>
/*返回顶部按钮*/
.scroll-to-top {
  opacity: 0;
  position: fixed;
  right: 20px;
  bottom: 40px;
  width: 40px;
  /* 设置按钮的宽度和高度相等，以创建一个圆形按钮 */
  height: 40px;
  /* 设置背景色为白色 */
  background-color: #fff;
  /* 去除边框 */
  border: none;
  /* 使用50%的border-radius将按钮变成圆形 */
  border-radius: 50%;
  /* 设置文本颜色 */
  color: #409eff;
  /* 文本居中 */
  text-align: center;
  /* 垂直居中文本 */
  line-height: 40px;
  /* 设置文本字号 */
  font-size: 20px;
  /* 鼠标悬停时显示手型光标 */
  cursor: pointer;
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.2);
  z-index: 999;
  transition: all 0.5s;
}

.scroll-to-top i {
  position: absolute;
  top: 10px;
  left: 10px;
}

.scroll-to-top:hover {
  background-color: #f2f6fc;
}
</style>
