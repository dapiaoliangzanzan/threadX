<template>
    <div class="main-class">
        <el-card class="main-card">
            <template #header>
            <div class="card-header">
                <span class="card-title">我的服务</span>
                <i class="icon iconfont  icon-shuaxin mouse-shadow server-shuaxin"></i>
            </div>
            </template>
            <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
        </el-card>

        <el-card class="main-card">
            <template #header>
            <div class="card-header">
                <span class="card-title">我的动态</span>
                <i class="icon iconfont  icon-shuaxin mouse-shadow server-shuaxin"></i>
            </div>
            </template>
            <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
        </el-card>
    </div>
    <div class="main-class2">
        <el-card class="main-card">
            <template #header>
            <div class="card-header">
                <span class="card-title">欢迎使用ThreadX</span>
            </div>
            </template>
            <div class="welcome-main">
                <div class="welcome-left">
                    <img src="../assets/threadx-xiezuo.png" alt="欢迎使用ThreadX">
                </div>

                <div class="welcome-right">
                    <div>
                        Hi 皇甫科星，我是TAPD，是你开发中必不可少的伙伴！
                    </div>

                    <div>
                        TAPD是一站式敏捷研发协作平台，
                        <br>
                        帮助团队透明工作进展，提升协作效率
                    </div>
                </div>
            </div>
        </el-card>

        <el-card class="main-card">
            <template #header>
            <div class="card-header">
                <span class="card-title">我关注的</span>
                <i class="icon iconfont  icon-shuaxin mouse-shadow server-shuaxin"></i>
            </div>
            </template>
            <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
        </el-card>
    </div>
</template>

<script lang="ts">
import { defineComponent,ref } from 'vue'
import '../assets/css/index.css'

export default defineComponent({
    setup () {
        

        return {}
    }
})
</script>

<style lang="scss" scoped>
    .main-class {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        align-items: flex-start; /* 第一行的垂直对齐 */
       
    }

    .main-class2 {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        align-items: flex-end; /* 第二行的垂直对齐 */
        margin-top: 15px;
       
    }


    .main-card {
        width: calc((100% - 20px) / 2);
        height: calc((100vh - 105px) / 2);

        
    }

    .icon-shuaxin {
        cursor: pointer;
        font-size: 22px;
    }

    .card-header {
        display: flex;
        height: 20px;
        justify-content: space-between;
        align-items: center;
    }

    .card-title {
        font-weight: bold;
    }

    .welcome-main {
        display: flex;
        

    }

    .welcome-left {
        position: absolute;
        top: 65%;
        img {
            width: 30%;
        }
    }

    .welcome-right {
        justify-content: space-between;
    }

</style>