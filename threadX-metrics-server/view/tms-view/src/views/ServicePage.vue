<template>
    <div class="pageBody">
        <div :span="4" class="treeColClass">
            <el-input style="margin-top: 0px;" v-model="treeFilterText" clearable placeholder="搜索实例" :prefix-icon="Search"/>
            <el-tree
                ref="treeRef"
                :data="serverTreeData"
                empty-text="没有实例数据"
                :props="defaultProps"
                :default-expand-all="false"
                accordion
                :filter-node-method="filterNode"
                :highlight-current="true"
                @node-click="handleNodeClick"
            />
        </div>
        <div :span="20" class="instanceDataBody">
            <div class="em-logo-class" v-if="emLogo">
                <img src="../assets/icon/douding.png" alt="">
                <el-text  type="info">请选择要查看的实例...</el-text>
            </div>
            <div v-else>表格数据:{{ instanceId }}</div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent,ref,watch,onMounted } from 'vue'
import { ElTree } from 'element-plus'
import { Search } from '@element-plus/icons-vue'
import ServerService from '../services/ServerService'
import router from '@/router'

export default defineComponent({
    setup () {

        onMounted(() =>{
            loadRouterParam();
            loadTreeData();
        });
        //是否显示空logo
        const emLogo = ref(true)
        // 定义树结构的数据体系
        const serverTreeData= ref([]);
        //点击的实例的id
        const instanceId = ref()
        // 定义标签和子标签的属性
        const defaultProps = ref({
            children: 'children',
            label: 'label',
        });
        // 定义筛选条件值
        const treeFilterText = ref()
        // 定义树引用
        const treeRef = ref<InstanceType<typeof ElTree>>()
        // 监听搜索框  将搜索值传入引用对象中
        watch(treeFilterText, (val) => {
            filterTreeData()
        })

        /**
         * 对树节点进行筛选时执行的方法， 返回 false 则表示这个节点会被隐藏
         * @param value 搜索的值
         * @param data 当前标签
         */
        const filterNode = (value: string, data:any) => {
            if (!value) return true
            //是否包含搜索值
            return data.label.includes(value)
        }


        /**
         * 当出发节点点击的时候回调的方法
         * @param data 点击的值
         */
        const handleNodeClick = (data: any) => {
            if (data.parentId != null) {
                instanceId.value = data.id
                loadInstanceData()
            }
            
        }


        /**
         * 加载实例的数据
         */
         const loadInstanceData = ()=>{
            const instanceIdValue = instanceId.value
            if (instanceIdValue == null) {
                emLogo.value = true
            }else {
                emLogo.value = false
            }
            
        }

        

        /**
         * 加载路由参数
         */
        const loadTreeData = () =>{
            ServerService.findServerAndInstanceData().then(response =>{
                serverTreeData.value = response
            }).finally(() =>{
                filterTreeData();
                loadInstanceData();
            })
            
        }

        /**
         * 根据输入数据过滤数据
         */
        const filterTreeData = ()=>{
            treeRef.value!.filter(treeFilterText.value)
        }

        const loadRouterParam = ()=>{
            treeFilterText.value = router.currentRoute.value.query.instanceName
            instanceId.value = router.currentRoute.value.query.instanceId
        }


        return {
            serverTreeData,
            defaultProps,
            treeFilterText,
            treeRef,
            instanceId,
            emLogo,
            Search,
            filterNode,
            handleNodeClick
        }
    }
})
</script>


<style scoped lang="scss">
    .pageBody {
        display: flex;
    }
    .treeColClass {
        height: 91vh;
        background-color: rgb(255, 255, 255);
        border: 1px solid rgb(225, 227, 225);
        border-radius: 5px;
        width: 15%;
        /* box-shadow:水平位置 垂直位置 模糊距离 阴影尺寸（影子大小） 阴影颜色  内/外阴影； */
        box-shadow: 0 10px 10px  rgba(0, 0, 0, .3);
        .el-input {
            margin-bottom: 10px;
        }
    }

    .instanceDataBody {
        height: 91vh;
        background-color: rgb(255, 255, 255);
        margin-left: 10px;
        width: 85%;
        border: 1px solid rgb(225, 227, 225);
        box-shadow: 0 10px 10px  rgba(0, 0, 0, .3);
        border-radius: 5px;
    }

    .em-logo-class {
        display: flex;
        flex-direction: column;
        height: 100%;
        justify-content: center;
        align-items: center;

        .el-text {
            margin-top: 10px;

        }
    }
</style>