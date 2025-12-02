import axios from 'axios'
import type { AxiosRequestConfig, AxiosResponse, AxiosError } from 'axios'

// 创建axios实例
const service = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080', // 默认后端地址
  timeout: 15000 // 请求超时时间
})

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 在发送请求之前做些什么
    const token = localStorage.getItem('access_token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    // 对请求错误做些什么
    console.error('Request error:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse) => {
    const { code, message, data } = response.data
    
    // 根据返回的code值来做不同的处理
    if (code === 200) {
      return data
    } else {
      // 处理其他状态码
      console.error(`Error ${code}: ${message}`)
      return Promise.reject(new Error(message || 'Error'))
    }
  },
  (error: AxiosError) => {
    console.error('Response error:', error)
    
    if (error.response?.status === 401) {
      // token过期或无效，清除本地存储并跳转到登录页
      localStorage.removeItem('access_token')
      // 这里可以添加跳转到登录页的逻辑
    }
    
    return Promise.reject(error)
  }
)

// 封装请求方法
const request = async <T>(config: AxiosRequestConfig): Promise<T> => {
  try {
    const response: AxiosResponse<T> = await service(config)
    return response.data
  } catch (error) {
    throw error
  }
}

export default request