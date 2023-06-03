<template>
    <div>
      <el-menu
      default-active="1"
      active-text-color="#ff0000"
      background-color="#D8D0D0"
      :collapse="true"
      router="true"
    >
      <img src="../assets/logo.svg" alt="threadX" class="logoClass">

      <el-menu-item v-for="item in menuData" :key="item.id" :index="item.menuUrl">
        <i :class="['icon', 'iconfont',  item.menuIcon, 'aside-icon-item']"></i>
        <template #title>{{ item.menuName }}</template>
      </el-menu-item>
    </el-menu>
    </div>
    
  </template>

<script lang="ts">
import { defineComponent,ref, computed, onMounted } from 'vue'
import '../assets/icon/iconfont.css'
import '../assets/css/el-drawer-index.css'
import { Search } from '@element-plus/icons-vue'
import * as request from '../services/menuService'

export default defineComponent({
    setup () {
      onMounted(() =>{
        getMenuAllData()
      });
      //菜单数据
      const menuData = ref<any[]>([]) ;
      /**
       * 获取菜单数据
       */
      const getMenuAllData = async () =>{
        const res = await request.getLeftMenu();
        menuData.value=res
      };
      
      //返回定义的方法和属性变量
      return {
        menuData,
        getMenuAllData
      }
    }
    
})
</script>

<style lang="scss" scoped>

    .el-aside{
      .el-menu {
          height: 100vh;
          width: 90px;
      }
      .logoClass {
          width: 90px;
          height: 90px;
      }

      .aside-icon-item {
          color: #ffffff !important; 
          font-size: 35px !important;
      }

      .el-menu-item {
          width: 90px;
          height: 90px;
      }

      .is-active {
          color: #000;
      }
    }

    .my-project-text {
      color: #313030;
    }

    .block-class {
      margin-top: 27px;
    }

</style>