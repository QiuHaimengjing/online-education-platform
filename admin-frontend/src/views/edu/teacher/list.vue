<template>
  <div class="app-container">
    <!-- 返回顶部按钮 -->
    <back-top-btn />
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline" align="center">

      <el-form-item label="搜索"/>

      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="讲师名"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="teacherQuery.level" clearable placeholder="讲师头衔">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <!-- 卡片 -->
    <el-card>
      <div slot="header">
        <span>讲师列表</span>
      </div>
      <!-- 表格 -->
      <el-table v-loading="loading" :data="list" element-loading-text="数据加载中" border fit highlight-current-row>

        <el-table-column label="序号" width="70" align="center">
          <template slot-scope="scope">
            {{ (page - 1) * limit + scope.$index + 1 }}
          </template>
        </el-table-column>

        <el-table-column prop="name" label="名称" width="200" align="center"/>

        <el-table-column label="头衔" width="150" align="center">
          <template slot-scope="scope">
            {{ scope.row.level === 1 ? '高级讲师' : '首席讲师' }}
          </template>
        </el-table-column>

        <el-table-column prop="intro" label="资历" show-overflow-tooltip align="center"/>

        <el-table-column prop="gmtCreate" label="添加时间" width="160" align="center"/>

        <el-table-column prop="sort" label="排序" width="60" align="center"/>

        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <router-link :to="'/teacher/edit/' + scope.row.id">
              <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
            </router-link>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :page-sizes="[5, 10, 15, 20]"
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
import teacher from '@/api/edu/teacher'
export default {
  // 注册组件
  components: { BackTopBtn },
  data() { // 定义变量和初始值
    return {
      list: null, // 查询之后接口返回集合
      page: 1, // 当前页
      limit: 10, // 每页记录数
      total: 0, // 总记录数
      teacherQuery: {}, // 条件封装对象
      loading: false // 加载状态
    }
  },
  created() { // 页面渲染之前执行，一般调用methods定义的方法
    this.getList()
  },
  methods: { // 创建具体方法，调用接口
    // 每页记录数改变
    handleSizeChange(val) {
      this.limit = val
      this.getList()
    },
    // 讲师列表的方法
    getList(page = 1) {
      this.page = page
      this.loading = true
      teacher.getTeacherListPage(this.page, this.limit, this.teacherQuery)
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
      this.teacherQuery = {}
      // 查询所有讲师数据
      this.getList()
    },
    // 删除讲师的方法
    removeDataById(id) {
      this.$confirm('此操作将永久删除讲师记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // 点击确认，执行then方法
        // 调用删除方法
        teacher.deleteTeacherId(id)
          .then(response => { // 删除成功
            // 提示信息
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            // 回到列表页面
            this.getList()
          })
      })
    }
  }
}
</script>

<style>
.el-tooltip__popper {
  max-width: 50%;
}
</style>
