<template>
  <div>
    <h3>welcome</h3>
  </div>
</template>

<script>
  export default {
    name: "welcome",
    mounted:function () {
      this.$parent.activeSidebar("welcome-sidebar");
    },
    methods:{

    }
  }
</script>

<style scoped>

</style>