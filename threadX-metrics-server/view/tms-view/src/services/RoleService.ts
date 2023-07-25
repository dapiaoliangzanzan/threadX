import ApiUtils from './api';

class RoleService {
    /**
     * 分页查询角色信息
     * @param rolePageConditions 查询条件
     * @returns 角色信息
     */
    public static findAllByPage(rolePageConditions:any):Promise<any> {
        return ApiUtils.post('/role/findAllByPage', rolePageConditions).then((response) =>{
            return response;
        })
        .catch((error: any) => {
            // 处理错误情况
            console.error("查询全部角色信息请求失败：", error);
            return {}
        });
    }

    /**
     * 查询对应角色的权限信息
     * @param roleId 角色的id
     * @returns 对应角色的权限信息
     */
    public static findRoleAuthority(roleId:any):Promise<any> {
        return ApiUtils.get('/role/findRoleAuthority', {
            roleId
        }).then((response) =>{
            return response;
        })
        .catch((error: any) => {
            // 处理错误情况
            console.error("查询对应角色的权限信息失败！", error);
            return {}
        });
    }

    /**
     * 保存角色配置
     * @param roleVo 角色配置
     * @returns 
     */
    public static saveRole(roleVo:any):Promise<any> {
        return ApiUtils.post('/role/save', roleVo);
    }
    
}

export default RoleService