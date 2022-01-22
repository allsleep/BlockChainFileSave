<template>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <router-link to="/userinfo">个人资料</router-link>
          <!-- <dl class="layui-nav-child">
            <dd><a href="javascript:;">个人资料详情</a></dd>
            <dd><a href="javascript:;">menu 2</a></dd>
            <dd><a href="javascript:;">menu 3</a></dd>
            <dd><a href="">the links</a></dd>
          </dl> -->
        </li>

        <li class="layui-nav-item">
          <router-link to="/fileup">上传文件</router-link>
          <!-- <dl class="layui-nav-child">
            <dd><a href="javascript:;">list 1</a></dd>
            <dd><a href="javascript:;">list 2</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl> -->
        </li>
        <li class="layui-nav-item"><a href="javascript:;">上链数据</a></li>
        <!-- <li class="layui-nav-item"><a href="">the links</a></li> -->
      </ul>
    </div>
  </div>
</template>

<script>
export default {
    name: "LayoutLeft"
}
</script>

<style>

</style>