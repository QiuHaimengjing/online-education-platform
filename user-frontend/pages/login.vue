<template>
  <div class="main">
    <div class="title">
      <router-link to="/login">
        <a class="active" href="#">登录</a>
      </router-link>
      <span>·</span>
      <router-link to="/register">
        <a href="#">注册</a>
      </router-link>
    </div>

    <div class="sign-up-container">
      <el-form ref="userForm" :model="user">

        <el-form-item
          :rules="[{ required: true, message: '请输入手机号码', trigger: 'blur' }, { validator: checkPhone, trigger: 'blur' }]"
          class="input-prepend"
          prop="mobile">
          <div>
            <el-input v-model="user.mobile" type="text" placeholder="手机号" />
            <i class="iconfont icon-phone" />
          </div>
        </el-form-item>

        <el-form-item
          :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]"
          class="input-prepend"
          prop="password">
          <div>
            <el-input v-model="user.password" type="password" placeholder="密码" />
            <i class="iconfont icon-password" />
          </div>
        </el-form-item>

        <div class="btn">
          <input type="button" class="sign-in-button" value="登录" @click="submitLogin()">
        </div>
      </el-form>
      <!-- 更多登录方式 -->
      <div class="more-sign">
        <h6>社交帐号登录</h6>
        <ul>
          <li><a
            id="weixin"
            class="weixin"
            target="_blank"
            href="http://qy.free.idcfengye.com/api/ucenter/weixinLogin/login"><i class="iconfont icon-weixin" /></a>
          </li>
          <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li>
        </ul>
      </div>
    </div>

  </div>
</template>

<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import cookie from 'js-cookie'
import loginApi from '@/api/login'
export default {
  layout: 'sign',

  data() {
    return {
      // 封装登陆手机号和密码对象
      user: {
        mobile: '',
        password: ''
      },
      // 用户信息
      loginInfo: {}
    }
  },

  methods: {
    // 登录的方法
    submitLogin() {
      loginApi.submitLogin(this.user)
        .then(response => {
          // 第二步 获取token字符串放到cookie里面
          // 第一个参数cookie名称，第二个参数值，第三个参数作用范围
          cookie.set('edu_token', response.data.token, { domain: 'localhost' })
          // 第三步 创建拦截器
          // 第四步 调用接口 根据token获取用户信息，为了首页显示
          loginApi.getLoginUserInfo()
            .then(response => {
              this.loginInfo = response.data.userInfo
              // 获取返回用户信息，放到cookie里面
              cookie.set('edu_ucenter', JSON.stringify(this.loginInfo), { domain: 'localhost' })

              // 跳转页面
              this.$router.back()
            })
        })
    },
    checkPhone(rule, value, callback) {
      // debugger
      if (!(/^1[34578]\d{9}$/.test(value))) {
        return callback(new Error('手机号码格式不正确'))
      }
      return callback()
    }
  }
}
</script>
<style scoped>
a {
  text-decoration: none;
}
</style>