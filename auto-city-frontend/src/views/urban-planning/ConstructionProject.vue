<template>
  <div class="construction-project-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>建设工程</span>
          <el-button type="primary" @click="handleCreate">新增项目</el-button>
        </div>
      </template>
      
      <!-- 搜索和筛选 -->
      <div class="filter-container">
        <el-form :inline="true" :model="filterForm" class="demo-form-inline">
          <el-form-item label="项目名称">
            <el-input v-model="filterForm.projectName" placeholder="请输入项目名称" />
          </el-form-item>
          <el-form-item label="项目类型">
            <el-select v-model="filterForm.projectType" placeholder="请选择项目类型" clearable>
              <el-option label="道路建设" value="road" />
              <el-option label="桥梁建设" value="bridge" />
              <el-option label="房屋建设" value="building" />
              <el-option label="公园建设" value="park" />
              <el-option label="其他" value="other" />
            </el-select>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="filterForm.status" placeholder="请选择状态" clearable>
              <el-option label="规划中" value="planning" />
              <el-option label="施工中" value="constructing" />
              <el-option label="已完成" value="completed" />
              <el-option label="已暂停" value="paused" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleFilter">搜索</el-button>
            <el-button @click="resetFilter">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      
      <!-- 项目进度统计 -->
      <div class="stats-container">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-card class="stat-card">
              <div class="stat-item">
                <div class="stat-label">总项目数</div>
                <div class="stat-value">{{ stats.total }}</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card class="stat-card">
              <div class="stat-item">
                <div class="stat-label">进行中</div>
                <div class="stat-value" :class="getStatClass('constructing')">{{ stats.constructing }}</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card class="stat-card">
              <div class="stat-item">
                <div class="stat-label">已完成</div>
                <div class="stat-value" :class="getStatClass('completed')">{{ stats.completed }}</div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card class="stat-card">
              <div class="stat-item">
                <div class="stat-label">平均进度</div>
                <div class="stat-value">{{ stats.averageProgress }}%</div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
      
      <!-- 表格数据 -->
      <el-table :data="constructionProjects" style="width: 100%" v-loading="loading">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="projectName" label="项目名称" />
        <el-table-column prop="projectType" label="项目类型">
          <template #default="scope">
            <el-tag :type="getProjectTypeTag(scope.row.projectType)">
              {{ getProjectTypeName(scope.row.projectType) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="location" label="位置" />
        <el-table-column prop="budget" label="预算(万元)" />
        <el-table-column prop="startDate" label="开始日期" width="120">
          <template #default="scope">
            {{ formatDate(scope.row.startDate) }}
          </template>
        </el-table-column>
        <el-table-column prop="endDate" label="结束日期" width="120">
          <template #default="scope">
            {{ formatDate(scope.row.endDate) }}
          </template>
        </el-table-column>
        <el-table-column prop="progress" label="进度">
          <template #default="scope">
            <el-progress :percentage="scope.row.progress" :status="getProgressStatus(scope.row.status)" />
          </template>
        </el-table-column>
        <el-table-column prop="manager" label="项目经理" />
        <el-table-column prop="status" label="状态">
          <template #default="scope">
            <el-tag :type="getStatusTag(scope.row.status)">
              {{ getStatusName(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
            <el-button size="small" type="primary" @click="handleView(scope.row)">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <!-- 分页 -->
      <div class="pagination-container">
        <el-pagination
          v-model:current-page="pagination.currentPage"
          v-model:page-size="pagination.pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :total="pagination.total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>
    
    <!-- 对话框 -->
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" />
        </el-form-item>
        <el-form-item label="项目描述" prop="projectDescription">
          <el-input v-model="form.projectDescription" type="textarea" />
        </el-form-item>
        <el-form-item label="项目类型" prop="projectType">
          <el-select v-model="form.projectType" style="width: 100%">
            <el-option label="道路建设" value="road" />
            <el-option label="桥梁建设" value="bridge" />
            <el-option label="房屋建设" value="building" />
            <el-option label="公园建设" value="park" />
            <el-option label="其他" value="other" />
          </el-select>
        </el-form-item>
        <el-form-item label="位置" prop="location">
          <el-input v-model="form.location" />
        </el-form-item>
        <el-form-item label="预算(万元)" prop="budget">
          <el-input-number v-model="form.budget" :min="0" controls-position="right" style="width: 100%" />
        </el-form-item>
        <el-form-item label="开始日期" prop="startDate">
          <el-date-picker
            v-model="form.startDate"
            type="date"
            placeholder="选择日期"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="结束日期" prop="endDate">
          <el-date-picker
            v-model="form.endDate"
            type="date"
            placeholder="选择日期"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="进度(%)" prop="progress">
          <el-slider v-model="form.progress" :max="100" show-input />
        </el-form-item>
        <el-form-item label="项目经理" prop="manager">
          <el-input v-model="form.manager" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" style="width: 100%">
            <el-option label="规划中" value="planning" />
            <el-option label="施工中" value="constructing" />
            <el-option label="已完成" value="completed" />
            <el-option label="已暂停" value="paused" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import { constructionProjectApi } from '@/api/modules/urbanPlanning'
import type { ConstructionProject } from '@/types/urbanPlanning'

// 数据相关
const constructionProjects = ref<ConstructionProject[]>([])
const loading = ref(false)

// 分页相关
const pagination = reactive({
  currentPage: 1,
  pageSize: 10,
  total: 0
})

// 搜索过滤相关
const filterForm = reactive({
  projectName: '',
  projectType: '',
  status: ''
})

// 统计数据
const stats = reactive({
  total: 0,
  constructing: 0,
  completed: 0,
  averageProgress: 0
})

// 对话框相关
const dialogVisible = ref(false)
const dialogTitle = ref('')
const isEdit = ref(false)
const form = reactive<ConstructionProject>({
  id: 0,
  projectName: '',
  projectDescription: '',
  projectType: '',
  location: '',
  budget: 0,
  startDate: '',
  endDate: '',
  progress: 0,
  manager: '',
  status: 'planning',
  createdTime: '',
  updatedTime: ''
})

// 表单引用
const formRef = ref<FormInstance>()

// 验证规则
const rules = reactive<FormRules>({
  projectName: [{ required: true, message: '请输入项目名称', trigger: 'blur' }],
  projectType: [{ required: true, message: '请选择项目类型', trigger: 'change' }],
  location: [{ required: true, message: '请输入位置', trigger: 'blur' }],
  budget: [{ required: true, message: '请输入预算', trigger: 'blur' }],
  startDate: [{ required: true, message: '请选择开始日期', trigger: 'change' }],
  endDate: [{ required: true, message: '请选择结束日期', trigger: 'change' }],
  manager: [{ required: true, message: '请输入项目经理', trigger: 'blur' }]
})

// 计算统计数据
const calculateStats = () => {
  stats.total = constructionProjects.value.length
  stats.constructing = constructionProjects.value.filter(p => p.status === 'constructing').length
  stats.completed = constructionProjects.value.filter(p => p.status === 'completed').length
  
  if (stats.total > 0) {
    const totalProgress = constructionProjects.value.reduce((sum, project) => sum + project.progress, 0)
    stats.averageProgress = Math.round(totalProgress / stats.total)
  } else {
    stats.averageProgress = 0
  }
}

// 获取数据
const fetchData = async () => {
  loading.value = true
  try {
    const response = await constructionProjectApi.getAllConstructionProjects()
    constructionProjects.value = response || []
    pagination.total = constructionProjects.value.length
    calculateStats()
  } catch (error) {
    ElMessage.error('获取数据失败')
  } finally {
    loading.value = false
  }
}

// 处理创建
const handleCreate = () => {
  dialogTitle.value = '新增建设工程'
  isEdit.value = false
  // 重置表单
  Object.assign(form, {
    id: 0,
    projectName: '',
    projectDescription: '',
    projectType: '',
    location: '',
    budget: 0,
    startDate: '',
    endDate: '',
    progress: 0,
    manager: '',
    status: 'planning',
    createdTime: '',
    updatedTime: ''
  })
  dialogVisible.value = true
}

// 处理编辑
const handleEdit = (row: ConstructionProject) => {
  dialogTitle.value = '编辑建设工程'
  isEdit.value = true
  Object.assign(form, row)
  dialogVisible.value = true
}

// 处理查看
const handleView = (row: ConstructionProject) => {
  dialogTitle.value = '查看建设工程'
  isEdit.value = true
  Object.assign(form, row)
  dialogVisible.value = true
}

// 处理删除
const handleDelete = (row: ConstructionProject) => {
  ElMessageBox.confirm('确认删除该项目吗？', '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await constructionProjectApi.deleteConstructionProject(row.id)
      ElMessage.success('删除成功')
      fetchData()
    } catch (error) {
      ElMessage.error('删除失败')
    }
  })
}

// 提交表单
const submitForm = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        if (isEdit.value) {
          await constructionProjectApi.updateConstructionProject(form.id, form)
          ElMessage.success('更新成功')
        } else {
          await constructionProjectApi.createConstructionProject(form)
          ElMessage.success('创建成功')
        }
        dialogVisible.value = false
        fetchData()
      } catch (error) {
        ElMessage.error('操作失败')
      }
    }
  })
}

