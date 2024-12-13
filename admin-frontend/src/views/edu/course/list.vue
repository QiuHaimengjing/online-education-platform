<template>
  <div class="app-container">
    <!-- 返回顶部按钮 -->
    <back-top-btn />
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline" align="center">

      <el-form-item label="搜索"/>

      <el-form-item>
        <el-input v-model="courseQuery.title" placeholder="课程名称"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="courseQuery.status" clearable placeholder="发布状态">
          <el-option :value="'Normal'" label="已发布"/>
          <el-option :value="'Draft'" label="未发布"/>
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="courseQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="courseQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <el-card>
      <div slot="header">
        <span>课程列表</span>
      </div>
      <!-- 表格 -->
      <el-table v-loading="loading" :data="list" element-loading-text="数据加载中" border fit highlight-current-row>

        <el-table-column label="序号" width="70" align="center">
          <template slot-scope="scope">
            {{ (page - 1) * limit + scope.$index + 1 }}
          </template>
        </el-table-column>

        <el-table-column prop="title" label="课程名称" width="300" align="center"/>

        <el-table-column label="课程状态" width="100" align="center">
          <template slot-scope="scope">
            {{ scope.row.status === 'Normal' ? '已发布' : '未发布' }}
          </template>
        </el-table-column>

        <el-table-column prop="lessonNum" width="100" label="课时数" align="center"/>

        <el-table-column prop="gmtCreate" label="添加时间" width="200" align="center"/>

        <el-table-column prop="viewCount" label="浏览数量" width="100" align="center"/>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <router-link :to="'/course/info/' + scope.row.id">
              <el-button type="primary" size="mini" icon="el-icon-edit" style="margin-right: 10px;">基本信息</el-button>
            </router-link>
            <router-link :to="'/course/chapter/' + scope.row.id">
              <el-button type="primary" size="mini" icon="el-icon-edit" style="margin-right: 10px;">课程大纲</el-button>
            </router-link>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除课程</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 分页 -->
    <el-pagination
      :page-sizes="[5, 10, 15, 20]"
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="sizes, total, prev, pager, next, jumper"
      @current-change="getList"
      @size-change="handleSizeChange"
    />
  </div>
</template>

<script>
// 引入返回顶部按钮组件
import BackTopBtn from '@/components/BackTopBtn'
// 引入teacher.js
import course from '@/api/edu/course'
export default {
  // 注册组件
  components: { BackTopBtn },
  data() { // 定义变量和初始值
    return {
      list: null, // 查询之后接口返回集合
      page: 1, // 当前页
      limit: 10, // 每页记录数
      total: 0, // 总记录数
      courseQuery: {}, // 条件封装对象
      loading: false // 加载状态
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 每页记录数改变
    handleSizeChange(val) {
      this.limit = val
      this.getList()
    },
    // 课程列表的方法
    getList(page = 1) {
      this.page = page
      this.loading = true
      course.getCourseListPage(this.page, this.limit, this.courseQuery)
        .then(response => {
          // 请求成功response接口返回的数据
          this.list = response.data.rows
          this.total = response.data.total
          // 请求成功后默认>=0.5秒loading动画
          setTimeout(() => {
            this.loading = false
          }, 500)
        })
        .catch(error => {
          // 请求失败
          this.loading = false
          console.log(error)
        })
    },
    // 清空查询条件
    resetData() {
      // 表单输入项清空
      this.courseQuery = {}
      // 查询所有讲师数据
      this.getList()
    },
    // 删除课程的方法
    removeDataById(id) {
      this.$confirm('此操作将永久删除课程记录，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // 点击确认，执行then方法
        // 调用删除方法
        course.deleteCourseId(id)
          .then(response => {
            // 删除成功
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            // 刷新列表
            this.getList()
          })
      })
    }
  }
}
</script>
