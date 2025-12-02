<template>
  <div class="planning-approval-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>规划审批</span>
          <el-button type="primary" @click="handleCreate">新增审批</el-button>
        </div>
      </template>
      
      <!-- 搜索和筛选 -->
      <div class="filter-container">
        <el-form :inline="true" :model="filterForm" class="demo-form-inline">
          <el-form-item label="审批事项">
            <el-input v-model="filterForm.approvalItem" placeholder="请输入审批事项" />
          </el-form-item>
          <el-form-item label="申请人">
            <el-input v-model="filterForm.applicant" placeholder="请输入申请人" />
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
      
      <!-- 审批流程图 -->
      <div class="process-container">
        <el-steps :active="currentStep" finish-status="success" simple>
          <el-step title="提交申请" />
          <el-step title="部门初审" />
          <el-step title="专家评审" />
          <el-step title="领导审批" />
          <el-step title="完成" />
        </el-steps>
      </div>
      
      <!-- 表格数据 -->
      <el-table :data="planningApprovals" style="width: 100%" v-loading="loading">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="approvalItem" label="审批事项" />
        <el-table-column prop="applicant" label="申请人" />
        <el-table-column prop="applicationDate" label="申请日期" width="120">
          <template #default="scope">
            {{ formatDate(scope.row.applicationDate) }}
          </template>
        </el-table-column>
        <el-table-column prop="approvalDate" label="审批日期" width="120">
          <template #default="scope">
            {{ formatDate(scope.row.approvalDate) }}
          </template>
        </el-table-column>
        <el-table-column prop="approvalStatus" label="审批状态">
          <template #default="scope">
            <el-tag :type="getApprovalStatusTag(scope.row.approvalStatus)">
              {{ getApprovalStatusName(scope.row.approvalStatus) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="approver" label="审批人" />
        <el-table-column label="关联信息">
          <template #default="scope">
            <div v-if="scope.row.relatedPlanId">规划ID: {{ scope.row.relatedPlanId }}</div>
            <div v-if="scope.row.relatedProjectId">项目ID: {{ scope.row.relatedProjectId }}</div>
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
        <el-form-item label="审批事项" prop="approvalItem">
          <el-input v-model="form.approvalItem" />
        </el-form-item>
        <el-form-item label="申请人" prop="applicant">
          <el-input v-model="form.applicant" />
        </el-form-item>
        <el-form-item label="申请日期" prop="applicationDate">
          <el-date-picker
            v-model="form.applicationDate"
            type="date"
            placeholder="选择日期"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="审批日期" prop="approvalDate">
          <el-date-picker
            v-model="form.approvalDate"
            type="date"
            placeholder="选择日期"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="审批状态" prop="approvalStatus">
          <el-select v-model="form.approvalStatus" style="width: 100%">
            <el-option label="待审批" value="pending" />
            <el-option label="已批准" value="approved" />
            <el-option label="已拒绝" value="rejected" />
          </el-select>
        </el-form-item>
        <el-form-item label="审批人" prop="approver">
          <el-input v-model="form.approver" />
        </el-form-item>
        <el-form-item label="备注" prop="comments">
          <el-input v-model="form.comments" type="textarea" />
        </el-form-item>
        <el-form-item label="关联规划ID">
          <el-input-number v-model="form.relatedPlanId" :min="0" controls-position="right" style="width: 100%" />
        </el-form-item>
        <el-form-item label="关联项目ID">
          <el-input-number v-model="form.relatedProjectId" :min="0" controls-position="right" style="width: 100%" />
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
import { planningApprovalApi } from '@/api/modules/urbanPlanning'
import type { PlanningApproval } from '@/types/urbanPlanning'

// 数据相关
const planningApprovals = ref<PlanningApproval[]>([])
const loading = ref(false)

// 分页相关
const pagination = reactive({
  currentPage: 1,
  pageSize: 10,
  total: 0
})

// 当前步骤（模拟审批流程）
const currentStep = ref(2)

// 搜索过滤相关
const filterForm = reactive({
  approvalItem: '',
  applicant: '',
  approvalStatus: ''
})

// 对话框相关
const dialogVisible = ref(false)
const dialogTitle = ref('')
const isEdit = ref(false)
const form = reactive<PlanningApproval>({
  id: 0,
  approvalItem: '',
  applicant: '',
  applicationDate: '',
  approvalDate: '',
  approvalStatus: 'pending',
  approver: '',
  comments: '',
  relatedPlanId: 0,
  relatedProjectId: 0,
  createdTime: '',
  updatedTime: ''
})

// 表单引用
const formRef = ref<FormInstance>()

// 验证规则
const rules = reactive<FormRules>({
  approvalItem: [{ required: true, message: '请输入审批事项', trigger: 'blur' }],
  applicant: [{ required: true, message: '请输入申请人', trigger: 'blur' }],
  applicationDate: [{ required: true, message: '请选择申请日期', trigger: 'change' }],
  approvalStatus: [{ required: true, message: '请选择审批状态', trigger: 'change' }],
  approver: [{ required: true, message: '请输入审批人', trigger: 'blur' }]
})

// 获取数据
const fetchData = async () => {
  loading.value = true
  try {
    const response = await planningApprovalApi.getAllPlanningApprovals()
    planningApprovals.value = response || []
    pagination.total = planningApprovals.value.length
  } catch (error) {
    ElMessage.error('获取数据失败')
  } finally {
    loading.value = false
  }
}

// 处理创建
const handleCreate = () => {
  dialogTitle.value = '新增规划审批'
  isEdit.value = false
  // 重置表单
  Object.assign(form, {
    id: 0,
    approvalItem: '',
    applicant: '',
    applicationDate: '',
    approvalDate: '',
    approvalStatus: 'pending',
    approver: '',
    comments: '',
    relatedPlanId: 0,
    relatedProjectId: 0,
    createdTime: '',
    updatedTime: ''
  })
  dialogVisible.value = true
}

// 处理编辑
const handleEdit = (row: PlanningApproval) => {
  dialogTitle.value = '编辑规划审批'
  isEdit.value = true
  Object.assign(form, row)
  dialogVisible.value = true
}

// 处理查看
const handleView = (row: PlanningApproval) => {
  dialogTitle.value = '查看规划审批'
  isEdit.value = true
  Object.assign(form, row)
  dialogVisible.value = true
}

// 处理删除
const handleDelete = (row: PlanningApproval) => {
  ElMessageBox.confirm('确认删除该审批记录吗？', '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await planningApprovalApi.deletePlanningApproval(row.id)
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
          await planningApprovalApi.updatePlanningApproval(form.id, form)
          ElMessage.success('更新成功')
        } else {
          await planningApprovalApi.createPlanningApproval(form)
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
    approvalItem: '',
    applicant: '',
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
.planning-approval-container {
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

.process-container {
  margin-bottom: 20px;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>