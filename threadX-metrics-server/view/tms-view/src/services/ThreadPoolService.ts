import ApiUtils from './api';

/**
 * 线程池查询服务
 */
class ThreadPoolService {
    public static findThreadPoolDetail(data:any): Promise<any> {
        return ApiUtils.post('/threadPool/findThreadPoolDetail', data).then((response) =>{
            return response;
        }).catch(error =>{
            console.log(error)
        })
    }
}

export default ThreadPoolService