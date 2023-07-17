<template>
    <div>
        <div class="search-class">
            <el-input v-model="searchValue" placeholder="请输入要搜索的用户名或昵称" clearable />
            <el-button type="primary" @click="searchMethod">搜索用户</el-button>
        </div>
        <div class="user-data-class">
            <el-table :data="userDataTable" style="width: 100%" height="760">
                <el-table-column prop="nickName" label="用户昵称" width="180" />
                <el-table-column prop="userName" label="用户名" width="180" />
                <el-table-column prop="email" label="用户邮箱" />
                <el-table-column prop="createTime" label="创建时间" />
                <el-table-column prop="updateTime" label="修改时间" />
                <el-table-column prop="state" label="是否可用" />
            </el-table>
        </div>
        <div class="table-page-class">
            <el-pagination layout="prev, pager, next" :total="dataTotal" />
        </div>
    </div>
</template>

<script setup lang="ts">
    import {ref} from 'vue'
    
    //搜索值
    const searchValue = ref()
    //用户表格数据
    const userDataTable = ref([])
    //每一页显示的数据
    const pageSize = ref(20)
    //数据总条数
    const dataTotal = ref(0)
    //搜索方法
    const searchMethod = () =>{
        console.log(searchValue.value)
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