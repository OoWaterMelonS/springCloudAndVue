<template>
  <div>
    <button type="button" v-on:click="selectFile()" class="btn btn-white btn-default btn-round">
      <i class="ace-icon fa fa-upload"></i>
      {{ text }}
    </button>
    <input class="hidden" type="file" ref="file" v-on:change="uploadFile()" v-bind:id="inputId+'-input'">
  </div>
</template>

<script>
  export default {
    name: 'pagination',
    props: {
      text:{
        default: "上传文件"
      },
      inputId:{
        default: "file-upload"
      },
      suffixs:{
        default: []
      },
      afterUpload: {
        type: Function,
        default: null
      },
      itemCount: Number // 显示的页码数，比如总共有100页，只显示10页，其它用省略号表示  有多少个按钮
    },
    data: function () {
      return {
        total: 0, // 总行数
        size: 10, // 每页条数
        page: 0, // 当前页码
        pageTotal: 0, // 总页数
        pages: [], // 显示的页码数组
      }
    },
    methods: {

      uploadFile() {
        let _this = this;
        let formData = new window.FormData();
        // key："file"必须和后端controller参数名一致
        let file = _this.$refs.file.files[0];

        // 判断文件格式
        let suffixs = _this.suffixs;
        let fileName = file.name;
        let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
        let validateSuffix = false;
        for (let i = 0; i < suffixs.length; i++) {
          if (suffixs[i].toLowerCase() === suffix) {
            validateSuffix = true;
            break;
          }
        }
        if (!validateSuffix) {
          Toast.warning("文件格式不正确！只支持上传：" + suffixs.join(","));
          $("#"+_this.inputId+"-input").val("");
          return;
        }
        formData.append('file', file);
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/upload', formData).then((response) => {
          Loading.hide();
          let resp = response.data;

          console.log("上传文件成功",resp);
          _this.afterUpload(resp);
          $("#"+_this.inputId+"-input").val("");
        });
      },

      selectFile() {
        $("#file-upload-input").trigger("click");
      },
    }
  }
</script>
