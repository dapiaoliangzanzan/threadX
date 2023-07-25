<template>
    <div>
        <div class="search-class">
            <el-input :prefix-icon="Search" @keyup.enter="loadRoleList" v-model="searchValue" placeholder="请输入要搜索的角色名称" clearable />
            <el-button type="primary" @click="loadRoleList">搜索角色</el-button>
            <el-button type="success" @click="createRoleMethod" :icon="Plus">新建角色</el-button>
        </div>

        <div class="role-data-class">
            <el-table :data="roleDataTable" style="width: 100%" height="79.5vh">
                <el-table-column prop="roleName" label="角色名称" align="center"/>
                <el-table-column prop="roleDesc" label="角色说明" align="center" />
                <el-table-column prop="createDate" label="创建时间" align="center"/>
                <el-table-column prop="updateDate" label="修改时间" align="center"/>
                <el-table-column fixed="right" label="操作" align="center">
                    <template #default="scope">
                        <el-button  link type="primary" size="small" @click="findUser(scope.row.id)">查看用户</el-button>  
                        <el-button  link type="primary" size="small" @click="updateRole(scope.row.id)">修改</el-button>
                        <el-button  link type="primary" size="small" @click="deleteRole(scope.row.id)">删除</el-button>                      
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

        <el-dialog v-model="createRoleDialogVisible" append-to-body title="角色设置" width="50%" draggable open-delay="200" close-delay="200" :close-on-click-modal="false">
            <div class="create-dialog-class">
                <el-form
                    ref="createRoleFormRef"
                    :model="createRoleFormModel"
                    label-position="right"
                    :rules="rules"
                    label-width="100px"
                    status-icon
                >
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="角色名称:" prop="roleName">
                                <el-input placeholder="角色名称" v-model="createRoleFormModel.roleName" style="width: 100%"/>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="角色介绍:" prop="roleDesc">
                                <el-input
                                    v-model="createRoleFormModel.roleDesc"
                                    :autosize="{ minRows: 2, maxRows: 10 }"
                                    type="textarea"
                                    placeholder="角色简介"
                                />
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-divider>角色菜单</el-divider>
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="用户菜单权限:" prop="selectMenuList" >
                                <el-checkbox-group v-model="createRoleFormModel.selectMenuList">
                                    <el-checkbox v-for="menu in menus" :key="menu.id" :label="menu.id">
                                        <el-tooltip
                                            effect="dark"
                                            :content="menu.menuDesc"
                                            placement="top"
                                        >
                                            {{ menu.name }}
                                        </el-tooltip>
                                    </el-checkbox>
                                </el-checkbox-group>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-divider>角色权限</el-divider>
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="用户菜单权限:" prop="selectPermissionList" >
                                <el-checkbox-group v-model="createRoleFormModel.selectPermissionList">
                                    <el-checkbox v-for="permission in permissions" :key="permission.id" :label="permission.id">
                                        <el-tooltip
                                            effect="dark"
                                            :content="permission.permissionDesc"
                                            placement="top"
                                        >
                                            {{ permission.name }}
                                        </el-tooltip>
                                        
                                     </el-checkbox>
                                </el-checkbox-group>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>

                
            </div>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="createRoleDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="createRoleDialogVisible = false">确认</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup lang="ts">
    import { ref , onMounted} from 'vue'
    import { Search,Plus } from '@element-plus/icons-vue'
    import RoleService from '@/services/RoleService'
    import MenuService from '@/services/MenuService'
    import PermissionService from '@/services/PermissionService'
    import { ElMessage, ElMessageBox } from 'element-plus'
    import type { FormInstance, FormRules } from 'element-plus'

    interface Menu {
        id: string,
        name:string,
        menuDesc:string
    }

    interface Permission {
        id: string,
        name:string,
        permissionDesc:string
    }


    interface CreateRoleForm {
        roleName: string
        roleDesc: string
        selectMenuList: string[],
        selectPermissionList:string[]
    }

    //对应表单的值
    const createRoleFormModel = ref<CreateRoleForm>({
        roleName:'',
        roleDesc:'',
        selectMenuList: [],
        selectPermissionList: []
    })

    //对应表单的引用
    const createRoleFormRef = ref<FormInstance>()

    onMounted(() =>{
        loadRoleList();
        loadAllAuthority();
    });

    //所有的菜单
    const menus = ref<Menu[]>([])
    //所有的权限信息
    const permissions = ref<Permission[]>([])
    //显示新建修改弹出框
    const createRoleDialogVisible = ref(false)
    //搜索值
    const searchValue = ref()
    //角色表格数据
    const roleDataTable = ref([])
    //当前页
    const currentPage = ref(1)
    //每一页显示的数据
    const pageSize = ref(18)
    //数据总条数
    const dataTotal = ref(0)
    //翻页
    const currentPageChange = () =>{
        loadRoleList();
    }

    const rules = ref<FormRules>(
        {
            roleName: [
                { 
                    required: true, 
                    message: '角色名不允许为空', 
                    trigger: 'blur' 
                }
            ],
            roleDesc: [
                { 
                    required: true, 
                    message: '角色介绍不允许为空', 
                    trigger: 'blur' 
                }
            ]
        }
    )

    const findUser = (id:any) => {
        console.log("asd")
    }

    const updateRole = (id:any) => {
        console.log("asd")
    }

    const deleteRole = (id:any) => {
        console.log("asd")
    }

    const loadRoleList = () =>{
        RoleService.findAllByPage({
            roleName:searchValue.value,
            pageSize: pageSize.value,
            currentPage:currentPage.value
        }).then(response =>{
            dataTotal.value = response.total;
            roleDataTable.value = response.data;
        });
    }

    /**
     * 创建一个新的角色
     */
    const createRoleMethod = () => {
        createRoleFormModel.value.selectMenuList = []
        createRoleFormModel.value.selectPermissionList = []
        createRoleDialogVisible.value = true;
    }

    /**
     * 获取所有的权限  菜单权限和权限
     */
    const loadAllAuthority = ()=>{
        MenuService.findAllMenu().then(response =>{
            menus.value = response
        })

        PermissionService.findAllPermission().then(response =>{
            permissions.value = response
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

    .role-data-class {
        margin-top: 10px;
    }

    .table-page-class {
        display: flex;
        justify-content: flex-end;
        background-color: rgb(255, 255, 255);
    }

</style>