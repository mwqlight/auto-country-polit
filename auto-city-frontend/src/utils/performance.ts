// 性能优化工具函数
/// <reference types="node" />

/**
 * 防抖函数
 * @param func 需要防抖的函数
 * @param delay 延迟时间(ms)
 * @returns 防抖后的函数
 */
export function debounce<T extends (...args: any[]) => any>(
  func: T,
  delay: number
): (...args: Parameters<T>) => void {
  let timeoutId: NodeJS.Timeout | null = null
  return function (this: any, ...args: Parameters<T>) {
    if (timeoutId) {
      clearTimeout(timeoutId)
    }
    timeoutId = setTimeout(() => {
      func.apply(this, args)
    }, delay)
  }
}

/**
 * 节流函数
 * @param func 需要节流的函数
 * @param delay 延迟时间(ms)
 * @returns 节流后的函数
 */
export function throttle<T extends (...args: any[]) => any>(
  func: T,
  delay: number
): (...args: Parameters<T>) => void {
  let lastExecTime = 0
  return function (this: any, ...args: Parameters<T>) {
    const now = Date.now()
    if (now - lastExecTime >= delay) {
      func.apply(this, args)
      lastExecTime = now
    }
  }
}

/**
 * 内存泄漏检测
 */
export class MemoryLeakDetector {
  private observers: MutationObserver[] = []
  private intervals: NodeJS.Timeout[] = []
  private timeouts: NodeJS.Timeout[] = []

  /**
   * 添加MutationObserver
   * @param observer 
   */
  addObserver(observer: MutationObserver) {
    this.observers.push(observer)
  }

  /**
   * 添加定时器
   * @param interval 
   */
  addInterval(interval: NodeJS.Timeout) {
    this.intervals.push(interval)
  }

  /**
   * 添加延时器
   * @param timeout 
   */
  addTimeout(timeout: NodeJS.Timeout) {
    this.timeouts.push(timeout)
  }

  /**
   * 清理所有观察者和定时器
   */
  cleanup() {
    // 断开所有MutationObserver
    this.observers.forEach(observer => observer.disconnect())
    this.observers = []

    // 清除所有interval
    this.intervals.forEach(interval => clearInterval(interval))
    this.intervals = []

    // 清除所有timeout
    this.timeouts.forEach(timeout => clearTimeout(timeout))
    this.timeouts = []
  }
}

/**
 * 虚拟滚动配置
 */
export interface VirtualScrollOptions {
  containerHeight: number
  itemHeight: number
  bufferSize: number
}

/**
 * 虚拟滚动hook
 * @param options 虚拟滚动配置
 * @returns 可视区域的起始索引和结束索引
 */
export function useVirtualScroll(options: VirtualScrollOptions) {
  const { containerHeight, itemHeight, bufferSize } = options
  const visibleCount = Math.ceil(containerHeight / itemHeight)
  const totalBufferSize = bufferSize * 2
  const totalVisibleCount = visibleCount + totalBufferSize

  return {
    visibleCount,
    totalVisibleCount,
    calculateRange: (scrollTop: number, totalItems: number) => {
      const start = Math.max(0, Math.floor(scrollTop / itemHeight) - bufferSize)
      const end = Math.min(totalItems, start + totalVisibleCount)
      return { start, end }
    }
  }
}

/**
 * 图片懒加载
 */
export class ImageLazyLoader {
  private observer: IntersectionObserver | null = null

  constructor() {
    if ('IntersectionObserver' in window) {
      this.observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            const img = entry.target as HTMLImageElement
            const src = img.dataset.src
            if (src) {
              img.src = src
              img.classList.remove('lazy')
              this.observer?.unobserve(img)
            }
          }
        })
      }, {
        rootMargin: '50px 0px'
      })
    }
  }

  /**
   * 观察图片元素
   * @param img 图片元素
   */
  observe(img: HTMLImageElement) {
    if (this.observer) {
      this.observer.observe(img)
    } else {
      // 降级处理：直接加载图片
      const src = img.dataset.src
      if (src) {
        img.src = src
      }
    }
  }

  /**
   * 停止观察
   */
  disconnect() {
    this.observer?.disconnect()
  }
}

/**
 * 性能监控
 */
export class PerformanceMonitor {
  private metrics: Record<string, number[]> = {}

  /**
   * 记录性能指标
   * @param name 指标名称
   * @param value 指标值
   */
  recordMetric(name: string, value: number) {
    if (!this.metrics[name]) {
      this.metrics[name] = []
    }
    this.metrics[name].push(value)
  }

  /**
   * 获取指标统计信息
   * @param name 指标名称
   * @returns 统计信息
   */
  getMetrics(name: string) {
    const values = this.metrics[name] || []
    if (values.length === 0) return null

    const sorted = [...values].sort((a, b) => a - b)
    const sum = sorted.reduce((a, b) => a + b, 0)
    
    return {
      count: values.length,
      avg: sum / values.length,
      min: sorted[0],
      max: sorted[sorted.length - 1],
      p50: sorted[Math.floor(sorted.length * 0.5)],
      p95: sorted[Math.floor(sorted.length * 0.95)],
      p99: sorted[Math.floor(sorted.length * 0.99)]
    }
  }

  /**
   * 上报性能数据到服务器
   * @param endpoint 上报接口
   */
  async reportMetrics(endpoint: string) {
    const reportData: Record<string, any> = {}
    
    Object.keys(this.metrics).forEach(key => {
      reportData[key] = this.getMetrics(key)
    })

    try {
      await fetch(endpoint, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(reportData)
      })
    } catch (error) {
      console.error('Failed to report performance metrics:', error)
    }
  }
}

// 创建全局性能监控实例
export const performanceMonitor = new PerformanceMonitor()

/**
 * 计算组件渲染时间
 * @param componentName 组件名称
 * @param renderFunction 渲染函数
 * @returns 渲染结果
 */
export async function measureRenderTime<T>(
  componentName: string,
  renderFunction: () => Promise<T> | T
): Promise<T> {
  const startTime = performance.now()
  try {
    const result = await Promise.resolve(renderFunction())
    const endTime = performance.now()
    performanceMonitor.recordMetric(`${componentName}_render_time`, endTime - startTime)
    return result
  } catch (error) {
    const endTime = performance.now()
    performanceMonitor.recordMetric(`${componentName}_render_time`, endTime - startTime)
    throw error
  }
}