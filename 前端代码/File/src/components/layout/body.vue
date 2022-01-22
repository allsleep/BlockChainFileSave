<template>
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <!-- <div style="padding: 15px;">
      <br><br>
      <blockquote class="layui-elem-quote layui-text">
        <ul>
          <li>
              hello world
          </li>
        </ul>
      </blockquote>
      <br><br><br>
      <div class="layui-card layui-panel">
        <div class="layui-card-header">
          下面是充数内容，为的是出现滚动条
        </div>
        <div class="layui-card-body">
            显示内容
        </div>
      </div>
      <br><br>
    </div> -->
    <router-view></router-view>
  </div>
</template>

<script>
import FileUpload from '../body/FileUpload'
export default {
    name: "LayoutBody",
    components: {
        FileUpload
    }
}
</script>

<style>

</style>