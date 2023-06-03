import ApiUtils from './api';

/**
 * 分页查询实例信息
 * @param instanceItemFindConditions 查询条件
 * @returns 
 */
export function  getByPage(instanceItemFindConditions:any): Promise<any> {
    return ApiUtils.post("/instanceItem/findByPage", instanceItemFindConditions)
    .then((response) =>{
        return response
    })
    .catch((error: any) => {
        // 处理错误情况
        console.error("查询实例信息请求失败：", error);
        return []
    });
}