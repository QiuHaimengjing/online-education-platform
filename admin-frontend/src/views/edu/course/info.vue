<template>
  <div class="app-container">
    <!-- 返回顶部组件 -->
    <back-top-btn />
    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <!-- 表单 -->
    <el-form label-width="120px">

      <el-form-item label="课程标题">
        <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
      </el-form-item>

      <!-- 分类列表 -->
      <el-form-item label="一级分类">
        <!-- 一级分类 -->
        <el-select
          v-model="courseInfo.subjectParentId"
          placeholder="请选择"
          @change="subjectLevelOneChanged">
          <el-option
            v-for="subject in subjectOneList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"/>
        </el-select>
        <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
          <el-option
            v-for="subject in subjectTwoList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"/>
        </el-select>
      </el-form-item>

      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select
          v-model="courseInfo.teacherId"
          placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"/>
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
      </el-form-item>

      <!-- 课程简介 -->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description"/>
      </el-form-item>

      <!-- 课程封面-->
      <el-form-item label="课程封面">

        <el-upload
          ref="upload"
          :action="BASE_API + '/eduoss/fileoss'"
          :auto-upload="false"
          :show-file-list="true"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :on-change="onSelectFile"
          class="avatar-uploader">
          <img v-if="imgUrl" :src="imgUrl" class="avatar">
          <i v-else class="el-icon-plus" />
        </el-upload>

      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/> 元
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import course from '@/api/edu/course'
import subject from '@/api/edu/subject'
// 引入富文本组件
import Tinymce from '@/components/Tinymce'
// 引入返回顶部组件
import BackTopBtn from '@/components/BackTopBtn'
export default {
  // 注册组件
  components: { Tinymce, BackTopBtn },
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      defaultInfo: { // 初始化默认表单值
        title: '',
        subjectId: '', // 二级分类ID
        subjectParentId: '', // 一级分类ID
        teacherId: '',
        lessonNum: 0,
        description: '',
        cover: '',
        price: 0
      },
      courseInfo: {
        title: '',
        subjectId: '', // 二级分类ID
        subjectParentId: '', // 一级分类ID
        teacherId: '',
        lessonNum: 0,
        description: '',
        cover: '',
        price: 0
      },
      teacherList: [], // 封装所有讲师
      subjectOneList: [], // 一级分类列表
      subjectTwoList: [], // 二级分类列表
      BASE_API: process.env.BASE_API, // 接口API地址
      imgUrl: '', // 预览图片地址
      courseId: '' // 课程id
    }
  },
  watch: { // 监听
    $route(to, from) { // 路由变化方式，路由发生变化，方法就会执行
      this.init()
    }
  },
  created() {
    // 初始化一级分类
    this.getOneSubject()
    // 初始化所有讲师
    this.getListTeacher()
    this.init()
  },
  methods: {
    init() {
      // 判断路径是否有id值
      if (this.$route.params && this.$route.params.id) {
        this.courseId = this.$route.params.id
        // 调用根据id查询课程的方法
        this.getInfo()
      } else { // 路径没有id值，做添加
        // 清空表单
        this.imgUrl = ''
        this.courseInfo = { ...this.defaultInfo }
      }
    },
    // 根据课程id查询
    getInfo() {
      course.getCourseInfoId(this.courseId)
        .then(response => {
          // 在courseInfo课程基本信息，包含 一级分类id 和 二级分类id
          this.courseInfo = response.data.courseInfoVo
          // 初始化封面
          this.imgUrl = this.courseInfo.cover
          // 1.查询所有的分类，包括一级和二级
          subject.getSubjectList()
            .then(response => {
            // 2.获取所有一级分类
              this.subjectOneList = response.data.list
              // 3.把所有一级分类数组进行遍历
              for (let i = 0; i < this.subjectOneList.length; i++) {
              // 获取每个一级分类
                const oneSubject = this.subjectOneList[i]
                // 比较当前courseInfo里面一级分类id和所有的一级分类id
                if (this.courseInfo.subjectParentId === oneSubject.id) {
                // 获取一级分类所有的二级分类
                  this.subjectTwoList = oneSubject.children
                }
              }
            })
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
    handleAvatarSuccess(res, file) {
      // 服务器返回封面地址
      this.courseInfo.cover = res.data.url
      // 判断添加还是修改
      if (!this.courseInfo.id) {
        // 添加
        this.addCourse()
      } else {
        // 修改
        this.updateCourse()
      }
    },
    // 封面上传之前调用的方法
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    // 添加课程
    addCourse() {
      course.addCourseInfo(this.courseInfo)
        .then(response => {
          // 提示
          this.$message({
            type: 'success',
            message: '添加课程信息成功！'
          })
          // 跳转到第二步
          this.$router.push({ path: '/course/chapter/' + response.data.courseId })
        })
    },
    // 修改课程
    updateCourse() {
      course.updateCourseInfo(this.courseInfo)
        .then(response => {
        // 提示
          this.$message({
            type: 'success',
            message: '修改课程信息成功！'
          })
          // 跳转到第二步
          this.$router.push({ path: '/course/chapter/' + this.courseId })
        })
    },
    // 保存并下一步
    saveOrUpdate() {
      // 判断添加还是修改
      if (!this.courseInfo.id) {
        // 添加 先上传图片
        this.$refs.upload.submit()
      } else {
        // 编辑 判断是否更换封面
        if (this.imgUrl === this.courseInfo.cover) { // 未更换则直接调用修改接口
          // 调用接口上传课程信息
          this.updateCourse()
        } else { // 先上传封面，再调用修改接口
          this.$refs.upload.submit()
        }
      }
    },
    // 点击某个一级分类，触发change，显示对应二级分类
    subjectLevelOneChanged(value) {
      // value就是一级分类id值
      // 遍历所有的分类，包含一级和二级
      for (let i = 0; i < this.subjectOneList.length; i++) {
        // 每个一级分类
        const oneSubject = this.subjectOneList[i]
        // 判断：所有一级分类id 和 点击一级分类id是否一样
        if (value === oneSubject.id) {
          // 从一级分类获取里面所有的二级分类
          this.subjectTwoList = oneSubject.children
          // 把二级分类id值清空
          this.courseInfo.subjectId = ''
        }
      }
    },
    // 查询所有一级分类
    getOneSubject() {
      subject.getSubjectList()
        .then(response => {
          this.subjectOneList = response.data.list
        })
    },
    // 查询所有讲师
    getListTeacher() {
      course.getListTeacher()
        .then(response => {
          this.teacherList = response.data.items
        })
    }
  }
}
</script>

<style scoped>
.tinymce-container {
  line-height: 29px;
}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.avatar-uploader {
  width: 178px;
  height: 178px;
  border: 1px dashed;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: all .3s linear .1s;
}
.avatar-uploader:hover {
  border-color: #409EFF;
}
.avatar-uploader:hover i {
  color: #409EFF;
}
.avatar-uploader i {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
  transition: all .3s linear .1s;
}
</style>
