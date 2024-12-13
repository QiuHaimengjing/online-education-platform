<template>
  <div class="main">
    <div class="title">
      <router-link to="/login">
        <a href="#">登录</a>
      </router-link>
      <span>·</span>
      <router-link to="/register">
        <a class="active" href="#">注册</a>
      </router-link>
    </div>

    <div class="sign-up-container">
      <el-form ref="userForm" :model="params">

        <el-form-item
          :rules="[{ required: true, message: '请输入你的昵称', trigger: 'blur' }]"
          class="input-prepend restyle"
          prop="nickname">
          <div>
            <el-input v-model="params.nickname" type="text" placeholder="你的昵称" />
            <i class="iconfont icon-user" />
          </div>
        </el-form-item>

        <el-form-item
          :rules="[{ required: true, message: '请输入手机号码', trigger: 'blur' }, { validator: checkPhone, trigger: 'blur' }]"
          class="input-prepend restyle"
          prop="mobile">
          <div>
            <el-input v-model="params.mobile" type="text" placeholder="手机号" />
            <i class="iconfont icon-phone" />
          </div>
        </el-form-item>

        <el-form-item
          :rules="[{ required: true, message: '请输入验证码', trigger: 'blur' }]"
          class="input-prepend restyle"
          prop="code">
          <div style="width: 100%;display: block;float: left;position: relative">
            <el-input v-model="params.code" type="text" placeholder="验证码" />
            <i class="iconfont icon-phone" />
          </div>
          <div class="btn" style="position:absolute;right: 0;top: 6px;width: 40%;">
            <a
              :value="codeTest"
              href="javascript:"
              type="button"
              style="border: none;background-color: none"
              @click="getCodeFun()">{{ codeTest }}</a>
          </div>
        </el-form-item>

        <el-form-item
          :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]"
          class="input-prepend"
          prop="password">
          <div>
            <el-input v-model="params.password" type="password" placeholder="设置密码" />
            <i class="iconfont icon-password" />
          </div>
        </el-form-item>

        <div class="btn">
          <input type="button" class="sign-up-button" value="注册" @click="submitRegister()">
        </div>
        <p class="sign-up-msg">
          点击 “注册” 即表示您同意并愿意遵守简书
          <br>
          <a target="_blank" href="http://www.jianshu.com/p/c44d171298ce">用户协议</a>
          和
          <a target="_blank" href="http://www.jianshu.com/p/2ov8x3">隐私政策</a> 。
        </p>
      </el-form>
      <!-- 更多注册方式 -->
      <div class="more-sign">
        <h6>社交帐号直接注册</h6>
        <ul>
          <li><a id="weixin" class="weixin" target="_blank" href="http://huaan.free.idcfengye.com/api/ucenter/wx/login"><i
            class="iconfont icon-weixin" /></a></li>
          <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq" /></a></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import registerApi from '@/api/register'
export default {
  layout: 'sign',
  data() {
    return {
      params: { // 封装注册输入数据
        mobile: '',
        code: '',
        nickname: '',
        password: ''
      },
      sending: true, // 是否发送验证码
      second: 60, // 倒计时间
      codeTest: '获取验证码'
    }
  },
  methods: {
    // 通过输入的手机号发送验证码
    getCodeFun() {
      registerApi.sendCode(this.params.mobile)
        .then(response => {
          this.sending = false
          // 调用倒计时的方法
          this.timeDown()
        })
    },
    // 注册提交的方法
    submitRegister() {
      registerApi.registerMember(this.params)
        .then(response => {
          // 提示注册成功
          this.$message({
            message: '注册成功',
            type: 'success'
          })
          // 跳转到登录页面
          this.$router.push({ path: '/login' })
        })
    },
    //  验证码倒计时
    timeDown() {
      const result = setInterval(() => {
        --this.second
        this.codeTest = this.second
        if (this.second < 1) {
          clearInterval(result)
          this.sending = true
          // this.disabled = false;
          this.second = 60
          this.codeTest = '获取验证码'
        }
      }, 1000)
    },
    // 手机号码校验规则
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