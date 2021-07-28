import axios from "axios";

axios.defaults.baseURL = '/';
/***
 * /water/search/item  成功码 200，失败码 7777， 错误码 9999
 *查询项目信息  上送报文类型form-data
 *key   item_id
 */
export const SEARCH_ITEM_INFO = params => axios.post('/water/search/item', params)

// export const SEARCH_ITEM_INFO = (params) => {
//     return axios.get('../static/mock/waterItem.json', params)
// }
/***
 * /water/search/application
 *查询审批信息  上送报文类型form-data
 *key   item_id
 */
export const SEARCH_ITEM_APPLICATION = params => axios.post('/water/search/application', params)

/***
 * /water/search/item
 *新增申请  上送报文类型form-data
 *key   item_id
 */
export const ADD_APPLICATION = params => axios.post('/water/add/application', params)

/***
 * /water/search/item
 *撤回申请  上送报文类型form-data
 *key   item_id
 */
export const RECALL_APPLICATION = params => axios.post('/water/recall/application', params)


/***
 * /water/search/account
 *查询可用账户信息  上送报文类型form-data
 *key   item_id
 */
export const SEARCH_ACCOUNT = params => axios.post('/water/search/account', params)

/***
 * /water/search/account
 *查询可用账户信息  上送报文类型form-data
 *key   item_id
 */
export const GET_APPLICATION_ITEM = params => axios.get('/water/application/items', params)

/***
 * 
 *审批  上送报文类型form-data
 *key   item_id
 */
export const WATER_APPROVE = params => axios.post('/water/approve', params)

///water/application/detail

/***
 * 
 *审批  上送报文类型form-data
 *key   item_id
 */
export const WATER_DETAILS = params => axios.post('/water/application/detail', params)

