import ApiUtils from './api';


/**
 * 查询当前用户下拥有的左侧菜单栏
 * @returns 
 */
export function getLeftMenu(): Promise<any> {
    return ApiUtils.post("/menu/findThisUserMenu")
    .then((response) =>{
        return response
    })
    .catch((error: any) => {
        // 处理错误情况
        console.error("查询左侧菜单栏信息请求失败：", error);
    });
}