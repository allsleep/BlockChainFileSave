<template>
    <div id="signin-box">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="screen" rel="stylesheet" type="text/css">
        <div class="form">
            <div class="item">
              <i class="fa fa-font" aria-hidden="true"></i>
              <input type="text" placeholder="请输入用户名" v-model="username">
            </div>
            <!-- <div class="item">
              <i class="fa fa-key" aria-hidden="true"></i>
              <input type="text" placeholder="请输入密码" v-model="password">
            </div> -->
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
</template>

<script>
export default {
    name: 'UserInfo',
    data: function (){
      return {
        "accountId": "123",
        "author": "linhan",
        "idCard": "330382199908131739",
        "password": "wu82293382",
        "phoneNumber": "13185887658",
        "username": "linhan"
      }
    },
}
</script>

<style>
#signin-box {
    width: 30%;
    height: auto;
    margin: 0 auto;
    margin-top: 15%;
    text-align: center;
    /* background: #00000011; */
    padding: 20px 50px;
}

/* .item {
    border: solid;
} */


#signin-box .form .item input{
    margin-top: 15px;
}
#signin-box .form i{
    font-size: 18px;
    color: rgb(14, 13, 13);
}
#signin-box .form .item input {
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
</style>