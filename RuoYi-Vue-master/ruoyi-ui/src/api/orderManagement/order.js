import request from '@/utils/request'

// 查询订单管理列表
export function listOrder(query) {
  return request({
    url: '/orderManagement/order/list',
    method: 'get',
    params: query
  })
}

// 本周中每天已支付订单的数量统计
export function listOrderAnalysis(query) {
  return request({
    url: '/orderManagement/order/listAnalysis',
    method: 'get',
    params: query
  })
}

// 本周中销量最高的前五本书
export function listOrderAnalysis1(query) {
  return request({
    url: '/orderManagement/order/listAnalysis1',
    method: 'get',
    params: query
  })
}

// 查询订单管理详细
export function getOrder(orderId) {
  return request({
    url: '/orderManagement/order/' + orderId,
    method: 'get'
  })
}

export function updateState(data) {
  return request({
    url:'/orderManagement/order/updateState',
    method:'post',
    data:data
  })
}

//更新库存
export function updateNum(orderId){
  return request({
    url:'/orderManagement/order/updateNum/'+orderId,
    method:'put'
  })
}


// 新增订单管理
export function addOrder(data) {
  return request({
    url: '/orderManagement/order',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateOrder(data) {
  return request({
    url: '/orderManagement/order',
    method: 'put',
    data: data
  })
}

// 删除订单管理
export function delOrder(orderId) {
  return request({
    url: '/orderManagement/order/' + orderId,
    method: 'delete'
  })
}
