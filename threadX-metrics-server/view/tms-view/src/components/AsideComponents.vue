<template>
    <el-menu
      default-active="1"
      active-text-color="#ff0000"
      background-color="#D8D0D0"
      :collapse="true"
    >
      <img src="../assets/logo.png" alt="threadX" class="logoClass">
      <el-menu-item index="1">
        
        
        <i class="icon iconfont  icon-gongzuotai1 aside-icon-item"></i>
        <template #title>工作台</template>
      </el-menu-item>

      <el-menu-item index="2">
        <i class="icon iconfont icon-icon-project aside-icon-item"></i>
        <template #title>项目</template>
      </el-menu-item>

    </el-menu>
  </template>

<script lang="ts">
import { defineComponent } from 'vue'
import '../assets/icon/iconfont.css'

export default defineComponent({
    setup () {
        return {
            
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

</style>