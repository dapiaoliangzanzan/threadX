<template>
    <div class="topClass"> 
        top
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    setup () {
        

        return {}
    }
})
</script>

<style scoped>
    .topClass {
        height: 50px;
        border-radius: 50px !important;
        display: flex;
        justify-content: center;
        justify-items: center;

        
    }
</style>