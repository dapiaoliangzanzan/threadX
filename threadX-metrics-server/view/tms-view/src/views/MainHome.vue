<template>
      <div class="common-layout">
    <el-container>
      <el-aside>
        <AsideComponents/>
      </el-aside>

      <el-main>
        <div class="topDiv">
          <el-row>
          <el-col :span="24">
            <TopComponents/>
          </el-col>
        </el-row>


        </div>

        <div class="mainDiv">
          <el-row>
          <el-col :span="24">
            <MainComponents/>
          </el-col>
        </el-row>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import AsideComponents from '@/components/AsideComponents.vue';
import TopComponents from '@/components/TopComponents.vue';
import MainComponents from '@/components/MainComponents.vue';

export default defineComponent({
    setup () {
        

        return {}
    },
    components: {
        AsideComponents,
        TopComponents,
        MainComponents
    }
})
</script>

<style scoped>
  .el-aside{
      width: var(--el-aside-width,90px);
  }

  .topDiv {
    background-color: white;
  }

  .mainDiv {
    background-color: white;
    margin-top: 10px;
  }

  .el-main {
    --el-main-padding: 10px;
  }
</style>