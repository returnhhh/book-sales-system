import request from '@/utils/request'

// 查询购物车信息列表
export function listCart(query) {
  return request({
    url: '/shoppingCart/cart/list',
    method: 'get',
    params: query
  })
}

// 查询购物车信息详细
export function getCart(scId) {
  return request({
    url: '/shoppingCart/cart/' + scId,
    method: 'get'
  })
}

// 新增购物车信息
export function addCart(data) {
  return request({
    url: '/shoppingCart/cart',
    method: 'post',
    data: data
  })
}

// 修改购物车信息
export function updateCart(data) {
  return request({
    url: '/shoppingCart/cart',
    method: 'put',
    data: data
  })
}

// 删除购物车信息
export function delCart(scId) {
  return request({
    url: '/shoppingCart/cart/' + scId,
    method: 'delete'
  })
}
