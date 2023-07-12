import ApiUtils from './api';

/**
 * 线程池查询服务
 */
class ThreadPoolService {

    /**
     * 查询线程池详情
     * @param data 参数
     * @returns  实例的具体详情
     */
    public static findThreadPoolDetail(data:any): Promise<any> {
        return ApiUtils.post('/threadPool/findThreadPoolDetail', data).then((response) =>{
            return response;
        }).catch(error =>{
            console.log(error)
            return {}
        })
    }
}

export default ThreadPoolService