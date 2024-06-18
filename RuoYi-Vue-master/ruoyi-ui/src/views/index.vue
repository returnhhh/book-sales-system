<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <blockquote class="text-warning text-center" style="font-size: 30px">
          <h1 class="text-danger">
            欢迎来到图书售卖系统
          </h1>
        </blockquote>
        <hr />
      </el-col>
    </el-row>
    <div style="display: flex; justify-content: space-between;">
      <div id="main" style="width: 900px; height: 600px;"></div>
      <div id="main1" style="width: 900px; height: 600px;"></div>
    </div>
  </div>
</template>

<script>
import {listOrder, listOrderAnalysis, listOrderAnalysis1} from "@/api/orderManagement/order";

export default {
  name: "Index",
  data() {
    return {
      bookName:[],
      number:[],
      time:[],
      // 版本号
      version: "3.8.7"
    };
  },
  mounted() {
    listOrderAnalysis1().then(response=>{
      console.log(response);
      let result=response.data
      this.bookName=new Array(result.length)
      this.number=new Array(result.length)
      for(let i=0;i<result.length;i++){
        let books=result[i]
        this.bookName[i]=books.bookName
        this.number[i]=books.number
      }
      this.getBooks()
    })

    listOrderAnalysis().then(response=>{
      console.log(response);
      let result=response.data
      this.bookName=new Array(result.length)
      this.number=new Array(result.length)
      for(let i=0;i<result.length;i++){
        let books=result[i]
        this.time[i]=books.time
        this.number[i]=books.number
      }
      this.getBookss()
    })

    this.getBookss()
  },
  methods: {
    getBookList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    goTarget(href) {
      window.open(href, "_blank");
    },
    getBooks(){
      //获取echarts对象
      let myChart = this.$echarts.init(document.getElementById("main"));
      let option = {
        title: {
          text: '近一个月销量'
        },
        tooltip: {},
        xAxis: {
          data: this.bookName
        },
        yAxis: {},
        series: [
          {
            name: 'sales',
            type: 'bar',
            data: this.number
          }
        ]
      };
      option && myChart.setOption(option,true);
    },
    getBookss(){
      let myChart = this.$echarts.init(document.getElementById("main1"));
      let option = {
        xAxis: {
          type: 'category',
          data: this.time
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.number,
            type: 'line'
          }
        ]
      };
      option && myChart.setOption(option,true);
    }
  }
};

</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

