import request from '@/utils/request'

// 查询图书商城信息列表
export function listBooks(query) {
  return request({
    url: '/books/books/list',
    method: 'get',
    params: query
  })
}

// 查询图书商城信息详细
export function getBooks(bookId) {
  return request({
    url: '/books/books/' + bookId,
    method: 'get'
  })
}

// 新增图书商城信息
export function addBooks(data) {
  return request({
    url: '/books/books',
    method: 'post',
    data: data
  })
}

// 修改图书商城信息
export function updateBooks(data) {
  return request({
    url: '/books/books',
    method: 'put',
    data: data
  })
}

// 删除图书商城信息
export function delBooks(bookId) {
  return request({
    url: '/books/books/' + bookId,
    method: 'delete'
  })
}
