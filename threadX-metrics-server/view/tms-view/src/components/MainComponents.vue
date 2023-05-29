<template>
    <div class="main-class">
        <el-card class="main-card">
            <template #header>
            <div class="card-header">
                <span class="card-title">实例信息</span>
                <i class="icon iconfont  icon-shuaxin mouse-shadow server-shuaxin"></i>
            </div>
            </template>

            <div class="card-main">
                <el-table :data="instanceList" stripe style="width: 100%" :max-height="cardMainyHeight" row-key="id">
                    <el-table-column fixed prop="instanceName" label="实例名称"/>
                    <el-table-column prop="serverName" label="所属服务"/>
                    <el-table-column prop="createDate" label="创建时间" />
                    <el-table-column prop="state" label="存活状态">
                        <template #default="scope">
                            <el-popover effect="light" trigger="hover" placement="top" width="auto">
                            <template #default>
                                <div  v-if="instanceStateCheck(scope.row.state)">【{{ scope.row.instanceName }}】 正在被持续监控！</div>
                                <div style="color: red;" v-else>
                                    【{{ scope.row.instanceName }}】 实例不可用，请检查该实例！
                                </div>
                            </template>
                            <template #reference>
                                <el-tag :type="instanceStateTagType(scope.row.state)">{{ instanceState(scope.row.state) }}</el-tag>
                            </template>
                            </el-popover>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="page_div">
                    <el-pagination
                        small
                        background
                        layout="prev, pager, next"
                        :total="instanceTotalCount"
                        :page-size="instancePageSize"
                        :pager-count="5"
                        :hide-on-single-page="false"
                    />
                </div>
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
import { defineComponent,ref,computed } from 'vue'
import '../assets/css/index.css'

export default defineComponent({
    setup () {
              //当前屏幕的高度
        const windowHeight = ref(window.innerHeight);
        //内容的高度
        const cardMainyHeight = computed(() => {
            return (windowHeight.value - 381)/2;
        });

        //返回当前实例的状态  监控中的返回true  断联的返回false
        const instanceStateCheck = computed(()=>(state:string) => {
            return state === "0"
        });
        // 判断当前 实例是否是运行中
        const instanceState = computed(()=>(state:string) => {
            if(instanceStateCheck.value(state)) {
                return "活跃"
            }
            return "断联";
        });
        // 返回当前的实例状态的标签的类型  如果正在运行返回  success 否则返回  danger
        const instanceStateTagType = computed(() =>(state:string)=> {
            if(instanceStateCheck.value(state)) {
                return "success"
            }
            return "danger";
        })
        // 实例数据
        const instanceList = ref([
            {
                "id":"1",
                "instanceName":"测试用例1",
                "serverName":"测试服务",
                "createDate":"2023-05-29 16:00:32",
                "state":"0"
            },
            {
                "id":"2",
                "instanceName":"测试用例2",
                "serverName":"测试服务",
                "createDate":"2023-05-29 16:00:32",
                "state":"1"
            },
            {
                "id":"3",
                "instanceName":"测试用例3",
                "serverName":"测试服务",
                "createDate":"2023-05-29 16:00:32",
                "state":"0"
            },
            {
                "id":"4",
                "instanceName":"测试用例4",
                "serverName":"测试服务",
                "createDate":"2023-05-29 16:00:32",
                "state":"0"
            },
            {
                "id":"5",
                "instanceName":"测试用例5",
                "serverName":"测试服务",
                "createDate":"2023-05-29 16:00:32",
                "state":"0"
            },
            {

                "id":"6",
                "instanceName":"测试用例6",
                "serverName":"测试服务",
                "createDate":"2023-05-29 16:00:32",
                "state":"0"
            }
        ])
        // 当前页面
        const instanceThisPageNum = ref(1);
        // 每一页显示的条数
        const instancePageSize = ref(6);
        // 总条数
        const instanceTotalCount = ref(62);

        return {
            instanceList,
            cardMainyHeight,
            instanceState,
            instanceStateCheck,
            instanceStateTagType,
            instanceThisPageNum,
            instancePageSize,
            instanceTotalCount
        }
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
        overflow-x: auto;
        overflow-y: auto;
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
        .page_div {
            display: flex;
            justify-content: end;
            margin-top: 20px;
        }
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