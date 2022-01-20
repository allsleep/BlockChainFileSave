<template>
    <!-- <div class="layui-layout layui-layout-admin"> -->
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">文件上链系统</div>
        <!-- 头部区域（可配合layui 已有的水平导航） -->
        <ul class="layui-nav layui-layout-left" v-show="false">
        <!-- 移动端显示 -->
        <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
            <i class="layui-icon layui-icon-spread-left"></i>
        </li>
        <li class="layui-nav-item layui-hide-xs"><a href="">nav 1</a></li>
        <li class="layui-nav-item layui-hide-xs"><a href="">nav 2</a></li>
        <li class="layui-nav-item layui-hide-xs"><a href="">nav 3</a></li>
        <li class="layui-nav-item">
            <a href="javascript:;">测试1</a>
            <dl class="layui-nav-child">
            <dd><a href="">个人信息</a></dd>
            <dd><a href="">文件上传</a></dd>
            <dd><a href="">上链数据</a></dd>
            </dl>
        </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item layui-hide layui-show-md-inline-block">
            <a href="javascript:;">
            <!-- <img src="../../other/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"  class="layui-nav-img"> -->
            个人信息
            </a>
            <dl class="layui-nav-child">
            <dd><a href="">Your Profile</a></dd>
            <dd><a href="">Settings</a></dd>
            <dd><a href="">Sign out</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
            <a href="javascript:;">
            <i class="layui-icon layui-icon-more-vertical"></i>
            </a>
        </li>
        </ul>
  </div>
    <!-- </div> -->
</template>

<script>
export default {
    name: "LayoutHeader"
}
</script>

<style>

</style>