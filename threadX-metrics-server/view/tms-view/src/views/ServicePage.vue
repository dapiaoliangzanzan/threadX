<template>
    <div class="pageBody">
        <div :span="4" class="treeColClass">
            <el-input v-model="treeFilterText" placeholder="搜索实例" :prefix-icon="Search"/>
            <el-tree
                ref="treeRef"
                :data="serverTreeData"
                :props="defaultProps"
                :default-expand-all="false"
                accordion
                :filter-node-method="filterNode"
                @node-click="handleNodeClick"
            />
        </div>
        <div :span="20" class="instanceDataBody">
            实例数据
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent,ref,watch,onMounted } from 'vue'
import { ElTree } from 'element-plus'
import { Search } from '@element-plus/icons-vue'
import ServerService from '../services/ServerService'

export default defineComponent({
    setup () {

        onMounted(() =>{
            loadTreeData()
        });

        // 定义树结构的数据体系
        const serverTreeData= ref([]);
        // 定义标签和子标签的属性
        const defaultProps = ref({
            children: 'children',
            label: 'label',
        });
        // 定义筛选条件值
        const treeFilterText = ref('')
        // 定义树引用
        const treeRef = ref<InstanceType<typeof ElTree>>()
        // 监听搜索框  将搜索值传入引用对象中
        watch(treeFilterText, (val) => {
            treeRef.value!.filter(val)
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
            console.log(data)
        }

        const loadTreeData = async() =>{
            serverTreeData.value = await ServerService.findServerAndInstanceData()
        }

        return {
            serverTreeData,
            defaultProps,
            treeFilterText,
            treeRef,
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
</style>