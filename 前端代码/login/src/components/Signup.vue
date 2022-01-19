<template>
<div>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="screen" rel="stylesheet" type="text/css">
    <div id="signin-box" v-show="sigin_show">
      <h1>用户注册</h1>
      <div class="form">
            <div class="item">
              <i class="fa fa-font" aria-hidden="true"></i>
              <input type="text" placeholder="请输入用户名" v-model="username">
            </div>
            <div class="item">
              <i class="fa fa-key" aria-hidden="true"></i>
              <input type="text" placeholder="请输入密码" v-model="password">
            </div>
            <div class="item">
              <i class="fa fa-user-circle-o" aria-hidden="true"></i>
              <input type="text" placeholder="请输入姓名" v-model="author">
            </div>
            <div class="item">
              <i class="fa fa-id-card-o" aria-hidden="true"></i>
              <input type="text" placeholder="请输入身份证号码" v-model="idCard">
            </div>
            <div class="item">
              <i class="fa fa-mobile" aria-hidden="true"></i>
              <input type="text" placeholder="请输入手机号码" v-model="phoneNumber">
            </div>
      </div>
    </div>
</div>
</template>

<script>
export default {

}
</script>
#login-box {
    width: 30%;
    height: auto;
    margin: 0 auto;
    margin-top: 15%;
    text-align: center;
    background: #00000011;
    padding: 20px 50px;
}

#login-box h1{
    color: black;
}
#login-box .form .item input{
    margin-top: 15px;
}
#login-box .form i{
    font-size: 18px;
    color: rgb(14, 13, 13);
}
#login-box .form .item input {
    width: 180px;
    font-size: 18px;
    border: 0;
    border-bottom: 2px solid#fff;
    padding: 5px 10px;
    background:#ffffff00;
    color: rgb(22, 21, 21);
}

#signin-box .form .item input {
    width: 180px;
    font-size: 18px;
    border: 0;
    border-bottom: 2px solid#fff;
    padding: 5px 10px;
    background:#ffffff00;
    color: rgb(22, 21, 21);
    margin-top: 10px;
    margin-bottom: 10px;
}

.click_button {
    /* margin-top: 15px; */
    margin:15px 10px 0px 10px;
    width: 180px;
    height: 30px;
    font-size: 20px;
    font-weight: 700;
    color: #fff;
    background:linear-gradient(to right,#beb7bb 0%,#131101e1 100%);
    border: 0;
    border-radius: 15px;
}
.click_button:hover {
    background:linear-gradient(to right,#f3097e 0%,#e6d813e1 100%);
}
<style>

</style>