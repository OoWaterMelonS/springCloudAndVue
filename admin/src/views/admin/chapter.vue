<template>
  <div>
    <!--    <p>-->
    <!--      <button v-on:click="add()" class="btn btn-white btn-default btn-round">-->
    <!--        <i class="ace-icon fa fa-edit"></i>-->
    <!--        新增-->
    <!--      </button>-->
    <!--      &nbsp;-->
    <!--      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">-->
    <!--        <i class="ace-icon fa fa-refresh"></i>-->
    <!--        刷新-->
    <!--      </button>-->
    <!--    </p>-->
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
                    <td>{{ chapter.Id }}</td>
                    <td>{{ chapter.name }}</td>
                    <td>{{ chapter.courseId}}</td>
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
<!--            <pageination ref="pagination" v-bind:list="list" v-bind:item-count="6"></pageination>-->
            <div class="hr hr-18 dotted hr-double"></div>

            <div id="modal-table" class="modal fade" tabindex="-1">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header no-padding">
                    <div class="table-header">
                      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                      </button>
                      Results for "Latest Registered Domains
                    </div>
                  </div>

                  <div class="modal-body no-padding">
                    <table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
                      <thead>
                      <tr>
                        <th>Domain</th>
                        <th>Price</th>
                        <th>Clicks</th>

                        <th>
                          <i class="ace-icon fa fa-clock-o bigger-110"></i>
                          Update
                        </th>
                      </tr>
                      </thead>

                      <tbody>
                      <tr>
                        <td>
                          <a href="#">ace.com</a>
                        </td>
                        <td>$45</td>
                        <td>3,330</td>
                        <td>Feb 12</td>
                      </tr>

                      <tr>
                        <td>
                          <a href="#">base.com</a>
                        </td>
                        <td>$35</td>
                        <td>2,595</td>
                        <td>Feb 18</td>
                      </tr>

                      <tr>
                        <td>
                          <a href="#">max.com</a>
                        </td>
                        <td>$60</td>
                        <td>4,400</td>
                        <td>Mar 11</td>
                      </tr>

                      <tr>
                        <td>
                          <a href="#">best.com</a>
                        </td>
                        <td>$75</td>
                        <td>6,500</td>
                        <td>Apr 03</td>
                      </tr>

                      <tr>
                        <td>
                          <a href="#">pro.com</a>
                        </td>
                        <td>$55</td>
                        <td>4,250</td>
                        <td>Jan 21</td>
                      </tr>
                      </tbody>
                    </table>
                  </div>

                  <div class="modal-footer no-margin-top">
                    <button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
                      <i class="ace-icon fa fa-times"></i>
                      Close
                    </button>

                    <ul class="pagination pull-right no-margin">
                      <li class="prev disabled">
                        <a href="#">
                          <i class="ace-icon fa fa-angle-double-left"></i>
                        </a>
                      </li>

                      <li class="active">
                        <a href="#">1</a>
                      </li>

                      <li>
                        <a href="#">2</a>
                      </li>

                      <li>
                        <a href="#">3</a>
                      </li>

                      <li class="next">
                        <a href="#">
                          <i class="ace-icon fa fa-angle-double-right"></i>
                        </a>
                      </li>
                    </ul>
                  </div>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
            </div>

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
export default {
  name: "chapter",
  mounted() {
    let _this = this;
    _this.list();
    // 方法1
    // let _this = this
    // _this.$parent.activeSidebar("business-chapter-sidebar");
  },
  data: function () {
    return {
      chapter: {},
      chapters: []// 初始化
    }
  },
  methods: {
    list() {
      let _this = this;
      _this.$ajax.get("http://127.0.0.1:9000/business/admin/chapter/list1").then((response) => {
        console.log("查询大章列表", response.data.content);
        _this.chapters = response.data.content;
      })
    }
  }

}
</script>

<style scoped>

</style>