import ApiUtils from './api';

/**
 * 分页查询实例信息
 * @param instanceItemFindConditions 查询条件
 * @returns 
 */
export function findThreadTaskDataErrorCalculationTop10(): Promise<any> {
    return ApiUtils.get("/threadTaskData/findThreadTaskDataErrorCalculationTop10")
    .then((response) =>{
        return response
    })
    .catch((error: any) => {
        // 处理错误情况
        console.error("查询线程池任务错误数据top10请求失败：", error);
        return []
    });
}