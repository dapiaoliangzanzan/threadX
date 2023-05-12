<template>
      <div class="common-layout">
    <el-container>
      <el-aside>
        <AsideComponents/>
      </el-aside>

      <el-main>主</el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import AsideComponents from '@/components/AsideComponents.vue';

export default defineComponent({
    setup () {
        

        return {}
    },
    components: {
        AsideComponents
    }
})
</script>

<style scoped>
    .logoClass {
        width: 70px;
        height: 70px;
        overflow: hidden;
        object-fit: cover;
    }
</style>