import axios from 'axios'
import {Message} from 'element-ui';
import router from '../router'
import {mymessage} from '@/utils/mymessage';
const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API, // api 的 base_url
    timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
    config => {
        if (!config.headers['X-MOWORDS-Token']) {//设置全局守卫，判断是否有token，如没有则返回登录
            config.headers['X-MOWORDS-Token'] = `${window.localStorage.getItem(
                'Authorization'
            ) || ''}`;
        }
        return config;
    },
    err => Promise.reject(err)
)
service.interceptors.response.use(success => {
    if (success.status && success.status == 200 && success.data.status == 500) {
        Message.error({message: success.data.msg})
        return;
    }
    if (success.data.msg) {
        Message.success({message: success.data.msg})
    }
    return success.data;
}, error => {
    if (error.response.status == 504 || error.response.status == 404) {
        Message.error({message: '服务器被吃了( ╯□╰ )'})
    } else if (error.response.status == 403) {
        Message.error({message: '权限不足，请联系管理员'})
    } else if (error.response.status == 401) {
        mymessage.error({message: error.response.data.msg ? error.response.data.msg : '尚未登录，请登录'})
        router.replace('/');
    } else {
        if (error.response.data.msg) {
            Message.error({message: error.response.data.msg})
        } else {
            Message.error({message: '未知错误!'})
        }
    }
    return;
})

let base = '';

export const postKeyValueRequest = (url, params) => {
    return service({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        transformRequest: [function (data) {
            let ret = '';
            for (let i in data) {
                ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
            }
            return ret;
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    });
}
export const postRequest = (url, params) => {
    return service({
        method: 'post',
        url: `${base}${url}`,
        data: params
    })
}
export const putRequest = (url, params) => {
    return service({
        method: 'put',
        url: `${base}${url}`,
        data: params
    })
}
export const getRequest = (url, params) => {
    return service({
        method: 'get',
        url: `${base}${url}`,
        params: params
    })
}
export const deleteRequest = (url, params) => {
    return service({
        method: 'delete',
        url: `${base}${url}`,
        params: params
    })
}
