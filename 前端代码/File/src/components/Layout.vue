<template>
  <div>
    <div class="layui-layout layui-layout-admin">
      <LayoutHeader />
      <LayoutLeft />
      <LayoutBody />
      <LayoutFooter />
    </div>
  </div>
</template>

<script>
import LayoutFooter from './layout/footer.vue'
import LayoutHeader from './layout/header.vue'
import LayoutLeft from './layout/left.vue'
import LayoutBody from './layout/body.vue'

export default {
  name: 'Layout',
  components: {
    LayoutFooter,
    LayoutHeader,
    LayoutLeft,
    LayoutBody
  }
}
</script>

<style>
@import "../css/layui.css";
</style>
