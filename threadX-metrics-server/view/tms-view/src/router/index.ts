import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/worktable' // 添加重定向规则
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginPage.vue')
  },
  {
    path: '/',
    name: 'home',
    component: () => import('../views/MainHome.vue'),
    children: [
      {
        path: 'worktable',
        name: 'worktable',
        component: () => import('../views/TmsWorktable.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
