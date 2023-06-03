import axios from 'axios';
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';


class ApiUtils {
  private static axiosInstance = axios.create({
    baseURL: 'http://127.0.0.1:8081',
    // 其他Axios配置，如超时时间等
    timeout: 30000,
    headers: {
      'Source-Name': 'ThreadX-tms',
      'threadx-token': '5cdb3dadadec4b3d88776e6b02dc3c481'
    
    }
  });

  /**
   * 添加默认的拦截器
   */
  public static setupInterceptors(): void {
    //请求拦截器
    this.axiosInstance.interceptors.request.use(function (config) {
      // 显示进度条
      NProgress.start();
      return config;
    }, function (error) {
      // 对请求错误做些什么
      NProgress.done();
      return Promise.reject(error);
    });

    /**
     * 响应拦截器
     */
    this.axiosInstance.interceptors.response.use(function (response) {
      // 2xx 范围内的状态码都会触发该函数。
      //解构数据
      const { code, message, result } = response.data;
      if (code === '000000') {
        // 隐藏进度条
        NProgress.done();
        // 返回成功的data
        return result;
      } else {
        // 隐藏进度条
        NProgress.done();
        // 抛出错误提示
        return Promise.reject(new Error(message));
      }
 
      return response;
    }, function (error) {
      // 超出 2xx 范围的状态码都会触发该函数。
      // 对响应错误做点什么
      // 隐藏进度条
      NProgress.done();
      return Promise.reject(error);
    });
  }

  /**
   * post请求方法
   * @param url 请求的地址
   * @param data 发送的数据
   * @param config 可选的Axios请求配置
   * @returns 最终的返回结果
   */
  public static async post<T>(url: string, data?: any, config?: any): Promise<T> {
    return await this.axiosInstance.post(url, data, config);
  }

  /**
   * get请求方法
   * @param url 请求的地址
   * @param params 参数  json类型的就行
   * @param config 可选的Axios请求配置
   * @returns 最终的返回结果
   */
  public static async get<T>(url: string, params?: any, config?: any): Promise<T> {
    const response  = await this.axiosInstance.get(url, { params, ...config });
    return response.data;
  }
}

// 设置默认拦截器
ApiUtils.setupInterceptors();


export default ApiUtils;
