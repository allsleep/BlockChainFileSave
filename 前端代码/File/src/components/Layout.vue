<template>
  <div>
    <div class="layui-layout layui-layout-admin">
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
  

  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">个人资料</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">个人资料详情</a></dd>
            <!-- <dd><a href="javascript:;">menu 2</a></dd>
            <dd><a href="javascript:;">menu 3</a></dd>
            <dd><a href="">the links</a></dd> -->
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">上传文件</a>
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
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
      内容主体区域
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
    </div>
  </div>
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    底部固定区域
  </div>
</div>
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>

<style>
    @import "../css/layui.css";
</style>
