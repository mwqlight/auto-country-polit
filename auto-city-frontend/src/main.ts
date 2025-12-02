import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import VueLazyLoad from 'vue3-lazyload'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(VueLazyLoad, {
  // 全局配置
  loading: '', // 加载时的占位图
  error: '', // 加载失败时的图片
  lifecycle: {
    loading: (el) => {
      console.log('image loading', el)
    },
    error: (el) => {
      console.log('image error', el)
    },
    loaded: (el) => {
      console.log('image loaded', el)
    }
  }
})

app.mount('#app')
