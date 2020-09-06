<template>
  <div>
    <div>
        <router-link to="/business/course" >
          <h3><i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue">&nbsp;{{course.name}}</i></h3>
        </router-link>
    </div>
    <div class="hr hr-18 dotted hr-double"></div>
    <div class="main-container ace-save-state" id="main-container">
      <div class="main-content">
        <div class="main-content-inner">
          <div class="page-content">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
              <div class="col-xs-12">
                <table id="simple-table" class="table  table-bordered table-hover">
                  <thead>
                  <tr>
                    <th class="detail-col">ID</th>
                    <th class="detail-col">名称</th>
                    <th class="detail-col">操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="chapter in chapters">
                    <td class="detail-col">{{ chapter.id }}</td>
                    <td class="detail-col">{{ chapter.name }}</td>
                    <td>
                      <div class="hidden-sm hidden-xs btn-group">
                        <button v-on:click="toSection(chapter)" class="btn btn-xs btn-round btn-primary">
                          小节
                        </button>&nbsp;
                        <button v-on:click="edit(chapter)" class="btn btn-xs btn-round btn-info">
                          编辑
                        </button>&nbsp;
                        <button v-on:click="del(chapter.id)" class="btn btn-xs btn-round btn-danger">
                          删除
                        </button>
                      </div>
                    </td>
                  </tr>

                  </tbody>
                </table>

              </div><!-- /.span -->
            </div><!-- /.row -->
            <div class="hr hr-18 dotted hr-double"></div>
            <pagination ref="pagination" v-bind:list="list" v-bind:item-count="6"></pagination>

            <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">表单</h4>
                  </div>
                  <div class="modal-body">
                    <form class="form-horizontal">
                      <div class="form-group">
                        <label class="col-sm-2 control-label">大章名称</label>
                        <div class="col-sm-10">
                          <input v-model="chapter.name" class="form-control" placeholder="大章名称">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="col-sm-2 control-label">课程名称</label>
                        <div class="col-sm-10">
<!--                          <input v-model="chapter.courseId" class="form-control" placeholder="课程id">-->
                        <p class="form-control-static">{{course.name}}</p>
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
                  </div>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->


            <p class="pull-right">
<!--              不带参数的返回，使用router-link更方便，使用button的情况可以做更多的操作，例如传递参数-->
              <router-link to="/business/course" class="btn btn-white btn-default btn-round">
                <i class="fa fa-arrow-left">返回课程</i>
              </router-link>
              <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                新增
              </button>
              &nbsp;
              <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
              </button>
            </p>
            <!-- PAGE CONTENT ENDS -->
          </div><!-- /.page-content -->

        </div>
      </div><!-- /.main-content -->


      <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
      </a>
    </div><!-- /.main-container -->
  </div>
</template>

<script>
import Pagination from "../../components/pagination"


export default {
  components: {Pagination},
  name: "chapter",
  data: function () {
    return {
      chapter: {},  //映射表单数据
      chapters: [],// 初始化
      course:{},
    }
  },
  mounted() {
    let _this = this;
    let course = SessionStorage.get("course") || {};
    if(Tool.isEmpty(course)){
      _this.$router.push("/welcome");
    }
    _this.course = course;
    _this.$refs.pagination.size = 5;
    _this.list(1);
    // 方法1
    // let _this = this
    // _this.$parent.activeSidebar("business-chapter-sidebar");
  },
  methods: {
    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.chapter = {};
      $("#form-modal").modal("show");
      // $(".modal").modal("hide");
      // $(".modal").modal({background:'static'});
    },

    /**
     * 点击【编辑】
     */
    edit(chapter) {
      let _this = this
      _this.chapter = $.extend({}, chapter);// jquery的函数，复制一份，不直接修改chapter本体
      $("#form-modal").modal("show");
      // $(".modal").modal("hide");
      // $(".modal").modal({background:'static'});
    },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER+"/business/admin/chapter/list", {
        page: page,
        size: _this.$refs.pagination.size,// ref 获取子组件其中的一个变量,设定好一页要多少条数
        courseId:_this.course.id,
      }).then((response) => {
        Loading.hide();
        console.log(_this.course.id);
        let resp = response.data;
        _this.chapters = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total)
      })
    },

    /**
     * 点击【保存】
     */
    save() {
      let _this = this;
      _this.chapter.courseId = _this.course.id;
      //保存校验
      if(!Validator.require(_this.chapter.name,"名称")
          || !Validator.length(_this.chapter.courseId,"课程Id",1,8)){
        return ;
      }

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER+"/business/admin/chapter/save", _this.chapter).then((response) => {
        Loading.hide();
        let resp = response.data;

        if (resp.success) {
          $("#form-modal").modal("hide");
          _this.list(1);
          Toast.success("保存成功");
        }
        else {
          Toast.warning(resp.message);
        }
      })
    },

    /**
     * 点击【删除】
     */
    del(id) {
      let _this = this;
      Confirm.show("确认删除大章？",function (){
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER+"/business/admin/chapter/delete/" + id).then((response) => {
              Loading.hide();
              let resp = response.data;
              if (resp.success) {
                _this.list(1);
                Toast.success("删除成功");
              }
            }
        )
      })
    },

    /**
     * 点击【跳转课程】
     */
    toSection(chapter){
      let _this = this;
      _this.$router.push("/business/section");
      SessionStorage.set("chapter",chapter);
    }
  }

}
</script>

<style scoped>

</style>