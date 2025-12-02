import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import DashboardView from '../views/DashboardView.vue'

// 懒加载组件
const lazyLoad = (view: string) => {
  return () => import(/* webpackChunkName: "[request]" */ `@/views/${view}.vue`)
}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/dashboard/overview'
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardView,
      children: [
        {
          path: '',
          redirect: 'overview'
        },
        {
          path: 'overview',
          name: 'dashboard-overview',
          component: () => import(/* webpackChunkName: "dashboard-overview" */ '../views/dashboard/Overview.vue')
        },
        {
          path: 'environment',
          name: 'dashboard-environment',
          component: () => import(/* webpackChunkName: "dashboard-environment" */ '../views/dashboard/Environment.vue')
        },
        {
          path: 'energy',
          name: 'dashboard-energy',
          component: () => import(/* webpackChunkName: "dashboard-energy" */ '../views/dashboard/Energy.vue')
        },
        {
          path: 'safety',
          name: 'dashboard-safety',
          component: () => import(/* webpackChunkName: "dashboard-safety" */ '../views/dashboard/Safety.vue')
        },
        {
          path: 'service',
          name: 'dashboard-service',
          component: () => import(/* webpackChunkName: "dashboard-service" */ '../views/dashboard/Service.vue')
        },
        {
          path: 'economic',
          name: 'dashboard-economic',
          component: () => import(/* webpackChunkName: "dashboard-economic" */ '../views/dashboard/Economic.vue')
        },
        {
          path: 'ai-decision',
          name: 'dashboard-ai-decision',
          component: () => import(/* webpackChunkName: "dashboard-ai-decision" */ '../views/dashboard/AIDecision.vue')
        },
        {
          path: 'icon-demo',
          name: 'icon-demo',
          component: () => import(/* webpackChunkName: "icon-demo" */ '../views/IconDemo.vue')
        },
        {
          path: 'traffic',
          name: 'dashboard-traffic',
          children: [
            {
              path: '',
              redirect: 'flow'
            },
            {
              path: 'flow',
              name: 'traffic-flow',
              component: () => import(/* webpackChunkName: "traffic-flow" */ '../views/dashboard/traffic/Flow.vue')
            },
            {
            path: 'incident',
            name: 'traffic-incident',
            component: () => import(/* webpackChunkName: "traffic-incident" */ '../views/dashboard/traffic/Incident.vue')
            },
            {
              path: 'signal',
              name: 'traffic-signal',
              component: () => import(/* webpackChunkName: "traffic-signal" */ '../views/dashboard/traffic/Signal.vue')
            }
          ]
        }
      ]
    }
  ],
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { top: 0 }
    }
  }
})

export default router
