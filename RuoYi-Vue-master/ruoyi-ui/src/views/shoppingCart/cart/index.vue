<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图书编号" prop="bookId">
        <el-input
          v-model="queryParams.bookId"
          placeholder="请输入图书编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="num">
        <el-input
          v-model="queryParams.num"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cartList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="购物车id" align="center" prop="scId" />
      <el-table-column label="图书编号" align="center" prop="bookId" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="数量" align="center" prop="num" />
      <el-table-column label="书名" align="center" prop="bookName" />
      <el-table-column label="图片" align="center" prop="bookImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.bookImg" :width="100" :height="100"/>
        </template>
      </el-table-column>
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
            <div>
              <!-- 触发对话框显示的按钮 -->
              <el-button type="primary" @click="showDlg(scope.row.scId,scope.row.bookId,scope.row.userId,scope.row.bookName,scope.row.price,scope.row.bookImg,scope.row.num,scope.row.userName,scope.row.phone,scope.row.contactName,scope.row.address)">下单</el-button>

              <!-- 对话框组件 -->
              <el-dialog
                title="请输入收货人信息"
                :visible.sync="dialogVisible"
                width="50%"
                @close="dialogVisible = false">
                <!-- 对话框内容 -->
                <el-form>
                <el-form-item label="收货人姓名" prop="contactName">
                <el-input  v-model="scope.row.contactName" placeholder="请输入收货人姓名"></el-input>
                </el-form-item>
                <el-form-item label="联系电话" prop="phone">
                <el-input  v-model="scope.row.phone" placeholder="请输入联系电话"></el-input>
                </el-form-item>
                <el-form-item label="收货地址" prop="address">
                <el-input  v-model="scope.row.address" placeholder="请输入收货地址"></el-input>
                </el-form-item>
                <!-- 对话框底部操作按钮 -->
                </el-form>
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button style="background-color: cornflowerblue; margin-left: 1px; color: white; margin-top: 10px;" @click="handleAddOrder(scope.row)">确定</el-button>
              </el-dialog>
            </div>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改购物车信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="数量" prop="num">
          <el-input v-model="form.num" placeholder="请输入数量" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCart, getCart, delCart, addCart, updateCart } from "@/api/shoppingCart/cart";
import {addOrder} from "@/api/orderManagement/order";

export default {
  name: "Cart",
  data() {
    return {
      currentId : '',
      dialogVisible: false, // 控制对话框的显示与隐藏
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 购物车信息表格数据
      cartList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bookId: null,
        userId: null,
        num: null,
        bookName: null,
        bookImg: null,
        price: null,
        userName: null,
        phone: null
      },
      // 表单参数
      form: {
        phone: null,
        contactName:null,
        address:null,
        state : null
      },
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    showDlg(scId,bookId,userId,bookName,price,bookImg,num,userName){
      this.currentId = scId
      this.dialogVisible = true
      this.bookId = bookId
      this.userId = userId
      this.bookName = bookName
      this.price = price
      this.bookImg = bookImg
      this.num = num
      this.userName = userName

    },
    /** 查询购物车信息列表 */
    getList() {
      this.loading = true;
      listCart(this.queryParams).then(response => {
        this.cartList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        scId: null,
        bookId: null,
        userId: null,
        num: null,
        createTime: null,
        bookName: null,
        bookImg: null,
        price: null,
        userName: null
      };
      this.resetForm("form");
    },
    /** 购物车物品添加到订单按钮 */
    handleAddOrder(row) {
      this.form.scId = this.currentId
      this.form.bookId = this.bookId
      this.form.userId = this.userId
      this.form.bookName = this.bookName
      this.form.price = this.price
      this.form.bookImg = this.bookImg
      this.form.num = this.num
      this.form.userName = this.userName
      this.form.phone = row.phone
      this.form.contactName = row.contactName
      this.form.address = row.address
      this.form.state = "未支付"
      this.dialogVisible = false
      console.log(this.form);

      const scIds = this.currentId || this.ids;
      const books = this.form;
      this.$modal.confirm('是否添加订单id为"' + scIds + '"的数据项？').then(function() {
        return addOrder(books);
      }).then(() => {
        this.$modal.msgSuccess("添加成功");
      }).catch(() => {});
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.scId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 添加到订单按钮 */
    handleAddOrders() {
      this.reset();
      this.open = true;
      this.title = "添加购物车信息";
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加购物车信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scId = row.scId || this.ids
      getCart(scId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改购物车信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scId != null) {
            updateCart(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCart(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const scIds = row.scId || this.ids;
      this.$modal.confirm('是否确认删除购物车信息编号为"' + scIds + '"的数据项？').then(function() {
        return delCart(scIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('shoppingCart/cart/export', {
        ...this.queryParams
      }, `cart_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
