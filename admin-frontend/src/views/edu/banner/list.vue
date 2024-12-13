<template>
  <div class="app-container">
    <!-- 返回顶部按钮 -->
    <back-top-btn />
    <!-- 卡片 -->
    <el-card>
      <div slot="header">
        <span>轮播图列表</span>
      </div>
      <!-- 表格 -->
      <el-table v-loading="loading" :data="list" element-loading-text="数据加载中" border fit highlight-current-row style="width: 100%" >

        <el-table-column label="序号" width="70" align="center">
          <template slot-scope="scope">
            {{ (page - 1) * limit + scope.$index + 1 }}
          </template>
        </el-table-column>

        <el-table-column prop="title" label="标题" width="200" align="center"/>

        <el-table-column label="图片" width="300" align="center">
          <template slot-scope="scope">
            <el-image
              :src="scope.row.imageUrl"
              :preview-src-list="list.map(item => item.imageUrl)"
              style="width: 300px; height: 50px"
              fit="contain"
            />
          </template>
        </el-table-column>

        <el-table-column prop="linkUrl" label="链接地址" width="200" align="center"/>

        <el-table-column prop="sort" label="排序" width="100" align="center"/>

        <el-table-column prop="gmtCreate" label="添加时间" width="200" align="center"/>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <router-link :to="'/banner/info/' + scope.row.id">
              <el-button type="primary" icon="el-icon-edit" circle size="medium"/>
            </router-link>
            <el-button type="danger" icon="el-icon-delete" circle size="medium" @click="removeDataById(scope.row.id)"/>
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
import banner from '@/api/edu/banner'
export default{
  // 注册组件
  components: { BackTopBtn },
  data() {
    return {
      list: null, // 查询之后接口返回集合
      page: 1, // 当前页
      limit: 10, // 每页记录数
      total: 0, // 总记录数
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
    // 获取轮播图列表的方法
    getList(page = 1) {
      this.page = page
      this.loading = true
      banner.getBannerListPage(this.page, this.limit)
        .then(response => {
          // 请求成功response接口返回的数据
          this.list = response.data.items
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
    // 删除轮播图的方法
    removeDataById(id) {
      this.$confirm('此操作将永久删除轮播图记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // 点击确认，执行then方法
        // 调用删除方法
        banner.deleteBannerId(id)
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
