<template>
  <div>
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
    <pageination ref="pagination" v-bind:list="list" v-bind:item-count="6"></pageination>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>名称</th>
        <th>课程ID</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="chapter in chapters">
        <td>{{chapter.id}}</td>
        <td>{{chapter.name}}</td>
        <td>{{chapter.courseId}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(chapter)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(chapter.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
          <div class="hidden-md hidden-lg">
            <div class="inline pos-rel">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul
                class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                <li>
                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																			<span class="blue">
																				<i class="ace-icon fa fa-search-plus bigger-120"></i>
																			</span>
                  </a>
                </li>

                <li>
                  <a v-on:click="edit(chapter)" class="tooltip-success" data-rel="tooltip" title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                  </a>
                </li>

                <li>
                  <a v-on:click="del(chapter.id)" class="tooltip-error" data-rel="tooltip" title="Delete">
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

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
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
                  <input v-model="chapter.name" class="form-control" placeholder="名称">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">课程ID</label>
                <div class="col-sm-10">
                  <input v-model="chapter.courseId" class="form-control" placeholder="课程ID">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div><!-- /.span -->

</template>

<script>
  import Pageination from "../../components/pageination";
  export default {
    name: "chapter",
    components: {Pageination},
    data:function(){
      return {
        chapter:{},// chapter 变量用于绑定form表单的数据
        chapters: []
      }
    },
    mounted:function () {
      // sidebar 激活样式方法一
      // this.$parent.activeSidebar("business-chapter-sidebar")
      let _this = this;
      _this.$refs.pagination.size = 5;
      _this.list(1);
    },
    methods:{
      add(){
        let _this = this;
        _this.chapter={};// 这个是保证在每一次新增的时候 不会带入上一次修改时候留下的数据  因为上次修改数据还保存在chapter中没有被清空
        // 此处不是一个方法
        $("#form-modal").modal("show")
      },
      edit(chapter){
        let _this = this;//1 点击要弹出模态框  2  模态打开以后要展示对应id的记录的数据
        // _this.chapter = chapter;// 此处修改其他的地方也跟着进行了改变
        _this.chapter = $.extend({},chapter);// 使用jquery的方法  复制一份给模态框展示需要 当用户取消的时候，就不会把值给数据库表格数据
        $("#form-modal").modal("show");

      },
      list(page){
        let _this = this;
        _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list',{
          page: page,
          size: _this.$refs.pagination.size
        }).then((response)=>{
          console.log("查询章列表结果",response);
          let resp = response.data;
          _this.chapters=resp.content.list;
          //  点击对应的按钮 要进行渲染  编程激活的状态
          _this.$refs.pagination.render(page,resp.content.total)
        });

      },
      save(page){
        let _this = this;
        _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/save',_this.chapter).then((response)=>{
          console.log("保存章列表结果",response);// chapter作为前后端交互传参
          let resp = response.data;
          if(resp.success){
            //如果数据成功  就把模态框给关闭掉
            $("#form-modal").modal("hide");
            toast.success("保存成功！");
            _this.list(1);
          }
        });

      },
      del(id){
        let _this = this;
        Swal.fire({
          title: '确认删除',
          text: "删除后不可恢复，确认删除!",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: '确认!'
        }).then((result) => {
          if (result.value) {
            _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/' + id).then((response) => {
              console.log("删除章列表结果", response);// chapter作为前后端交互传参
              _this.list(1);//删除成功以后 重新查询一遍即可
              toast.success("删除成功！");
            });

          }
        });


      },
    }
  }
</script>

<style scoped>

</style>