<template>
  <div>
    <pagination ref="pagination" v-bind:list="list" v-bind:item-count="6"></pagination>
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
                    <th class="detail-col">课程</th>
                    <th class="detail-col">操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="chapter in chapters">
                    <td class="detail-col">{{ chapter.id }}</td>
                    <td class="detail-col">{{ chapter.name }}</td>
                    <td class="detail-col">{{ chapter.courseId}}</td>
                    <td>
                      <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-success">
                          <i class="ace-icon fa fa-check bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-info">
                          <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger">
                          <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-warning">
                          <i class="ace-icon fa fa-flag bigger-120"></i>
                        </button>
                      </div>

                      <div class="hidden-md hidden-lg">
                        <div class="inline pos-rel">
                          <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                          </button>

                          <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                            <li>
                              <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                    <span class="blue">
                                      <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                    </span>
                              </a>
                            </li>

                            <li>
                              <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                    <span class="green">
                                      <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                    </span>
                              </a>
                            </li>

                            <li>
                              <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                    <span class="red">
                                      <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </span>
                              </a>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div><!-- /.span -->
            </div><!-- /.row -->

            <div class="hr hr-18 dotted hr-double"></div>

            <div class="modal fade" tabindex="-1" role="dialog">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">表单</h4>
                  </div>
                  <div class="modal-body">
                    <form class="form-horizontal">
                      <div class="form-group">
                        <label  class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                          <input v-model="chapter.name" class="form-control"  placeholder="名称">
                        </div>
                      </div>
                      <div class="form-group">
                        <label  class="col-sm-2 control-label">课程id</label>
                        <div class="col-sm-10">
                          <input v-model="chapter.courseId" class="form-control"  placeholder="课程id">
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button"  class="btn btn-default" data-dismiss="modal">取消</button>
                    <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
                  </div>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            <p>
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
  components:{Pagination},
  name: "chapter",
  mounted() {
    let _this = this;
    _this.$refs.pagination.size=5;
    _this.list();
    // 方法1
    // let _this = this
    // _this.$parent.activeSidebar("business-chapter-sidebar");
  },
  data: function () {
    return {
      chapter: {},  //映射表单数据
      chapters: []// 初始化
    }
  },
  methods: {
    add(){
      let _this = this
      $(".modal").modal("show");
      // $(".modal").modal("hide");
      // $(".modal").modal({background:'static'});
    },
    list(page) {
      let _this = this;
      _this.$ajax.post("http://127.0.0.1:9000/business/admin/chapter/list",{
        page:page,
        size:_this.$refs.pagination.size// ref 获取子组件其中的一个变量,设定好一页要多少条数
      }).then((response) => {
        console.log("查询大章列表", response);
        _this.chapters = response.data.content.list;
        _this.$refs.pagination.render(page,response.data.total)
      })
    },
    save() {
      let _this = this;
      _this.$ajax.post("http://127.0.0.1:9000/business/admin/chapter/save1",_this.chapter).then((response) => {
        console.log("保存大章", response);
      })
    },

  }

}
</script>

<style scoped>

</style>