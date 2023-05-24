<template>
    <div class="main-class">
        <el-card class="main-card">
            <template #header>
            <div class="card-header">
                <span class="card-title">最新服务</span>
                <i class="icon iconfont  icon-shuaxin mouse-shadow server-shuaxin"></i>
            </div>
            </template>

            <div class="card-main">
                <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
            </div>
            
        </el-card>

        <el-card class="main-card">
            <template #header>
            <div class="card-header">
                <span class="card-title">异常线程池Top10</span>
                <i class="icon iconfont  icon-shuaxin mouse-shadow server-shuaxin"></i>
            </div>
            </template>
            
            <div class="card-main">
                <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
            </div>
        </el-card>
    </div>
    <div class="main-class2">
        <el-card class="main-card">
            <template #header>
            <div class="card-header">
                <span class="card-title">欢迎使用ThreadX</span>
            </div>
            </template>
            <div class="card-main">
                <div class="welcome-main">
                    <div class="welcome-left">
                        <img src="../assets/threadx-xiezuo.png" alt="欢迎使用ThreadX">
                    </div>

                    <div class="welcome-right">
                        <div class="welcome-text">
                            <div>
                                Hi 开发者，我是ThreadX，是你开发中必不可少的伙伴！
                            </div>
                            <br>
                            <div>
                                ThreadX是一个业务无侵入式的线程池监控管理平台，
                                <br>
                                帮助团队更好的监控线程任务状态，更小的修改成本。
                            </div>
                        </div>

                        <div class="welcome-link">
                            <span class="welcome-title">操作指引</span>

                            <ul>
                                <li>
                                    <span class="welcome_li_title">更新日志：</span>
                                    <a class="welcome_li_a" href="">社区更新日志</a>
                                </li>

                                <li>
                                    <span class="welcome_li_title">官方文档：</span>
                                    <a class="welcome_li_a" href="">社区官方网文档</a>
                                </li>
                            </ul>
                        </div>
                        
                    </div>

                    

                </div>
            </div>
            
        </el-card>

        <el-card class="main-card">
            <template #header>
            <div class="card-header">
                <span class="card-title">CPU使用率Top10</span>
                <i class="icon iconfont  icon-shuaxin mouse-shadow server-shuaxin"></i>
            </div>
            </template>
            
            <div class="card-main">
                <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
            </div>
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

    .card-main {
        
    }

    .card-title {
        font-weight: bold;
    }

    .welcome-main {
        display: flex;
        box-sizing: border-box;
        height: 345px;
        

    }

    .welcome-left {
        width: 30%;
        display: flex;
        align-items: center;
        img {
            width: 257px;
            height: 187px;
        }
    }

    .welcome-right {
        width: 70%;
        margin-top: 6%;
        padding-left: 10%;
        .welcome-text {
            width: 80%;
            line-height: 30px;
            color: #182b50;
            font-size: 14px;
        }

    }

    .welcome-link {
        margin-top: 20px;
    }

    .welcome-title {
            
            font-weight: bold;
            margin-bottom: 20px;
        }

        ul {

            li {
                margin: 0;
                padding: 0;
                list-style: none;
            }
        }

        .welcome_li_title {
            padding-left: 7px;
            position: relative;
            font-size: 12px;
            color: #8c95a8;
            margin-bottom: 10px;
        }

        .welcome_li_a {
            font-size: 12px;
        }

</style>