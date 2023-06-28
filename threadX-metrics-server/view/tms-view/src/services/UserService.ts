import ApiUtils from './api';
import LocalStorageUtil from '@/common/LocalStorageUtil';
import router from '@/router';

class UserService{

    /**
     * 用户登录
     * @param userName 用户名
     * @param password  用户密码
     */
    public static login(userName:string, password:string):void {
        ApiUtils.post("/user/login", {
            "userName":userName,
            "password":password
        }).then((response) =>{
            LocalStorageUtil.loginTokenSave(response as string)
            router.push('/worktable')
        })
        
    }
}

export default UserService;