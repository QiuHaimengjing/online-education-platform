<template>
  <div class="app-container">
    <!-- 卡片 -->
    <el-card>
      <div slot="header">
        <span>添加轮播图</span>
      </div>
      <!-- 表单 -->
      <el-form label-width="120px">

        <el-form-item label="标题" class="title">
          <el-input v-model="bannerInfo.title" placeholder="请输入标题"/>
        </el-form-item>

        <!-- 轮播图片 -->
        <el-form-item label="轮播图片">

          <el-upload
            ref="upload"
            :action="BASE_API + '/eduoss/fileoss'"
            :auto-upload="false"
            :on-success="handleBannerSuccess"
            :on-change="onSelectFile"
            class="banner-uploader"
          >
            <el-image v-if="imgUrl" :src="imgUrl"/>
            <i v-else class="el-icon-plus"/>
          </el-upload>

        </el-form-item>

        <el-form-item label="链接地址" class="linkUrl">
          <el-input v-model="bannerInfo.linkUrl" placeholder="示例：/course"/>
        </el-form-item>

        <el-form-item label="排序">
          <el-input-number v-model="bannerInfo.sort" :min="1" controls-position="right" placeholder="轮播图顺序"/>
        </el-form-item>

        <el-form-item>
          <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
        </el-form-item>

      </el-form>
    </el-card>
  </div>
</template>
<script>
import bannerApi from '@/api/edu/banner'
export default {
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      img: '',
      // 默认初始化信息
      defaultInfo: {
        title: '',
        imageUrl: '',
        linkUrl: '',
        sort: ''
      },
      // 轮播图信息
      bannerInfo: {
        title: '',
        imageUrl: '',
        linkUrl: '',
        sort: ''
      },
      BASE_API: process.env.BASE_API, // 接口API地址
      imgUrl: '', // 预览图片地址
      bannerId: '' // 轮播图id
    }
  },
  watch: { // 监听
    $route(to, from) { // 路由变化方式，路由发生变化，方法就会执行
      this.init()
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      // 判断路径是否有id值
      if (this.$route.params && this.$route.params.id) {
        this.bannerId = this.$route.params.id
        // 调用根据id查询轮播图信息方法
        this.getBannerInfo()
      } else { // 路径没有id值，做添加
        // 清空表单
        this.imgUrl = ''
        this.bannerInfo = { ...this.defaultInfo }
      }
    },
    // 根据id查询轮播图信息方法
    getBannerInfo() {
      bannerApi.getBannerInfoId(this.bannerId)
        .then(response => {
          // 轮播图信息赋值
          this.bannerInfo = response.data.item
          // 图片地址赋值
          this.imgUrl = this.bannerInfo.imageUrl
        })
    },
    // 上传封面变换时执行的方法
    onSelectFile(uploadFile, fileList) {
      // 转换后的地址例如：blob:http://localhost:9528/53b9db82-2dab-4632-8ef9-b7ee9f408731
      this.imgUrl = URL.createObjectURL(uploadFile.raw) // 生成预览图片地址
      if (fileList.length > 1) { // 如果文件数量大于1，删除第一个文件（上一个文件）
        fileList.shift()
      }
    },
    // 上传封面成功调用的方法
    handleBannerSuccess(res, file) {
      // 服务器返回封面地址
      this.bannerInfo.imageUrl = res.data.url
      // 判断添加还是修改
      if (!this.bannerInfo.id) {
        // 添加
        this.addBanner()
      } else {
        // 修改
        this.updateBanner()
      }
    },
    // 添加轮播图的方法
    addBanner() {
      bannerApi.addBannerInfo(this.bannerInfo)
        .then(response => {
          // 提示
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          // 跳转到列表页面
          this.$router.push('/banner/list')
        })
    },
    // 修改轮播图
    updateBanner() {
      bannerApi.updateBannerInfo(this.bannerInfo)
        .then(response => {
          // 提示
          this.$message({
            type: 'success',
            message: '修改轮播图信息成功!'
          })
          // 跳转到列表页面
          this.$router.push('/banner/list')
        })
    },
    // 保存并下一步
    saveOrUpdate() {
      // 判断添加还是修改
      if (!this.bannerInfo.id) {
        // 添加 先上传图片
        this.$refs.upload.submit()
      } else {
        // 修改 判断是否更换封面
        if (this.imgUrl === this.bannerInfo.imageUrl) { // 未更换则直接调用修改接口
          // 调用接口上传轮播图信息
          this.updateBanner()
        } else { // 更换则先上传图片，再调用接口
          this.$refs.upload.submit()
        }
      }
    }
  }
}
</script>
<style scoped>
.title .el-input {
  width: 300px;
}

.linkUrl .el-input {
  width: 400px;
}

.banner-uploader {
  width: auto;
  height: 380px;
  border: 1px dashed;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: all .3s linear .1s;
  text-align: center;
}

.banner-uploader:hover {
  border-color: #409EFF;
}

.banner-uploader:hover i {
  color: #409EFF;
}

.banner-uploader i {
  font-size: 50px;
  color: #8c939d;
  width: 1150px;
  height: 380px;
  line-height: 380px;
  text-align: center;
  transition: all .3s linear .1s;
}
</style>
