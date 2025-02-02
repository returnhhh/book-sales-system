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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
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

<!--    显示书籍信息-->
    <el-table v-loading="loading" :data="booksList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="排序" align="center" prop="bookNumber" />
      <el-table-column label="图书编号" align="center" prop="bookId" />
      <el-table-column label="创建时间" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, `{y}-{m}-{d} {h}:{i}:{s}`) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateDate, `{y}-{m}-{d} {h}:{i}:{s}`) }}</span>
        </template>
      </el-table-column>
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

    <!-- 添加或修改图书商城信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="书籍名称" prop="bookName">
          <el-input v-model="form.bookName" placeholder="请输入书籍名称" />
        </el-form-item>
        <el-form-item label="图书类型" prop="bookType">
          <el-input v-model="form.bookType" placeholder="请输入书籍名称" />
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="出版社" prop="press">
          <el-input v-model="form.press" placeholder="请输入出版社" />
        </el-form-item>
        <el-form-item label="图片" prop="bookImg">
          <image-upload v-model="form.bookImg" limit="1"></image-upload>
        </el-form-item>
        <el-form-item label="价格" prop="bookPrice">
          <el-input v-model="form.bookPrice" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="书籍简介" prop="bookInventory">
          <el-input v-model="form.bookInventory" placeholder="请输入书籍简介" />
        </el-form-item>
        <el-form-item label="出版时间" prop="bookPressTime">
          <el-date-picker clearable
            v-model="form.bookPressTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出版时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="库存" prop="bookNum">
          <el-input v-model="form.bookNum" placeholder="请输入库存" />
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
import { listBooks, getBooks, delBooks, addBooks, updateBooks } from "@/api/books/books";

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
      form: {},
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加图书商城信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bookId = row.bookId || this.ids
      getBooks(bookId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改图书商城信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bookId != null) {
            updateBooks(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBooks(this.form).then(response => {
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
      const bookIds = row.bookId || this.ids;
      this.$modal.confirm('是否确认删除图书商城信息编号为"' + bookIds + '"的数据项？').then(function() {
        return delBooks(bookIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
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