// 处理搜索
const handleFilter = () => {
  // 这里可以根据实际需求添加搜索逻辑
  fetchData()
}

// 重置搜索
const resetFilter = () => {
  Object.assign(filterForm, {
    projectName: '',
    projectType: '',
    status: ''
  })
  fetchData()
}

// 分页相关方法
const handleSizeChange = (val: number) => {
  pagination.pageSize = val
  fetchData()
}

const handleCurrentChange = (val: number) => {
  pagination.currentPage = val
  fetchData()
}

// 工具方法
const formatDate = (dateString: string) => {
  if (!dateString) return ''
  const date = new Date(dateString)
  return date.toLocaleDateString()
}

const getProjectTypeTag = (type: string) => {
  switch (type) {
    case 'road': return 'primary'
    case 'bridge': return 'success'
    case 'building': return 'warning'
    case 'park': return 'info'
    case 'other': return 'default'
    default: return 'info'
  }
}

const getProjectTypeName = (type: string) => {
  switch (type) {
    case 'road': return '道路建设'
    case 'bridge': return '桥梁建设'
    case 'building': return '房屋建设'
    case 'park': return '公园建设'
    case 'other': return '其他'
    default: return '未知'
  }
}

const getStatusTag = (status: string) => {
  switch (status) {
    case 'planning': return 'info'
    case 'constructing': return 'warning'
    case 'completed': return 'success'
    case 'paused': return 'danger'
    default: return 'info'
  }
}

const getStatusName = (status: string) => {
  switch (status) {
    case 'planning': return '规划中'
    case 'constructing': return '施工中'
    case 'completed': return '已完成'
    case 'paused': return '已暂停'
    default: return '未知'
  }
}

const getProgressStatus = (status: string) => {
  switch (status) {
    case 'completed': return 'success'
    case 'paused': return 'exception'
    default: return ''
  }
}

const getStatClass = (type: string) => {
  switch (type) {
    case 'constructing': return 'stat-constructing'
    case 'completed': return 'stat-completed'
    default: return ''
  }
}

// 初始化
onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.construction-project-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.filter-container {
  margin-bottom: 20px;
}

.stats-container {
  margin-bottom: 20px;
}

.stat-card {
  border-radius: 8px;
}

.stat-item {
  text-align: center;
}

.stat-label {
  font-size: 14px;
  color: #666;
  margin-bottom: 5px;
}

.stat-value {
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

.stat-constructing {
  color: #e6a23c;
}

.stat-completed {
  color: #67c23a;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>