<template>
  <div class="sign">
    <i class="iconfont icon-eduhome" @click="home"/>
    <!--标题-->
    <!-- <div class="logo">
      <img src="~/assets/img/logo.png" alt="logo">
    </div> -->
    <!--表单-->
    <nuxt />
  </div>
</template>
<script>
import '~/assets/css/icon/iconfont.css'
export default{
  methods: {
    home() {
      this.$router.push({ path: '/' })
    }
  }
}
</script>
<style scoped>
.icon-eduhome {
  font-size: 30px;
  cursor: pointer;
  text-shadow: 1px 1px 2px #fff;
  transition: all 0.3s linear;
}
.icon-eduhome:hover {
  filter: brightness(1.5);
}
</style>