<template>
  <client-only>
    <div class="in-wrap">
      <!-- 公共头引入 -->
      <header class="header">
        <div class="logo">
          <img src="~/assets/img/小湫.png" width="100%" alt="在线教育">
          <span>在线教育</span>
        </div>
        <ul>
          <li>
            <router-link to="/">
              <a href="#">首页</a>
            </router-link>
          </li>
          <li>
            <router-link to="/course">
              <a href="#">课程</a>
            </router-link>
          </li>
          <li>
            <router-link to="/teacher">
              <a href="#">名师</a>
            </router-link>
          </li>
          <li>
            <a href="https://www.invictusqiu.com/" target="_blank">关于我</a>
          </li>
          <li v-if="!loginInfo.id">
            <router-link to="/login">
              <a href="#">登录</a>
            </router-link>
          </li>
          <li v-else>
            <a href="javascript:void(0)" @click="logout()">退出登录</a>
          </li>
          <!-- 滑动条 -->
          <div class="nav-box"/>
          <!-- 登录者头像和昵称 -->
          <div v-if="loginInfo.id" class="avatar">
            <img :src="loginInfo.avatar">
            <span>{{ loginInfo.nickname }}</span>
          </div>
        </ul>
      </header>
      <!-- /公共头引入 -->

      <nuxt />

      <!-- 公共底引入 -->
      <footer id="footer">
        <section class="container">
          <div class>
            <h4 class="hLh30">
              <span class="fsize18 f-fM c-999">友情链接</span>
            </h4>
            <ul class="of flink-list">
              <li>
                <a href="http://www.atguigu.com/" title="尚硅谷" target="_blank">尚硅谷</a>
              </li>
            </ul>
            <div class="clear"/>
          </div>
          <div class="b-foot">
            <section class="fl col-7">
              <section class="mr20">
                <section class="b-f-link">
                  <a href="#" title="关于我们" target="_blank">关于我们</a>|
                  <a href="#" title="联系我们" target="_blank">联系我们</a>|
                  <a href="#" title="帮助中心" target="_blank">帮助中心</a>|
                  <a href="#" title="资源下载" target="_blank">资源下载</a>|
                  <span>服务热线：010-56253825(北京) 0755-85293825(深圳)</span>
                  <span>Email：info@atguigu.com</span>
                </section>
                <section class="b-f-link mt10">
                  <span>©2018课程版权均归谷粒学院所有 京ICP备17055252号</span>
                </section>
              </section>
            </section>
            <aside class="fl col-3 tac mt15">
              <section class="gf-tx">
                <span>
                  <img src="~/assets/img/wx-icon.png" alt>
                </span>
              </section>
              <section class="gf-tx">
                <span>
                  <img src="~/assets/img/wb-icon.png" alt>
                </span>
              </section>
            </aside>
            <div class="clear"/>
          </div>
        </section>
      </footer>
    <!-- /公共底引入 -->
    </div>
  </client-only>
</template>
<script>
import '~/assets/css/reset.css'
import '~/assets/css/theme.css'
import '~/assets/css/global.css'
import '~/assets/css/web.css'
import cookie from 'js-cookie'
import loginApi from '@/api/login'
import '~/assets/css/base.css'
import '~/assets/css/activity_tab.css'
import '~/assets/css/bottom_rec.css'
import '~/assets/css/nice_select.css'
import '~/assets/css/order.css'
import '~/assets/css/swiper-3.3.1.min.css'
import '~/assets/css/pages-weixinpay.css'
import '~/assets/css/header.css'
export default {
  data() {
    return {
      token: '',
      loginInfo: {
        id: '',
        age: '',
        avatar: '',
        mobile: '',
        nickname: '',
        sex: ''
      }
    }
  },
  // 监听路由变化
  watch: {
    $route(to, from) {
      this.getNavDom()
    }
  },
  created() {
    // 获取路径里面token值
    this.token = this.$route.query.token
    if (this.token) { // 判断路径是否有token值
      this.wxLogin()
    }
    this.showInfo()
  },
  methods: {
    // 获取导航栏dom元素，并修改样式
    getNavDom() {
      const box = document.querySelector('.nav-box')
      // 根据路由地址，修改导航栏样式
      if (this.$route.path === '/') {
        box.className = 'nav-box router-index'
      } else if (this.$route.path === '/course') {
        box.className = 'nav-box router-course'
      } else if (this.$route.path === '/teacher') {
        box.className = 'nav-box router-teacher'
      }
    },
    // 微信登录显示的方法
    wxLogin() {
      // 把token值放到cookie里面
      cookie.set('edu_token', this.token, { domain: 'localhost' })
      cookie.set('edu_ucenter', '', { domain: 'localhost' })
      // 调用接口，根据token值获取用户信息
      loginApi.getLoginUserInfo()
        .then(response => {
          this.loginInfo = response.data.userInfo
          cookie.set('edu_ucenter', JSON.stringify(this.loginInfo), { domain: 'localhost' })
        })
    },
    // 退出
    logout() {
      // 清空cookie值
      cookie.set('edu_token', '', { domain: 'localhost' })
      cookie.set('edu_ucenter', '', { domain: 'localhost' })
      // 回到首页面
      window.location.href = '/'
    },
    // 创建方法，从cookie获取用户信息
    showInfo() {
      // 从cookie获取用户信息
      const userStr = cookie.get('edu_ucenter')
      // 把字符串转换json对象(js对象)
      if (userStr) {
        this.loginInfo = JSON.parse(userStr)
      }
    }
  }
}
</script>
