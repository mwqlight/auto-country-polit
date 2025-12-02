<template>
  <div class="land-use-plan-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>土地利用规划</span>
          <el-button type="primary" @click="handleCreate">新增规划</el-button>
        </div>
      </template>
      
      <!-- 搜索和筛选 -->
      <div class="filter-container">
        <el-form :inline="true" :model="filterForm" class="demo-form-inline">
          <el-form-item label="规划名称">
            <el-input v-model="filterForm.planName" placeholder="请输入规划名称" />
          </el-form-item>
          <el-form-item label="用地类型">
            <el-select v-model="filterForm.landUseType" placeholder="请选择用地类型" clearable>
              <el-option label="住宅用地" value="residential" />
              <el-option label="商业用地" value="commercial" />
              <el-option label="工业用地" value="industrial" />
              <el-option label="公共设施用地" value="public" />
              <el-option label="绿地" value="green" />
            </el-select>
          </el-form-item>
          <el-form-item label="审批状态">
            <el-select v-model="filterForm.approvalStatus" placeholder="请选择审批状态" clearable>
              <el-option label="待审批" value="pending" />
              <el-option label="已批准" value="approved" />
              <el-option label="已拒绝" value="rejected" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleFilter">搜索</el-button>
            <el-button @click="resetFilter">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      
      <!-- 表格数据 -->
      <el-table :data="landUsePlans" style="width: 100%" v-loading="loading">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="planName" label="规划名称" />
        <el-table-column prop="landUseType" label="用地类型">
          <template #default="scope">
            <el-tag :type="getLandUseTypeTag(scope.row.landUseType)">
              {{ getLandUseTypeName(scope.row.landUseType) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="area" label="面积(平方米)" />
        <el-table-column prop="location" label="位置" />
        <el-table-column prop="planner" label="规划师" />
        <el-table-column prop="approvalStatus" label="审批状态">
          <template #default="scope">
            <el-tag :type="getApprovalStatusTag(scope.row.approvalStatus)">
              {{ getApprovalStatusName(scope.row.approvalStatus) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createdTime" label="创建时间" width="180">
          <template #default="scope">
            {{ formatDate(scope.row.createdTime) }}
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
        <el-form-item label="规划名称" prop="planName">
          <el-input v-model="form.planName" />
        </el-form-item>
        <el-form-item label="规划描述" prop="planDescription">
          <el-input v-model="form.planDescription" type="textarea" />
        </el-form-item>
        <el-form-item label="用地类型" prop="landUseType">
          <el-select v-model="form.landUseType" style="width: 100%">
            <el-option label="住宅用地" value="residential" />
            <el-option label="商业用地" value="commercial" />
            <el-option label="工业用地" value="industrial" />
            <el-option label="公共设施用地" value="public" />
            <el-option label="绿地" value="green" />
          </el-select>
        </el-form-item>
        <el-form-item label="面积(平方米)" prop="area">
          <el-input-number v-model="form.area" :min="0" controls-position="right" style="width: 100%" />
        </el-form-item>
        <el-form-item label="位置" prop="location">
          <el-input v-model="form.location" />
        </el-form-item>
        <el-form-item label="规划师" prop="planner">
          <el-input v-model="form.planner" />
        </el-form-item>
        <el-form-item label="审批状态" prop="approvalStatus">
          <el-select v-model="form.approvalStatus" style="width: 100%">
            <el-option label="待审批" value="pending" />
            <el-option label="已批准" value="approved" />
            <el-option label="已拒绝" value="rejected" />
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
import { landUsePlanApi } from '@/api/modules/urbanPlanning'
import type { LandUsePlan } from '@/types/urbanPlanning'

// 数据相关
const landUsePlans = ref<LandUsePlan[]>([])
const loading = ref(false)

// 分页相关
const pagination = reactive({
  currentPage: 1,
  pageSize: 10,
  total: 0
})

// 搜索过滤相关
const filterForm = reactive({
  planName: '',
  landUseType: '',
  approvalStatus: ''
})

// 对话框相关
const dialogVisible = ref(false)
const dialogTitle = ref('')
const isEdit = ref(false)
const form = reactive<LandUsePlan>({
  id: 0,
  planName: '',
  planDescription: '',
  landUseType: '',
  area: 0,
  location: '',
  planner: '',
  approvalStatus: 'pending',
  createdTime: '',
  updatedTime: ''
})

// 表单引用
const formRef = ref<FormInstance>()

// 验证规则
const rules = reactive<FormRules>({
  planName: [{ required: true, message: '请输入规划名称', trigger: 'blur' }],
  landUseType: [{ required: true, message: '请选择用地类型', trigger: 'change' }],
  area: [{ required: true, message: '请输入面积', trigger: 'blur' }],
  location: [{ required: true, message: '请输入位置', trigger: 'blur' }],
  planner: [{ required: true, message: '请输入规划师', trigger: 'blur' }]
})

// 获取数据
const fetchData = async () => {
  loading.value = true
  try {
    const response = await landUsePlanApi.getAllLandUsePlans()
    landUsePlans.value = response || []
    pagination.total = landUsePlans.value.length
  } catch (error) {
    ElMessage.error('获取数据失败')
  } finally {
    loading.value = false
  }
}

// 处理创建
const handleCreate = () => {
  dialogTitle.value = '新增土地利用规划'
  isEdit.value = false
  // 重置表单
  Object.assign(form, {
    id: 0,
    planName: '',
    planDescription: '',
    landUseType: '',
    area: 0,
    location: '',
    planner: '',
    approvalStatus: 'pending',
    createdTime: '',
    updatedTime: ''
  })
  dialogVisible.value = true
}

// 处理编辑
const handleEdit = (row: LandUsePlan) => {
  dialogTitle.value = '编辑土地利用规划'
  isEdit.value = true
  Object.assign(form, row)
  dialogVisible.value = true
}

// 处理查看
const handleView = (row: LandUsePlan) => {
  dialogTitle.value = '查看土地利用规划'
  isEdit.value = true
  Object.assign(form, row)
  dialogVisible.value = true
}

// 处理删除
const handleDelete = (row: LandUsePlan) => {
  ElMessageBox.confirm('确认删除该规划吗？', '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await landUsePlanApi.deleteLandUsePlan(row.id)
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
          await landUsePlanApi.updateLandUsePlan(form.id, form)
          ElMessage.success('更新成功')
        } else {
          await landUsePlanApi.createLandUsePlan(form)
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
    planName: '',
    landUseType: '',
    approvalStatus: ''
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

const getLandUseTypeTag = (type: string) => {
  switch (type) {
    case 'residential': return 'primary'
    case 'commercial': return 'success'
    case 'industrial': return 'warning'
    case 'public': return 'info'
    case 'green': return 'success'
    default: return 'info'
  }
}

const getLandUseTypeName = (type: string) => {
  switch (type) {
    case 'residential': return '住宅用地'
    case 'commercial': return '商业用地'
    case 'industrial': return '工业用地'
    case 'public': return '公共设施用地'
    case 'green': return '绿地'
    default: return '未知'
  }
}

const getApprovalStatusTag = (status: string) => {
  switch (status) {
    case 'pending': return 'warning'
    case 'approved': return 'success'
    case 'rejected': return 'danger'
    default: return 'info'
  }
}

const getApprovalStatusName = (status: string) => {
  switch (status) {
    case 'pending': return '待审批'
    case 'approved': return '已批准'
    case 'rejected': return '已拒绝'
    default: return '未知'
  }
}

// 初始化
onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.land-use-plan-container {
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

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>