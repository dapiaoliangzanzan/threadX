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
        });
    }
}

export default RoleService