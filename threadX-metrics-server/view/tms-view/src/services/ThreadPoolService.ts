import ApiUtils from './api';

/**
 * 线程池查询服务
 */
class ThreadPoolService {

    /**
     * 查询线程池的具体详情 
     * @param data 参数
     * @returns  线程池的具体详情
     */
    public static findThreadPoolDetail(data:any): Promise<any> {
        return ApiUtils.post('/threadPool/findThreadPoolDetail', data).then((response) =>{
            return response;
        }).catch(error =>{
            console.log(error)
            return {}
        })
    }


    /**
     * 根据查询条件分页查询线程池
     * @param data 参数
     * @returns  根据查询条件分页查询线程池
     */
    public static findPageByThreadPoolPageDataConditions(data:any): Promise<any> {
        return ApiUtils.post('/threadPool/findPageByThreadPoolPageDataConditions', data).then((response) =>{
            return response;
        }).catch(error =>{
            console.log(error)
            return []
        })
    }
}

export default ThreadPoolService