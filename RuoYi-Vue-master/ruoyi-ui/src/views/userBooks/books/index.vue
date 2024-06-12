<template>
  <div class="app-container">
<!--    查询-->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图书编号" prop="bookId">
        <el-input
          v-model="queryParams.bookId"
          placeholder="请输入图书编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="书籍名称" prop="bookName">
        <el-input
          v-model="queryParams.bookName"
          placeholder="请输入书籍名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图书类型" prop="bookType">
        <el-input
          v-model="queryParams.bookType"
          placeholder="请输入图书类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出版社" prop="press">
        <el-input
          v-model="queryParams.press"
          placeholder="请输入出版社"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出版时间" prop="bookPressTime">
        <el-date-picker clearable
          v-model="queryParams.bookPressTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出版时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

<!--    增删改导按钮-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['userBooks:books:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<!--    显示书籍信息-->
    <el-table v-loading="loading" :data="booksList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="排序" align="center" prop="bookNumber" />
      <el-table-column label="图书编号" align="center" prop="bookId" />
      <el-table-column label="书籍名称" align="center" prop="bookName" />
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="出版社" align="center" prop="press" />
      <el-table-column label="图书类型" align="center" prop="bookType" />
      <el-table-column label="图片" align="center" prop="bookImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.bookImg" :width="100" :height="100"/>
        </template>
      </el-table-column>
      <el-table-column label="价格" align="center" prop="bookPrice" />
      <el-table-column label="书籍简介" align="center" prop="bookInventory" />
      <el-table-column label="出版时间" align="center" prop="bookPressTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bookPressTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="库存" align="center" prop="bookNum" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-input-number style="width: 120px; margin-left: 1px; margin-bottom: 10px;" v-model="form.num" :min="1" :max="100" label="描述文字"></el-input-number>
          <br />
          <el-button style="background-color: orangered; margin-left: 1px; color: white; margin-top: 10px;" @click="handleAddCart(scope.row)">加入购物车</el-button>
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
  </div>
</template>

<script>
import {getBooks, listBooks} from "@/api/userBooks/books";
import {addCart} from "@/api/shoppingCart/cart";

export default {
  name: "Books",
  data() {
    return {
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
      //传参id
      bookId:this.$route.query.bookId,
      // 图书商城信息表格数据
      booksList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bookId: null,
        bookNumber: null,
        createDate: null,
        updateDate: null,
        bookName: null,
        author: null,
        press: null,
        bookType: null,
        bookImg: null,
        bookPrice: null,
        bookInventory: null,
        bookPressTime: null,
        bookNum: null,
      },
      // 表单参数
      form: { num: 1 },
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询图书商城信息列表 */
    getList() {
      this.loading = true;
      listBooks(this.queryParams).then(response => {
        this.booksList = response.rows;
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
        num: null,
        bookNumber:null,
        bookId: null,
        createBy: null,
        createDate: null,
        updateBy: null,
        updateDate: null,
        bookName: null,
        author: null,
        press: null,
        bookType: null,
        bookImg: null,
        bookPrice: null,
        bookInventory: null,
        bookPressTime: null,
        bookNum: null,
        createTime: null
      };
      this.resetForm("form");
    },
    /** 添加到购物车按钮 */
    handleAddCart(row) {
      const bookIds = row.bookId || this.ids;
      this.$modal.confirm('是否添加图书编号为"' + bookIds + '"的数据项？').then(function() {
        getBooks(bookIds)
        return addCart(bookIds);
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
      this.ids = selection.map(item => item.bookId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('books/books/export', {
        ...this.queryParams
      }, `books_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
