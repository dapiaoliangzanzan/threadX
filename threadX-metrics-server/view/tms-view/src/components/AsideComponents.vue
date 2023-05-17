<template>
    <div>
      <el-drawer v-model="drawer" offset-x="90px" open-delay="40" close-delay="50" :modal="false" direction="ltr" title="项目" size="20%">
        <span class="my-project-text">我的项目</span>

        <div class="block-class">
          <el-input v-model="searchValue" class="w-50 m-2" placeholder="搜索项目">
            <template #prefix>
              <el-icon class="el-input__icon"><Search /></el-icon>
            </template>
          </el-input>
        </div>

        <div class="block-class">
          <el-scrollbar :height="calculatedHeight">
              123456
          </el-scrollbar>
        </div>
      </el-drawer>

      <el-menu
      default-active="1"
      active-text-color="#ff0000"
      background-color="#D8D0D0"
      :collapse="true"
    >
      <img src="../assets/logo.svg" alt="threadX" class="logoClass">
      <el-menu-item index="1">
        <i class="icon iconfont  icon-gongzuotai aside-icon-item"></i>
        <template #title>工作台</template>
      </el-menu-item>

      <el-menu-item index="2" @click="openLeft">
        <i class="icon iconfont icon-icon-project aside-icon-item"></i>
        <template #title>项目</template>
      </el-menu-item>

    </el-menu>
    </div>
    
  </template>

<script lang="ts">
import { defineComponent,ref, computed } from 'vue'
import '../assets/icon/iconfont.css'
import '../assets/css/el-drawer-index.css'
import { Search } from '@element-plus/icons-vue'

export default defineComponent({
    setup () {
      //当前屏幕的高度
      const windowHeight = ref(window.innerHeight);
      //内容的高度
      const calculatedHeight = computed(() => {
        return windowHeight.value - 230;
      });

      //定义抽屉组件是否弹出
      const drawer = ref(false)

      const searchValue = ref('')
      // 弹出抽屉组件
      const openLeft = ()=> {
        drawer.value = true
      }
      
      //返回定义的方法和属性变量
      return {
        drawer,
        searchValue,
        calculatedHeight,
        openLeft
      }
    },
    components:{
      Search
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