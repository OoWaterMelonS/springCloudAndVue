<template>
  <div>
    <h3>welcome 欢迎界面</h3>
  </div>
</template>

<script>
export default {
  name: "welcome",
  mounted() {
    let _this = this
    _this.$parent.activeSidebar("welcome-sidebar");
  },
  methods:function (){

  }
}
</script>

<style scoped>

</style>