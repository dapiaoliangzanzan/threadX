<template>
    <div>
        <div class="search-class">
            <el-input :prefix-icon="Search" v-model="searchValue" placeholder="请输入要搜索的用户名或昵称" clearable />
            <el-button type="primary" @click="searchMethod">搜索用户</el-button>

            <el-button type="success" @click="searchMethod" class="create-user-button-class" :icon="Plus">新建用户</el-button>
        </div>
        <div class="user-data-class">
            <el-table :data="userDataTable" style="width: 100%" height="760">
                <el-table-column prop="nickName" label="用户昵称" align="center"/>
                <el-table-column prop="userName" label="用户名" align="center" />
                <el-table-column prop="email" label="用户邮箱" align="center"/>
                <el-table-column prop="createTime" label="创建时间" align="center"/>
                <el-table-column prop="updateTime" label="修改时间" align="center"/>
                <el-table-column prop="state" label="是否可用" width="100" :formatter="stateFormatter" align="center"/>
                <el-table-column fixed="right" label="操作" align="center">
                    <template #default="scope">
                        <el-button v-if="scope.row.state == '1'" link type="primary" size="small">冻结用户</el-button>
                        <el-button v-else link type="primary" size="small">解除冻结</el-button>
                        <el-button link type="primary" size="small">删除用户</el-button>
                        <el-tooltip
                            effect="dark"
                            content="修改用户操作权限、用户菜单权限、用户信息"
                            placement="top"
                        >
                            <el-button link type="primary" size="small">修改用户</el-button>
                        </el-tooltip>
                        
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div class="table-page-class">
            <el-pagination layout="prev, pager, next" 
                :page-size="pageSize" 
                :pager-count="5" 
                :total="dataTotal" 
                v-model:current-page="currentPage" 
                @current-change="currentPageChange"
            />
        </div>
    </div>
</template>

<script setup lang="ts">
    import {ref, onMounted} from 'vue'
    import type { TableColumnCtx } from 'element-plus'
    import { Search,Plus } from '@element-plus/icons-vue'
    import UserManagerService from '@/services/UserManagerService'


    onMounted(() =>{
        loadAllUserData()
    });
    //搜索值
    const searchValue = ref()
    //用户表格数据
    const userDataTable = ref([])
    //当前也
    const currentPage = ref(1)
    //每一页显示的数据
    const pageSize = ref(18)
    //数据总条数
    const dataTotal = ref(0)
    //搜索方法
    const searchMethod = () =>{
        loadAllUserData();
    }
    //翻页
    const currentPageChange = () =>{
        loadAllUserData();
    }

    const stateFormatter = (row:any, column: TableColumnCtx<any>)=> {
        if (row.state === '1') {
            return "有效"
        } else {
            return "冻结"
        }
            
    }

    /**
     * 加载所有的用户数据
     */
    const loadAllUserData = ()=>{
        UserManagerService.findAllUser({
            userName: searchValue.value,
            nickName: searchValue.value,
            pageSize: pageSize.value,
            pageNumber: currentPage.value
        }).then(response =>{
            userDataTable.value = response.data
            dataTotal.value = response.total
        })
        
    }

</script>

<style scoped lang="scss">
    .search-class {
        background-color: rgb(255, 255, 255);
        height: 60px;
        border-radius: 5px;
        display: flex;
        align-items: center;
        justify-content: flex-end;
        padding-right: 10px;

        .el-input {
            margin-right: 5px;
            width: 15%;
        }
    }

    .user-data-class {
        margin-top: 10px;
    }

    .table-page-class {
        display: flex;
        justify-content: flex-end;
        background-color: rgb(255, 255, 255);
    }
</style>